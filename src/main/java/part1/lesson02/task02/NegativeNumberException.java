package part1.lesson02.task02;

/**
 * Класс исключения, возникающего, если было найдено отрицательное число
 * @autor Aleksey Danilchik
 */
public class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}
