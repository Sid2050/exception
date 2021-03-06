package part1.lesson02.task03;

import org.apache.log4j.Logger;

/**
 * Класс сортирует объекты Person сортировкой методом выбора
 * @autor Aleksey Danilchik
 */
public class SelectSort implements Sort {
    private static Logger LOGGER = Logger.getLogger(SelectSort.class);
    private long timeSort;
    private Person[] persons;

    /**
     * Конструктор - инициализирует массив persons
     * @param persons массив объектов Person
     */
    public SelectSort(Person[] persons) {
        this.persons = persons;
    }

    /**
     * Метод сортирует массив, если {@code compareTo} возвращает 1.
     * @throws EqualNameAndAgeException, если встречаются два одинаковых объекта.
     */
    public void sortingObject() throws EqualNameAndAgeException {
        long start = System.currentTimeMillis();
        int max;
        for (int i = 0; i < persons.length; i++) {
            max = i;
            for (int j = i + 1; j < persons.length; j++) {
                Person p1 = persons[max];
                Person p2 = persons[j];
                if (p1.isEqualsNameAndAge(p2))
                    throw new EqualNameAndAgeException("Найдены объекты с одинаковым именем и возрастом");

                if (p1.compareTo(p2) == 1)
                    max = j;
            }
            swap(i, max);
        }
        timeSort = System.currentTimeMillis() - start;
        LOGGER.info("Массив успешно отсортирован методом выбора");
    }

    private void swap(int one, int two) {
        Person temp = persons[one];
        persons[one] = persons[two];
        persons[two] = temp;
    }

    /**
     * Метод выводит на экран коллекцию объектов
     */
    public void display() {
        for (Person p : persons) {
            System.out.println(p.toString());
        }
    }

    /**
     * Метод получения значения поля {@link SelectSort#timeSort}
     * @return время сортировки в наносекундах
     */
    public long getTimeSorting() {
        return timeSort;
    }
}
