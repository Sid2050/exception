package part1.lesson12.task02;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Пользовательский загрузчик класса. Не проверяет был ли загружен класс.
 * @autor Aleksey Danilchik
 */
public class MyClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("target/classes/part1/lesson12/task02/MyClass.class"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
