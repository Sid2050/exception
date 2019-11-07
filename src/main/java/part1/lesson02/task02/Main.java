package part1.lesson02.task02;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Основной класс
 * @autor Aleksey Danilchik
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Задание 2. Программа запущена");
        int[] array = ArrayIntNumber.fillArrayWithRandomNumbers(50);
        ArrayIntNumber arrayIntNumber = new ArrayIntNumber(array);
        List<Integer> list;
        try {
            list = arrayIntNumber.calculateSquareOfNumber();
            list.forEach(i -> System.out.println(i));
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Задание 2. Завершение работы программы");
    }
}
