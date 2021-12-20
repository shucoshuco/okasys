package es.felix.oka.cells.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CellUpdate {

    @Getter
    private final Integer level;

    @Getter
    private final String title;

    @Getter
    private final String description;
}
