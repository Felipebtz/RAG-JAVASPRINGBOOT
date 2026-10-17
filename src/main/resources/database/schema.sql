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

CREATE TABLE ai_run (
    id BIGSERIAL PRIMARY KEY,

    team_id BIGINT NOT NULL,

    user_id BIGINT NOT NULL,

    ticket_id BIGINT NOT NULL,

    featured_key VARCHAR(255),

    status VARCHAR(30),

    provider VARCHAR(50),

    model VARCHAR(50),

    input_hash VARCHAR(100),

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    finished_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    error_message TEXT,


    CONSTRAINT fk_airun_team_id
        FOREIGN KEY (team_id)
        REFERENCES team(id)
        ON DELETE CASCADE

    CONSTRAINT fk_airun_user_id
        FOREIGN KEY (user_id)
        REFERENCES user(id)
        ON DELETE CASCADE

    CONSTRAINT fk_airun_ticket_id
      FOREIGN KEY (ticket_id)
      REFERENCES ticket(id)
      ON DELETE SET NULL



);

CREATE TABLE IF NOT EXISTS ai_usage (
    id BIGSERIAL PRIMARY KEY,

    ai_run BIGINT NOT NULL,

    prompt_tokens INTEGER DEFAULT 0,

    completion_tokens INTEGER DEFAULT 0,

    total_tokens INTEGER DEFAULT 0,
    
    cost_real DECIMAL 10 4,
    
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_aiusage_airun
      FOREIGN KEY (ai_run)
      REFERENCES ai_run(id)
      ON DELETE CASCADE

);