package com.vbt.logistics.bean.driver;

import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteDriverBean {
    private final DriverRepository repo;

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Driver not found: " + id);
        repo.deleteById(id);
    }
}
