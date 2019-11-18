package part1.lesson07;

import java.math.BigInteger;
import java.util.concurrent.*;

/**
 * Класс получает результаты расчёта факториала числа.
 * @autor Aleksey Danilchik
 */
public class RunPool {
    private static ConcurrentMap<Integer, BigInteger> map = new ConcurrentHashMap<>();
    private ExecutorService executorService;

    /**
     * Конструктор - инициализирует поле {@code ExecutorService}
     * @param executorService - объект типа {@code ExecutorService}
     */
    public RunPool(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * Метод добавляет задачу в пул задач и добавляет результат вычисления в коллекцию.
     * @param numb число, факториал которого необходимо рассчитать.
     */
    public void start(int numb) {
        Future<BigInteger> future = executorService.submit(new Factorial(numb));
        try {
            map.put(numb, future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
    }

    /**
     * Метод возвращает коллекцию с результатами вычисления.
     * @return коллекция результатов вычисления.
     */
    public static ConcurrentMap<Integer, BigInteger> getMap() {
        return map;
    }
}
