import java.util.ArrayList;
import java.util.Collections;

public class RandomGenerator {
    public static void main(String[] args) {

        System.out.println("Рандомные пять чисел из массива:");
        int count = 0;

        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 2; i <= 30; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int number : numbers) {
            count += 1;
            System.out.println(number);
            if (count == 5) {
                break;
            }
        }
    }
}
