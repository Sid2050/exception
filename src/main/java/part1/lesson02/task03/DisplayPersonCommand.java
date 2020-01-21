package part1.lesson02.task03;

/**
 * @autor Aleksey Danilchik
 */
public class DisplayPersonCommand implements Command {
    private Sort objects;

    public DisplayPersonCommand(Sort objects) {
        this.objects = objects;
    }

    @Override
    public void execute() {
        objects.display();
    }
}
