package part1.lesson06.task02;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        String[] arr = GenerationTxtFile.generateArrayString();
        final GenerationTxtFile generationTxtFile = new GenerationTxtFile();
        generationTxtFile.getFiles("", 5, 1000, arr, 100);
    }
}
