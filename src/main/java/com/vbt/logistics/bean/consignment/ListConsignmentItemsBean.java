package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ConsignmentMapper;
import com.vbt.logistics.repository.ConsignmentItemRepository;
import com.vbt.logistics.repository.ConsignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListConsignmentItemsBean {
    private final ConsignmentRepository consRepo;
    private final ConsignmentItemRepository itemRepo;
    private final ConsignmentMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<ConsignmentItemDto> list(Long consignmentId, Pageable pageable) {
        consRepo.findById(consignmentId)
                .orElseThrow(() -> new NotFoundException("Consignment not found: " + consignmentId));

        var page = itemRepo.findById_ConsignmentId(consignmentId, pageable)
                .map(mapper::mapItem);

        return PageResponseDto.from(page);
    }
}
