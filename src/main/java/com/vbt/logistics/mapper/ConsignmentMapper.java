package com.vbt.logistics.mapper;

import com.vbt.logistics.dto.ConsignmentDto;
import com.vbt.logistics.dto.ConsignmentItemDto;
import com.vbt.logistics.entity.Consignment;
import com.vbt.logistics.entity.ConsignmentItem;
import org.springframework.stereotype.Component;

@Component
public class ConsignmentMapper {

    public ConsignmentDto map(Consignment c) {
        return new ConsignmentDto(
                c.getId(),
                c.getCreatedAt()
        );
    }

    public ConsignmentItemDto mapItem(ConsignmentItem ci) {
        return new ConsignmentItemDto(
                ci.getId().getConsignmentId(),
                ci.getId().getOrderItemId(),
                ci.getWeightKg(),
                ci.getVolumeM3()
        );
    }
}
