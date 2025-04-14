-- Create account table
CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(16) NOT NULL UNIQUE,
    balance NUMERIC(19, 2) NOT NULL,
    user_id INTEGER REFERENCES users (id)
);

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Insert sample users
INSERT INTO account (account_number, pin, balance)
VALUES
('ACC123456', 1000.00),
('ACC654321', 2500.50);

INSERT INTO users (username, password) VALUES
('john_doe', 'password'),
('jane_doe', 'password');