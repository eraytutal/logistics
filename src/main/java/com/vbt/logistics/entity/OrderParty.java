package com.vbt.logistics.entity;

import com.vbt.logistics.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_party", schema = "logistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;


    @Column(name = "party_name", length = 150)
    private String partyName;
}
