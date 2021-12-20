package es.felix.oka.cells.domain.usecases;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.dto.CellUpdate;
import es.felix.oka.cells.domain.mappers.CellMapperImpl;
import es.felix.oka.cells.domain.repository.CellRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CrudUseCaseImplTest {

    private CrudUseCaseImpl target;

    private CellRepository repository;

    @BeforeEach
    void initialize() {
        repository = mock(CellRepository.class);
        target = new CrudUseCaseImpl(repository, new CellMapperImpl());
    }

    @Test
    void testGetInvalidCell() {
        final String id = "1";
        when(repository.findOne(id)).thenReturn(Mono.empty());
        assertNull(target.getCell(id).block());
    }

    @Test
    void testGetCellById() {
        final String id = "1";
        Cell cell = new Cell(id, 1, "Title", "Desc");
        when(repository.findOne(id)).thenReturn(Mono.just(cell));
        assertEquals(cell, target.getCell(id).block());
    }

    @Test
    void testUpdateCell() {
        final String id = "1";
        CellUpdate newCell = new CellUpdate(5, null, "New Desc");
        Cell oldCell = new Cell(id, 1, "Title", "Old Desc");
        when(repository.findOne(id)).thenReturn(Mono.just(oldCell));
        when(repository.save(oldCell)).thenReturn(Mono.just(oldCell));
        Cell updated = target.updateCell(id, newCell).block();
        assertEquals(oldCell.getTitle(), updated.getTitle());
        assertEquals(newCell.getLevel(), updated.getLevel());
        assertEquals(newCell.getDescription(), updated.getDescription());

        verify(repository).save(oldCell);
    }
}