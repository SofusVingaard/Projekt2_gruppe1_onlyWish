package org.example.projekt2_gruppe1_onlywish.repository;


import org.example.projekt2_gruppe1_onlywish.config.InitData;
import org.example.projekt2_gruppe1_onlywish.model.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    /*@Autowired
    InitData initData;*/
    @Autowired
    private DataSource dataSource;

    public void saveCreateWishlist(Wishlist wishlist) {
        String sql = "INSERT INTO wishlist (user_id, name, description) VALUES (?, ?, ?)";


        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, wishlist.getUserId());
            statement.setString(2, wishlist.getName());
            statement.setString(3, wishlist.getDescription());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public Wishlist getWishlistbyName(String name, int userId){
        String sql = "SELECT * FROM wishlist WHERE name = ? AND user_id = ?";
        Wishlist wishlist = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, name);
            statement.setInt(2, userId);

            try(ResultSet resultSet= statement.executeQuery()){
                if(resultSet.next()){
                    wishlist = new Wishlist();
                    wishlist.setId(resultSet.getInt("id"));
                    wishlist.setUserId(resultSet.getInt("user_id"));
                    wishlist.setName(resultSet.getString("name"));
                    wishlist.setDescription(resultSet.getString("description"));
                }
                }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return wishlist;

    }

    public void deleteWishlist( String name, int userId) {
        String sql= "DELETE FROM wishlist WHERE name = ? AND user_id = ?";

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, name);
            statement.setInt(2, userId);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public List<Wishlist> getWishlistsByUserId(int userId) {
        String sql = "SELECT * FROM wishlists WHERE user_id = ?";
        List<Wishlist> wishlists = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setId(resultSet.getInt("id"));
                    wishlist.setUserId(resultSet.getInt("user_id"));
                    wishlist.setName(resultSet.getString("name"));
                    wishlist.setDescription(resultSet.getString("description"));
                    // Add any other wishlist fields from your database

                    wishlists.add(wishlist);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // In production, consider throwing a custom exception
            // throw new DataAccessException("Error finding wishlists by user ID", e);
        }

        return wishlists;
    }
}
