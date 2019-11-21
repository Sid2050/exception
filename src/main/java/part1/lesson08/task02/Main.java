package part1.lesson08.task02;

import java.io.IOException;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple(13, "string", 999, new InnerWithSimple(16, "inner string", 666));
        System.out.println(simple);
        try {
            Serialization.convertObjectToXML(simple, "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Simple newSimple = (Simple) Serialization.convertXMLToObject("Simple.xml");
        System.out.println(newSimple);
        System.out.println(simple.equals(newSimple));
    }
}
