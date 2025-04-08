package org.example.projekt2_gruppe1_onlywish.repository;

import org.example.projekt2_gruppe1_onlywish.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class WishRepository {

    @Autowired
    private DataSource dataSource;

    public ArrayList<Wish> getAllWish() {
        ArrayList<Wish> Wishlist = new ArrayList<>();
        String sql = "SELECT * FROM wish";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Wish wish = new Wish();
                wish.setId(resultSet.getInt("id"));
                wish.setImageUrl("image");
                wish.setProductlink("Link");
                wish.setPrice(resultSet.getBigDecimal("price"));
                wish.setDescription("description");
                wish.setName("name");
                //wish.setWishlist();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Wishlist;
    }

    public void save(Wish wish) {
        String sql = "INSERT INTO wish (name, wishlist, price, description) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wish.getId());
            statement.setString(2, wish.getName());
            statement.setString(3, wish.getDescription());
            statement.setBigDecimal(4, wish.getPrice());
            statement.setString(5, wish.getImageUrl());
            statement.setString(6, wish.getProductlink());

            //statement.set(, wish.getWishlist());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM wish WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Wish getWishById(int id) {
        Wish wish = null;
        String sql = "SELECT * FROM wish WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    wish = new Wish();
                    wish.setId(resultSet.getInt("id"));
                    wish.setImageUrl(resultSet.getString("image"));
                    wish.setProductlink(resultSet.getString("url"));
                    wish.setPrice(resultSet.getBigDecimal("price"));
                    wish.setDescription(resultSet.getString("description"));
                    wish.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wish;
    }

    public void update(Wish wish) {
        String sql = "UPDATE wish SET name = ?, description = ?, price = ?, url = ?, image = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {


            statement.setString(1, wish.getName());
            statement.setString(2, wish.getDescription());
            statement.setBigDecimal(3, wish.getPrice());
            statement.setString(4, wish.getImageUrl());
            statement.setString(5, wish.getProductlink());
            statement.setInt(6, wish.getId());
            //statement.set(, wish.getWishlist());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Wish getAllWish(int id) {
        return null;
    }

    public void reserveWish(int wishId) {
        String sql = "UPDATE wish SET reserved = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, true);
            statement.setInt(2, wishId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void unreserveWish(int wishId) {
        String sql = "UPDATE wish SET reserved = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, false);
            statement.setInt(2, wishId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void addContribution(int wishId, int userId, BigDecimal amount) {
        String sql = "INSERT INTO contribution (wish_id, user_id, amount) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishId);
            statement.setInt(2, userId);
            statement.setBigDecimal(3, amount);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public BigDecimal getTotalContributions(int wishId) {
        String sql = "SELECT SUM(amount) FROM contribution WHERE wish_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, wishId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }
    public BigDecimal getAmountMissing(int wishId) {
        Wish wish = getWishById(wishId);
        BigDecimal total = getTotalContributions(wishId);
        if (wish != null && wish.getPrice() != null) {
            return wish.getPrice().subtract(total != null ? total : BigDecimal.ZERO);
        }
        return BigDecimal.ZERO;
    }
}
