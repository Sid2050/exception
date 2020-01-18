package part3.lesson15.create;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @autor Aleksey Danilchik
 */
public class FillTable {
    public void fillUser(Statement statement) {
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

    public void fillRole(Statement statement) {
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

    public void fillUserRole(Statement statement) {
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
