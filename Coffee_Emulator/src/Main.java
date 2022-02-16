
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Кофе-машина! Добро пожаловать, Валяев Георгий!");

        //TODO: Java get int from console
        Scanner moneyInput = new Scanner(System.in);
        System.out.print("Введите внесённую сумму денег: ");
        int moneyAmount = moneyInput.nextInt();
        System.out.println("Вы внесли " + moneyAmount + " руб.");

        boolean canBuyAnything = false;

        ArrayList<Drink> drinks = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get("", "drinks.txt"));
            for (String line: lines) {

                String[] tokens = line.split("\s+");

                // "\s+" - это разделение строки на элементы и добавление
                // их в строковый массив по нескольким (...+...) символам пробела (\s).

                if (tokens.length != 2) {
                    continue;
                }

                drinks.add(new Drink(tokens[0], Integer.parseInt(tokens[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Drink drink : drinks) {
            if (moneyAmount >= drink.getPrice()) {
                System.out.println("Вы можете купить " + drink.getName() + ".");
                canBuyAnything = true;
            }
        }

        if (!canBuyAnything) { // это аналог == false
            System.out.println("Сейчас недостаточно средств :(");
        }
    }
}
