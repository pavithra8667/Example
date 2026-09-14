package com.infinite.ehrSystem.diagnosis.entity;

import com.infinite.ehrSystem.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.JdbcTypeCode;

import org.hibernate.type.SqlTypes;
import java.util.UUID;

@Entity
@Table(name = "diagnoses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnosis extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID id;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "visit_id", nullable = false , length = 36)
    private UUID visitId;

    @Column(name = "diagnosis_code", nullable = false, length = 20)
    private String diagnosisCode;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "diagnosis_type", nullable = false)
    private DiagnosisType diagnosisType;
}