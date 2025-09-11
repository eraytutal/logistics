package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.location.*;
import com.vbt.logistics.dto.*;
import com.vbt.logistics.dto.CreateLocationRequestDto;
import com.vbt.logistics.dto.UpdateLocationRequestDto;
import com.vbt.logistics.dto.LocationFilterDto;
import com.vbt.logistics.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CreateLocationBean createBean;
    private final GetLocationBean getBean;
    private final UpdateLocationBean updateBean;
    private final DeleteLocationBean deleteBean;
    private final ListLocationsBean listBean;


    @Override
    @Transactional
    public LocationDto create(CreateLocationRequestDto req) {
        return createBean.create(req);
    }

    @Override
    @Transactional(readOnly = true)
    public LocationDto get(Long id) {
        return getBean.get(id);
    }

    @Override
    @Transactional
    public LocationDto update(Long id, UpdateLocationRequestDto req) {
        return updateBean.update(id, req);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        deleteBean.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<LocationDto> list(LocationFilterDto filter, Pageable pageable) {
        return listBean.list(filter, pageable);
    }
}

