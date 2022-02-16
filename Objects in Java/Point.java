
public class Point {
    private int x;
    private int y;

    public Point() {
        this(1, 1);
    }

    public Point(int x, int y) { // создание своего конструктора через перегрузку метода
        this.x = x;
        this.y = y;
    }

    public void printPoint() { // вывод координат точки
        System.out.print("(" + x + ", " + y + ")");
    }

    private Point center(Point other) {
        return new Point((x + other.x) / 2, (y + other.y) / 2);
    }

    public void scale() {
        this.x = this.x / 2;
        this.y = this.y / 2;
        printPoint();
    }
}
