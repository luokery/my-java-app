INSERT INTO product (name, price, stock, create_time) VALUES
('Product A', 10.00, 100, NOW()),
('Product B', 20.00, 200, NOW()),
('Product C', 30.00, 300, NOW());

INSERT INTO customer (username, nickname, email, password, gender, birth_date, status, created_at) VALUES
('user1', 'User One', 'user1@example.com', 'pass1', 'Male', '1990-01-01', 'ACTIVE', NOW()),
('user2', 'User Two', 'user2@example.com', 'pass2', 'Female', '1992-02-02', 'ACTIVE', NOW()),
('user3', 'User Three', 'user3@example.com', 'pass3', 'Male', '1994-03-03', 'INACTIVE', NOW());
