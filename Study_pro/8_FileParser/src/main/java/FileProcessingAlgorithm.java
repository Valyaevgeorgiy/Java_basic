import java.io.*;
import java.util.*;
import java.lang.annotation.*;

// Абстрактный обработчик
abstract class FileHandler {
    protected FileHandler nextHandler;
    protected String fileExtension;

    public void setNextHandler(FileHandler handler) {
        nextHandler = handler;
    }

    public void processFile(String filename) {
        if (canHandleFile(filename)) {
            System.out.println("Обработчик " + this.getClass().getSimpleName() + " получил файл " + filename);
            copyFile(filename);
        } else if (nextHandler != null) {
            nextHandler.processFile(filename);
        } else {
            System.out.println("Ни один обработчик не может обработать файл " + filename);
        }
    }

    protected abstract boolean canHandleFile(String filename);
    protected abstract void copyFile(String filename);
}

// Обработчик для файлов типа TXT
class TxtFileHandler extends FileHandler {
    public TxtFileHandler() {
        fileExtension = "txt";
    }

    protected boolean canHandleFile(String filename) {
        return filename.endsWith(".txt");
    }

    protected void copyFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Обработчик для файлов типа XML
class XmlFileHandler extends FileHandler {
    public XmlFileHandler() {
        fileExtension = "xml";
    }

    protected boolean canHandleFile(String filename) {
        return filename.endsWith(".xml");
    }

    protected void copyFile(String filename) {
        // Логика обработки файлов XML
    }
}

// Обработчик для файлов типа JSON
class JsonFileHandler extends FileHandler {
    public JsonFileHandler() {
        fileExtension = "json";
    }

    protected boolean canHandleFile(String filename) {
        return filename.endsWith(".json");
    }

    protected void copyFile(String filename) {
        // Логика обработки файлов JSON
    }
}

// Обработчик для файлов типа CSV
class CsvFileHandler extends FileHandler {
    public CsvFileHandler() {
        fileExtension = "csv";
    }

    protected boolean canHandleFile(String filename) {
        return filename.endsWith(".csv");
    }

    protected void copyFile(String filename) {
        // Логика обработки файлов CSV
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface ProgramDescription {
    String value();
}

// Применяем аннотацию к классу FileProcessingAlgorithm
@ProgramDescription("Программа для обработки файлов следующих типов: JSON, TXT, XML и CSV.")
// Класс, реализующий алгоритм обработки файлов
public class FileProcessingAlgorithm {
    private FileHandler handlerChain;

    public FileProcessingAlgorithm() {
        // Создаем цепочку обработчиков
        handlerChain = new TxtFileHandler();
        handlerChain.setNextHandler(new XmlFileHandler());
        handlerChain.setNextHandler(new JsonFileHandler());
        handlerChain.setNextHandler(new CsvFileHandler());
    }

    public void processFiles(List<String> filenames) {
        for (String filename : filenames) {
            handlerChain.processFile(filename);
        }
    }

    public static void main(String[] args) {
        // Получаем текст аннотации в консоли
        Class<FileProcessingAlgorithm> clazz = FileProcessingAlgorithm.class;
        ProgramDescription annotation = clazz.getAnnotation(ProgramDescription.class);
        String description = annotation.value();
        System.out.println("Описание программы: " + description);

        String inputFilePath = "input.txt";  // Путь к файлу со списком обрабатываемых файлов
        String outputFilePath = "output.txt";  // Путь к выходному файлу

        // Чтение списка файлов из входного файла
        List<String> filenames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                filenames.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Создание объекта алгоритма и обработка файлов
        FileProcessingAlgorithm algorithm = new FileProcessingAlgorithm();
        algorithm.processFiles(filenames);

        System.out.println("Файлы успешно обработаны. Результат сохранен в " + outputFilePath);
    }
}
