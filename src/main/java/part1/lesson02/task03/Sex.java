package part1.lesson02.task03;

/**
 * Перечисление пола человека
 * @autor Aleksey Danilchik
 */
public enum Sex {
    MAN("Мужчина"),
    WOMAN("Женщина");

    String sex;

    Sex(String s) {
        this.sex = s;
    }

    public String getSex() {
        return sex;
    }
}
