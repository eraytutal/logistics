package com.vbt.logistics.bean.carrier;

import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.repository.CarrierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCarrierBean {

    private final CarrierRepository carrierRepo;

    @Transactional
    public void delete(Long id) {
        if (!carrierRepo.existsById(id)) {
            throw new NotFoundException("Carrier not found: " + id);
        }
        carrierRepo.deleteById(id);
    }
}
