package com.ecoevents.ecoevents.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_registrations")
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 关键点：这里不是存 int event_id，而是直接存一个 Event 对象！
    @ManyToOne // 1. 关系描述：多个(Many)报名记录 可以属于 一个(One)活动
    @JoinColumn(name = "event_id", nullable = false) // 2. 连接暗号：数据库里用 "event_id" 这一列来找活动
    private Event event;

    @ManyToOne // 1. 关系描述：多个(Many)报名记录 可以属于 一个(One)志愿者
    @JoinColumn(name = "volunteer_id", nullable = false) // 2. 连接暗号：数据库里用 "volunteer_id" 这一列来找志愿者
    private Volunteer volunteer;

    @Column(name = "registration_date", insertable = false, updatable = false)
    private LocalDateTime registrationDate;

    @Column(columnDefinition = "TEXT") // 对应 SQL 里的 notes TEXT
    private String notes;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
