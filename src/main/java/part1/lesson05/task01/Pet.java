package part1.lesson05.task01;

import java.util.UUID;

/**
 * Класс домашнего животного со свойствами <b>id</b>, <b>name</b>, <b>person</b> и <b>weight</b>
 * @autor Aleksey Danilchik
 */
public class Pet implements Comparable<Pet> {
    private UUID id;
    private String name;
    private Person person;
    private double weight;

    /**
     * Конструктор - создание нового объекта. Генерирует уникальный идентификационный номер для объекта.
     * @param name имя питомца
     * @param person хозяин. Объект типа {@link Person}
     * @param weight вес питомца
     */
    public Pet(String name, Person person, double weight) {
        id = UUID.randomUUID();
        this.name = name;
        this.person = person;
        this.weight = weight;
    }

    /**
     * Метод получения значения поля {@link Pet#id}.
     * @return возвращает id.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Метод получения значения поля {@link Pet#name}.
     * @return возвращает имя питомца.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод получения значения поля {@link Pet#person}.
     * @return возвращает хозяина питомца.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Метод получения значения поля {@link Pet#weight}.
     * @return возвращает вес питомца.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Метод изменения значения поля {@link Pet#person}.
     * @param person объект типа {@link Person}.
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Метод изменения значения поля {@link Pet#weight}.
     * @param weight вес питомца типа {@code double}.
     */
    public void setWeight(double weight) {
        this.weight = weight;
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

        Pet pet = (Pet) o;

        if (Double.compare(pet.weight, weight) != 0) return false;
        if (id != null ? !id.equals(pet.id) : pet.id != null) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        return person != null ? person.equals(pet.person) : pet.person == null;
    }

    /**
     * Метод генерирует хеш-код.
     * @return хеш-код объекта.
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Метод сортирует объект по следующим правилам:
     * <ul>
     *     <li>Правила метода {@link Person#compareTo(Person)}</li>
     *     <li>Кличка питомца по алфавиту</li>
     *     <li>Вес питомца по возрастанию</li>
     * </ul>
     * @param o объект сравнения.
     * @return
     */
    @Override
    public int compareTo(Pet o) {
        int result = person.compareTo(o.person);
        if (result != 0)
            return result;
        result = name.compareTo(o.name);
        if (result != 0)
            return result;
        result = Double.compare(weight, o.weight);
        return result;
    }

    /**
     * Строковое представление объекта.
     * @return строка.
     */
    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", person=" + person +
                ", weight=" + weight +
                '}';
    }
}
