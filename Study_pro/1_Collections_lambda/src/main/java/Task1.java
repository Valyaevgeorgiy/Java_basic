// Посчитать количество каждого символа в ведённом слове

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        System.out.print("Введите ваш текст: ");
        Scanner sc = new Scanner(System.in);
        Map<String, Integer> word = new LinkedHashMap<>();
        String inputConsole = sc.nextLine();
        for (int i=0; i < inputConsole.length(); i++) {
            char symb = inputConsole.charAt(i);
            String letter = String.valueOf(symb);
            if (!letter.equals(" ")) {
                word.putIfAbsent(letter, 0);
                word.put(letter, word.get(letter) + 1);
            }
        }

        String pattern = "%s -> %d";
        for (Map.Entry<String, Integer> w : word.entrySet()) {
            System.out.println(String.format(pattern, w.getKey(), w.getValue()));
        }
    }

}
