package com.vbt.logistics.bean.driver;

import com.vbt.logistics.dto.DriverDto;
import com.vbt.logistics.dto.UpdateDriverRequestDto;
import com.vbt.logistics.exception.ConflictException;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.DriverMapper;
import com.vbt.logistics.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateDriverBean {
    private final DriverRepository repo;
    private final DriverMapper mapper;

    @Transactional
    public DriverDto update(Long id, UpdateDriverRequestDto req) {
        var d = repo.findById(id).orElseThrow(() -> new NotFoundException("Driver not found: " + id));

        if (!d.getLicenseNo().equalsIgnoreCase(req.licenseNo())
                && repo.existsByLicenseNoIgnoreCase(req.licenseNo())) {
            throw new ConflictException("License already exists: " + req.licenseNo());
        }

        d.setFullName(req.fullName());
        d.setLicenseNo(req.licenseNo());
        d.setPhone(req.phone());
        return mapper.map(d);
    }
}

