package part1.lesson05.task01;


import part1.lesson05.task01.enums.Sex;

/**
 * Класс персоны со свойствами <b>age</b>, <b>sex</b> и <b>name</b>.
 * @autor Aleksey Danilchik
 */
public class Person implements Comparable<Person> {
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
                ", sex=" + sex.getSex() +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Метод сортирует объект по следующим правилам:
     * <ul>
     * <li>Первые идут мужчины</li>
     * <li>Выше в списке тот, кто более старший</li>
     * <li>Имена сортируются по алфавиту</li>
     * </ul>
     * @param o
     * @return
     */
    @Override
    public int compareTo(Person o) {
        int result;
        result = (sex == Sex.WOMAN && o.sex == Sex.MAN) ? 1 : 0;
        if (result == 0)
            result = (sex == Sex.MAN && o.sex == Sex.WOMAN) ? -1 : 0;
        if (result != 0)
            return result;
        result = Integer.compare(o.age, age);
        if (result != 0)
            return result;
        result = o.name.compareTo(name);
        return result;
    }

    /**
     * Метод сравнивает два объекта.
     * @param o объект с которым надо сравнить.
     * @return {@code true}, если объект полностью идентичен;
     *         {@code false} в остальных случаях.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (sex != person.sex) return false;
        return name != null ? name.equals(person.name) : person.name == null;
    }

    /**
     * Метод генерирует хеш-код.
     * @return хеш-код объекта.
     */
    @Override
    public int hashCode() {
        int result = age;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
