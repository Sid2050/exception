package part1.lesson03.task02;

/**
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        ObjectBox objectBox = new ObjectBox();
        Integer integer = new Integer(15);
        objectBox.addObject(integer);
        objectBox.addObject(new Double(13.0));
        System.out.println(objectBox.dump());
        objectBox.deleteObject(integer);
        System.out.println(objectBox.dump());
    }
}
