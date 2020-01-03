package part3.lesson15.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import part3.lesson15.ConnectionManager.ConnectionManager;
import part3.lesson15.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Класс-реализация работы с объектом {@code User}.
 * @autor Aleksey Danilchik
 */
public class UserDaoJdbcImpl implements UserDao {
    private static final String INSERT_SQL = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_SQL_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SELECT_SQL_BY_LOGIN_ID = "SELECT * FROM user WHERE login_ID = ?";
    private static final String UPDATE_SQL_BY_ID = "UPDATE user SET name = ?, birthday = ?, login_ID = ?," +
                                                    "city = ?, email = ?, description = ? WHERE id = ?";
    private static final String DELETE_SQL_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String CREATE_TABLE_USER
            = "DROP TABLE IF EXISTS user;\n"
            + "CREATE TABLE user ("
            +   "id INT NOT NULL AUTO_INCREMENT, "
            +   "name VARCHAR(255) NULL, "
            +   "birthday VARCHAR(45) NULL, "
            +   "login_ID VARCHAR(45) NULL, "
            +   "city VARCHAR(45) NULL, "
            +   "email VARCHAR(45) NULL, "
            +   "description VARCHAR(255) NULL, "
            + "PRIMARY KEY (id)); ";

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
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе addUser", e);
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
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе getUserById", e);
        }
        LOGGER.info("Метод getUserById. Объект user с id " + id + " - не найден.");
        return user;
    }

    /**
     * Метод получает объект {@code User} из базы данных по {@code loginId}.
     * @param loginId {@code loginId} объекта.
     * @return объект типа {@link User}.
     */
    @Override
    public User getUserByLoginId(String loginId) {
        User user = null;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL_BY_LOGIN_ID)){
            preparedStatement.setString(1, loginId);
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
                return user;
            }
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе getUserByLoginId", e);
        }
        LOGGER.info("Метод getUserByLoginIdAndName. Объект user с login_Id " + loginId + " - не найден.");
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
        } catch (SQLException e) {
            LOGGER.error("Ошибка в методе deleteUserById", e);
            return false;
        }
        return true;
    }

    @Override
    public void createTable() {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_USER)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Some thing wrong in createTable method", e);
        }
    }
}
