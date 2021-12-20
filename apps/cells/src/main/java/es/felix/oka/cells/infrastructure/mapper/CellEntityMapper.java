package es.felix.oka.cells.infrastructure.mapper;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.infrastructure.entity.CellEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CellEntityMapper {

    CellEntity mapToEntity(Cell cell);

    Cell mapToDomain(CellEntity cell);
}
