package com.vbt.logistics.bean.driver;

import com.vbt.logistics.dto.CreateDriverRequestDto;
import com.vbt.logistics.dto.DriverDto;
import com.vbt.logistics.entity.Driver;
import com.vbt.logistics.exception.ConflictException;
import com.vbt.logistics.mapper.DriverMapper;
import com.vbt.logistics.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateDriverBean {
    private final DriverRepository repo;
    private final DriverMapper mapper;

    @Transactional
    public DriverDto create(CreateDriverRequestDto req) {
        if (repo.existsByLicenseNoIgnoreCase(req.licenseNo())) {
            throw new ConflictException("License already exists: " + req.licenseNo());
        }
        Driver d = Driver.builder()
                .fullName(req.fullName())
                .licenseNo(req.licenseNo())
                .phone(req.phone())
                .build();
        return mapper.map(repo.save(d));
    }
}

