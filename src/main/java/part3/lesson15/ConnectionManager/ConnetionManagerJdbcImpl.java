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
    private static String driverDB;
    private static String userName;
    private static String password;
    private static String url;

    private static final Logger LOGGER = LogManager.getLogger(ConnetionManagerJdbcImpl.class);

    private static final String PATH_PROP = "/part3.lesson15.ConnectionManager/file-propDB.properties";

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
        ConnetionManagerJdbcImpl.getDataConnectionDb();
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
        try (InputStream inputStream = connectonManager.getClass().getResourceAsStream(PATH_PROP)) {
            properties.load(inputStream);

            driverDB = properties.getProperty("driverDB");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
//            driverDB = "com.mysql.cj.jdbc.Driver";
//            userName = "root";
//            password = "qwerty";
//            url = "jdbc:mysql://host.docker.internal:3306/homework?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
        } catch (IOException e) {
            LOGGER.error("Ошибка в методе getDataConnectionDb", e);
        }
        LOGGER.info("Метод getDataConnectionDb. Успешно прочитаны данные");
    }
}
