CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,

    subject VARCHAR(255) NOT NULL,

    status VARCHAR(30) NOT NULL DEFAULT 'OPEN',

    priority INTEGER NOT NULL DEFAULT 3
        CHECK (priority BETWEEN 1 AND 5),

    department VARCHAR(100),

    sentiment VARCHAR(30),

    ai_tags JSONB NOT NULL DEFAULT '[]'::jsonb,

    summary TEXT,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    triaged_at TIMESTAMPTZ
);

CREATE TABLE ticket_messages (
    id BIGSERIAL PRIMARY KEY,

    ticket_id BIGINT NOT NULL,

    body TEXT NOT NULL,

    author_type VARCHAR(20) NOT NULL
        CHECK (author_type IN ('CUSTOMER', 'AGENT', 'SYSTEM')),

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_ticket_messages_ticket
        FOREIGN KEY (ticket_id)
        REFERENCES tickets(id)
        ON DELETE CASCADE
);