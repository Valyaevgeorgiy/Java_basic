import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SecDay {
    public static void main(String[] args) {

        System.out.println("Кофе-машина! Добро пожаловать, Валяев Георгий!");

        //TODO: Java get int from console
        Scanner moneyInput = new Scanner(System.in);
        System.out.print("Введите внесённую сумму денег: ");
        int moneyAmount = moneyInput.nextInt();
        System.out.println("Вы внесли " + moneyAmount + " руб.");

        boolean canBuyAnything = false;

        // HashMap - коллекция (словарь Python) для хранения имён и цен напитков

        HashMap<String, Integer> drinkName2Price = new HashMap<>();
        drinkName2Price.put("капучино", 200);
        drinkName2Price.put("раф", 125);
        drinkName2Price.put("эспрессо", 100);
        drinkName2Price.put("воду", 50);
        drinkName2Price.put("молоко", 74);

        // Специфический цикл for для прохода по коллекции и созданию
        // нового элемента, состоящего из проходимого набора данных цен и имён напитков

        for (Map.Entry<String, Integer> entry : drinkName2Price.entrySet()) {
            if (moneyAmount >= entry.getValue()) {
                System.out.println("Вы можете купить " + entry.getKey() + ".");
                canBuyAnything = true;
            }
        }

        if (!canBuyAnything) { // это аналог == false
            System.out.println("Сейчас недостаточно средств :(");
        }

//        // Исключения в Java и конструкция try ... catch
//        int[] drinkPrices = {200, 125, 100, 50, 74};
//
//        System.out.println("BEFORE");
//
//        try {
//            System.out.println(drinkPrices[5]);
//        } catch (Exception e) {
//            System.out.println("Исключенька :((");
//        }
//        System.out.println("AFTER");
    }
}
