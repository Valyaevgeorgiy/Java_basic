import java.io.FileReader;
import java.util.*;

public class Task2 {

    public final static String fileName = "src/main/resources/fileTask2.txt";
    public static int choice;
    public static String tuple;
    public static boolean isEnd = true;

    public static String ReadFile(String file) {
        StringBuilder data = new StringBuilder();
        try {
            FileReader newReader = new FileReader(file);
            int character = newReader.read();
            while (character != -1) {
                data.append((char) character);
                character = newReader.read();
            }
            newReader.close();
        } catch (Exception e) {
            System.out.println("Данные из файла не прочитаны вследствие ошибки:");
            e.printStackTrace();
        }
        return data.toString();
    }

    public static void processDataFile(HashMap<String, String> dataDict, String fileData) {
        String[] pairs = fileData.split("\n");
        String key, value;
        String finalValue;
        System.out.println("Получившаяся словарная структура:");
        for (String pair: pairs){
            key = pair.split(":")[0];
            value = pair.split(":")[1];
            dataDict.put(key, value);
            finalValue = key + ": " + dataDict.get(key);
            System.out.println(finalValue);
        }
    }

    public static void main(String[] args) {

        /*
        Напишите простую программу-словарь, которая считывает информацию из файла и сохраняет ее в
        HashMap. Пользователь должен иметь возможность искать слово с помощью меню, которое постоянно
        отображается. Текстовый файл должен иметь следующую структуру:
        Слово1: Значение1
        Слово2: Значение2
        ....
        */

        HashMap<String, String> finalData = new HashMap<>();
        String infoFirstStep = ReadFile(fileName);
        Scanner in = new Scanner(System.in);
        while (isEnd) {
            System.out.println("""
                        ================ Меню =============
                        1 - Добавить пару «ключ:значение».
                        2 - Найти пару по ключу.
                        3 - Вывести все имеющиеся слова в HashMap структуре.
                        4 - Вывести все пары «ключ:значение» из файла.
                        5 - Выйти.
                    """);
            System.out.print("Выберите пункт из меню выше: ");
            choice = in.nextInt();
            switch (choice) {
                case (1) -> {
                    System.out.println();
                    System.out.print("Введите пару «ключ:значение» (пример => 1:привет): ");
                    tuple = in.next();
                    finalData.put(tuple.split(":")[0], tuple.split(":")[1]);
                    System.out.println("Пара была добавлена, результат можно увидеть в 3 пункте меню!");
                    System.out.println();
                }
                case (2) -> {
                    System.out.println();
                    System.out.print("Введите нужный ключ: ");
                    String needKey = in.next();
                    if (finalData.containsKey(needKey)) {
                        String needValue = finalData.get(needKey);
                        System.out.println("Пара найдена");
                        System.out.println(needKey + ": " + needValue);
                    } else {
                        System.out.println("Пара с таким ключом не найдена!");
                    }
                    System.out.println();
                }
                case (3) -> {
                    System.out.println();
                    System.out.println("Имеющиеся пары структуры HashMap:");
                    finalData.forEach((key, value) -> System.out.println(key + ": " + value));
                    System.out.println();
                }
                case (4) -> {
                    System.out.println();
                    processDataFile(finalData, infoFirstStep);
                    System.out.println();
                }
                default -> isEnd = false;
            }
        }
    }
}
