package part1.lesson06.task01;

import java.io.*;

/**
 * Основной класс. Читает данные из файла и создаёт файл уникальных слов.
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        CollectionOfUniqueWords collectionOfUniqueWords = new CollectionOfUniqueWords();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("uniqueFile.txt", false)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                collectionOfUniqueWords.processingStringAndAddWords(line);
            }
            writer.write(collectionOfUniqueWords.returnString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
