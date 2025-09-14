package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.mapper.ConsignmentMapper;
import com.vbt.logistics.repository.ConsignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListConsignmentsBean {
    private final ConsignmentRepository repo;
    private final ConsignmentMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<ConsignmentDto> list(Pageable pageable) {
        var page = repo.findAll(pageable).map(mapper::map);
        return PageResponseDto.from(page);
    }
}
