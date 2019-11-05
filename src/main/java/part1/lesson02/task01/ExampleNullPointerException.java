package part1.lesson02.task01;

/**
 * Класс моделирует ошибку {@link NullPointerException}
 * @autor Aleksey Danilchik
 */
public class ExampleNullPointerException {

    String s;

    {
        if (s.equals("")) {
            s = "123";
        }
    }

    public static void main(String[] args) {
        ExampleNullPointerException exampleNullPointerException = new ExampleNullPointerException();
    }
}
