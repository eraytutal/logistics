package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.vehicle.*;
import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final CreateVehicleBean createBean;
    private final GetVehicleBean getBean;
    private final UpdateVehicleBean updateBean;
    private final DeleteVehicleBean deleteBean;
    private final ListVehiclesBean listBean;

    @Override
    @Transactional
    public VehicleDto create(CreateVehicleRequestDto req) {
        return createBean.create(req);
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleDto get(Long id) {
        return getBean.get(id);
    }

    @Override
    @Transactional
    public VehicleDto update(Long id, UpdateVehicleRequestDto req) {
        return updateBean.update(id, req);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        deleteBean.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<VehicleDto> list(String q, Pageable pageable) {
        return listBean.list(q, pageable);
    }
}
