package part3.lesson15.dao;

import part3.lesson15.pojo.User;

import java.util.List;

/**
 * Интерфейс.
 * @autor Aleksey Danilchik
 */
public interface UserDao {
    boolean addUser(User user);
    boolean addUsersBatch(List<User> list);
    User getUserById(Integer id);
    User getUserByLoginIdAndName(String loginId, String name);
}
