package es.felix.oka.cells.application.mapper;

import es.felix.oka.cells.domain.Cell;
import es.felix.oka.cells.domain.dto.CellUpdate;
import es.felix.oka.schemas.model.CellApiObj;
import es.felix.oka.schemas.model.CellNoValidatedApiObj;
import org.mapstruct.Mapper;

@Mapper
public interface CellApiMapper {

    Cell apiToDomain(CellApiObj source);

    CellApiObj domainToApi(Cell source);

    Cell apiToDomain(CellNoValidatedApiObj source);

    CellUpdate apiToDomainUpdate(CellNoValidatedApiObj source);
}
