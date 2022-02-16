
public class HW_Cycles {
    public static void main(String[] args) {
        System.out.print("Результат: [");
        for (int i = 0; i < 238; i++) {
            if (i % 2 == 0) {
                if (i == 236) {
                    System.out.print(i + "]");
                } else {
                    System.out.print(i + " ");
                }
            }
        }
    }
}
