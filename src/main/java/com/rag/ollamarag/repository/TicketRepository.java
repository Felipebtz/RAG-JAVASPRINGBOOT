package com.rag.ollamarag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rag.ollamarag.model.Ticket;

public interface TicketRepository extends JpaRepository <Ticket, Long>{
    
    
}
