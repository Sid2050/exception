package part1.lesson10.server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

/**
 * Класс-сервер.
 * @autor Aleksey Danilchik
 */
public class ChatServer extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ChatServer.class);
    private static final int PORT = 7331;
    private static final int BUFFER = 1024;

    private DatagramSocket socket;
    private Map<String, PortAndAddressClient> clientNames;

    public ChatServer() throws IOException {
        socket = new DatagramSocket(PORT);
        clientNames = new HashMap<>();
    }

    /**
     * В данном потоке принимаются и отправляются сообщения всем пользователям чата.
     */
    @Override
    public void run() {
        while (true) {
            try {
                byte[] buf = new byte[BUFFER];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String fullMessage = new String(buf, 0, buf.length);
                String name = getNameClient(fullMessage);
                String nameTo = getNameToClient(fullMessage);
                String message = getMessage(fullMessage);
                fillCollections(name, packet);

                System.out.println(fullMessage);

                if ("all".equals(nameTo))
                    sendMessageAll((name + ": " + message).getBytes());

                if (clientNames.containsKey(nameTo)) {
                    PortAndAddressClient portAndAddressClient = clientNames.get(nameTo);
                    sendMessage((name + ": " + message).getBytes(), portAndAddressClient);
                }

            } catch(IOException e) {
                LOGGER.error("Ошибка при работе сервера.", e);
            }
        }
    }

    private static String getNameClient(String message) {
        return message.substring(0, message.indexOf('|'));
    }

    private static String getNameToClient(String message) {
        return message.substring(message.indexOf('|') + 1, message.indexOf(':'));
    }

    private static String getMessage(String message) {
        return message.substring(message.indexOf(':') + 1).trim();
    }

    private void fillCollections(String name, DatagramPacket packet) {
        InetAddress clientAddress = packet.getAddress();
        int clientPort = packet.getPort();

        if (!clientNames.containsKey(name)) {
            PortAndAddressClient portAndAddressClient = new PortAndAddressClient(clientPort, clientAddress);
            clientNames.put(name, portAndAddressClient);
        }
    }

    private void sendMessageAll(byte[] data) throws IOException {
        for (Map.Entry<String, PortAndAddressClient> entry : clientNames.entrySet()) {
            PortAndAddressClient portAndAddressClient = entry.getValue();
            InetAddress cl = portAndAddressClient.getInetAddress();
            int cp = portAndAddressClient.getPort();
            DatagramPacket packet = new DatagramPacket(data, data.length, cl, cp);
            socket.send(packet);
        }
    }

    private void sendMessage(byte[] data, PortAndAddressClient portAndAddressClient) throws IOException {
        InetAddress cl = portAndAddressClient.getInetAddress();
        int cp = portAndAddressClient.getPort();
        DatagramPacket packet = new DatagramPacket(data, data.length, cl, cp);
        socket.send(packet);
    }

    public static void main(String[] args) throws Exception {
        ChatServer s = new ChatServer();
        s.start();
    }
}
