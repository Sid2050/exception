package part1.lesson10.server;

import java.net.InetAddress;

/**
 * Класс для хранения данных о клиенте чата.
 * @autor Aleksey Danilchik
 */
public class PortAndAddressClient {
    private int port;
    private InetAddress inetAddress;

    public PortAndAddressClient(int port, InetAddress inetAddress) {
        this.port = port;
        this.inetAddress = inetAddress;
    }

    public int getPort() {
        return port;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }
}
