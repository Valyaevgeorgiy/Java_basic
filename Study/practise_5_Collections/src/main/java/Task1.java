import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Task1 {

    public static boolean isBalanced = true;
    static boolean areBracketsBalanced(String expr) {
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);
            if (x == '(' || x == '[' || x == '{') {
                stack.push(x);
                continue;
            } else if (x == ' ') {
                continue;
            }

            char check;
            try {
                switch (x) {
                    case ')':
                        check = stack.pop();
                        if (check == '[' || check == '{') return !isBalanced;
                        continue;
                    case ']':
                        check = stack.pop();
                        if (check == '(' || check == '{') return !isBalanced;
                        continue;
                    case '}':
                        check = stack.pop();
                        if (check == '(' || check == '[') return !isBalanced;
                }
            } catch (NoSuchElementException e) {
                return !isBalanced;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        /*
        Используйте ArrayDeque для проверки синтаксиса математического выражения (относительно (){}[]).
        Пример: {(1+2+3)(5-2)*3}-5 верно, а {1+2+3)(5-2)*3}-5 неверно. Ваша программа должна уметь
        вычислять математическое выражение и давать окончательный результат. Учтите, что все числа
        являются целыми числами, и возможные операции над ними: вычитание (-), сложение (+) и умножение (*).
        */

        System.out.print("Введите математическое выражение: ");
        Scanner in = new Scanner(System.in);
        String expr = in.nextLine();
        System.out.println("Результат ввода: " + expr);
        if (areBracketsBalanced(expr)) {
            System.out.println("Balanced well done!");
            Expression expression = new ExpressionBuilder(expr).build();
            double result = expression.evaluate();
            System.out.print("Результат вычисления математического выражения => ");
            System.out.println(result);
        } else {
            System.out.println("Not Balanced!");
        }
    }
}
