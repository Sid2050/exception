package part1.lesson03.task01;

import java.util.List;

/**
 * @autor Aleksey Danilchik
 */
public class Main {
    static Number[] numbers = new Number[10];

    public static void main(String[] args) {
        fillNumbers();
        MathBox mathBox = new MathBox(numbers);
        System.out.println("Сумма всех элементов коллекции в MathBox = " + mathBox.summator());

        List<Number> list = mathBox.splitter(2);
        System.out.println("Результат деления всех элементов коллекции в MathBox на 2:");
        list.forEach(System.out::println);
    }

    public static void fillNumbers() {
        numbers[0] = new Integer(3);
        numbers[1] = new Integer(3);
        numbers[2] = new Short((short) 30);
        numbers[3] = new Byte((byte)13);
        numbers[4] = new Double(50.12);
        numbers[5] = new Float(26.14F);
        numbers[6] = new Float(26.14F);
        numbers[7] = new Long(10L);
        numbers[8] = new Integer(10);
        numbers[9] = new Integer(10);
    }
}
