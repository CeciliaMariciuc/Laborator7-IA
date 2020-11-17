public class Row {
    private int x1;
    private int x2;
    private int y;

    public Row(int x1, int x2, int y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y=" + y +
                '}';
    }
}
