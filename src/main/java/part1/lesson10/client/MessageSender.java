package part1.lesson10.client;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Класс отправляет сообщение.
 * @autor Aleksey Danilchik
 */
public class MessageSender implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(MessageSender.class);
    private static final int PORT = 7331;
    private DatagramSocket socket;
    private String name;

    MessageSender(DatagramSocket socket, String name) {
        this.socket = socket;
        this.name = name;
    }

    private void sendMessage(String s) {
        try {
            byte[] buf = s.getBytes();
            InetAddress address = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
            socket.send(packet);
        } catch (IOException e) {
            LOGGER.error("Ошибка при отправке сообщения.", e);
        }
    }

    /**
     * В данном потоке считывается сообщение и отправляется на сервер.
     */
    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            try {
                while (!in.ready()) {
                    LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100));
                }
                String msg = in.readLine();
                sendMessage(getFullMessage(msg));
                if ("quit".equals(msg))
                    break;
            } catch(IOException e) {
                LOGGER.error("Ошибка при чтении сообщения.", e);
            }
        }
    }

    private String getFullMessage(String message) {
        if (message.indexOf(':') == -1)
            return name + "|all:" + message;

        return name + "|" + message;
    }
}
