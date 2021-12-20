package es.felix.oka.cells.domain.usecases;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.dto.CellUpdate;
import reactor.core.publisher.Mono;

public interface CrudUseCase {

    Mono<Cell> getCell(String id);

    Mono<Cell> addCell(Cell cell);

    Mono<Cell> updateCell(String id, CellUpdate update);

    void deleteCel(String id);
}
