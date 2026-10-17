CREATE TABLE tickets (
    id BIGSERIAL PRIMARY KEY,

    subject VARCHAR(255) NOT NULL, 
    
    status VARCHAR(30) NOT NULL DEFAULT 'OPEN',

    -- priority: nome da coluna. SMALLINT: tipo numérico inteiro pequeno.NOT NULL: não permite valor nulo. 
    --DEFAULT 3: assume 3 quando não informado.CHECK: garante valores entre 1 e 5.
    priority SMALLINT NOT NULL DEFAULT 3 CHECK (priority BETWEEN 1 AND 5),

    departament VARCHAR(100),

    sentiment VARCHAR(100),

    ai_tags JSONB NOT NULL DEFAULT '[]'::jsonb,
    
    summary TEXT,

    created_at TIMESTAMPTZ NOT DEFAULT NOW(),

    triaged_at TIMESTAMPTZ
    )

CREATE TABLE ticketsMessage (
    id BIGSERIAL PRIMARY KEY,

    ticked_id BIGINT NOT NULL,

    body TEXT NOT NULL,

    author_type VARCHAR(20) NOT NULL
        CHECK (author_type IN ('CUSTOMER', 'AGENT', 'SYSTEM')),

        created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

        CONSTRAINT fk_ticket_message_ticket
         FOREIGN KEY (ticked_id)
         REFERENCES tickets(id)
         ON DELETE CASCADE
 
)