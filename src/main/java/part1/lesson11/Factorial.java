package part1.lesson11;

import java.math.BigInteger;
import java.util.stream.IntStream;

/**
 * Класс рассчитывает факториал числа используя {@code Stream API}.
 * @autor Aleksey Danilchik
 */
public class Factorial {
    private Factorial() {}

    /**
     * Метод рассчитывает факториал числа.
     * @param n число, факториал которого необходимо рассчитать.
     * @return факториал числа.
     */
    public static BigInteger streamedParallel(int n) {
        if (n < 2)
            return BigInteger.valueOf(1);
        return IntStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger::multiply)
                .get();
    }
}
