package es.felix.oka.cells.domain.usecases;

import es.felix.oka.cells.domain.Cell;

import java.util.List;

public interface BuildGameUseCase {

    List<Cell> buildGame(int cells);
}
