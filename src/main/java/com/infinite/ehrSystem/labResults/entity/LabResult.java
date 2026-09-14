package com.infinite.ehrSystem.labResults.entity;

import com.infinite.ehrSystem.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "lab_results", uniqueConstraints = {@UniqueConstraint(name = "uk_lab_result_order", columnNames = "lab_order_id")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResult extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "lab_order_id", nullable = false, unique = true , length = 36)
    private UUID labOrderId;

    @Column(name = "result_value", nullable = false)
    private String resultValue;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "reference_range", nullable = false)
    private String referenceRange;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultFlag flag;

    @Column(name = "result_date", nullable = false)
    private LocalDate resultDate;
}