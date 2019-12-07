package part1.lesson07;

import java.math.BigInteger;
import java.util.concurrent.Callable;

/**
 * Класс рассчитывает факториал числа.
 * @autor Aleksey Danilchik
 */
public class Factorial implements Callable<BigInteger> {
    int numb;

    /**
     * Конструктор - инициализирует поле {@code numb}.
     * @param numb - число, факториал которогу необходимо рассчитать.
     */
    public Factorial(int numb) {
        this.numb = numb;
    }

    /**
     * Метод рассчитывает факториал числа.
     * @return факториал числа типа {@code BigInteger}.
     * @throws Exception
     */
    @Override
    public BigInteger call() throws Exception {
        BigInteger bigInteger = BigInteger.valueOf(1);
        for (int i = 2; i <= numb; i++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }
        return bigInteger;
    }
}
