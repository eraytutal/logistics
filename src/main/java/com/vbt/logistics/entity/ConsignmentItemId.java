package com.vbt.logistics.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ConsignmentItemId implements Serializable {
    @Column(name = "consignment_id")
    private Long consignmentId;


    @Column(name = "order_item_id")
    private Long orderItemId;
}
