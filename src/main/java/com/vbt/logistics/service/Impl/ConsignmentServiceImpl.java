package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.consignment.CreateConsignmentBean;
import com.vbt.logistics.bean.consignment.DeleteConsignmentBean;
import com.vbt.logistics.bean.consignment.GetConsignmentBean;
import com.vbt.logistics.bean.consignment.ListConsignmentItemsBean;
import com.vbt.logistics.bean.consignment.ListConsignmentsBean;
import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.service.ConsignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ConsignmentServiceImpl implements ConsignmentService {

    private final CreateConsignmentBean createBean;
    private final GetConsignmentBean getBean;
    private final DeleteConsignmentBean deleteBean;
    private final ListConsignmentsBean listBean;
    private final ListConsignmentItemsBean listItemsBean;

    @Override
    @Transactional
    public ConsignmentDto create() {
        return createBean.create();
    }

    @Override
    @Transactional(readOnly = true)
    public ConsignmentDto get(Long id) {
        return getBean.get(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        deleteBean.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<ConsignmentDto> list(Pageable pageable) {
        return listBean.list(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<ConsignmentItemDto> listItems(Long consignmentId, Pageable pageable) {
        return listItemsBean.list(consignmentId, pageable);
    }
}
