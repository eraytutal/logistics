package com.vbt.logistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "consignment_item", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsignmentItem {
    @EmbeddedId
    private ConsignmentItemId id;


    @ManyToOne(fetch = FetchType.LAZY) @MapsId("consignmentId")
    @JoinColumn(name = "consignment_id", nullable = false)
    private Consignment consignment;


    @ManyToOne(fetch = FetchType.LAZY) @MapsId("orderItemId")
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;


    @Column(name = "weight_kg", precision = 12, scale = 3)
    private BigDecimal weightKg;


    @Column(name = "volume_m3", precision = 12, scale = 3)
    private BigDecimal volumeM3;
}
