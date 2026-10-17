package com.rag.ollamarag.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
@Entity 
    @Table (name = "tickets")

public class Ticket {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String subject;

    @Column(nullable = false, length = 30)
    private String status = "OPEN";

    @Column (nullable = false )
    private Integer priority = 3;
    
    @Column (length = 100)
    private String department;

    @Column (length = 30)
    private String sentiment;
    
    @JdbcTypeCode(SqlTypes.JSON)
    @Column (name = "ai_tags", columnDefinition = "jsonb", nullable = false)
    private List<String> aiTags = new ArrayList<>();

    @Column (columnDefinition = "TEXT")
    private String summary;

    @Column (name="created_at", nullable = false)
    private Instant createdAt;

    @Column (name="triaged_at")
    private Instant triagedAt;

    @PrePersist 
    public void OnCreate() {
        if (createdAt == null){
            createdAt = Instant.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getdepartment() {
        return department;
    }

    public void setdepartment(String department) {
        this.department = department;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public List<String> getaiTags() {
        return aiTags;
    }

    public void setaiTags(List<String> aiTags) {
        this.aiTags = aiTags;
    }

    public String getsummary() {
        return summary;
    }

    public void setsummary(String summary) {
        this.summary = summary;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getTriagedAt() {
        return triagedAt;
    }

    public void setTriagedAt(Instant triagedAt) {
        this.triagedAt = triagedAt;
    }
}
