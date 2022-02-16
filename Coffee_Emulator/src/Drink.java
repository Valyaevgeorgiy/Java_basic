public class Drink {

    private String name;
    private int price;

    // Конструктор класса Drink
    public Drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Getter имени напитка из конструктора класса
    public String getName() {
        return name;
    }

    // Getter цены напитка из конструктора класса
    public int getPrice() {
        return price;
    }

    public static void main(String[] args) {

    }
}
