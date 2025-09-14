package com.vbt.logistics.bean.driver;

import com.vbt.logistics.dto.DriverDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.DriverMapper;
import com.vbt.logistics.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetDriverBean {
    private final DriverRepository repo;
    private final DriverMapper mapper;

    @Transactional(readOnly = true)
    public DriverDto get(Long id) {
        var d = repo.findById(id).orElseThrow(() -> new NotFoundException("Driver not found: " + id));
        return mapper.map(d);
    }
}
