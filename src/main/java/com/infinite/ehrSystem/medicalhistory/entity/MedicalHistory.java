package com.infinite.ehrSystem.medicalhistory.entity;

import com.infinite.ehrSystem.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "medical_history")
@Getter
@Setter
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "patient_id",
            nullable = false
    )
    private Patient patient;

    @Column(
            name = "history_type",
            nullable = false,
            length = 30
    )
    private String historyType;

    @Column(
            name = "condition_name",
            nullable = false,
            length = 150
    )
    private String conditionName;

    @Column(name = "diagnosed_year")
    private Integer diagnosedYear;

    @Column(columnDefinition = "TEXT")
    private String notes;
}