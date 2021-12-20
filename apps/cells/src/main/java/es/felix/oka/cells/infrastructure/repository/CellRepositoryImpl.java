package es.felix.oka.cells.infrastructure.repository;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.repository.CellRepository;
import es.felix.oka.cells.infrastructure.mapper.CellEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CellRepositoryImpl implements CellRepository {

    private final CellMongoRepository springRepo;

    private final CellEntityMapper mapper;

    @Override
    public Mono<Cell> findOne(String id) {
        return springRepo.findById(id).map(mapper::mapToDomain);
    }

    @Override
    public Mono<Cell> save(Cell cell) {
        return springRepo.save(mapper.mapToEntity(cell)).map(mapper::mapToDomain);
    }

    @Override
    public void delete(String id) {
        springRepo.deleteById(id);
    }

    @Override
    public Flux<Cell> getAllCells() {
        return springRepo.findAll().map(mapper::mapToDomain);
    }
}
