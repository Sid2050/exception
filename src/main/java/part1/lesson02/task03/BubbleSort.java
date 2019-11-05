package part1.lesson02.task03;

import org.apache.log4j.Logger;

/**
 * Класс сортирует объекты Person пузырьковой сортировкой
 * @autor Aleksey Danilchik
 */
public class BubbleSort implements Sort {
    private static Logger LOGGER = Logger.getLogger(BubbleSort.class);
    private long timeSort;
    private Person[] persons;

    /**
     * Конструктор - инициализирует массив persons
     * @param persons массив объектов Person
     */
    public BubbleSort(Person[] persons) {
        this.persons = persons;
    }

    /**
     * Метод сортирует массив по следующим правилам:
     * <ul>
     * <li>Первые идут мужчины</li>
     * <li>Выше в списке тот, кто более старший</li>
     * <li>Имена сортируются по алфавиту</li>
     * </ul>
     * @throws EqualNameAndAgeException
     */
    public void sortingObject() throws EqualNameAndAgeException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < persons.length; i++) {
            for (int j = i + 1; j < persons.length; j++) {
                Person p1 = persons[i];
                Person p2 = persons[j];
                if (p1.isEqualsNameAndAge(p2))
                    throw new EqualNameAndAgeException("Найдены объекты с одинаковым именем и возрастом");

                if (p1.isWoman() && p2.isMan()) {
                    swap(i, j);
                } else if (p1.isMan() && p2.isMan()) {
                    sortingAgeOrName(i, j, p1, p2);
                } else if (p1.isWoman() && p2.isWoman()) {
                    sortingAgeOrName(i, j, p1, p2);
                }
            }
        }
        timeSort = System.currentTimeMillis() - start;
        LOGGER.info("Массив успешно отсортирован методом пузырька");
    }

    private void sortingAgeOrName(int i, int j, Person p1, Person p2) {
        if (p1.getAge() < p2.getAge()) {
            swap(i, j);
        } else if (p1.getAge() == p2.getAge()) {
            if (p1.getName().compareTo(p2.getName()) > 0) {
                swap(i, j);
            }
        }
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
     * Метод получения значения поля {@link BubbleSort#timeSort}
     * @return время сортировки в наносекундах
     */
    public long getTimeSorting() {
        return timeSort;
    }
}
