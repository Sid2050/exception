package part3.lesson15.dao;

import part3.lesson15.pojo.User;

import java.util.List;

/**
 * Интерфейс.
 * @autor Aleksey Danilchik
 */
public interface UserDao {
    boolean addUser(User user);

    User getUserById(Integer id);

    User getUserByLoginId(String loginId);

    boolean updateUserById(User user);

    boolean deleteUserById(Integer id);

    void createTable();
}
