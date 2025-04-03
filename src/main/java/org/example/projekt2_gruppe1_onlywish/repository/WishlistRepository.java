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

@Repository
public class WishlistRepository {
    @Autowired
    InitData initData;
    @Autowired
    private DataSource dataSource;

    public void saveWishlist(Wishlist wishlist) {
        String sql = "INSERT INTO wishlist (user, name, description) VALUES (?, ?, ?)";


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
        Wishlist wishlist = new Wishlist();
        String sql = "SELECT * FROM wishlist WHERE name = ? AND user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, name);
            statement.setInt(2, userId);

            try(ResultSet resultSet= statement.executeQuery()){
                if(resultSet.next()){
                    wishlist.setId(resultSet.getInt("id"));
                    wishlist.setUserId(resultSet.getInt("user_id"));
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
}
