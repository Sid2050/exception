package part1.lesson08.task02;

/**
 * @autor Aleksey Danilchik
 */
public class InnerWithSimple {
    int i;
    String s;
    long l;

    public InnerWithSimple() {
    }

    public InnerWithSimple(int i, String s, long l) {
        this.i = i;
        this.s = s;
        this.l = l;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InnerWithSimple that = (InnerWithSimple) o;

        if (i != that.i) return false;
        if (l != that.l) return false;
        return s != null ? s.equals(that.s) : that.s == null;
    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + (s != null ? s.hashCode() : 0);
        result = 31 * result + (int) (l ^ (l >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "InnerWithSimple{" +
                "i=" + i +
                ", s='" + s + '\'' +
                ", l=" + l +
                '}';
    }
}
