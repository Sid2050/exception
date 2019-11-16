package part1.lesson06.task02;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @autor Aleksey Danilchik
 */
public class GenerationTxtFile {
    private static final int MAX_SIZE_ARRAY = 1000;
    private static final int MAX_SENTENCE = 20;
    private static final int MAX_WORD = 15;
    private static final int MAX_LETTER = 15;
    private static final int MIN_VALUE = 1;
    private static String letters = "abcdefghijklmnopqrstuvwxyz";
    private static String[] arr = {"!", "?", "."};
    private static Random random = new Random();
    private String[] words;
    private double probability;

    /**
     * Метод генерирует массив случайных слов.
     * Размер массива равен 1<=n<=1000.
     * @return массив типа {@code String}.
     */
    public static String[] generateArrayString() {
        int sizeArray = MIN_VALUE + random.nextInt(MAX_SIZE_ARRAY);
        String[] arr = new String[sizeArray];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = generateWord();
        }
        return arr;
    }

    /**
     * Метод создаёт txt файлы заданного размера.
     * @param path путь сохранения файлов.
     * @param n количество файлов.
     * @param size размер файла.
     * @param words массив слов, которые должны входить в предложение с определённой вероятностью.
     * @param probability вероятность вхождения слова из массива в предложение.
     */
    public void getFiles(String path, int n, int size, String[] words, int probability) {
        this.words = words;
        calculateProbability(probability);
        for (int i = 0; i < n; i++) {
            try (FileWriter writer = new FileWriter(path + "text" + i + ".txt")) {
                writer.write(getParagraphs(size));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void calculateProbability(int probability) {
        this.probability = (1.0 / probability);
    }

    private String getParagraphs(int size) {
        StringBuilder sbParagraphs = new StringBuilder();
        while (sbParagraphs.length() <= size) {
            int countSentence = MIN_VALUE + random.nextInt(MAX_SENTENCE);
            sbParagraphs.append(generateParagraph(getSentence(countSentence)));
        }
        return sbParagraphs.toString();
    }

    private String generateParagraph(List<String> sbSentence) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sbSentence.size(); i++) {
            temp.append(sbSentence.get(i)).append(" ");
        }
        temp.append("\n\r");
        return temp.toString();
    }

    private List<String> getSentence(int n) {
        List<String> sbSentence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int countWords = MIN_VALUE + random.nextInt(MAX_WORD);
            sbSentence.add(generateSentence(getWords(countWords)));
        }
        return sbSentence;
    }

    private String generateSentence(List<String> sbWords) {
        String temp = "";
        for (int i = 0; i < sbWords.size(); i++) {
            temp += sbWords.get(i) + " ";
        }
        return temp.substring(0, 1).toUpperCase()
                + temp.substring(1, temp.length() - 1)
                + arr[random.nextInt(3)];
    }

    private List<String> getWords(int n) {
        List<String> sbWords = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sbWords.add(generateWord());
        }
        insertWordFromArray(sbWords);
        insertComma(sbWords);
        return sbWords;
    }

    private static String generateWord() {
        StringBuilder temp = new StringBuilder();
        int countLetter = MIN_VALUE + random.nextInt(MAX_LETTER);
        for (int i = 0; i < countLetter; i++) {
            temp.append(letters.charAt(random.nextInt(letters.length())));
        }
        return temp.toString();
    }

    private void insertWordFromArray(List<String> sbWords) {
        if (probabilityInsertWordFromArray()) {
            int sbElement = random.nextInt(sbWords.size());
            int indexWord = random.nextInt(words.length);
            sbWords.set(sbElement, words[indexWord]);
        }
    }

    private void insertComma(List<String> sbWords) {
        if (sbWords.size() > 1) {
            int sbElement = random.nextInt(sbWords.size());
            if (sbElement != sbWords.size() - 1)
                sbWords.set(sbElement, sbWords.get(sbElement) + ",");
        }
    }

    private boolean probabilityInsertWordFromArray() {
        return (probability >= random.nextDouble());
    }
}
