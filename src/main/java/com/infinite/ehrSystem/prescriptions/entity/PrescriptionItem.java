package com.infinite.ehrSystem.prescriptions.entity;

import com.infinite.ehrSystem.common.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "prescription_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItem extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID itemId;

    private String medicineName;

    private String dosage;

    private String frequency;

    private Integer durationDays;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}