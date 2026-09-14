package com.infinite.ehrSystem.prescriptions.entity;

import com.infinite.ehrSystem.common.AuditableEntity;
import com.infinite.ehrSystem.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(length = 36)
    private UUID prescriptionId;

    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionItem> items;
}