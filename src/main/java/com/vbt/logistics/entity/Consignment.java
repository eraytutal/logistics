package com.vbt.logistics.entity;

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
public class Consignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;


    @Builder.Default
    @OneToMany(mappedBy = "consignment", fetch = FetchType.LAZY)
    private List<ConsignmentItem> consignmentItem = new ArrayList<>();
}
