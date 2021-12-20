package es.felix.oka.cells.domain.usecases;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.dto.CellUpdate;
import es.felix.oka.cells.domain.mappers.CellMapper;
import es.felix.oka.cells.domain.repository.CellRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
public class CrudUseCaseImpl implements CrudUseCase {

    private final CellRepository repository;

    private final CellMapper mapper;

    @Override
    public Mono<Cell> getCell(String id) {
        return repository.findOne(id);
    }

    @Override
    public Mono<Cell> addCell(@Valid Cell cell) {
        return repository.save(cell);
    }

    @Override
    public Mono<Cell> updateCell(String id, CellUpdate update) {
        Cell dbCell = repository.findOne(id).block();
        mapper.copyNotNullPropeties(update, dbCell);
        return repository.save(dbCell);
    }

    @Override
    public void deleteCel(String id) {
        repository.delete(id);
    }
}
