package com.vbt.logistics.bean.location;

import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteLocationBean {
    private final LocationRepository repo;

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Location not found: " + id);
        repo.deleteById(id);
    }
}

