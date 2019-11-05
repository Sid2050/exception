package part1.lesson02.task02;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Класс хранит массив целых чисел и выводит в консоль те числа,
 * целая часть квадратного корня, возведённая в квадрат, которых равна числу.
 * @autor Aleksey Danilchik
 */
public class ArrayIntNumber {
    private static final Logger LOGGER = Logger.getLogger(ArrayIntNumber.class);

    private int[] array;

    /**
     * Конструктор - инициализирует массив и заполняет числами
     * @param n размер массива
     * @see ArrayIntNumber#ArrayIntNumber(int)
     */
    public ArrayIntNumber(int n) {
        array = new int[n];
        fillArrayWithRandomNumbers();
        LOGGER.info("Массив создан и заполнен");
    }

    /**
     * Метод заполняет массив числами от -1 до 99
     */
    private void fillArrayWithRandomNumbers() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100) - 1;
        }
    }

    /**
     * Метод выводит в консоль число, если целая часть квадратного
     * корня этого числа, возведённого в степень 2, равно этому числу.
     * Если встречается отрицательное число, то генерируется исключение.
     * @throws NegativeNumberException
     */
    public void calculateSquareOfNumber() throws NegativeNumberException {
        for (int x : array) {
            if (x < 0)
                throw new NegativeNumberException("Найдено отрицательное число");

            int square = (int) Math.floor(Math.sqrt(x));
            if (isIntegerPartIsEqualToTheNumber(x, square))
                System.out.println(x);
        }
        LOGGER.info("Удачное завершение метода calculateSquareOfNumber");
    }

    /**
     * Метод сравнивает число и квадрат целой части извлечённого корня из числа.
     * @param number число, из которого извлечён квадратный корень
     * @param integerPart целая часть квадратного корня
     * @return возвращает результат сравнения
     */
    private boolean isIntegerPartIsEqualToTheNumber(int number, int integerPart) {
        return number == Math.pow(integerPart, 2);
    }
}
