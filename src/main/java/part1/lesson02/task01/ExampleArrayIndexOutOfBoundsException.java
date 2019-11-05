package part1.lesson02.task01;

/**
 * Класс моделирует ошибку {@link ArrayIndexOutOfBoundsException}
 * @autor Aleksey Danilchik
 */
public class ExampleArrayIndexOutOfBoundsException {
    private int[] array = {1, 2, 3, 4, 5};

    public static void main(String[] args) {
        ExampleArrayIndexOutOfBoundsException exampleArrayIndexOutOfBoundsException = new ExampleArrayIndexOutOfBoundsException();
        exampleArrayIndexOutOfBoundsException.generateArrayIndexOutOfBoundsException();
    }

    /**
     * Метод обращается к несуществующему индексу массива
     * @exception ArrayIndexOutOfBoundsException
     */
    private void generateArrayIndexOutOfBoundsException() {
        for (int i = 0; i < 6; i++) {
            array[i] = array[i] + 1;
        }
    }
}
