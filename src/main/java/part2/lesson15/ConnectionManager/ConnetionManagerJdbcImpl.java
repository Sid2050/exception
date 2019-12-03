package part2.lesson15.ConnectionManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @autor Aleksey Danilchik
 */
public class ConnetionManagerJdbcImpl implements ConnectonManager {
    private static ConnectonManager connectonManager;
    private static String driverDB;
    private static String userName;
    private static String password;
    private static String url;

    private static final String PATH_PROP = "./resources/file-propDB.properties";

    static {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(PATH_PROP)) {
            properties.load(inputStream);

            driverDB = properties.getProperty("driverDB");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConnetionManagerJdbcImpl() {}

    public static ConnectonManager getInstance() {
        if (connectonManager == null) {
            connectonManager = new ConnetionManagerJdbcImpl();
        }
        return connectonManager;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driverDB);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
