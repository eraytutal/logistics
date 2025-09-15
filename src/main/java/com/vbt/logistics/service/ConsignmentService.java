package com.vbt.logistics.service;

import com.vbt.logistics.dto.AddConsignmentItemRequestDto;
import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import org.springframework.data.domain.Pageable;

public interface ConsignmentService {
    ConsignmentDto create();

    ConsignmentDto get(Long id);

    void delete(Long id);

    PageResponseDto<ConsignmentDto> list(Pageable pageable);

    PageResponseDto<ConsignmentItemDto> listItems(Long consignmentId, Pageable pageable);

    ConsignmentItemDto addItem(Long consignmentId, AddConsignmentItemRequestDto req);

    void deleteItem(Long consignmentId, Long orderItemId);

    boolean itemExists(Long consignmentId, Long orderItemId);

    ConsignmentItemDto getItem(Long consignmentId, Long orderItemId);
}
