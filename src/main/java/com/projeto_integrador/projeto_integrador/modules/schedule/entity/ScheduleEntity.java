package com.projeto_integrador.projeto_integrador.modules.schedule.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Data
@Builder
@EqualsAndHashCode(of = "ScheduleId")
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "schedules")
public class ScheduleEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long ScheduleId;

    @NotNull
    @Column(name = "teacher")
    private Long teacher;

    @NotNull
    @Column(name = "subject")
    private Long subject;

    @NotNull
    @Column(name = "time")
    private Long time;

    @Column(name = "room")
    private Long room;

    @NotNull
    @Column(name = "course")
    private Long course;

    @CreationTimestamp
    private LocalDateTime create_at;
    
    @UpdateTimestamp
    private LocalDateTime update_at;
}