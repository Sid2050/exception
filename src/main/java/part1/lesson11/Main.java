package part1.lesson11;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        int[] array = fillArray(10);

        Map<Integer, BigInteger> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], Factorial.streamedParallel(array[i]));
        }
        System.out.println(map);
    }

    private static int[] fillArray(int n) {
        int[] temp = new int[n];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = random.nextInt(50);
        }
        return temp;
    }


}
