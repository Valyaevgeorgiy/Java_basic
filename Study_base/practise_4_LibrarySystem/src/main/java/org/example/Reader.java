package org.example;

import java.util.ArrayList;

public class Reader extends Human {
    /**
     * Кол-во книг у читателя
     */
    public int bookAmount;
    /**
     * Какие именно id книг взял читатель
     */
    public ArrayList<Integer> bookIds;

    public Reader(int id, String name, String surname, String middleName, String address, int bookAmount, ArrayList<Integer> bookIds) {
        super(id, name, surname, middleName, address);
        this.bookAmount = bookAmount;
        this.bookIds = bookIds;
    }

    /**
     * Читатель берет книгу. Прося библиотечного работника
     * @param books экземпляр класса {@link Book}
     * @param libraryEmployee экземпляр класса {@link LibraryEmployee}
     * @param bookId для книги которую читатель хочет взять
     * @throws Exception При получении количества книг в библиотеке открывается файл с записью ID книг. В случае если не получается открыть файл вызывает исключение.
     */
    public void takeBook(Book books, LibraryEmployee libraryEmployee, int bookId) throws Exception {
        // Читатель просит библиотечного работника дать ему книгу
        libraryEmployee.giveBookToReader(books, this, bookId);
    }

    /**
     * Читатель возвращает книгу. Прося библиотечного работника
     * @param libraryEmployee экземпляр класса {@link LibraryEmployee}
     * @param bookId для книги которую читатель хочет вернуть
     */
    public void returnBook(LibraryEmployee libraryEmployee, int bookId){
        // Читатель дает книгу библиотечному
        // работнику и он возвращает книгу в библиотеку
        libraryEmployee.returnBookFromReader(this, bookId);
    }
}
