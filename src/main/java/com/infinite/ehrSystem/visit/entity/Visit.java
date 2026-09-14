package com.infinite.ehrSystem.visit.entity;

import com.infinite.ehrSystem.common.AuditableEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "visits", uniqueConstraints = {@UniqueConstraint(columnNames = "visit_code")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Visit extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id", nullable = false, updatable = false , length = 36)
    private UUID id;

    @Column(name = "visit_code", nullable = false, unique = true)
    private String visitCode;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "patient_id", nullable = false ,length = 36)
    private UUID patientId;

    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "doctor_id", nullable = false , length =36)
    private UUID doctorId;

    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status;

}