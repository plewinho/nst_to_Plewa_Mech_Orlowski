package com.main.casinoapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserData {
    public static User getAuthenticatedUser(String email, String password, Connection connection) {
        try {
            String sql = "SELECT * FROM user WHERE user_email=? AND user_password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int userBalance = resultSet.getInt("user_balance");
                String userNickname = resultSet.getString("user_nickname");
                int userAge = resultSet.getInt("user_age");
                User authenticatedUser = new User(userId, email, password);
                authenticatedUser.setBalance(userBalance);
                authenticatedUser.setNickname(userNickname);
                authenticatedUser.setAge(userAge);
                return authenticatedUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateBalanceInDatabase(int userId, int newBalance) {
        String sql = "UPDATE user SET user_balance = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = db.mycon().prepareStatement(sql)) {
            preparedStatement.setInt(1, newBalance);
            preparedStatement.setInt(2, userId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Aktualizacja salda nie powiodła się, żaden wiersz nie został zmieniony.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
