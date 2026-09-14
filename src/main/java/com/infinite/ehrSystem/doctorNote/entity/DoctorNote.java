package com.infinite.ehrSystem.doctorNote.entity;

import com.infinite.ehrSystem.visit.entity.Visit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "doctor_notes")
@Getter
@Setter
@NoArgsConstructor
public class DoctorNote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id", nullable = false)
    private Visit visit;

    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @Column(name = "note_type", nullable = false)
    private String noteType;

    @Column(name = "note_text", nullable = false, length = 2000)
    private String noteText;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}