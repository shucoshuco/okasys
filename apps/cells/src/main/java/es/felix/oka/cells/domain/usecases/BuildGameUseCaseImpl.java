package es.felix.oka.cells.domain.usecases;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.repository.CellRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BuildGameUseCaseImpl implements BuildGameUseCase {

    private final CellRepository repository;

    @Override
    public List<Cell> buildGame(int nCells) {
        List<Cell> cells = repository.getAllCells().collectList().block();
        if (cells == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<Cell>> levelsMap =
                cells.stream().collect(Collectors.groupingBy(Cell::getLevel));

        levelsMap.values().forEach(Collections::shuffle);

        List<Cell> result = new LinkedList<>();
        boolean allEmpty = false;
        while (!allEmpty && result.size() < nCells) {
            allEmpty = true;
            for (int level : levelsMap.keySet()) {
                if (!levelsMap.get(level).isEmpty()) {
                    result.add(levelsMap.get(level).remove(0));
                    allEmpty = false;
                }
            }
        }

        return result.stream().sorted(Comparator.comparing(Cell::getLevel)).collect(Collectors.toList());
    }
}
