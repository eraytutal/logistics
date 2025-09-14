package com.vbt.logistics.bean.driver;

import com.vbt.logistics.dto.DriverDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.mapper.DriverMapper;
import com.vbt.logistics.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListDriversBean {
    private final DriverRepository repo;
    private final DriverMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<DriverDto> list(String q, Pageable pageable) {
        var page = (q == null || q.isBlank())
                ? repo.findAll(pageable)
                : repo.findByFullNameContainingIgnoreCaseOrLicenseNoContainingIgnoreCase(q.trim(), q.trim(), pageable);
        return PageResponseDto.from(page.map(mapper::map));
    }
}
