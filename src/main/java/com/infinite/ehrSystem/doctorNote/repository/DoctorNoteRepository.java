package com.infinite.ehrSystem.doctorNote.repository;

import com.infinite.ehrSystem.doctorNote.entity.DoctorNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorNoteRepository extends JpaRepository<DoctorNote, UUID> {

    List<DoctorNote> findByVisit_Id(UUID visitId);

}