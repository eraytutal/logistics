package com.vbt.logistics.bean.carrier;

import com.vbt.logistics.dto.CarrierDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.mapper.CarrierMapper;
import com.vbt.logistics.repository.CarrierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListCarriersBean {

    private final CarrierRepository carrierRepo;
    private final CarrierMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<CarrierDto> list(String q, Pageable pageable) {
        var page = (q == null || q.isBlank())
                ? carrierRepo.findAll(pageable)
                : carrierRepo.findByNameContainingIgnoreCase(q.trim(), pageable);
        return PageResponseDto.from(page.map(mapper::map));
    }
}
