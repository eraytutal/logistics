package com.vbt.logistics.entity;

import com.vbt.logistics.enums.EntityType;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;


@Entity
@Table(name = "status_event", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    private EntityType entityType;


    @Column(name = "entity_id", nullable = false)
    private Long entityId; // Polymorphic target


    @Column(length = 60)
    private String status;


    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;


    @Column(columnDefinition = "text")
    private String note;
}
