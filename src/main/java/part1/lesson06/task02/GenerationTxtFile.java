package part1.lesson06.task02;

import java.util.List;
import java.util.Random;

/**
 * @autor Aleksey Danilchik
 */
public class GenerationTxtFile {
    private static String letters = "abcdefgh";
    private static String[] arr = {"!", "?", "."};
    private String[] words;
    private double probability;

    void getFiles(String path, int n, int size, String[] words, int probability) {
        this.words = words;
        calculateProbability(probability);
    }

    private void calculateProbability(int probability) {
        this.probability = (1.0 / probability);
    }


    private void insertWordFromArray(List<StringBuilder> sbWords) {
        if (probabilityInsertWordFromArray()) {
            int numberElement = (int) Math.floor(Math.random() * sbWords.size());
        }
    }

    private boolean probabilityInsertWordFromArray() {
        Random random = new Random();
        return (probability >= random.nextDouble());
    }
}
