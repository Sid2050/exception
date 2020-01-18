package part3.lesson15.create;

import part3.lesson15.ConnectionManager.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Класс для создания таблиц и добавления тестовых данных.
 * @autor Aleksey Danilchik
 */
public class CreationTable {
    public void dropTableDB(Statement statement) {
        try {
            statement.execute("DROP TABLE IF EXISTS user, role, user_role");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUser(Statement statement) {
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

    public void createRole(Statement statement) {
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

    public void createUserRole(Statement statement) {
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
}
