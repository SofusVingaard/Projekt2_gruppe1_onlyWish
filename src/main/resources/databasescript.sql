CREATE DATABASE IF NOT EXISTS onlywish;
USE onlywish;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100)NOT NULL,
                       age INT,
                       email VARCHAR(250) NOT NULL unique,
                       password VARCHAR(250) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wishlists (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id INT NOT NULL,
                           name VARCHAR(100) NOT NULL,
                           description varchar(250),
                           FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE wishes (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        wishlist_id INT NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        description TEXT,
                        price DECIMAL(10,2),
                        url VARCHAR(50000),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        product_link VARCHAR(255),
                        reserve BOOLEAN DEFAULT false,
                        FOREIGN KEY (wishlist_id) REFERENCES wishlists(id) ON DELETE CASCADE
);
ALTER TABLE wishes
    MODIFY url LONGTEXT;