package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.repository.ConsignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteConsignmentBean {
    private final ConsignmentRepository repo;

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Consignment not found: " + id);
        repo.deleteById(id);
    }
}
