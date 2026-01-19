CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version BIGINT
);

CREATE TABLE IF NOT EXISTS todos (
    id UUID PRIMARY KEY,
    status VARCHAR(20) NOT NULL,
    user_id UUID NOT NULL,
    description TEXT,
    version BIGINT
);

CREATE INDEX IF NOT EXISTS idx_todos_user_id ON todos(user_id);
