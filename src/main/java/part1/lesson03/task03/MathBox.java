package part1.lesson03.task03;

import part1.lesson03.task03.exceptions.TryAddObjectException;

import java.util.*;

/**
 * Класс принимает массив объектов типа {@code Number}, заполняет коллекцию {@code HashSet},
 * исключая дубликаты. В данном классе реализованы методы:
 * <ul>
 * <li>{@link MathBox#summator()} - возвращает сумму всех чисел коллекции</li>
 * <li>{@link MathBox#splitter(int)} - делит все числа коллекции на делитель и возвращает результат</li>
 * <li>{@link MathBox#removeIntFromCollection(Integer)} - удаляет из коллекции значение типа {@code Integer}</li>
 * </ul>
 * @autor Aleksey Danilchik
 */
public class MathBox extends ObjectBox {
    private UUID id;
    private Set<Number> numberSet;

    public MathBox() {
        id = UUID.randomUUID();
        numberSet = new HashSet<>();
    }

    /**
     * Конструктор - инициализирует коллекцию и заполняет её данными из массива.
     * @param arr массив объектов типа {@code Number}.
     */
    public MathBox(Number[] arr) {
        id = UUID.randomUUID();
        numberSet = new HashSet<>();
        fillSet(arr);
    }

    private void fillSet(Number[] arr) {
        for (int i = 0; i < arr.length; i++) {
            numberSet.add(arr[i]);
        }
    }

    /**
     * Метод суммирует все объекты коллекции.
     * @return результат суммирования типа {@code double}.
     */
    public double summator() {
        double sum = 0;
        for (Number number : numberSet) {
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
    public List<Number> splitter(int devider) {
        List<Number> temp = new ArrayList<>();
        for (Number number : numberSet) {
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
        for (Number number : numberSet) {
            if (integer.equals(number)) {
                numberSet.remove(number);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addObject(Object o) throws TryAddObjectException {
        if (o instanceof Object)
            throw new TryAddObjectException("Попытка добавить Object");
        return numberSet.add((Number) o);
    }

    @Override
    public String dump() {
        return "MathBox{" +
                "numberSet=" + numberSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MathBox mathBox = (MathBox) o;

        if (id != null ? !id.equals(mathBox.id) : mathBox.id != null) return false;
        return numberSet != null ? numberSet.equals(mathBox.numberSet) : mathBox.numberSet == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
