package part3.lesson15.ConnectionManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс-реализация получения объекта {@code Connection} к БД {@code MySQL} через {@code jdbc}-драйвер.
 * @autor Aleksey Danilchik
 */
@EJB
public class ConnetionManagerJdbcImpl implements ConnectionManager {
    private static ConnectionManager connectonManager;

    private static final Logger LOGGER = LogManager.getLogger(ConnetionManagerJdbcImpl.class);

    private ConnetionManagerJdbcImpl() {}

    /**
     * Статический метод получения объекта класса {@link ConnectionManager}.
     * @return объект {@code ConnetionManagerJdbcImpl}.
     */
    public static ConnectionManager getInstance() {
        if (connectonManager == null) {
            connectonManager = new ConnetionManagerJdbcImpl();
            LOGGER.info("Метод getInstance. Объект создан");
        }
        LOGGER.info("Метод getInstance. Объект получен");
        return connectonManager;
    }

    /**
     * Статический метод получения объекта класса {@link Connection}.
     * @return объект {@code Connection}.
     */
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework?" +
                    "verifyServerCertificate=false&useSSL=false&requireSSL=false&" +
                    "useLegacyDatetimeCode=false&amp&serverTimezone=UTC",
                    "root", "qwerty");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Ошибка в методе getConnection", e);
        }
        LOGGER.info("Метод getConnection. Соединение установлено");
        return connection;
    }
}
