package part1.lesson08.task02;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Класс сериализует и десериализует объект в {@code xml} файл.
 * @autor Aleksey Danilchik
 */
public class Serialization {

    private Serialization() {}

    /**
     * Метод создаёт {@code xml} документ, сериализую объект.
     * @param object объект, который необходимо сериализовать.
     * @param path путь сохранения файла.
     * @throws IOException
     */
    public static void convertObjectToXML(Object object, String path) throws IOException {
        Document xml = new Document();
        Element root = new Element(object.getClass().getName());
        xml.setRootElement(root);
        writeNode(object, root);
        Format fmt = Format.getPrettyFormat();
        XMLOutputter serializer = new XMLOutputter(fmt);
        serializer.output(xml, new FileOutputStream(new File(path + object.getClass().getSimpleName() + ".xml")));
    }

    private static void writeNode(Object object, Element root) {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Element name;
            try {
                if (field.getType().getSimpleName().equals("String")
                        || field.getType().isPrimitive()) {
                    name = new Element(field.getName());
                    name.setAttribute("name", field.getName());
                    name.addContent(field.get(object).toString());
                    root.addContent(name);
                } else {
                    Class<?> innerObject = field.getType();
                    Object innerObjectValue = field.get(object);

                    name = new Element(innerObject.getName());
                    name.setAttribute("name", field.getName());
                    writeNode(innerObjectValue, name);
                    root.addContent(name);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод десериализует объект из {@code xml} файла.
     * @param path путь к файлу.
     * @return десериализованный объект.
     */
    public static Object convertXMLToObject(String path) {
        Object object = null;
        SAXBuilder parser = new SAXBuilder();
        Document xmlDoc;
        try {
            xmlDoc = parser.build(new File(path));
            Class clazz = Class.forName(xmlDoc.getRootElement().getName());
            object = clazz.newInstance();
            List<Element> elements = xmlDoc.getRootElement().getChildren();
            readObject(clazz, object, elements);
        } catch (JDOMException | IOException |ClassNotFoundException |
                IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return object;
    }

    private static void readObject(Class clazz, Object object, List<Element> elements) throws ClassNotFoundException,
            NoSuchFieldException,
            IllegalAccessException, InstantiationException {
        for (Element element : elements) {
            Field field = clazz.getDeclaredField(element.getAttributeValue("name"));

            field.setAccessible(true);
            switch (field.getType().getName()) {
                case "boolean": {
                    field.setBoolean(object, Boolean.valueOf(element.getValue()));
                    break;
                }
                case "java.lang.String": {
                    field.set(object, element.getValue());
                    break;
                }
                case "byte": {
                    field.setByte(object, Byte.valueOf(element.getValue()));
                    break;
                }
                case "char": {
                    field.setChar(object, element.getValue().charAt(0));
                    break;
                }
                case "double": {
                    field.setDouble(object, Double.valueOf(element.getValue()));
                    break;
                }
                case "float": {
                    field.setFloat(object, Float.valueOf(element.getValue()));
                    break;
                }
                case "int": {
                    field.setInt(object, Integer.valueOf(element.getValue()));
                    break;
                }
                case "long": {
                    field.setLong(object, Long.valueOf(element.getValue()));
                    break;
                }
                case "short": {
                    field.setShort(object, Short.valueOf(element.getValue()));
                    break;
                }
                default: {
                    List<Element> innerElements = element.getChildren();
                    Class innerClazz = Class.forName(element.getName());
                    Object innerObject = innerClazz.newInstance();
                    readObject(innerClazz, innerObject, innerElements);
                    field.set(object, innerObject);
                }
            }
        }
    }
}
