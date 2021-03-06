package part1.lesson03.task03;

import part1.lesson03.task03.exceptions.TryAddObjectException;

import java.util.HashSet;
import java.util.Set;

/**
 * Класс принимает объекты типа {@code Object}, исключая дубликаты. В данном классе реализованы методы:
 * <ul>
 * <li>{@link ObjectBox#addObject(Object)} - добавляет объект в коллекцию</li>
 * <li>{@link ObjectBox#deleteObject(Object)} - удаляет объект из коллекции</li>
 * <li>{@link ObjectBox#dump()} - возвращает содержимое коллекции типа {@code String}</li>
 * </ul>
 * @autor Aleksey Danilchik
 */
public class ObjectBox {
    private Set<Object> set;

    /**
     * Конструктор - инициализирует коллекцию.
     */
    public ObjectBox() {
        set = new HashSet<>();
    }

    /**
     * Метод добавляет объект в коллекцию.
     * @param o объект, который необходимо добавить
     * @return {@code true}, если объект был добавлен в коллекцию;
     *         {@code false} в остальных случаях.
     */
    public boolean addObject(Object o) throws TryAddObjectException {
        return set.add(o);
    }

    /**
     * Метод удаляет объект из коллекции, если он был найден.
     * @param o объект, который необходимо удалить.
     * @return {@code true}, если объект был удалён из коллекции;
     *         {@code false} в ином случае.
     */
    public boolean deleteObject(Object o) {
        if (set.contains(o)) {
            set.remove(o);
            return true;
        }
        return false;
    }

    /**
     * Метод выводит содержимое коллекции в строку.
     * @return содержимое коллекции типа {@code String}.
     */
    public String dump() {
        return "ObjectBox{" +
                "set=" + set +
                '}';
    }

    public Set<Object> getSet() {
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ObjectBox objectBox = (ObjectBox) o;

        return set != null ? set.equals(objectBox.set) : objectBox.set == null;
    }

    @Override
    public int hashCode() {
        return set != null ? set.hashCode() : 0;
    }
}
