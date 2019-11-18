package part1.lesson07;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Основной класс. Генерирует массив чисел для рассчёта факториала.
 * @autor Aleksey Danilchik
 */
public class Main {
    static int[] array;
    static Random random = new Random();

    public static void main(String[] args) {
        array = fillArray();
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < array.length; i++) {
            RunPool pool = new RunPool(executorService);
            pool.start(array[i]);
        }
        executorService.shutdown();

        ConcurrentMap<Integer, BigInteger> map = RunPool.getMap();
        System.out.println(map);
    }


    private static int[] fillArray() {
        int[] temp = new int[50];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = random.nextInt(100);
        }
        return temp;
    }
}
