package com.infinite.ehrSystem.allergy.entity;
import com.infinite.ehrSystem.patient.entity.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.infinite.ehrSystem.patient.entity.Patient;
import java.util.UUID;

@Entity
@Table(
        name = "allergies",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_patient_allergen",
                        columnNames = {
                                "patient_id",
                                "allergen"
                        }
                )
        }
)
@Getter
@Setter
public class Allergy {

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
            nullable = false,
            length = 150
    )
    private String allergen;

    @Column(
            nullable = false,
            length = 255
    )
    private String reaction;

    @Column(
            nullable = false,
            length = 20
    )
    private String severity;
}
