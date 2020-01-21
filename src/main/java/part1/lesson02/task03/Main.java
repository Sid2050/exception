package part1.lesson02.task03;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Основной класс. Создаёт массив объектов Person и сортирует их двумя разными способами.
 * @autor Aleksey Danilchik
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Задание 3. Программа запущена");
        GeneratePerson gp = new GeneratePerson(20);
        Person[] persons = gp.generationAndFillArray();
        SimpleRemoteControl control = new SimpleRemoteControl();

        BubbleSort bubbleSort = new BubbleSort(Arrays.copyOf(persons, persons.length));
        control.setCommand(new SortingPersonCommand(bubbleSort));
        control.buttonWasPressed();
        control.setCommand(new DisplayPersonCommand(bubbleSort));
        control.buttonWasPressed();
        System.out.println("Время сортировки массива пузырьком: " + bubbleSort.getTimeSorting() +
                " наносекунд");

        System.out.println();

        SelectSort selectSort = new SelectSort(Arrays.copyOf(persons, persons.length));
        control.setCommand(new SortingPersonCommand(selectSort));
        control.buttonWasPressed();
        control.setCommand(new DisplayPersonCommand(selectSort));
        control.buttonWasPressed();
        System.out.println("Время сортировки массива методом выбора: " + selectSort.getTimeSorting() +
                            " наносекунд");

        LOGGER.info("Задание 3. Завершение работы программы");
    }
}
