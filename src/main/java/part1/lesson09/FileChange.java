package part1.lesson09;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * Класс изменяет метод doWork класса SomeClass, принимая код метода,
 * который пользователь вводит в консоль.
 * @autor Aleksey Danilchik
 */
public class FileChange {
    private File file = new File("C:\\Users\\SiD\\IdeaProjects\\src20-02-exception\\src\\main\\java\\part1\\lesson09\\SomeClass.java");
    private File fileWorker = new File("C:\\Users\\SiD\\IdeaProjects\\src20-02-exception\\src\\main\\java\\part1\\lesson09\\Worker.java");

    /**
     * Метод осуществляет запуск методов чтения файла,
     * получения нового кода метода doWork класса SomeClass,
     * изменение прочитанного файла и компиляция файла с изменениями.
     */
    public void readFileAndChangeMethodAndWriteFile() {
        writeNewFile();
        compileFile();
    }

    /**
     * Метод для получения полного пути к новому файлу.
     * @return полный путь к файлу.
     */
    public String getAbsolutePathNewFile() {
        return file.getAbsolutePath();
    }

    private List<String> readFileInList() {
        List<String> list = new ArrayList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getNewCode() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        System.out.println("Введите код программы:");
        String line = scanner.nextLine();
        while (!"".equals(line)) {
            sb.append(line).append("\n");
            line = scanner.nextLine();
        }
        return sb.toString();
    }

    private List<String> changeMethodInList() {
        List<String> list = readFileInList();
        try {
            int indexInput = findIndexInput(list, "    public void doWork() {");
            int countRemove = countRemoveElement(list, "    }", indexInput);
            while (countRemove > 0) {
                list.remove(indexInput);
                countRemove--;
            }
            list.add(indexInput, getNewCode());
        } catch (NotFoundIndexException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void writeNewFile() {
        List<String> list = changeMethodInList();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (String s : list) {
                writer.write(s + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findIndexInput(List<String> list, String find) throws NotFoundIndexException {
        for (String s : list) {
            if (find.equals(s)) {
                return list.indexOf(s) + 1;
            }
        }
        throw new NotFoundIndexException("Не найден индекс");
    }

    private static int countRemoveElement(List<String> list, String find, int start) throws NotFoundIndexException {
        for (int i = start; i < list.size(); i++) {
            if (find.equals(list.get(i))) {
                return i - start;
            }
        }
        throw new NotFoundIndexException("Не найден индекс");
    }

    private void compileFile() {
        Properties p = System.getProperties();
        String sep = p.getProperty("file.separator");
        String jrePath = p.getProperty("java.home");
        int lastIndex = jrePath.lastIndexOf(sep);
        String javac = jrePath.substring(0, lastIndex) + sep + "bin" + sep + "javac";
        if (p.getProperty("sun.desktop").equals("windows")) {
            javac += ".exe";
        } else {
            javac+=".sh";
        }

        try {
            Runtime.getRuntime().exec(javac + " " + fileWorker.getAbsolutePath() +  " " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
