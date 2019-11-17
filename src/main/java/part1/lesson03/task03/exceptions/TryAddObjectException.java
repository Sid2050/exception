package part1.lesson03.task03.exceptions;

/**
 * Исключение, возникающее при попытке положить объект типа {@code Object}.
 * @autor Aleksey Danilchik
 */
public class TryAddObjectException extends Exception {
    public TryAddObjectException(String message) {
        super(message);
    }
}
