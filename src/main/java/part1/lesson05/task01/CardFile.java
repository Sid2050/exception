package part1.lesson05.task01;

import part1.lesson05.task01.exceptions.DuplicateAnimalException;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс предоставляет методы для классов картотек.
 * @autor Aleksey Danilchik
 */
public interface CardFile<T> {
    void addAnimal(T item) throws DuplicateAnimalException;
    List<T> searchByNickname(String value);
    T searchById(UUID id);
}
