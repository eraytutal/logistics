package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.DocumentDto;
import com.vbt.logistics.entity.Document;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public DocumentDto mapDocumentDto(Document d) {
        return new DocumentDto(
                d.getId(),
                d.getDocType(),
                d.getFileUrl(),
                d.getRelatedType(),
                d.getRelatedId(),
                d.getUploadedAt()
        );
    }
}