package part1.lesson09;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

/**
 * Основной класс.
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Worker worker = new SomeClass();
        System.out.println("Реализация метода doWork до изменений:");
        worker.doWork();

        FileChange fileChange = new FileChange();
        fileChange.readFileAndChangeMethodAndWriteFile();
        String filePath = fileChange.getAbsolutePathNewFile();
        int pointIndex = filePath.lastIndexOf(".");
        String absulutePatch = filePath.substring(0, pointIndex);

        LockSupport.parkNanos(1_000_000_000L);

        MyClassLoader loader = new MyClassLoader();
        Class someClass = loader.findClass(absulutePatch);
        Worker workerNew = (Worker) someClass.newInstance();
        System.out.println("Реализация метода doWork после изменений:");
        workerNew.doWork();
    }


}
