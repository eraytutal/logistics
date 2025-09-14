package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.ConsignmentMapper;
import com.vbt.logistics.repository.ConsignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetConsignmentBean {
    private final ConsignmentRepository repo;
    private final ConsignmentMapper mapper;

    @Transactional(readOnly = true)
    public ConsignmentDto get(Long id) {
        var c = repo.findById(id).orElseThrow(() -> new NotFoundException("Consignment not found: " + id));
        return mapper.map(c);
    }
}
