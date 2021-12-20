package es.felix.oka.cells.domain.usecases;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.repository.CellRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildGameUseCaseImplTest {

    @InjectMocks
    private BuildGameUseCaseImpl target;

    @Mock
    private CellRepository repository;

    @Test
    public void dontFailIfNoCellsAvailable() {
        when(repository.getAllCells()).thenReturn(Flux.empty());
        assertTrue(target.buildGame(100).isEmpty());
    }

    @Test
    public void limitIsGreaterThanNumberOfCells() {
        when(repository.getAllCells()).thenReturn(Flux.just(
                new Cell("1", 1, "Cell 1", "Desc 1"),
                new Cell("2", 2, "Cell 2", "Desc 2")));

        List<Cell> cells = target.buildGame(10);
        assertEquals(2, cells.size());
        cells.sort(Comparator.comparing(Cell::getUid));
        assertEquals("1", cells.get(0).getUid());
        assertEquals("2", cells.get(1).getUid());
    }

    @Test
    public void divideDependingOnNumberOfLevels() {
        List<Cell> dbCells = new LinkedList<>();
        for (int i = 0; i < 3; i++) {
            dbCells.add(new Cell("1-" + i, 1, "Cell 1-" + i, "Desc 1-" + i));
            dbCells.add(new Cell("3-" + i, 3, "Cell 3-" + i, "Desc 3-" + i));
        }
        for (int i = 0; i < 5; i++) {
            dbCells.add(new Cell("2-" + i, 2, "Cell 2-" + i, "Desc 2-" + i));
            dbCells.add(new Cell("4-" + i, 4, "Cell 4-" + i, "Desc 4-" + i));
        }
        for (int i = 0; i < 6; i++) {
            dbCells.add(new Cell("5-" + i, 5, "Cell 5-" + i, "Desc 5-" + i));
        }
        for (int i = 0; i < 10; i++) {
            dbCells.add(new Cell("6-" + i, 6, "Cell 6-" + i, "Desc 6-" + i));
        }
        when(repository.getAllCells()).thenReturn(Flux.fromIterable(dbCells));

        List<Cell> cells = target.buildGame(30);
        assertEquals(30, cells.size());
        assertEquals(3, cells.stream().filter(c -> c.getLevel() == 1).count());
        assertEquals(3, cells.stream().filter(c -> c.getLevel() == 3).count());

        assertEquals(5, cells.stream().filter(c -> c.getLevel() == 2).count());
        assertEquals(5, cells.stream().filter(c -> c.getLevel() == 4).count());

        assertEquals(6, cells.stream().filter(c -> c.getLevel() == 5).count());
        assertEquals(8, cells.stream().filter(c -> c.getLevel() == 6).count());

        assertEquals(30, cells.stream().map(Cell::getUid).collect(Collectors.toSet()).size());

        for (int i = 1; i < cells.size(); i++) {
            assertTrue(
                    cells.get(i).getLevel() >= cells.get(i - 1).getLevel(),
                    "Cell level of position " + i + " should be bigger than level of position " + (i - 1));
        }
    }
}