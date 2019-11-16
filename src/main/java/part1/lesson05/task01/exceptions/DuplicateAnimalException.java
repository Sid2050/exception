package part1.lesson05.task01.exceptions;

/**
 * @autor Aleksey Danilchik
 */
public class DuplicateAnimalException extends Exception {
    public DuplicateAnimalException(String message) {
        super(message);
    }
}
