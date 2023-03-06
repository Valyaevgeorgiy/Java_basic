import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public final static String fileName = "src/main/resources/fileTask4.txt";
    public final static String finalFileName = "src/main/resources/fileTask4Results.txt";

    public static int choice;
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
        System.out.println("Первоначальное чтение файла успешно завершено!");
        return data.toString();
    }

    public static void processDataFile(TreeSet<String> onlyWords, String fileData) {
        String[] lines = fileData.split("\n");
        String wordStr;
        try {
            for (String line: lines) {
                Pattern word = Pattern.compile( "\\w+", Pattern.UNICODE_CHARACTER_CLASS );
                Matcher matcher = word.matcher( line );
                while ( matcher.find() ) {
                    wordStr = matcher.group().toLowerCase();
                    onlyWords.add(wordStr);
                }
            }
            System.out.println("Обработка данных завершена успешно. Результат доступен в следующем пункте!");
        } catch (Exception e) {
            System.out.println("Неудачная обработка данных вследствие ошибки:");
            e.printStackTrace();
        }
    }

    public static void fileWriting(String text) {
        try {
            FileWriter myWriter = new FileWriter(finalFileName);
            myWriter.write(String.valueOf(text));
            myWriter.close();
            System.out.println("Данные успешно записаны и сохранены в файл!");
        } catch (Exception e) {
            System.out.println("Данные в файл не записаны вследствие ошибки!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*
        Используя класс TreeSet, напишите программу, которая читает текст из файла, удаляет повторяющиеся
        слова и все знаки препинания, а затем сохраняет результат в новый текстовый файл.
        */

        TreeSet<String> onlyWords = new TreeSet<>();
        String infoFirstStep = ReadFile(fileName);
        Scanner in = new Scanner(System.in);

        while (isEnd) {
            System.out.println("""
                        ================ Меню =============
                        1 - Обработать текст через TreeSet.
                        2 - Вывести полученный результат обработки через TreeSet.
                        3 - Сохранить результат в новый текстовый файл.
                        4 - Выйти.
                    """);
            System.out.print("Выберите пункт из меню выше: ");
            choice = in.nextInt();
            switch (choice) {
                case (1) -> {
                    System.out.println();
                    processDataFile(onlyWords, infoFirstStep);
                    System.out.println();
                }
                case (2) -> {
                    System.out.println();
                    System.out.println("Итоговая структура слов из текста:");
                    System.out.println(onlyWords);
                    System.out.println();
                }
                case (3) -> {
                    System.out.println();
                    fileWriting(onlyWords.toString());
                    System.out.println();
                }
                default -> isEnd = false;
            }
        }
    }
}