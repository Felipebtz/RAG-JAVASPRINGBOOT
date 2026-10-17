package com.rag.ollamarag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rag.ollamarag.model.AiUsage;



public interface AiUsageRepository
        extends JpaRepository<AiUsage, Long> {

}