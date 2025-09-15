package com.vbt.logistics.bean.consignment;

import com.vbt.logistics.entity.ConsignmentItemId;
import com.vbt.logistics.exception.NotFoundException;
import com.vbt.logistics.repository.ConsignmentItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteConsignmentItemBean {
    private final ConsignmentItemRepository repo;

    @Transactional
    public void delete(Long consignmentId, Long orderItemId) {
        ConsignmentItemId id = new ConsignmentItemId(consignmentId, orderItemId);
        if (!repo.existsById(id)) throw new NotFoundException(
                "ConsignmentItem not found: consignmentId=%d, orderItemId=%d".formatted(consignmentId, orderItemId));
        repo.deleteById(id);
    }
}
