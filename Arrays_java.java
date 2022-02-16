// Массивы в языке Java

import java.util.Arrays; // импорт класса Arrays из пакета java.util

public class Arrays_java {
    public static void main(String[] args) {
        int[] arr;
        arr = new int[10]; // создание числового массива размером 10

        arr[0] = 4; // индексация массива начинается с 0, как и в Python
        arr[1] = arr[0] + 5;

        System.out.println(arr.length); // нахождение длины массива

        int[] arr_2 = { 1, 2, 3, 4, 5 }; // короткая запись инициализации массива через {}
        System.out.println(Arrays.toString(arr_2)); // вывод через спецметод java.util.Arrays.toString(int[] arr)

        for (int i = 0; i < arr_2.length; i++) {
            System.out.println(arr_2[i]);
        }
    }
}
