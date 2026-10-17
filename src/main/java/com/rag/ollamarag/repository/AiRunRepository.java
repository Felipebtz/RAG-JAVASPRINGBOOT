package com.rag.ollamarag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rag.ollamarag.model.AiRun;


public interface AiRunRepository
        extends JpaRepository<AiRun, Long> {

}   