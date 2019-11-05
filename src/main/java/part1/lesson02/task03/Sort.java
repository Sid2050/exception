package part1.lesson02.task03;

/**
 * Интерфейс представляет собой набор методов для любого класса
 * сортировки, реализующего данный интерфейс.
 * @author Aleksey Danilchik
 */
public interface Sort {
    void sortingObject() throws EqualNameAndAgeException;
    long getTimeSorting();
    void display();
}
