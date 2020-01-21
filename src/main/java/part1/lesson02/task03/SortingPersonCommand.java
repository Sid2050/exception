package part1.lesson02.task03;

/**
 * @autor Aleksey Danilchik
 */
public class SortingPersonCommand implements Command {
    private Sort objects;

    public SortingPersonCommand(Sort objects) {
        this.objects = objects;
    }

    @Override
    public void execute() {
        try {
            objects.sortingObject();
        } catch (EqualNameAndAgeException e) {
            System.out.println("Не удалось отсортировать массив.");
        }
    }
}
