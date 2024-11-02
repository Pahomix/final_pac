-- Создание таблицы пользователей
CREATE TABLE IF NOT EXISTS "user" (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
    );

-- Создание таблицы концертов
CREATE TABLE IF NOT EXISTS concert (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date_time TIMESTAMP,
    location VARCHAR(100)
    );

-- Создание таблицы билетов
CREATE TABLE IF NOT EXISTS ticket (
    id SERIAL PRIMARY KEY,
    concert_id INTEGER REFERENCES concert(id),
    user_id INTEGER REFERENCES "user"(id),
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE
    );

-- Создание таблицы отзывов
CREATE TABLE IF NOT EXISTS review (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES "user"(id),
    concert_id INTEGER REFERENCES concert(id),
    comment TEXT NOT NULL,
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5)
    );

-- Создание таблицы платежей
CREATE TABLE IF NOT EXISTS payment (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES "user"(id),
    amount DECIMAL(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );


CREATE TABLE IF NOT EXISTS venue (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(255)
    );

-- Создание таблицы заказов (Order)
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES "user"(id),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2)
    );

-- Создание таблицы исполнителей (Performer)
CREATE TABLE IF NOT EXISTS performer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    genre VARCHAR(50)
    );
