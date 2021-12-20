package es.felix.oka.cells.domain.mappers;

import es.felix.oka.cells.domain.dto.CellUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import es.felix.oka.cells.domain.Cell;
import org.mapstruct.ReportingPolicy;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CellMapper {

    void copyNotNullPropeties(CellUpdate from, @MappingTarget Cell to);
}
