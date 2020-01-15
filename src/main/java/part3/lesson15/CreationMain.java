package part3.lesson15;

import part3.lesson15.ConnectionManager.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для создания таблиц и добавления тестовых данных.
 * @autor Aleksey Danilchik
 */
public class CreationMain {
    public static void main(String[] args) {
        try (Connection connection = ConnectionManagerJdbcImpl.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            dropTableDB(statement);

            createUser(statement);
            fillUser(statement);
            createRole(statement);
            fillRole(statement);
            createUserRole(statement);
            fillUserRole(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTableDB(Statement statement) {
        try {
            statement.execute("DROP TABLE IF EXISTS user, role, user_role");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createUser(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "CREATE TABLE user ("
                    +   "id INT NOT NULL AUTO_INCREMENT, "
                    +   "name VARCHAR(255) NULL, "
                    +   "birthday VARCHAR(45) NULL, "
                    +   "login_ID VARCHAR(45) NULL, "
                    +   "city VARCHAR(45) NULL, "
                    +   "email VARCHAR(45) NULL, "
                    +   "description VARCHAR(255) NULL, "
                    + "PRIMARY KEY (id)); "
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillUser(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "INSERT INTO user (name, birthday, login_ID, city, email, description) "
                    + "VALUES "
                    +   "('Alex', '1987/07/23', 'cat1987', 'Krasnodar', 'mail@yandex.ru', ''),"
                    +   "('John', '1988/02/20', 'dog1988', 'Rostov', 'mail@gmail.com', ''),"
                    +   "('Kite', '1990/01/01', 'pushinka', 'Moscow', 'mail@rambler.ru', ''),"
                    +   "('Doc', '1977/11/15', 'ded77', 'Krasnodar', 'mail77@yandex.ru', ''),"
                    +   "('Pol', '1987/07/16', 'polik', 'Krasnodar', 'mail87@yandex.ru', '');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createRole(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "CREATE TABLE role ("
                    +   "id INT NOT NULL AUTO_INCREMENT, "
                    +   "name VARCHAR(45) NOT NULL CHECK "
                    +   "(name IN ('Administration', 'Clients', 'Billing')), "
                    +   "description VARCHAR(255) NULL, "
                    + "PRIMARY KEY (id)); "
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillRole(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "INSERT INTO role (name, description) "
                    + "VALUES "
                    +   "('Administration', ''),"
                    +   "('Clients', ''),"
                    +   "('Billing', '');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createUserRole(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "CREATE TABLE user_role ("
                    +   "id INT NOT NULL AUTO_INCREMENT, "
                    +   "user_id INT NULL, "
                    +   "role_id INT NULL, "
                    + "PRIMARY KEY (id), "
                    + "CONSTRAINT user_id "
                    +   "FOREIGN KEY (user_id) "
                    +   "REFERENCES user (id) "
                    +   "ON DELETE CASCADE "
                    +   "ON UPDATE CASCADE, "
                    + "CONSTRAINT role_id "
                    +   "FOREIGN KEY (role_id) "
                    +   "REFERENCES role (id) "
                    +   "ON DELETE CASCADE "
                    +   "ON UPDATE CASCADE); "
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void fillUserRole(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "INSERT INTO user_role (user_id, role_id) "
                    + "VALUES "
                    +   "(1, 1),"
                    +   "(2, 2),"
                    +   "(3, 2),"
                    +   "(4, 3),"
                    +   "(5, 3);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
