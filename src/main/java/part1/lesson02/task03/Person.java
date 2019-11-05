package part1.lesson02.task03;

/**
 * Класс персоны со свойствами <b>age</b>, <b>sex</b> и <b>name</b>.
 * @autor Aleksey Danilchik
 */
public class Person {
    private int age;
    private Sex sex;
    private String name;

    /**
     * Метод получения значения поля {@link Person#age}
     * @return возраст персоны
     */
    public int getAge() {
        return age;
    }

    /**
     * Метод получения значения поля {@link Person#name}
     * @return имя персоны
     */
    public String getName() {
        return name;
    }

    /**
     * Конструктор - создание нового объекта с определёнными значениями.
     * @param age возраст
     * @param sex пол
     * @param name имя
     */
    public Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    /**
     * Метод возвращает строковое предствавление объекта в определённом виде.
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Метод проверяет, является ли пол данного объекта Мужским.
     * @return мужской пол объекта или нет
     */
    public boolean isMan() {
        return sex == Sex.MAN;
    }

    /**
     * Метод проверяет, является ли пол данного объекта Женским.
     * @return женский пол объекта или нет
     */
    public boolean isWoman() {
        return sex == Sex.WOMAN;
    }

    /**
     * Метод проверяет, одинаковое ли имя и возраст у объектов
     * @param p объект класса {@link Person}
     * @return равно имя и возраст
     */
    public boolean isEqualsNameAndAge(Person p) {
        return (name.equals(p.getName())) && (age == p.getAge());
    }
}
