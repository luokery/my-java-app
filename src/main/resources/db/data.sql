DELETE FROM product WHERE 1=1;
INSERT INTO product (id, name, price, stock, create_time) VALUES
('pd0t00001', 'Product A', 10.00, 100, '2025-11-20 16:47:23'),
('pd0t00002', 'Product B', 20.00, 200, '2025-11-20 16:47:23'),
('pd0t00003', 'Product C', 30.00, 300, '2025-11-20 16:47:23');

DELETE FROM customer WHERE 1=1;
INSERT INTO customer (id, username, nickname, email, password, gender, birth_date, status, created_at) VALUES
('cr0t00001', 'user1', 'User One', 'user1@example.com', 'pass1', 'Male', '1990-01-01', 'ACTIVE', '2025-11-20 16:47:23'),
('cr0t00002', 'user2', 'User Two', 'user2@example.com', 'pass2', 'Female', '1992-02-02', 'ACTIVE', '2025-11-20 16:47:23'),
('cr0t00003', 'user3', 'User Three', 'user3@example.com', 'pass3', 'Male', '1994-03-03', 'INACTIVE', '2025-11-20 16:47:23');
