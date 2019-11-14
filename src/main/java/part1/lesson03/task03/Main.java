package part1.lesson03.task03;

import part1.lesson03.task03.exceptions.TryAddObjectException;

/**
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        ObjectBox mathBox = new MathBox();
        try {
            mathBox.addObject(new Integer(3));
            mathBox.addObject(new Object());
        } catch (TryAddObjectException e) {
            System.out.println(e.getMessage());
        }
    }


}
