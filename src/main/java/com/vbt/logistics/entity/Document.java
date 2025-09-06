package com.vbt.logistics.entity;

import com.vbt.logistics.enums.EntityType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.Instant;


@Entity
@Table(name = "document", schema = "logistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "doc_type", length = 60)
    private String docType;


    @Column(name = "file_url", columnDefinition = "text") // uzunluk limiti yok dikkat et.
    private String fileUrl;


    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "related_type", columnDefinition = "entity_type")
    private EntityType relatedType;


    @Column(name = "related_id")
    private Long relatedId;


    @Column(name = "uploaded_at", nullable = false)
    private Instant uploadedAt;

    @PrePersist
    void onCreate() {
        if (uploadedAt == null) uploadedAt = Instant.now();
    }
}
