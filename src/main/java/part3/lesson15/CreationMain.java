package part3.lesson15;

import part3.lesson15.ConnectionManager.ConnetionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @autor Aleksey Danilchik
 */
public class CreationMain {
    public static void main(String[] args) {
        try (Connection connection = ConnetionManagerJdbcImpl.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            createAndFillUserRole(statement);
            createAndFillUser(statement);
            createAndFillRole(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createAndFillUser(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
//                    + "DROP TABLE user_role; \n"
                    + "DROP TABLE user; \n"
                    + "CREATE TABLE user ("
                    +   "id INT NOT NULL AUTO_INCREMENT, "
                    +   "name VARCHAR(255) NULL, "
                    +   "birthday VARCHAR(45) NULL, "
                    +   "login_ID VARCHAR(45) NULL, "
                    +   "city VARCHAR(45) NULL, "
                    +   "email VARCHAR(45) NULL, "
                    +   "description VARCHAR(255) NULL, "
                    + "PRIMARY KEY (id));"
                    + "\n"
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

    private static void createAndFillRole(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "DROP TABLE role;"
                    + "CREATE TABLE role (\n"
                    +   "id INT NOT NULL AUTO_INCREMENT,\n"
                    +   "name VARCHAR(45) NOT NULL CHECK "
                    +   "(name IN ('Administration', 'Clients', 'Billing')),\n"
                    +   "description VARCHAR(255) NULL,\n"
                    + "PRIMARY KEY (id));"
                    + "\n"
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

    private static void createAndFillUserRole(Statement statement) {
        try {
            statement.execute("-- Database: homework\n"
                    + "DROP TABLE user_role;"
                    + "CREATE TABLE user_role (\n"
                    +   "id INT NOT NULL AUTO_INCREMENT,\n"
                    +   "user_id INT NULL,\n"
                    +   "role_id INT NULL,\n"
                    + "PRIMARY KEY (id),\n"
                    + "CONSTRAINT user_id\n"
                    +   "FOREIGN KEY (user_id)\n"
                    +   "REFERENCES user (id)\n"
                    +   "ON DELETE CASCADE\n"
                    +   "ON UPDATE CASCADE,\n"
                    + "CONSTRAINT role_id\n"
                    +   "FOREIGN KEY (role_id)\n"
                    +   "REFERENCES role (id)\n"
                    +   "ON DELETE CASCADE\n"
                    +   "ON UPDATE CASCADE);"
//                    + "\n"
//                    + "INSERT INTO user_role (user_id, role_id) "
//                    + "VALUES "
//                    +   "(1, 1),"
//                    +   "(2, 2),"
//                    +   "(3, 2),"
//                    +   "(4, 3),"
//                    +   "(5, 3);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
