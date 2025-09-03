package com.vbt.logistics.entity;

import com.vbt.logistics.enums.StopRole;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(
        name = "order_stop",
        schema = "logistics",
        uniqueConstraints = @UniqueConstraint(name = "uq_order_stop__order_role_seq",
                columnNames = {"order_id", "role", "seq"})
)
public class OrderStop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StopRole role;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;


    @Column(name = "window_start")
    private Instant windowStart;


    @Column(name = "window_end")
    private Instant windowEnd;


    private Integer seq;
}
