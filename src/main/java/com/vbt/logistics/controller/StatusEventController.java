package com.vbt.logistics.controller;

import com.vbt.logistics.bean.statusEvent.ListStatusEventsBean;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.StatusEventDto;
import com.vbt.logistics.enums.EntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/status-events")
public class StatusEventController {

    private final ListStatusEventsBean listBean;

    @GetMapping
    public PageResponseDto<StatusEventDto> list(
            @RequestParam EntityType entityType,
            @RequestParam Long entityId,
            @PageableDefault(size = 20)
            @SortDefault(sort = "occurredAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return listBean.list(entityType, entityId, pageable);
    }
}
