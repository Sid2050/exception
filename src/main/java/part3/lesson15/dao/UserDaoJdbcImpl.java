package part3.lesson15.dao;

import part3.lesson15.ConnectionManager.ConnectionManager;
import part3.lesson15.ConnectionManager.ConnetionManagerJdbcImpl;
import part3.lesson15.pojo.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @autor Aleksey Danilchik
 */
public class UserDaoJdbcImpl implements UserDao {
    private static ConnectionManager connectionManager =
            ConnetionManagerJdbcImpl.getInstance();

    @Override
    public boolean addUser(User user) {
        try (Connection connection = connectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBirthday());
            preparedStatement.setString(3, user.getLoginId());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
