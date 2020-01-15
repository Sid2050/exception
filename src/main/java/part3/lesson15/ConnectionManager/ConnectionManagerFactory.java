package part3.lesson15.ConnectionManager;

/**
 * @autor Aleksey Danilchik
 */
public class ConnectionManagerFactory {
    public ConnectionManager create(String manager) {
        if ("jdbc".equals(manager)) {
            return ConnectionManagerJdbcImpl.getInstance();
        }
        return null;
    }
}
