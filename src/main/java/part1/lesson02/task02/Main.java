package part1.lesson02.task02;

import org.apache.log4j.Logger;

/**
 * Основной класс
 * @autor Aleksey Danilchik
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Задание 2. Программа запущена");
        ArrayIntNumber arrayIntNumber = new ArrayIntNumber(50);
        try {
            arrayIntNumber.calculateSquareOfNumber();
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Задание 2. Завершение работы программы");
    }
}
