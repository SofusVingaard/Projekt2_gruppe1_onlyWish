CREATE DATABASE IF NOT EXISTS onlywish;
USE onlywish;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100)NOT NULL,
                       age INT NOT NULL,
                       email VARCHAR(250) NOT NULL unique,
                       password VARCHAR(250) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE wishlists (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           user_id VARCHAR(250) NOT NULL,
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
                        url VARCHAR(500),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        product_link VARCHAR(255),
                        FOREIGN KEY (wishlist_id) REFERENCES wishlists(id) ON DELETE CASCADE
);

CREATE TABLE reservations (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              wish_id INT NOT NULL,
                              reserver_user_id INT NOT NULL,
                              reserved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              FOREIGN KEY (wish_id) REFERENCES wishes(id) ON DELETE CASCADE,
                              FOREIGN KEY (reserver_user_id) REFERENCES users(id) ON DELETE CASCADE
);

