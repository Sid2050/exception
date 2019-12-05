package part3.lesson15;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import part3.lesson15.ConnectionManager.ConnectionManager;
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
    private static Logger logger = LogManager.getLogger(Main.class);
    private static final String INSERT_SQL = "INSERT INTO user "
            + "VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";

//    static {
//        PropertyConfigurator.configure("log4j.properties");
//    }

    public static void main(String[] args) {
        // Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
        // a) Используя параметризированный запрос
//        parameterizedQuery();

        // b) Используя batch процесс
//        parameterizedBatchQuery();

        // Сделать параметризированную выборку по login_ID и name одновременно
//        User user = getUserByLoginIdAndName("ded77", "Doc");
//        System.out.println(user);

        // Перевести connection в ручное управление транзакциями
        // a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
        // между sql операциями установить логическую точку сохранения(SAVEPOINT)
//        exampleGoodSavePoint();

        // б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
        // между sql операциями установить точку сохранения (SAVEPOINT A),
        // намеренно ввести некорректные данные на последней операции,
        // что бы транзакция откатилась к логической точке SAVEPOINT A
        exampleBadSavePoint();
    }

    private static void parameterizedQuery() {
        User user = new User(null, "Vasay", "1965-01-30",
                "vsevasay", "Karaganda", "ml@book.com",
                "Крутой чувак этот Вася!!!");
        UserDao userDao = new UserDaoJdbcImpl();
        userDao.addUser(user);
        System.out.println(userDao.getUserById(6));
    }

    private static void parameterizedBatchQuery() {
        List<User> list = new ArrayList<>();
        list.add(new User(null, "Stepan", "1970-01-30",
                "stepka70", "Magadan", "ml12@book.com",
                null));
        list.add(new User(null, "Gena", "1969-03-29",
                "genka29", "Sevastopol", "ml29@book.com",
                null));
        list.add(new User(null, "Gosha", "1999-01-30",
                "g99", "Piter", "g99@book.com",
                null));
        UserDao userDao = new UserDaoJdbcImpl();
        userDao.addUsersBatch(list);
        System.out.println(userDao.getUserById(7));
        System.out.println(userDao.getUserById(8));
        System.out.println(userDao.getUserById(9));
    }

    private static User getUserByLoginIdAndName(String loginId, String name) {
        UserDao userDao = new UserDaoJdbcImpl();
        return userDao.getUserByLoginIdAndName(loginId, name);
    }

    private static void exampleGoodSavePoint() {
        ConnectionManager connectionManager = ConnetionManagerJdbcImpl.getInstance();
        try (Connection connection = connectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_SQL)) {
                insertStatement.setString(1, "name1");
                insertStatement.setString(2, "2000-01-01");
                insertStatement.setString(3, "login1");
                insertStatement.setString(4, "city1");
                insertStatement.setString(5, "mail1");
                insertStatement.setString(6, null);
                insertStatement.executeUpdate();

                Savepoint savepoint = connection.setSavepoint("point1");

                insertStatement.setString(1, "name2");
                insertStatement.setString(2, "2000-01-02");
                insertStatement.setString(3, "login2");
                insertStatement.setString(4, "city2");
                insertStatement.setString(5, "mail2");
                insertStatement.setString(6, null);
                insertStatement.executeUpdate();

                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void exampleBadSavePoint() {
        ConnectionManager connectionManager = ConnetionManagerJdbcImpl.getInstance();
        Savepoint savepoint = null;
        Connection connection = null;
        try {
            connection = connectionManager.getConnection();
            connection.setAutoCommit(false);
            try (PreparedStatement insertStatement = connection.prepareStatement(INSERT_SQL)) {
                insertStatement.setString(1, "name3");
                insertStatement.setString(2, "2000-01-03");
                insertStatement.setString(3, "login3");
                insertStatement.setString(4, "city3");
                insertStatement.setString(5, "mail3");
                insertStatement.setString(6, null);
                insertStatement.executeUpdate();

                savepoint = connection.setSavepoint("point2");

                insertStatement.setString(1, "name4");
                insertStatement.setString(2, "2000-01-04");
                insertStatement.setString(3, "login4");
                insertStatement.setString(4, "city4");
                insertStatement.setString(5, "mail4");
                insertStatement.setString(6, null);
                insertStatement.executeUpdate();

                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.error("Ошибка метода exampleBadSavePoint", e);
            e.printStackTrace();
            try {
                connection.rollback(savepoint);
                connection.commit();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
