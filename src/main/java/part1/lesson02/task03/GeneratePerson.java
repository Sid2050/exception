package part1.lesson02.task03;

import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Класс создаёт массив объектов Person, генерируя возраст, пол и имя в случайном порядке.
 * @autor Aleksey Danilchik
 */
public class GeneratePerson {
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    private String[] manNames = {"Андрей", "Сергей", "Павел", "Игорь", "Владислав",
                                    "Александр", "Дмитрий", "Геннадий", "Алексей", "Юрий"};
    private String[] womanNames = {"Людмила", "Жанна", "Евгения", "Наталья", "Маша",
                                    "Мария", "Любовь", "Екатерина", "Юлия", "Клеопатра"};
    private Person[] persons;

    /**
     * Конструктор - инициализирует массив
     * @param sizeArray размер массива
     */
    public GeneratePerson(int sizeArray) {
        persons = new Person[sizeArray];
    }

    /**
     * Метод генерирует возраст, пол и имя объекта Person и заполняет массив.
     * @return массив объектов Person
     */
    public Person[] generationAndFillArray() {
        Random random = new Random();
        for (int i = 0; i < persons.length; i++) {
            Sex sex = generationSex(random.nextInt(2));
            persons[i] = new Person(random.nextInt(101), sex, generationName(sex, random.nextInt(10)));
        }
        LOGGER.info("Массив заполнен объектами Person");
        return persons;
    }

    private Sex generationSex(int i) {
        return i > 0 ? Sex.MAN : Sex.WOMAN;
    }

    private String generationName(Sex sex, int i) {
        return sex == Sex.MAN ? manNames[i] : womanNames[i];
    }
}
