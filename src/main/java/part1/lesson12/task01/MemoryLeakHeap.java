package part1.lesson12.task01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Основной класс. Генерирует ошибку {@code OutOfMemoryError} в Java heap space.
 * @autor Aleksey Danilchik
 */
public class MemoryLeakHeap {
    public static void main(String[] args) {
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(10));

        int size = 100;
        for (int i = 1; i < 100_000_000; i++) {
            int[] arr = new int[size];
            for (int j = size - 1; j > 1; j--) {
                arr[j] = j;
            }
            size *= 10;
        }
    }
}
