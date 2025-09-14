package com.vbt.logistics.bean.vehicle;

import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteVehicleBean {
    private final VehicleRepository repo;

    @Transactional
    public void delete(Long id) {
        if (!repo.existsById(id)) throw new NotFoundException("Vehicle not found: " + id);
        repo.deleteById(id);
    }
}

