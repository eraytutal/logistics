package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.carrier.*;
import com.vbt.logistics.dto.*;
import com.vbt.logistics.service.CarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CarrierServiceImpl implements CarrierService {

    private final CreateCarrierBean createBean;
    private final GetCarrierBean getBean;
    private final UpdateCarrierBean updateBean;
    private final DeleteCarrierBean deleteBean;
    private final ListCarriersBean listBean;

    @Override
    @Transactional
    public CarrierDto create(CreateCarrierRequestDto req) {
        return createBean.create(req);
    }

    @Override
    @Transactional(readOnly = true)
    public CarrierDto get(Long id) {
        return getBean.get(id);
    }

    @Override
    @Transactional
    public CarrierDto update(Long id, UpdateCarrierRequestDto req) {
        return updateBean.update(id, req);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        deleteBean.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<CarrierDto> list(String q, Pageable pageable) {
        return listBean.list(q, pageable);
    }
}
