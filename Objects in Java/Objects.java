// Объекты в языке Java

public class Objects {
    public static void main(String[] args) {

        // Point p = new Point(); - создание объекта (экземпляра класса) при помощи
        // конструктора по умолчанию

        Point p = new Point(8, 4); // создание объекта уже через собственный конструктор
        System.out.print("Начальная точка: ");
        p.printPoint();
        System.out.println();

        // Получение доступа к полям объекта при помощи .
        // p.x = 3;
        // p.y = 6;

        System.out.print("Новая точка: ");
        p.scale();
    }
}
