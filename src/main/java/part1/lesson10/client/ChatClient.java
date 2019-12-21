package part1.lesson10.client;

import java.net.DatagramSocket;
import java.util.Scanner;

/**
 * Основной класс-клиент.
 * @autor Aleksey Danilchik
 */
public class ChatClient {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя: ");
        String name = scanner.nextLine();

        DatagramSocket socket = new DatagramSocket();
        MessageReceiver messageReceiver = new MessageReceiver(socket, name);
        MessageSender messageSender = new MessageSender(socket, name);
        new Thread(messageReceiver).start();
        new Thread(messageSender).start();
    }
}