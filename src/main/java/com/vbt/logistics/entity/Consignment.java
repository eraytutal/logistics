package com.vbt.logistics.entity;

import com.vbt.logistics.entity.base.CreatedAtAuditBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "consignment", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consignment extends CreatedAtAuditBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Builder.Default
    @OneToMany(mappedBy = "consignment", fetch = FetchType.LAZY)
    private List<ConsignmentItem> consignmentItem = new ArrayList<>();
}
