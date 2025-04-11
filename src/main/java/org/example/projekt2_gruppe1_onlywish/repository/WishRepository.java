package org.example.projekt2_gruppe1_onlywish.repository;

import org.example.projekt2_gruppe1_onlywish.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class WishRepository {

    @Autowired
    private DataSource dataSource;

    public void save(Wish wish) {
        String sql = "INSERT INTO wishes (wishlist_id, name, description, price, url, product_link) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wish.getWishlistId());
            statement.setString(2, wish.getName());
            statement.setString(3, wish.getDescription());
            statement.setBigDecimal(4, wish.getPrice());
            statement.setString(5, wish.getImageUrl());
            statement.setString(6, wish.getProductlink());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM wishes WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wish getAllWish(int id) {
        return null;
    }

    public ArrayList<Wish> findByWishlistId(int wishlistId) {
        String sql = "SELECT * FROM wishes WHERE wishlist_id = ?";
        ArrayList<Wish> wishes = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, wishlistId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Wish wish = new Wish();
                wish.setId(rs.getInt("id"));
                wish.setWishlistId(rs.getInt("wishlist_id"));
                wish.setName(rs.getString("name"));
                wish.setDescription(rs.getString("description"));
                wish.setPrice(rs.getBigDecimal("price"));
                wish.setImageUrl(rs.getString("url"));
                wish.setProductlink(rs.getString("product_link"));
                wishes.add(wish);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishes;
    }
}
