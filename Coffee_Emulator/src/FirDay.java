
import java.util.Scanner;

public class FirDay {
    public static void main(String[] args) {
        System.out.println("Кофе-машина! Добро пожаловать, Валяев Георгий!");

        // Java get int from console
        Scanner moneyInput = new Scanner(System.in);
        System.out.print("Введите внесённую сумму денег: ");
        int moneyAmount = moneyInput.nextInt();
        System.out.println("Вы внесли " + moneyAmount + " руб.");

        boolean canBuyAnything = false;

        int[] drinkPrices = {200, 125, 100, 50, 74};
        String[] drinkNames = {"капучино", "раф", "эспрессо", "вода", "молоко"};

        int length = Math.min(drinkPrices.length, drinkNames.length);

        for (int i = 0; i < length; i++) {
            if (moneyAmount >= drinkPrices[i]) {
                System.out.println("Вы можете купить " + drinkNames[i] + ".");
                canBuyAnything = true;
            }
        }

        if (canBuyAnything == false) {
            System.out.println("Сейчас недостаточно средств :(");
        }
    }
}
