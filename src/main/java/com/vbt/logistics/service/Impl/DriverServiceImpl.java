package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.driver.*;
import com.vbt.logistics.dto.CreateDriverRequestDto;
import com.vbt.logistics.dto.DriverDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.dto.UpdateDriverRequestDto;
import com.vbt.logistics.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final CreateDriverBean createBean;
    private final GetDriverBean getBean;
    private final UpdateDriverBean updateBean;
    private final DeleteDriverBean deleteBean;
    private final ListDriversBean listBean;


    @Override
    @Transactional
    public DriverDto create(CreateDriverRequestDto req) {
        return createBean.create(req);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverDto get(Long id) {
        return getBean.get(id);
    }

    @Override
    @Transactional
    public DriverDto update(Long id, UpdateDriverRequestDto req) {
        return updateBean.update(id, req);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        deleteBean.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<DriverDto> list(String q, Pageable pageable) {
        return listBean.list(q, pageable);
    }
}
