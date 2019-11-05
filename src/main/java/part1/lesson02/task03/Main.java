package part1.lesson02.task03;

import org.apache.log4j.Logger;

/**
 * Основной класс. Создаёт массив объектов Person и сортирует их двумя разными способами.
 * @autor Aleksey Danilchik
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Задание 3. Программа запущена");
        GeneratePerson gp = new GeneratePerson(10);
        Person[] persons = gp.generationAndFillArray();

        BubbleSort bubbleSort = new BubbleSort(persons);
        try {
            bubbleSort.sortingObject();
            bubbleSort.display();
            System.out.println("Время сортировки массива пузырьком: " + bubbleSort.getTimeSorting() +
                    " наносекунд");
        } catch (EqualNameAndAgeException e) {
            System.out.println("Не удалось отсортировать массив методом выбора.");
            System.out.println(e.getMessage());
            LOGGER.error(e.getMessage());
        }

        System.out.println();

        SelectSort selectSort = new SelectSort(persons);
        try {
            selectSort.sortingObject();
            selectSort.display();
            System.out.println("Время сортировки массива методом выбора: " + selectSort.getTimeSorting() +
                                " наносекунд");
        } catch (EqualNameAndAgeException e) {
            System.out.println("Не удалось отсортировать массив методом выбора.");
            System.out.println(e.getMessage());
            LOGGER.error(e.getMessage());
        }
        LOGGER.info("Задание 3. Завершение работы программы");
    }
}
