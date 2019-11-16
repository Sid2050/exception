package part1.lesson06.task01.comparators;

import java.util.Comparator;

/**
 * Класс-компаратор. Сравнивает объекты типа {@code String} игнорируя регистр.
 * @autor Aleksey Danilchik
 */
public class IgnoreCaseComparator implements Comparator<String> {
    /**
     * Метод сравнивает два объекта типа {@code String} игнорируя регистр букв.
     * @param o1 объект типа {@code String}, который необходимо сравнить.
     * @param o2 объект типа {@code String}, с которым необходимо сравнить.
     * @return -1, 0 или 1, если объект {@code o1} больше, равен или меньше
     *         объекта {@code o2}.
     */
    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }
}
