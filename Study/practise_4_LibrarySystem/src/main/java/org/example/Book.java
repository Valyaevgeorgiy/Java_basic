package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

public class Book {

    /**
     * Путь от корневой директории до папки с ресурсами
     */
    private final String rootPath = "src/main/resources";
    /**
     * Папка с файлами атрибутов книг
     */
    private final String dataDir = "Books";
    /**
     * Имена файлов хранящих информацию о книге
     */
    private final String[] columnsName = new String[]{"id", "name", "author", "publisher", "edition", "publication_year", "category"};

    /**
     * Количество записей о книгах
     */
    private int length = 0;
    /**
     * Текущий номер просматриваемой строки
     */
    private int Index = 0;
    /**
     * Просматриваемый столбец
     */
    private String readColumnName;
    /**
     * Ридер для файла со значениями атрибутов книги
     * {@code java.io.FileReader}
     * Для создания используется функция {@link Book#startRead(String) startRead}
     */
    private java.io.FileReader reader;

    /**
     * {@code ArrayList} хранящий {@code HashMap<String, String>} с записями атрибутов всех книг
     * Можно получить через геттер {@link Book#getAllDataList()}}
     */
    private ArrayList<HashMap<String, String>> allData;

    /**
     * ID всех книг
     * После создания экземпляра обновляется
     * Можно обновить через метод {@link Book#getIdBooks()}
     */
    private ArrayList<Integer> IDBooks = new ArrayList<>();

    /**
     * Creator класса {@link Book }
     * при создании экземпляра считывает всю информацию в переменную {@code allData}
     * и получает ID книг {@link Book#IDBooks}
     */
    public Book() {
        try {
            allData = getAllData();
            getIdBooks();

            //System.out.println(allData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Проверка есть ли у книги заданный {@link Book#columnsName атрибут}
     *
     * @param column имя атрибута
     * @return {@code true} ели такой атрибут есть {@code false} если нет
     */
    private boolean checkColumnName(String column) {
        if (column.equals("log")) return true;
        for (String element : columnsName) {
            if (element.equals(column)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Считает количество строк и записывает в переменную {@code length}
     *
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    public void getCountRows() throws Exception {
        startRead(columnsName[0]);
        while (true) {
            if (stepOnNextLine() == -1) break;
        }
        length = Index;
        stopRead();
    }

    /**
     * Получает ID всех книг.
     * После чего перезаписывает атрибут класса {@link Book#IDBooks IDBooks}
     *
     * @return Возвращает {@code ArrayList<Integer>} с ID всех книг
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    public ArrayList<Integer> getIdBooks() throws Exception {
        startRead("id");
        IDBooks.clear();
        String id = getLine();
        while (id != null) {
            IDBooks.add(Integer.parseInt(id));
            id = getLine();
        }
        stopRead();
        return IDBooks;
    }

    /**
     * Начинать считывание файла с {@link Book#columnsName атрибутом} книги.
     * При начале считывания все изменяемые переменные с используемые для чтения ({@link Book#readColumnName читаемый атрибут},
     * {@link Book#Index индекс текущей строки}) сбрасываются, а {@link Book#reader } заменяется на новый
     *
     * @param column имя атрибута для считывания
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    private void startRead(String column) throws Exception {
        // Проверка на существование заданного атрибута
        if (!checkColumnName(column)) throw new Exception("don`t find column");
        reader = new java.io.FileReader(rootPath + "/" + dataDir + "/" + column);
        Index = 0;
        readColumnName = getLine();
    }

    /**
     * Остановка чтения файла
     */
    private void stopRead() {
        Index = 0;
        reader = null;
        readColumnName = null;
    }

    /**
     * Внутренний метод для перезаписи атрибута всех книг
     * используется для удаления строк
     *
     * @param column {@link Book#columnsName атрибут}
     * @param data   содержание файла минус удаляемая строка
     */
    private void rewriteColumn(String column, String data) {
        try {
            FileWriter myWriter = new FileWriter(rootPath + "/" + dataDir + "/" + column);
            myWriter.write(data);
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Text saving failed.");
            e.printStackTrace();
        }
    }

    /**
     * Добавление в атрибут нового значения
     *
     * @param column {@link Book#columnsName атрибут}
     * @param value  значение
     */
    private void addToColumn(String column, String value) {
        try {
            // Дозапись в существующий файл нового значения
            Files.write(Path.of(rootPath + "/" + dataDir + "/" + column), value.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Text append failed.");
            e.printStackTrace();
        }
    }

    /**
     * Функция для перехода по строкам {@link Book#columnsName атрибутов} книги
     *
     * @return {@link Book#Index Индекс} текущей строки, если файл закончился, вернется -1
     */
    private int stepOnNextLine() {
        // переменная для записи считываемого символа
        int character = 0;
        try {
            character = reader.read();
            // пока в файле есть символы считываем их
            while (character != -1) {
                // Если считанный символ это переход на новую строку, то считывание прерывается
                // и возвращается индекс новой строки
                if (character == 13) {
                    character = reader.read();
                    continue;
                }
                if (character == 10) {
                    Index++;
                    return Index;
                }
                character = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        // если файл кончился, вернется -1
        return -1;
    }

    /**
     * Функция для считывания текущей строки {@link Book#reader читаемого файла}.
     * После считывания переходит на новую строку
     *
     * @return Значение в строке, если ничего считать не удалось — файл кончился, вернется {@code null}
     */
    private String getLine() {
        // переменная для записи считываемого символа
        int character = 0;
        // StringBuilder для сохранения прочтенных символов
        StringBuilder sb = new StringBuilder();
        try {
            // Считывание символа
            character = reader.read();
            // пока в файле есть символы считываем их
            while (character != -1) {
                if (character == 13) {
                    character = reader.read();
                    continue;
                }
                if (character == 10) {
                    // Если считанный символ это переход на новую строку, то считывание прерывается
                    // и возвращается значение считанной строки
                    Index++;
                    return sb.toString();
                }
                // Сохраняем считанный символ
                sb.append((char) character);
                character = reader.read();
            }
        } catch (IOException e) {
            System.out.println("Text reading failed.");
            e.printStackTrace();
        }
        // если ничего считать не удалось — файл кончился, вернется null
        return null;
    }

    /**
     * Функция для получения значения строки с заданным индексом
     *
     * @param index Индекс считываемой строки
     * @return значение в считываемой строке
     * @throws Exception Если индекс запрашиваемой строки меньше текущего, то вызывается исключение
     *                   т.к прочесть значение строки будет невозможно
     */
    private String getValueFromIndex(int index) throws Exception {
        if (Index > index) throw new Exception("Position read after desired position. Restart Reader!");
        while (Index != index) {
            stepOnNextLine();
        }
        return getLine();
    }

    /**
     * Функция проверяет есть ли у книги заданный {@link Book#columnsName атрибут} и возвращает Индексы всех книг с таким атрибутом
     *
     * @param column {@link Book#columnsName атрибут} книги
     * @param value  искомое значение
     * @return {@code ArrayList<Integer>} с Индексами! строк, где содержится информация о книге
     */
    public ArrayList<Integer> getIndexesFromValue(String column, String value) {
        try {
            startRead(column);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> id = new ArrayList<>();
        String getValue = getLine();
        // Проходим по всем значениям атрибутов и ищем соответствия,
        // если соответствие найдено добавляем индекс к списку ответа
        while (getValue != null) {
            if (getValue.equals(value)) {
                id.add(Index - 1);
            }
            getValue = getLine();
        }
        return id;
    }

    /**
     * Функция для получения всей информации о книге по индексу
     *
     * @param index индекс получаемой строки
     * @return {@code HashMap<String, String>} где key это {@link Book#columnsName атрибут} а value это значение атрибута
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    public HashMap<String, String> getRowFromIndex(int index) throws Exception {
        HashMap<String, String> bookInfo = new HashMap<>();
        // Обходим все атрибуты книги
        for (String column : columnsName) {
            startRead(column);
            // Добавляем атрибут и значение этого атрибута
            bookInfo.put(column, getValueFromIndex(index));
        }
        return bookInfo;
    }

    /**
     * Функция для получения всей информации о книгах по индексам
     *
     * @param Indexes Индексы искомых книг
     * @return {@code ArrayList<HashMap<String, String>} где key это {@link Book#columnsName атрибут} а value это значение атрибута
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    public ArrayList<HashMap<String, String>> getRowsFromIndex(ArrayList<Integer> Indexes) throws Exception {
        ArrayList<HashMap<String, String>> books = new ArrayList<>();
        // Обходим все индексы
        for (int index : Indexes) {
            books.add(getRowFromIndex(index));
        }
        return books;
    }

    /**
     * Функция для получения всей информации о книгах в виде {@code ArrayList<HashMap<String, String>>}
     * записывает информацию в {@link Book#allData} с последующим доступом к ней через {@link Book#getAllDataList()}  геттер для атрибута} - {@link Book#allData}
     *
     * @return массив со словарями информации о книгах
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    public ArrayList<HashMap<String, String>> getAllData() throws Exception {
        getCountRows();
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        for (int index = 1; index < length; index++) {
            data.add(getRowFromIndex(index));
        }
        return data;
    }

    /**
     * Дополнительная функция для {@linkplain Book#deleteRow(int) deleteRow(int index)}
     * удаляет запись о книге по индексу из строки с заданным атрибутом
     *
     * @param column атрибут
     * @param index  индекс строки
     * @return возвращает удаленное значение
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    private String deleteRowFromColumn(String column, int index) throws Exception {
        StringBuilder saveData = new StringBuilder();
        String deleteValue;
        // начало считывания всех атрибутов
        startRead(column);
        // сохранение имени колонки атрибута
        saveData.append(readColumnName).append("\n");
        // считывание всех значений до индекса
        while (Index != index) {
            saveData.append(getLine()).append("\n");
        }
        // получение значения по индексу
        deleteValue = getLine();
        // считывание всех значений после индекса
        while (Index < length) {
            saveData.append(getLine()).append("\n");
        }
        // перезапись файла с атрибутами книг
        rewriteColumn(column, saveData.toString());
        return deleteValue;
    }

    /**
     * Удаляет информацию о книге по индексу
     *
     * @param index индекс
     * @return информация об удаленной книге
     * @throws Exception В случае если не получается открыть файл вызывает исключение
     */
    public HashMap<String, String> deleteRow(int index) throws Exception {
        getCountRows();
        HashMap<String, String> delRow = new HashMap<>();
        for (String column : columnsName) {
            delRow.put(column, deleteRowFromColumn(column, index));
        }
        length--;
        IDBooks.remove(Integer.parseInt(delRow.get("id")));
        return delRow;
    }

    /**
     * Удаляет информацию о книге по ID книги
     *
     * @param id Уникальный идентификатор книги
     * @return Удаляемая информация
     * @throws Exception В случае если не получается открыть файл вызывает исключение.
     *                   Если нет книги с таким ID
     */
    public HashMap<String, String> deleteRowByID(int id) throws Exception {
        if (!IDBooks.contains(id)) throw new Exception("Position read after desired position. Restart Reader!");
        int index = getIndexesFromValue("id", Integer.toString(id)).get(0);
        return deleteRow(index);
    }

    /**
     * Добавляет запись о новой книге
     *
     * @param newRow Информация о добавляемой книге. Имена атрибутов можно узнать через {@link Book#getColumnsName() имена атрибутов книги}
     * @throws Exception Если ID уже существует вызовет исключение. Все ID можно узнать через {@link Book#getIdBooks() getIdBooks() с перебором всех ID}
     *                   или {@link Book#getIDBooks() getIDBooks() через атрибут экземпляра} - {@link Book#IDBooks IDBooks}
     */
    public void addNewBook(HashMap<String, String> newRow) throws Exception {
        // Проверка на уникальность ID
        if (IDBooks.contains(Integer.parseInt(newRow.get("id")))) throw new Exception("ID already exist");
        for (String column : columnsName) {
            addToColumn(column, newRow.get(column) + "\n");
        }
        IDBooks.add(Integer.parseInt(newRow.get("id")));
        length++;
    }

    /**
     * Добавляет запись о новой книге
     *
     * @param id               Уникальный идентификатор книги. Все ID можно узнать через {@link Book#getIdBooks() getIdBooks() с перебором всех ID}
     *                         или {@link Book#getIDBooks() getIDBooks() через атрибут экземпляра} - {@link Book#IDBooks IDBooks}
     * @param name             Название книги
     * @param author           Автор
     * @param edition          Издание
     * @param publisher        Издатель
     * @param publication_year Год издания
     * @param category         Категория
     * @throws Exception Если ID уже существует, вызовет исключение.
     */
    public void addNewBook(int id, String name, String author, String edition, String publisher, int publication_year, String category) throws Exception {
        HashMap<String, String> addRow = new HashMap<>();
        addRow.put("id", Integer.toString(id));
        addRow.put("author", author);
        addRow.put("name", name);
        addRow.put("edition", edition);
        addRow.put("publisher", publisher);
        addRow.put("publication_year", Integer.toString(publication_year));
        addRow.put("category", category);
        addNewBook(addRow);
    }

    /**
     * Геттер атрибута {@link Book#columnsName} с названиями используемых атрибутов книги
     *
     * @return {@link Book#columnsName} - регистрируемые атрибуты книги
     */
    public String[] getColumnsName() {
        return columnsName;
    }

    /**
     * Геттер атрибута {@link Book#allData} со всей информации о книгах
     *
     * @return {@code ArrayList<HashMap<String, String>>} со словарями информации о книгах
     */
    public ArrayList<HashMap<String, String>> getAllDataList() {
        return allData;
    }

    /**
     * Геттер атрибута {@link Book#IDBooks} с ID всех книг
     *
     * @return ID всех записанных книг
     */
    public ArrayList<Integer> getIDBooks() {
        return IDBooks;
    }

    public int getLength() {
        return length;
    }
}
