package com.infinite.ehrSystem.vaccination.entity;

import com.infinite.ehrSystem.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "vaccinations")
@Getter
@Setter
@NoArgsConstructor
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    @Column(name = "dose_number", nullable = false)
    private Integer doseNumber;

    @Column(name = "administered_date", nullable = false)
    private LocalDate administeredDate;

    @Column(name = "administered_by")
    private String administeredBy;

    @Column(name = "next_due_date")
    private LocalDate nextDueDate;
}