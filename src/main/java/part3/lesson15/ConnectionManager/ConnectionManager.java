package part3.lesson15.ConnectionManager;

import java.sql.Connection;

/**
 * Интерфейс. Шаблон для реализации получения объекта {@code Connection}.
 * @autor Aleksey Danilchik
 */
public interface ConnectionManager {
    Connection getConnection();
}
