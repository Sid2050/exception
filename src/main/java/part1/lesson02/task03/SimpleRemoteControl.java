package part1.lesson02.task03;

/**
 * @autor Aleksey Danilchik
 */
public class SimpleRemoteControl {
    private Command slot;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) {
        slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
