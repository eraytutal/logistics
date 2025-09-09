package com.vbt.logistics.service.Impl;

import com.vbt.logistics.bean.documents.ListDocumentsBean;
import com.vbt.logistics.dto.DocumentDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final ListDocumentsBean listDocumentsBean;


    @Override
    @Transactional(readOnly = true)
    public PageResponseDto<DocumentDto> list(EntityType relatedType, Long relatedId, Pageable pageable) {
        return listDocumentsBean.list(relatedType, relatedId, pageable);
    }
}
