package part1.lesson05.task01;

import part1.lesson05.task01.enums.Sex;
import part1.lesson05.task01.exceptions.DuplicateAnimalException;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        CardFileOfPets cardFileOfPets = new CardFileOfPets();
        try {
            cardFileOfPets.addAnimal(new Pet("Jack", new Person(34, Sex.MAN, "Tom"), 1000));
            cardFileOfPets.addAnimal(new Pet("Jack", new Person(12, Sex.MAN, "Tim"), 800));
            cardFileOfPets.addAnimal(new Pet("Pit", new Person(18, Sex.WOMAN, "Lisa"), 1500));
            cardFileOfPets.addAnimal(new Pet("Pot", new Person(85, Sex.MAN, "Tad"), 500));
        } catch (DuplicateAnimalException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(cardFileOfPets.toString());
    }
}
