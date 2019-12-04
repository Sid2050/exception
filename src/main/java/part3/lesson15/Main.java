package part3.lesson15;

import part3.lesson15.dao.UserDao;
import part3.lesson15.dao.UserDaoJdbcImpl;
import part3.lesson15.pojo.User;

import java.text.ParseException;

/**
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        parameterizedQuery();
    }

    private static void parameterizedQuery() throws ParseException {
        User user = new User(null, "Vasay", "1965-01-30",
                "vsevasay", "Karaganda", "ml@book.com",
                "Крутой чувак этот Вася!!!");
        UserDao userDao = new UserDaoJdbcImpl();
        System.out.println(userDao.addUser(user));

    }
}
