package part3.lesson15.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    public static final String INSERT_SQL = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_SQL_BY_ID = "SELECT * FROM user WHERE id = ?";
    public static final String SELECT_SQL_BY_LOGIN_ID_AND_NAME = "SELECT * FROM user WHERE login_ID = ? AND name = ?";
    public static final String UPDATE_SQL_BY_ID = "UPDATE user SET name = ?, birthday = ?, login_ID = ?," +
                                                    "city = ?, email = ?, description = ? WHERE id = ?";
    public static final String DELETE_SQL_BY_ID = "DELETE FROM user WHERE id = ?";

    private static final Logger LOGGER = LogManager.getLogger(UserDaoJdbcImpl.class);
    private ConnectionManager connectionManager;

    public UserDaoJdbcImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Метод добавляет объект {@code User} в базу данных.
     * @param user объект класса {@link User}.
     * @return {@code true}, если данные успешно добавлены в таблицу,
     *          {@code false} в противном случае.
     */
    @Override
    public boolean addUser(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBirthday());
            preparedStatement.setString(3, user.getLoginId());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.executeUpdate();
            LOGGER.info("Метод addUser. Объект user добавлен.");
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе addUser", e);
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
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){
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
            LOGGER.info("Метод addUsersBatch. Объекты user добавлены.");
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе addUsersBatch", e);
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
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL_BY_ID)){
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                LOGGER.info("Метод getUserById. Объект user с id " + id + " - получен.");
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе getUserById", e);
        }
        LOGGER.info("Метод getUserById. Объект user с id " + id + " - не найден.");
        return user;
    }

    /**
     * Метод получает объект {@code User} из базы данных по {@code loginId} и {@code name}.
     * @param loginId {@code loginId} объекта.
     * @param name {@code name} объекта.
     * @return объект типа {@link User}.
     */
    @Override
    public User getUserByLoginIdAndName(String loginId, String name) {
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL_BY_LOGIN_ID_AND_NAME)){
            preparedStatement.setString(1, loginId);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                LOGGER.info("Метод getUserByLoginIdAndName. Объект user с login_Id " + loginId
                        + " и name " + name + " - получен.");
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе getUserByLoginIdAndName", e);
        }
        LOGGER.info("Метод getUserByLoginIdAndName. Объект user с login_Id " + loginId
                + " и name " + name + " - не найден.");
        return user;
    }

    /**
     * Метод обновляет объект {@code User} в базе данных.
     * @param user объект, на который необходимо обновить.
     * @return {@code true}, если объект успешно обновлен,
     *          {@code false} в противном случае.
     */
    @Override
    public boolean updateUserById(User user) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL_BY_ID)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBirthday());
            preparedStatement.setString(3, user.getLoginId());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.setInt(7, user.getId());
            LOGGER.info("Метод updateUserById. Обновлен " + preparedStatement.executeUpdate() + " объект.");
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе updateUserById", e);
            return false;
        }
        return true;
    }

    /**
     * Метод удаляет объект {@code User} из базы данных.
     * @param id индекс объекта.
     * @return {@code true}, если объект успешно удалён,
     *          {@code false} в противном случае.
     */
    @Override
    public boolean deleteUserById(Integer id) {
        try (Connection connection = connectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            LOGGER.info("Метод deleteUserById. Объект удалён.");
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе deleteUserById", e);
            return false;
        }
        return true;
    }
}
