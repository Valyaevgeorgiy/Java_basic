// Условные операторы в языке Java

public class If_else {
    public static void main(String[] args) {

        int a = 4, b = 3;

        // Стандартная версия оператора if-else
        if (a == b) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        // Короткая версия оператора (не рекомендуется новичкам, но поможет поместить
        // тело метода в 1 строку)
        if (a == b)
            System.out.println("Yes");
        else
            System.out.println("No");

        int k = 4, l = 5;

        boolean result;
        result = k < l; // истина
        result = k > l; // ложь

        result = k <= 4; // меньше или равно - истина
        result = l >= 6; // больше или равно - ложь

        result = k == l; // равно - ложь
        result = k != l; // неравно - истина

        result = k > l || k < l; // логическое ИЛИ - истина
        result = 3 < k && k < 6; // логическое И - истина
        result = !result; // логическое НЕ - ложь
    }
}