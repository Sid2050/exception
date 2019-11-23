package part1.lesson06.task01;

import part1.lesson06.task01.comparators.IgnoreCaseComparator;

import java.util.Set;
import java.util.TreeSet;

/**
 * Класс собирает коллекцию уникальных слов не учитывая регистр.
 * @autor Aleksey Danilchik
 */
public class CollectionOfUniqueWords {
    private IgnoreCaseComparator ignoreCaseComparator;
    private Set<String> uniqueWords;

    /**
     * Конструктор - инициализирует коллекцию.
     */
    public CollectionOfUniqueWords() {
        ignoreCaseComparator = new IgnoreCaseComparator();
        uniqueWords = new TreeSet<>(ignoreCaseComparator);
    }

    /**
     * Метод обрабатывает строку и добавляет слова в коллекцию.
     * @param value строка, которую необходимо обработать.
     */
    public void processingStringAndAddWords(String value) {
        addWords(processingString(value).split(" "));
    }

    private void addWords(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (!"".equals(arr[i])) {
                uniqueWords.add(arr[i]);
            }
        }
    }

    private String processingString(String value) {
        return value.replaceAll("\\pP", "");
    }

    /**
     * Метод возвращает строку уникальных слов.
     * @return строка уникальных слов.
     */
    public String returnString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : uniqueWords) {
            stringBuilder.append(word).append(" ");
        }
        return stringBuilder.toString();
    }
}
