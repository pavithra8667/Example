package com.infinite.ehrSystem.doctorNote.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorNoteDTO {
    private UUID id;
    @NotNull(message = "Visit ID is required")
    private UUID visitId;

    @NotNull(message = "Doctor ID is required")
    private UUID doctorId;

    @NotBlank(message = "Note type is required")
    private String noteType;

    @NotBlank(message = "Note text is required")
    @Size(min = 10, message = "Note must contain at least 10 characters")
    private String noteText;
    private LocalDateTime createdAt;
}