package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Book books = new Book();

        System.out.println(books.getRowFromIndex(3));

        int selectedOption = -1;

        List<Reader> all_readers = new ArrayList<Reader>();

        Manager manager1 = new Manager(1, "First Manager", "Surname1",
                "MiddleName1", "address1", 1);
        LibraryEmployee libraryEmployee1 = new LibraryEmployee(2, "Second Manager", "Surname2",
                "MiddleName2", "address2", 2);
        ArrayList<Integer> reader_books = new ArrayList<Integer>();
        Reader reader1 = new Reader(2, "Second Manager", "Surname2",
                "MiddleName2", "address2", 0, reader_books);

        reader1.takeBook(books, libraryEmployee1, 1);
        reader1.takeBook(books, libraryEmployee1, 2);
        reader1.takeBook(books, libraryEmployee1, 3);


        all_readers.add(reader1);

        manager1.findBooksByReaders(all_readers);

        System.out.println(reader1.bookIds);
        System.out.println(reader1.bookAmount);
        manager1.findBooksDistribution(all_readers, books);
        System.out.println(manager1.findBooksByCategory("roman"));
        //System.out.println(books.getRowFromIndex(2));

        //manager1.createNewBook(1, "dDS", "Dasd", "dasdas", "dasd", 1999, "dasd");
        System.out.println(books.getRowFromIndex(1));
        System.out.println("Добро пожаловать в систему управления библиотекой!");
        while (selectedOption != 4) {
            System.out.println("""
                
                1 - Менеджер // Интерфейс управления
                2 - Читатель // Интерфейс читателя
                3 - Библиотечный работник // Интерфейс работника
                4 - Завершить работу
                
                Введите команду:
                """);
            Scanner menu = new Scanner(System.in);
            selectedOption = menu.nextInt();
            switch (selectedOption) {
                case (1) -> {
                    System.out.println("""
                                   Меню менеджера
                                   
                        1 - Менеджер // Создать новую книгу
                        2 - Менеджер // Сколько книг по конкретной теме
                        3 - Менеджер // Какие книги брал читатель
                        4 - Менеджер // Статистика по книгам
                        5 - Менеджер // Расширенная статистика по книгам
                        
                        Введите команду:
                        """);

                    Scanner manager_menu = new Scanner(System.in);
                    int managerOption = manager_menu.nextInt();
                    switch (managerOption) {
                        // Создать новую книгу
                        case(1) -> {
                            System.out.println("Введите ID новой книги: ");
                            Scanner book_create_scanner = new Scanner(System.in);
                            int book_id = book_create_scanner.nextInt();

                            System.out.println("Введите название новой книги: ");
                            Scanner name_scanner = new Scanner(System.in);
                            String name_scanned = name_scanner.nextLine();

                            System.out.println("Введите автора новой книги: ");
                            Scanner author_scanner = new Scanner(System.in);
                            String book_author = author_scanner.nextLine();

                            System.out.println("Введите издание новой книги: ");
                            Scanner edition_scanner = new Scanner(System.in);
                            String book_edition = edition_scanner.nextLine();

                            System.out.println("Введите издателя новой книги: ");
                            Scanner publisher_scanner = new Scanner(System.in);
                            String book_publisher = publisher_scanner.nextLine();

                            System.out.println("Введите год новой книги: ");
                            Scanner year_scanner = new Scanner(System.in);
                            int book_year = year_scanner.nextInt();

                            System.out.println("Введите тему новой книги: ");
                            Scanner category_scanner = new Scanner(System.in);
                            String book_category = category_scanner.nextLine();

                            try {
                                manager1.createNewBook(book_id, name_scanned, book_author, book_edition, book_publisher, book_year, book_category);
                                System.out.println(books.getRowFromIndex(books.getIndexesFromValue("id", String.valueOf(book_id)).get(0)));
                            } catch (Exception e) {
                                System.out.println("Ошибка при добавлении книги: " + e);
                            }
                        }
                        // Сколько книг по конкретной теме
                        case (2) -> {
                            System.out.println("Введите тему для поиска: ");
                            Scanner book_category_scanner = new Scanner(System.in);
                            String book_category = book_category_scanner.nextLine();
                            manager1.findBooksByCategory(book_category);
                        }
                        // Какие книги брал читатель
                        case (3) -> {
                            manager1.findBooksByReader(all_readers);
                        }
                        // Менеджер статистика по книгам
                        case (4) -> {
                            manager1.findBooksDistribution(all_readers, books);
                        }
                        // Менеджер расширенная статистика
                        case (5) -> {
                            manager1.getAdvancedStatistics(books, libraryEmployee1);
                        }
                    }
                }
                case (2) -> {
                    System.out.println("""
                                   Меню читателя
                  
                        Введите ID читателя для работы с ним:
                        """);
                    Scanner reader_scanner = new Scanner(System.in);
                    int reader_id = reader_scanner.nextInt();
                    Reader ourReader = null;
                    for (Reader reader:
                            all_readers) {
                        if (reader.id == reader_id) {
                            System.out.println("Читатель с ID " + reader_id + " найден");
                            ourReader = reader;
                            System.out.println("""
                                   Меню читателя
                                   
                            1 - Читатель // Попросить работника выдать книгу
                            2 - Читатель // Вернуть книгу в библиотеку
                            3 - Читатель // Попросить работника выдать книгу по заданному критерию
                            """);
                            Scanner reader_menu = new Scanner(System.in);
                            int readerOption = reader_menu.nextInt();
                            switch (readerOption) {
                                // Попросить работника выдать книгу по ID
                                case (1) -> {
                                    System.out.println("Введите ID книги, чтобы взять: ");
                                    Scanner reader_scanner_1 = new Scanner(System.in);
                                    int book_id = reader_scanner_1.nextInt();
                                    libraryEmployee1.giveBookToReader(books, ourReader, book_id);
                                }
                                // Попросить работника отдать книгу по ID
                                case (2) -> {
                                    System.out.println("Введите ID книги, чтобы отдать: ");
                                    Scanner reader_scanner_2 = new Scanner(System.in);
                                    int book_id = reader_scanner_2.nextInt();
                                    libraryEmployee1.returnBookFromReader(ourReader, book_id);
                                }
                                // Попросить работника выдать первую попавшуюся книгу по заданному критерию
                                case (3) -> {
                                    System.out.println("""
                                    1 - Выдать по названию
                                    2 - Выдать по автору
                                    3 - Выдать по теме
                                    4 - Выдать по году издания
                                    5 - Выдать по издателю
                                    
                                    Введите номер интересующего критерия для выдачи:
                                    """);
                                    HashMap<String, String> criteria_take = new HashMap<>();

                                    Scanner reader_take_menu = new Scanner(System.in);
                                    int readerTake = reader_take_menu.nextInt();
                                    switch (readerTake) {
                                        case (1) -> {
                                            System.out.println("Введите название для выдачи: ");

                                            Scanner criteria_name = new Scanner(System.in);
                                            String user_criteria = criteria_name.nextLine();
                                            criteria_take.put("name", user_criteria);
                                            libraryEmployee1.giveBookByOption(books, ourReader, criteria_take, manager1.findBooksByReaders(all_readers));
                                        }
                                        case (2) -> {
                                            System.out.println("Введите автора для выдачи: ");

                                            Scanner criteria_name = new Scanner(System.in);
                                            String user_criteria = criteria_name.nextLine();
                                            criteria_take.put("author", user_criteria);
                                            libraryEmployee1.giveBookByOption(books, ourReader, criteria_take, manager1.findBooksByReaders(all_readers));
                                        }
                                        case (3) -> {
                                            System.out.println("Введите тему для поиска: ");

                                            Scanner criteria_category = new Scanner(System.in);
                                            String user_category = criteria_category.nextLine();
                                            criteria_take.put("category", user_category);
                                            libraryEmployee1.giveBookByOption(books, ourReader, criteria_take, manager1.findBooksByReaders(all_readers));
                                        }
                                        case (4) -> {
                                            System.out.println("Введите год издания для поиска: ");

                                            Scanner criteria_pubYear = new Scanner(System.in);
                                            String user_pubYear = criteria_pubYear.nextLine();
                                            criteria_take.put("publication_year", user_pubYear);
                                            libraryEmployee1.giveBookByOption(books, ourReader, criteria_take, manager1.findBooksByReaders(all_readers));
                                        }
                                        case (5) -> {
                                            System.out.println("Введите издателя для поиска: ");

                                            Scanner criteria_publisher = new Scanner(System.in);
                                            String user_publisher = criteria_publisher.nextLine();
                                            criteria_take.put("publisher", user_publisher);
                                            libraryEmployee1.giveBookByOption(books, ourReader, criteria_take, manager1.findBooksByReaders(all_readers));
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("Читатель с ID " + reader_id + " не найден");
                        }
                    }
                }
                case (3) -> {
                    System.out.println("""
                                Меню библиотечного работника
                        1 - Библиотечный работник // Искать книги по заданному критерию
                        
                        Введите команду:
                        """);
                    Scanner reader_menu = new Scanner(System.in);
                    int readerOption = reader_menu.nextInt();
                    switch (readerOption) {
                        case (1) ->  {
                            System.out.println("""
                            1 - Поиск по названию
                            2 - Поиск по автору
                            3 - Поиск по теме
                            4 - Поиск по году издания
                            5 - Поиск по издателю
                            
                            Введите номер интересующего поиска:
                            """);
                            Scanner reader_option = new Scanner(System.in);
                            int reader_criteria = reader_option.nextInt();
                            HashMap<String, String> criteria = new HashMap<>();
                            switch (reader_criteria) {
                                // Поиск по названию
                                case (1) -> {
                                    System.out.println("Введите название для поиска: ");

                                    Scanner criteria_name = new Scanner(System.in);
                                    String user_criteria = criteria_name.nextLine();
                                    criteria.put("name", user_criteria);
                                    System.out.println(libraryEmployee1.getBooksByCriteria(books, criteria));
                                }
                                // Поиск по автору
                                case (2) -> {
                                    System.out.println("Введите автора для поиска: ");

                                    Scanner criteria_author = new Scanner(System.in);
                                    String user_author = criteria_author.nextLine();
                                    criteria.put("author", user_author);
                                    System.out.println(libraryEmployee1.getBooksByCriteria(books, criteria));
                                }
                                // Поиск по теме
                                case (3) -> {
                                    System.out.println("Введите тему для поиска: ");

                                    Scanner criteria_category = new Scanner(System.in);
                                    String user_category = criteria_category.nextLine();
                                    criteria.put("category", user_category);
                                    System.out.println(libraryEmployee1.getBooksByCriteria(books, criteria));
                                }
                                // Поиск по году издания
                                case (4) -> {
                                    System.out.println("Введите год издания для поиска: ");

                                    Scanner criteria_pubYear = new Scanner(System.in);
                                    String user_pubYear = criteria_pubYear.nextLine();
                                    criteria.put("publication_year", user_pubYear);
                                    System.out.println(libraryEmployee1.getBooksByCriteria(books, criteria));
                                }
                                // Поиск по издателю
                                case (5) -> {
                                    System.out.println("Введите издателя для поиска: ");

                                    Scanner criteria_publisher = new Scanner(System.in);
                                    String user_publisher = criteria_publisher.nextLine();
                                    criteria.put("publisher", user_publisher);
                                    System.out.println(libraryEmployee1.getBooksByCriteria(books, criteria));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
