package part1.lesson08.task01;

/**
 * Класс-пример. Создан для демонстрации сериализации и десериализации.
 * @autor Aleksey Danilchik
 */
public class Simple {
    public int i;
    public String s;
    private long l;

    public Simple() {
    }

    public Simple(int i, String s, long l) {
        this.i = i;
        this.s = s;
        this.l = l;
    }

    @Override
    public String toString() {
        return "Simple{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", l=" + l +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Simple simple = (Simple) o;

        if (i != simple.i) return false;
        if (l != simple.l) return false;
        return s != null ? s.equals(simple.s) : simple.s == null;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + (s != null ? s.hashCode() : 0);
        result = 31 * result + (int) (l ^ (l >>> 32));
        return result;
    }
}
