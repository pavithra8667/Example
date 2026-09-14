package com.infinite.ehrSystem.patient.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "patient_code", nullable = false, unique = true, length = 30)
    private String patientCode;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(length = 20)
    private String gender;

    @Column(nullable = false, unique = true, length = 10)
    private String phone;

    @Column(name = "guardian_name", length = 100)
    private String guardianName;

}
