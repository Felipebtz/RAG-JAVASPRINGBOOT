package com.rag.ollamarag.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rag.ollamarag.model.TicketMessage;

public interface TicketMessageRepository
        extends JpaRepository<TicketMessage, Long> {

}