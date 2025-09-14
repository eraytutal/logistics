package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.entity.Consignment;
import com.vbt.logistics.mapper.ConsignmentMapper;
import com.vbt.logistics.repository.ConsignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateConsignmentBean {

    private final ConsignmentRepository repo;
    private final ConsignmentMapper mapper;

    @Transactional
    public ConsignmentDto create() {
        // Şimdilik boş bir consignment (yalnızca id + createdAt) -Sonra bakıcam.
        var saved = repo.save(Consignment.builder().build());
        return mapper.map(saved);
    }
}
