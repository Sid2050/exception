package part3.lesson15.ConnectionManager;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
public class ConnectionManagerJdbcImpl implements ConnectionManager {
    private static ConnectionManager connectionManager;
    private static String driverDB;
    private static String userName;
    private static String password;
    private static String url;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionManagerJdbcImpl.class);

    private static final String PATH_PROP = "/part3.lesson15.ConnectionManager/file-propDB.properties";

    private ConnectionManagerJdbcImpl() {}

    /**
     * Статический метод получения объекта класса {@link ConnectionManager}.
     * @return объект {@code ConnectionManagerJdbcImpl}.
     */
    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerJdbcImpl();
            LOGGER.info("Метод getInstance. Объект создан");
        }
        LOGGER.info("Метод getInstance. Объект получен");
        return connectionManager;
    }

    /**
     * Статический метод получения объекта класса {@link Connection}.
     * @return объект {@code Connection}.
     */
    @Override
    public Connection getConnection() {
        ConnectionManagerJdbcImpl.getDataConnectionDb();
        Connection connection = null;
        try {
            Class.forName(driverDB);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("Ошибка в методе getConnection", e);
        }
        LOGGER.info("Метод getConnection. Соединение установлено");
        return connection;
    }

    private static void getDataConnectionDb() {
        Properties properties = new Properties();
        try (InputStream inputStream = connectionManager.getClass().getResourceAsStream(PATH_PROP)) {
            properties.load(inputStream);

            driverDB = properties.getProperty("driverDB");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (IOException e) {
            LOGGER.error("Ошибка в методе getDataConnectionDb", e);
        }
        LOGGER.info("Метод getDataConnectionDb. Успешно прочитаны данные");
    }
}
