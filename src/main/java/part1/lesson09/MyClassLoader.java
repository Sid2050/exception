package part1.lesson09;

import java.io.*;

/**
 * Пользовательский загрузчик класса.
 * @autor Aleksey Danilchik
 */
public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File f = new File(name + ".class");
        if(!f.isFile())
            throw new ClassNotFoundException("Нет такого класса " + name);

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(f))) {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass("part1.lesson09.SomeClass", bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(f.getName());
    }
}
