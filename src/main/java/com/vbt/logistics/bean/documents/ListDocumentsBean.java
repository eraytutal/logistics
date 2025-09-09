package com.vbt.logistics.bean.documents;

import com.vbt.logistics.dto.DocumentDto;
import com.vbt.logistics.dto.PageResponseDto;
import com.vbt.logistics.enums.EntityType;
import com.vbt.logistics.mapper.DocumentMapper;
import com.vbt.logistics.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListDocumentsBean {
    private final DocumentRepository docRepo;
    private final DocumentMapper mapper;

    @Transactional(readOnly = true)
    public PageResponseDto<DocumentDto> list(EntityType relatedType, Long relatedId, Pageable pageable) {
        var page = docRepo.findByRelatedTypeAndRelatedId(relatedType, relatedId, pageable)
                .map(mapper::mapDocumentDto);
        return PageResponseDto.from(page);
    }
}
