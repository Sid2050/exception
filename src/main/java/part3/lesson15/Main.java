package part3.lesson15;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import part3.lesson15.ConnectionManager.ConnectionManager;
import part3.lesson15.ConnectionManager.ConnectionManagerFactory;
import part3.lesson15.ConnectionManager.ConnetionManagerJdbcImpl;
import part3.lesson15.dao.UserDao;
import part3.lesson15.dao.UserDaoJdbcImpl;
import part3.lesson15.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ConnectionManagerFactory managerFactory = new ConnectionManagerFactory();
        ConnectionManager connectionManager = managerFactory.create("jdbc");

        // Сделать параметризированную выборку по login_ID и name одновременно
        User user = getUserByLoginIdAndName(connectionManager,"ded77", "Doc");
        System.out.println(user);
    }

    private static User getUserByLoginIdAndName(ConnectionManager connectionManager, String loginId, String name) {
        UserDao userDao = new UserDaoJdbcImpl(connectionManager);
        return userDao.getUserByLoginIdAndName(loginId, name);
    }
}
