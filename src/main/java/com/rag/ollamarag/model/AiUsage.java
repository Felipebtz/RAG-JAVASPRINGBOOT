package com.rag.ollamarag.model;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;

public class AiUsage {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn (name = "ai_run")
    private AiRun aiRun;

    @Column (name = "prompt_tokens")
    private Integer prompt_token = 0;

    @Column (name= "completion_tokens")
    private Integer completion_tokens = 0;

    @Column (name= "total_tokens")
    private Integer total_tokens = 0;

    @Column (name = "cost_real")
    private BigDecimal cost_real;

    @Column (name="created_at", nullable = false)
    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AiRun getAiRun() {
        return aiRun;
    }

    public void setAiRun(AiRun aiRun) {
        this.aiRun = aiRun;
    }

    public Integer getPrompt_token() {
        return prompt_token;
    }

    public void setPrompt_token(Integer prompt_token) {
        this.prompt_token = prompt_token;
    }

    public Integer getCompletion_tokens() {
        return completion_tokens;
    }

    public void setCompletion_tokens(Integer completion_tokens) {
        this.completion_tokens = completion_tokens;
    }

    public Integer getTotal_tokens() {
        return total_tokens;
    }

    public void setTotal_tokens(Integer total_tokens) {
        this.total_tokens = total_tokens;
    }

    public BigDecimal getCost_real() {
        return cost_real;
    }

    public void setCost_real(BigDecimal cost_real) {
        this.cost_real = cost_real;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @PrePersist 
    public void OnCreate() {
        if (createdAt == null){
            createdAt = Instant.now();
        }
    }




}
