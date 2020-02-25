package part1.lesson10.client;

import org.apache.log4j.Logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Класс принимает сообщения и выводит его на экран.
 * @autor Aleksey Danilchik
 */
public class MessageReceiver implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(MessageReceiver.class);
    private DatagramSocket socket;
    private byte[] buf;
    private String name;

    /**
     * Конструктор. Инициализирует переменные экземпляра.
     * @param socket объект класса {@link DatagramSocket}.
     */
    MessageReceiver(DatagramSocket socket, String name) {
        this.socket = socket;
        this.name = name;
        buf = new byte[1024];
    }

    /**
     * В данном потоке принимается сообщение от пользователей чата.
     */
    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                String message = getMessage(received);
                System.out.println(received);

                if ("quit".equals(message))
                    break;
            } catch(Exception e) {
                LOGGER.error("Ошибка при приёме сообщения.", e);
            }
        }
    }

    private static String getMessage(String message) {
        return message.substring(message.indexOf(':') + 1).trim();
    }
}
