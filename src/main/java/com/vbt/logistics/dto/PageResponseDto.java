package com.vbt.logistics.dto;

import org.springframework.data.domain.Page;


import java.util.List;
import java.util.stream.Collectors;

public record PageResponseDto<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        String sort // ör: "seq,ASC;id,DESC"
) {
    public static <T> PageResponseDto<T> from(Page<T> page) {
        String sortStr = page.getSort().stream()
                .map(o -> o.getProperty() + "," + o.getDirection().name())
                .collect(Collectors.joining(";"));
        return new PageResponseDto<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                sortStr
        );
    }
}
