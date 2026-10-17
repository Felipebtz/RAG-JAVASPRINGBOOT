package com.rag.ollamarag.model;

import java.time.Instant;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

  @Entity 
    @Table (name = "ai_run")

public class AiRun {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany  (fetch = FetchType.LAZY)
    @JoinColumn (name = "team_id")
    private Team team;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "ticket_id")
    private Ticket ticket;

    @Column (name = "featured_key",length = 255)
    private String featured_key;

    @Column (name = "status", length = 30)
    private String status;

    @Column (name = "provider", length = 50)
    private String provider;

    @Column (name = "model", length = 50)
    private String model;

    @Column (name = "input_hash", length = 100)
    private String input_hash;

    @Column (name="created_at", nullable = false)
    private Instant createdAt;

    @Column (name="finished_at", nullable = false)
    private Instant finished_at;

    @Column (name = "error_message", columnDefinition = "TEXT")
    private String error_message;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getFeatured_key() {
        return featured_key;
    }

    public void setFeatured_key(String featured_key) {
        this.featured_key = featured_key;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInput_hash() {
        return input_hash;
    }

    public void setInput_hash(String input_hash) {
        this.input_hash = input_hash;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getFinished_at() {
        return finished_at;
    }

    public void setFinished_at(Instant finished_at) {
        this.finished_at = finished_at;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @PrePersist  
    public void OnCreate() {
        if (createdAt == null){
            createdAt = Instant.now();
        }
    }

    @PrePersist 
    public void OnFinish(){
        if (finished_at == null){
            finished_at = Instant.now();
        }
    }
    


    
}
