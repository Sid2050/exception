package part1.lesson05.task01;

import com.sun.istack.internal.NotNull;
import part1.lesson05.task01.exceptions.DuplicateAnimalException;

import java.util.*;

/**
 * Класс-картотека. В данном классе представлены следующие методы:
 * <ul>
 *     <li>{@link CardFileOfPets#addAnimal(Pet)} - добавление питомца</li>
 *     <li>{@link CardFileOfPets#searchByNickname(String)} - поиск питомца по кличке</li>
 *     <li>{@link CardFileOfPets#searchById(UUID)} - поиск питомца по id</li>
 * </ul>
 * @autor Aleksey Danilchik
 */
public class CardFileOfPets implements CardFile<Pet> {
    private Map<UUID, Pet> petMap;
    private Map<String, List<Pet>> nicknameMap;

    /**
     * Конструктор - инициализирует объект.
     */
    public CardFileOfPets() {
        petMap = new TreeMap<>();
        nicknameMap = new HashMap<>();
    }

    /**
     * Метод добавляет объект класса {@code Pet} в картотеку.
     * @param pet объект класса {@code Pet}.
     * @throws DuplicateAnimalException возникает при попытке добавления идентичного объекта.
     */
    @Override
    public void addAnimal(Pet pet) throws DuplicateAnimalException {
        if (checkForDuplicate(pet))
            throw new DuplicateAnimalException("Данное животное уже существует в картотеке");

        petMap.put(pet.getId(), pet);
        addNicknameMap(pet);
    }

    private boolean checkForDuplicate(Pet item) {
        if (petMap.containsKey(item.getId())) {
            Pet pet = petMap.get(item.getId());
            return pet.equals(item);
        }
        return false;
    }

    @NotNull
    private void addNicknameMap(Pet pet) {
        List<Pet> list;
        list = nicknameMap.containsKey(pet.getName()) ? nicknameMap.get(pet.getName()) : new ArrayList<>();
        list.add(pet);
        nicknameMap.put(pet.getName(), list);
    }

    /**
     * Метод поиска объектов по кличке.
     * @param value кличка питомца.
     * @return коллекцию объектов соответствующую значению {@code value},
     *         если таких объектов нет, то возвращается {@code null}.
     */
    @Override
    public List<Pet> searchByNickname(String value) {
        if (nicknameMap.containsKey(value))
            return nicknameMap.get(value);

        return null;
    }

    /**
     * Метод возвращает объект {@code Pet}.
     * @param id уникальный идентификационный номер.
     * @return объект {@code Pet}, если питомец найден,
     *         иначе {@code null}.
     */
    @Override
    public Pet searchById(UUID id) {
        if (petMap.containsKey(id))
            return petMap.get(id);

        return null;
    }

    /**
     * Метод возвращает строковое представление картотеки в отсортированном порядке
     * по правилам метода {@link Pet#compareTo(Pet)}
     * @return строка.
     */
    @Override
    public String toString() {
        return new TreeSet<>(petMap.values()).toString();
    }
}
