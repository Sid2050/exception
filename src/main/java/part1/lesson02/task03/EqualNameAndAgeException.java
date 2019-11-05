package part1.lesson02.task03;

/**
 * Класс-исключение возникающее, если было найдено два объекта
 * с одинаковым именем и возрастом
 * @autor Aleksey Danilchik
 */
public class EqualNameAndAgeException extends Exception {
    public EqualNameAndAgeException(String message) {
        super(message);
    }
}
