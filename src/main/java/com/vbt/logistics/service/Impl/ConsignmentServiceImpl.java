package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.consignment.*;
import com.vbt.logistics.dto.AddConsignmentItemRequestDto;
import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.entity.ConsignmentItemId;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ConsignmentMapper;
import com.vbt.logistics.repository.ConsignmentItemRepository;
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
    private final AddConsignmentItemBean addItemBean;
    private final DeleteConsignmentItemBean deleteItemBean;
    private final ConsignmentItemRepository consignmentItemRepository;
    private final ConsignmentMapper consignmentMapper;

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

    @Override
    @Transactional
    public ConsignmentItemDto addItem(Long consignmentId, AddConsignmentItemRequestDto req) {
        return addItemBean.add(consignmentId, req);
    }

    @Override
    @Transactional
    public void deleteItem(Long consignmentId, Long orderItemId) {
        deleteItemBean.delete(consignmentId, orderItemId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean itemExists(Long consignmentId, Long orderItemId) {
        return consignmentItemRepository.existsById(new ConsignmentItemId(consignmentId, orderItemId));

    }

    @Override
    @Transactional(readOnly = true)
    public ConsignmentItemDto getItem(Long consignmentId, Long orderItemId) {
        var consignmentItem = consignmentItemRepository.findById(new ConsignmentItemId(consignmentId, orderItemId))
                .orElseThrow(() -> new NotFoundException(
                        "ConsignmentItem not found: consignmentId=%d, orderItemId=%d".formatted(consignmentId, orderItemId)));
        return consignmentMapper.mapItem(consignmentItem);
    }
}
