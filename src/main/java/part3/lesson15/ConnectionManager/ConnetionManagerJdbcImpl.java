package part3.lesson15.ConnectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @autor Aleksey Danilchik
 */
public class ConnetionManagerJdbcImpl implements ConnectionManager {
    private static ConnectionManager connectonManager;
    private static String driverDB;
    private static String userName;
    private static String password;
    private static String url;

    private static final String PATH_PROP = "/part3.lesson15.ConnectionManager/file-propDB.properties";

    private ConnetionManagerJdbcImpl() {}

    public static ConnectionManager getInstance() {
        if (connectonManager == null) {
            connectonManager = new ConnetionManagerJdbcImpl();
        }
        return connectonManager;
    }

    @Override
    public Connection getConnection() {
        ConnetionManagerJdbcImpl.getFillDataConnectionDb();
        Connection connection = null;
        try {
            Class.forName(driverDB);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void getFillDataConnectionDb() {
        Properties properties = new Properties();
        try (InputStream inputStream = connectonManager.getClass().getResourceAsStream(PATH_PROP)) {
            properties.load(inputStream);

            driverDB = properties.getProperty("driverDB");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
