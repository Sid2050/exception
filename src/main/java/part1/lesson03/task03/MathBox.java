package part1.lesson03.task03;

import part1.lesson03.task03.exceptions.TryAddObjectException;

import java.util.*;

/**
 * Класс принимает массив объектов типа {@code T extends Number}, заполняет коллекцию {@code HashSet},
 * исключая дубликаты. В данном классе реализованы методы:
 * <ul>
 * <li>{@link MathBox#summator()} - возвращает сумму всех чисел коллекции</li>
 * <li>{@link MathBox#splitter(int)} - делит все числа коллекции на делитель и возвращает результат</li>
 * <li>{@link MathBox#removeIntFromCollection(Integer)} - удаляет из коллекции значение типа {@code Integer}</li>
 * </ul>
 * @autor Aleksey Danilchik
 */
public class MathBox<T extends Number> extends ObjectBox {
    private UUID id;
    private Set<T> set;

    public MathBox() {
        id = UUID.randomUUID();
        set = new HashSet<>();
    }

    /**
     * Конструктор - инициализирует коллекцию и заполняет её данными из массива.
     * @param arr массив объектов типа {@code Number}.
     */
    public MathBox(T[] arr) throws TryAddObjectException {
        id = UUID.randomUUID();
        set = new HashSet<>();
        fillSet(arr);
    }

    private void fillSet(T[] arr) throws TryAddObjectException {
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
    }

    /**
     * Метод суммирует все объекты коллекции.
     * @return результат суммирования типа {@code double}.
     */
    public double summator() {
        double sum = 0;
        for (T number : set) {
            sum += number.doubleValue();
        }
        return sum;
    }

    /**
     * Метод делит все элементы коллекции на делитель.
     * @param devider делитель.
     * @return коллекция значений типа {@code Number}, которая является результатом
     *         деления на {@code devider}.
     */
    public List<Double> splitter(int devider) {
        List<Double> temp = new ArrayList<>();
        for (T number : set) {
            temp.add(number.doubleValue() / devider);
        }
        return temp;
    }

    /**
     * Метод удаляет значение типа {@link Integer} из коллекции.
     * @param integer значение, которое необходимо найти и удалить.
     * @return {@code true} если объект найден и удалён;
     *         {@code false} в остальных случаях.
     */
    public boolean removeIntFromCollection(Integer integer) {
        for (T number : set) {
            if (integer.equals(number)) {
                set.remove(number);
                return true;
            }
        }
        return false;
    }

    /**
     * Метод добавляет объект в коллекцию.
     * @param o объект, который необходимо добавить.
     * @return {@code true}, если объект добавлен.
     * @throws TryAddObjectException возникает при попытке положить объект не типа {@code Number} в коллекцию.
     */
    @Override
    public boolean addObject(Object o) throws TryAddObjectException {
        if (o instanceof Number)
            return super.addObject(o);
        else
            throw new TryAddObjectException("Попытка положить Object в коллекцию.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MathBox mathBox = (MathBox) o;

        if (id != null ? !id.equals(mathBox.id) : mathBox.id != null) return false;
        return set != null ? set.equals(mathBox.set) : mathBox.set == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
