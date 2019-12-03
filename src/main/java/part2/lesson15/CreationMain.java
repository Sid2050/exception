package part2.lesson15;

import part2.lesson15.ConnectionManager.ConnetionManagerJdbcImpl;

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

            createAndFillUser(statement);
            createAndFillRole(statement);
            createAndFillUserRole(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createAndFillUser(Statement statement) {
        try {
            statement.execute("-- Database: user\n"
                    + "DROP TABLE IF EXISTS USER;"
                    + "CREATE TABLE user (\n"
                    +   "id INT NOT NULL,\n"
                    +   "name VARCHAR(255) NULL,\n"
                    +   "birthday DATETIME NULL,\n"
                    +   "login_ID VARCHAR(45) NULL,\n"
                    +   "city VARCHAR(45) NULL,\n"
                    +   "email VARCHAR(45) NULL,\n"
                    +   "description VARCHAR(255) NULL,\n"
                    + "PRIMARY KEY (id));"
                    + "\n"
                    + "INSERT INTO user (name, birthday, login_ID, city, email, description)\n"
                    + "VALUES\n"
                    +   "('Alex', '23/07/1987', 'cat1987', 'Krasnodar', 'mail@yandex.ru', '')\n"
                    +   "('John', '20/02/1988', 'dog1988', 'Rostov', 'mail@gmail.com', '')\n"
                    +   "('Kite', '01/01/1990', 'pushinka', 'Moscow', 'mail@rambler.ru', '')\n"
                    +   "('Doc', '15/11/1977', 'ded77', 'Krasnodar', 'mail77@yandex.ru', '')\n"
                    +   "('Pol', '16/07/1987', 'polik', 'Krasnodar', 'mail87@yandex.ru', '');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createAndFillRole(Statement statement) {
        try {
            statement.execute("-- Database: role\n"
                    + "DROP TABLE IF EXISTS role;"
                    + "CREATE TABLE role (\n"
                    +   "id INT NOT NULL,\n"
                    +   "name VARCHAR(45) NOT NULL CHECK "
                    +   "(name IN ('Administration', 'Clients', 'Billing')),\n"
                    +   "description VARCHAR(255) NULL,\n"
                    + "PRIMARY KEY (id));"
                    + "\n"
                    + "INSERT INTO role (name, description)\n"
                    + "VALUES\n"
                    +   "('Administration', '')\n"
                    +   "('Clients', '')\n"
                    +   "('Billing', '');"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createAndFillUserRole(Statement statement) {
        try {
            statement.execute("-- Database: user_role\n"
                    + "DROP TABLE IF EXISTS user_role;"
                    + "CREATE TABLE user_role (\n"
                    +   "id INT NOT NULL,\n"
                    +   "user_id INT NULL,\n"
                    +   "role_id INT NULL,\n"
                    + "PRIMARY KEY (id),\n"
                    + "INDEX user_id_idx (user_id ASC) VISIBLE,\n"
                    + "CONSTRAINT user_id\n"
                    +   "FOREIGN KEY (user_id)\n"
                    +   "REFERENCES user (id)\n"
                    +   "ON DELETE NO ACTION\n"
                    +   "ON UPDATE NO ACTION,\n"
                    + "CONSTRAINT role_id\n"
                    +   "FOREIGN KEY (role_id)\n"
                    +   "REFERENCES role (id)\n"
                    +   "ON DELETE NO ACTION\n"
                    +   "ON UPDATE NO ACTION);"
                    + "\n"
                    + "INSERT INTO user_role (user_id, role_id)\n"
                    + "VALUES \n"
                    +   "(1, 1)\n"
                    +   "(2, 2)\n"
                    +   "(3, 2)\n"
                    +   "(4, 3)\n"
                    +   "(5, 3);"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
