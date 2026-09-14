package com.infinite.ehrSystem.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity {

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @PrePersist
    protected void onCreate() {

        createdOn = LocalDateTime.now();
        updatedOn = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {

        updatedOn = LocalDateTime.now();
    }
}