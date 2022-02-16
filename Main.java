// https://ideone.com/ - онлайн-компилятор на Java

// Типы переменных и вывод строк в консоль в языке Java

public class Main {
    public static void main(String[] args) {
        // int Mynumber = 5;

        // double d = 4.5;
        // d = 3.0;

        // float f = (float) 4.5;
        // float f_2 = 5.5f;

        // char c = 'g';
        String s1 = new String("Who let the dogs out?");
        String s2 = "Who Who Who!";

        String s3 = s1 + " " + s2;

        System.out.println(s3);

        int num = 5;
        String s = "I have " + num + " cookies";

        System.out.println(s);

        boolean b = false;
        b = true;

        boolean toBe = false;
        b = toBe || !toBe;
        if (b) {
            System.out.println(toBe);
        }

        // int children = 0;
        // b = children;
    }
}
