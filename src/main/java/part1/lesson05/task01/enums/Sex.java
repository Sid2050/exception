package part1.lesson05.task01.enums;

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
