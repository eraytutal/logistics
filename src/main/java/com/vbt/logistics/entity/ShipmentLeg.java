package com.vbt.logistics.entity;


import com.vbt.logistics.enums.Mode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;


@Entity
@Table(
        name = "shipment_leg",
        schema = "logistics",
        uniqueConstraints = @UniqueConstraint(name = "uq_leg__shipment_seq", columnNames = {"shipment_id", "seq"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentLeg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shipment_id", nullable = false)
    private Shipment shipment;


    @Column(nullable = false)
    private Integer seq;


    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "mode", nullable = false, columnDefinition = "mode")
    private Mode mode;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle; // for ROAD (own fleet)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver; // for ROAD (own fleet)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id")
    private Carrier carrier; // for AIR/SEA or external ROAD


    @Column(name = "booking_ref", length = 80) private String bookingRef;
    @Column(name = "awb_no", length = 50) private String awbNo;
    @Column(name = "bl_no", length = 50) private String blNo;
    @Column(name = "cmr_no", length = 50) private String cmrNo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_location_id", nullable = false)
    private Location startLocation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_location_id", nullable = false)
    private Location endLocation;


    @Column(name = "planned_start") private Instant plannedStart;
    @Column(name = "planned_end") private Instant plannedEnd;
    @Column(name = "actual_start") private Instant actualStart;
    @Column(name = "actual_end") private Instant actualEnd;
}
