package part1.lesson02.task02;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
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
     * @param arr массив, над которым надо произвести вычисления
     * @see ArrayIntNumber#ArrayIntNumber(int[])
     */
    public ArrayIntNumber(int[] arr) {
        array = arr;
        LOGGER.info("Массив успешно передан в конструктор класса ArrayIntNumber");
    }

    /**
     * Метод создаёт и заполняет массив числами от -1 до 99
     * @param n размер массива
     * @return int[] temp - массив чисел
     */
    public static int[] fillArrayWithRandomNumbers(int n) {
        int[] temp = new int[n];
        Random random = new Random();
        for (int i = 0; i < temp.length; i++) {
            temp[i] = random.nextInt(100) - 1;
        }
        LOGGER.info("Массив успешно заполнен случайными числами");
        return temp;
    }

    /**
     * Метод создаёт список и заполняет его числами, если целая часть квадратного
     * корня этого числа, возведённого в степень 2, равно этому числу.
     * Если встречается отрицательное число, то генерируется исключение.
     * @return список чисел
     * @throws NegativeNumberException
     */
    public List<Integer> calculateSquareOfNumber() throws NegativeNumberException {
        List<Integer> list = new ArrayList<>();
        for (int x : array) {
            if (x < 0)
                throw new NegativeNumberException("Найдено отрицательное число");

            int square = (int) Math.floor(Math.sqrt(x));
            if (isIntegerPartIsEqualToTheNumber(x, square))
                list.add(x);
        }
        LOGGER.info("Удачное завершение метода calculateSquareOfNumber");
        return list;
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
