package es.felix.oka.cells.domain.repository;

import es.felix.oka.cells.domain.Cell;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CellRepository {

    Mono<Cell> findOne(String id);

    Mono<Cell> save(Cell cell);

    void delete(String id);

    Flux<Cell> getAllCells();
}
