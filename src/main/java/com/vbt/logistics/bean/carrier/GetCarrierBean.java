package com.vbt.logistics.bean.carrier;

import com.vbt.logistics.dto.CarrierDto;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.mapper.CarrierMapper;
import com.vbt.logistics.repository.CarrierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetCarrierBean {

    private final CarrierRepository carrierRepo;
    private final CarrierMapper mapper;

    @Transactional(readOnly = true)
    public CarrierDto get(Long id) {
        var c = carrierRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Carrier not found: " + id));
        return mapper.map(c);
    }
}
