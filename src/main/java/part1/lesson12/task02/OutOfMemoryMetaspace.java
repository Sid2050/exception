package part1.lesson12.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * Основнрой класс. Генерирует ошибку {@code OutOfMemoryError} в Metaspace.
 * @autor Aleksey Danilchik
 */
public class OutOfMemoryMetaspace {
    private static List<Class<?>> list = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        LockSupport.parkNanos(10_000_000_000L);
        while (true) {
            MyClassLoader cl = new MyClassLoader();
            Class<?> myClass = cl.findClass("part1.lesson12.task02.MyClass");
            list.add(myClass);
        }
    }
}
