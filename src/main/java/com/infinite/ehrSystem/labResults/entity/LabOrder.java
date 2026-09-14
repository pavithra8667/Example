package com.infinite.ehrSystem.labResults.entity;

import com.infinite.ehrSystem.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "lab_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabOrder extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "visit_id", nullable = false ,length = 36)
    private UUID visitId;

    @Column(name = "test_name", nullable = false, length = 100)
    private String testName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LabOrderStatus status;
}