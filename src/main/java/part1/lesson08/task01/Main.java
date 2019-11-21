package part1.lesson08.task01;

import java.io.IOException;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple(13, "string", 999);
        System.out.println(simple);
        try {
            Serialization.convertObjectToXML(simple, "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Simple newSimple = (Simple) Serialization.convertXMLToObject("Simple.xml");
        System.out.println(newSimple);
    }
}
