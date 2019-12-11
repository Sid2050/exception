package part3.lesson15.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import part3.lesson15.ConnectionManager.ConnectionManager;
import part3.lesson15.ConnectionManager.ConnetionManagerJdbcImpl;
import part3.lesson15.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UserDaoJdbcImplTest {
    private UserDao userDao;
    private ConnectionManager connectionManager;
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() {
        initMocks(this);
        connectionManager = spy(ConnetionManagerJdbcImpl.getInstance());
        connection = mock(Connection.class);
        userDao = new UserDaoJdbcImpl(connectionManager);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addUserTrue() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.INSERT_SQL)).thenReturn(preparedStatement);

        int id = 1;
        String name = "name";
        String birthday = "1900-01-01";
        String login_ID = "t01";
        String city = "city";
        String email = "email@mail.ru";
        String description = "description";

        User user = new User(id, name, birthday, login_ID, city, email, description);

        boolean result = userDao.addUser(user);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.INSERT_SQL);
        verify(preparedStatement, times(1)).setString(1, name);
        verify(preparedStatement, times(1)).setString(2, birthday);
        verify(preparedStatement, times(1)).setString(3, login_ID);
        verify(preparedStatement, times(1)).setString(4, city);
        verify(preparedStatement, times(1)).setString(5, email);
        verify(preparedStatement, times(1)).setString(6, description);
        verify(preparedStatement, times(1)).executeUpdate();
        assertTrue(result);
    }

    @Test
    void addUserFalse() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.INSERT_SQL)).thenThrow(new SQLException());

        boolean result = userDao.addUser(new User());

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.INSERT_SQL);
        assertFalse(result);
    }

    @Test
    void getUserByIdTrue() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.SELECT_SQL_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        int id = 1;
        String name = "name";
        String birthday = "1900-01-01";
        String login_ID = "t01";
        String city = "city";
        String email = "email@mail.ru";
        String description = "description";
        User user = new User(id, name, birthday, login_ID, city, email, description);

        when(resultSet.getInt(1)).thenReturn(id);
        when(resultSet.getString(2)).thenReturn(name);
        when(resultSet.getString(3)).thenReturn(birthday);
        when(resultSet.getString(4)).thenReturn(login_ID);
        when(resultSet.getString(5)).thenReturn(city);
        when(resultSet.getString(6)).thenReturn(email);
        when(resultSet.getString(7)).thenReturn(description);

        User user1 = userDao.getUserById(id);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.SELECT_SQL_BY_ID);
        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
        verify(resultSet,times(1)).getInt(1);
        verify(resultSet,times(1)).getString(2);
        verify(resultSet,times(1)).getString(3);
        verify(resultSet,times(1)).getString(4);
        verify(resultSet,times(1)).getString(5);
        verify(resultSet,times(1)).getString(6);
        verify(resultSet,times(1)).getString(7);
        assertEquals(user, user1);
    }

    @Test
    void getUserByIdFalse() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.SELECT_SQL_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(new SQLException());

        int id = 1;
        User user = userDao.getUserById(id);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.SELECT_SQL_BY_ID);
        verify(preparedStatement, times(1)).executeQuery();

        assertEquals(null, user);
    }

    @Test
    void updateUserByIdTrue() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.UPDATE_SQL_BY_ID)).thenReturn(preparedStatement);

        int id = 1;
        String name = "name";
        String birthday = "1900-01-01";
        String login_ID = "t01";
        String city = "city";
        String email = "email@mail.ru";
        String description = "description";
        User user = new User(id, name, birthday, login_ID, city, email, description);
        boolean result = userDao.updateUserById(user);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.UPDATE_SQL_BY_ID);
        verify(preparedStatement, times(1)).setString(1, user.getName());
        verify(preparedStatement, times(1)).setString(2, user.getBirthday());
        verify(preparedStatement, times(1)).setString(3, user.getLoginId());
        verify(preparedStatement, times(1)).setString(4, user.getCity());
        verify(preparedStatement, times(1)).setString(5, user.getEmail());
        verify(preparedStatement, times(1)).setString(6, user.getDescription());
        verify(preparedStatement, times(1)).setInt(7, user.getId());
        verify(preparedStatement, times(1)).executeUpdate();
        assertTrue(result);
    }

    @Test
    void updateUserByIdFalse() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.UPDATE_SQL_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException());

        int id = 1;
        String name = "name";
        String birthday = "1900-01-01";
        String login_ID = "t01";
        String city = "city";
        String email = "email@mail.ru";
        String description = "description";
        User user = new User(id, name, birthday, login_ID, city, email, description);
        boolean result = userDao.updateUserById(user);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.UPDATE_SQL_BY_ID);
        verify(preparedStatement, times(1)).setString(1, user.getName());
        verify(preparedStatement, times(1)).setString(2, user.getBirthday());
        verify(preparedStatement, times(1)).setString(3, user.getLoginId());
        verify(preparedStatement, times(1)).setString(4, user.getCity());
        verify(preparedStatement, times(1)).setString(5, user.getEmail());
        verify(preparedStatement, times(1)).setString(6, user.getDescription());
        verify(preparedStatement, times(1)).setInt(7, user.getId());
        verify(preparedStatement, times(1)).executeUpdate();
        assertFalse(result);
    }

    @Test
    void deleteUserByIdTrue() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.DELETE_SQL_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenReturn(true);

        int id  = 1;
        boolean result = userDao.deleteUserById(id);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.DELETE_SQL_BY_ID);
        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).execute();
        assertTrue(result);
    }

    @Test
    void deleteUserByIdFalse() throws SQLException {
        when(connectionManager.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(UserDaoJdbcImpl.DELETE_SQL_BY_ID)).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenThrow(new SQLException());

        int id  = 1;
        boolean result = userDao.deleteUserById(id);

        verify(connectionManager, times(1)).getConnection();
        verify(connection, times(1)).prepareStatement(UserDaoJdbcImpl.DELETE_SQL_BY_ID);
        verify(preparedStatement, times(1)).setInt(1, id);
        verify(preparedStatement, times(1)).execute();
        assertFalse(result);
    }
}