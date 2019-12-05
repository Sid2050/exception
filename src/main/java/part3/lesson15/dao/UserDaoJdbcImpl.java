package part3.lesson15.dao;

import part3.lesson15.ConnectionManager.ConnectionManager;
import part3.lesson15.ConnectionManager.ConnetionManagerJdbcImpl;
import part3.lesson15.pojo.User;

import java.sql.*;
import java.util.List;

/**
 * Класс-реализация работы с объектом {@code User}.
 * @autor Aleksey Danilchik
 */
public class UserDaoJdbcImpl implements UserDao {
    private static ConnectionManager connectionManager =
            ConnetionManagerJdbcImpl.getInstance();

    /**
     * Метод добавляет объект {@code User} в базу данных.
     * @param user объект класса {@link User}.
     * @return {@code true}, если данные успешно добавлены в таблицу,
     *          {@code false} в противном случае.
     */
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
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Метод добавляет объект {@code User} в базу данных используя пакетную вставку.
     * @param list список объектов {@code User}.
     * @return {@code true}, если данные успешно добавлены в таблицу,
     *          {@code false} в противном случае.
     */
    @Override
    public boolean addUsersBatch(List<User> list) {
        try (Connection connection = connectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)");
            for (User user : list) {
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getBirthday());
                preparedStatement.setString(3, user.getLoginId());
                preparedStatement.setString(4, user.getCity());
                preparedStatement.setString(5, user.getEmail());
                preparedStatement.setString(6, user.getDescription());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Метод получает объект {@code User} из базы данных по {@code id}.
     * @param id индекс записи в базе данных.
     * @return объект типа {@link User}.
     */
    @Override
    public User getUserById(Integer id) {
        try (Connection connection = connectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                preparedStatement.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод получает объект {@code User} из базы данных по {@code loginId} и {@code name}.
     * @param loginId {@code loginId} объекта.
     * @param name {@code name} объекта.
     * @return объект типа {@link User}.
     */
    @Override
    public User getUserByLoginIdAndName(String loginId, String name) {
        try (Connection connection = connectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM user WHERE login_ID = ? AND name = ?");
            preparedStatement.setString(1, loginId);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                preparedStatement.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
