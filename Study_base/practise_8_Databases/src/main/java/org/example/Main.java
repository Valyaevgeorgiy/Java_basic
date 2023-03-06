package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String DATABASE_URL = "jdbc:sqlite:mydb.db";

    public static void printouts(ResultSet res) {
        while (true) {
            try {
                if (!res.next()) break;
                System.out.println("ID: " + res.getInt("id"));
                System.out.println("Имя: " + res.getString("firstname"));
                System.out.println("Фамилия: " + res.getString("surname"));
                System.out.println("Дата рождения: " + res.getString("date_of_birth"));
                System.out.println("Место рождения: " + res.getString("place_of_birth"));
                System.out.println("Зарплата: " + res.getString("salary"));
                System.out.println("Семейное положение: " + res.getString("family_status"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {

        Menu mainMenu = new Menu();
        Menu changeMenu = new Menu();

        try (
            /*
            DriverManager:Этот элемент управляет списком драйверов БД.
            Каждой запрос на соединение требует соответствующего драйвера.
            Первое совпадение даёт нам соединение.
            Connection: Этот интерфейс обеспечивает нас методами для работы с БД.
            Все взаимодействия с БД происходят исключительно через Connection.
            */
                Connection conn = DriverManager.getConnection(DATABASE_URL);
            /*
            Statement: Для подтверждения SQL-запросов мы используем объекты,
            созданные с использованием этого интерфейса.
            */
                Statement stmt = conn.createStatement();
            /*
            ResultSet: Экземпляры этого элемента содержат данные, которые были получены в
            результате выполнения SQL – запроса. Он работает как итератор и “пробегает”
            по полученным данным.
            */
                ResultSet rs = stmt.executeQuery("SELECT * FROM people;");
        ) {
            changeMenu.addItem(1, "id", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                Integer id = in.nextInt();
                System.out.print("Введите новый ID: ");
                Integer new_id = in.nextInt();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET id = %s where id = %s", new_id, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            changeMenu.addItem(2, "name", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                String id = in.next();
                System.out.print("Введите новое имя работника: ");
                String new_name = in.next();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET firstname = '%s' where id = %s", new_name, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            changeMenu.addItem(3, "surname", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                String id = in.next();
                System.out.print("Введите новую фамилию работника: ");
                String new_surname = in.next();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET surname = '%s' where id = %s", new_surname, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            changeMenu.addItem(4, "date_of_birth", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                String id = in.next();
                System.out.print("Введите новую дату рождения работника: ");
                String new_date_of_birth = in.next();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET date_of_birth = '%s' where id = %s", new_date_of_birth, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            changeMenu.addItem(5, "place_of_birth", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                String id = in.next();
                System.out.print("Введите новое место рождения работника: ");
                String new_place_of_birth = in.next();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET place_of_birth = '%s' where id = %s", new_place_of_birth, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            changeMenu.addItem(6, "salary", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                String id = in.next();
                System.out.print("Введите новое значение зарплаты работника: ");
                String new_salary = in.next();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET salary = %s where id = %s", new_salary, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            changeMenu.addItem(7, "family_status", (stems, in) -> {
                System.out.print("Введите ID работника: ");
                String id = in.next();
                System.out.print("Введите новое семейное положение работника: ");
                String new_family_status = in.next();
                try {
                    stems.executeUpdate(String.format("UPDATE people SET family_status = %s where id = %s", new_family_status, id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });


            mainMenu.addItem(1, "Добавить работника", (stems, in) -> {
                try {
                    System.out.print("Имя: ");
                    String firstname = in.next();
                    System.out.print("Фамилия: ");
                    String surname = in.next();
                    System.out.print("Дата рождения: ");
                    String date_of_birth = in.next();
                    System.out.print("Место рождения: ");
                    String place_of_birth = in.next();
                    System.out.print("Заработная плата: ");
                    String salary = in.next();
                    System.out.print("Семейное положение: ");
                    String family_status = in.next();
                    stems.executeUpdate(String.format("INSERT INTO people(" +
                            "firstname, surname, date_of_birth, place_of_birth, salary, family_status) " +
                            "VALUES ('%s','%s','%s','%s', %s, %s)", firstname, surname, date_of_birth,
                            place_of_birth, salary, family_status)
                    );
                    System.out.println("Работник успешно добавлен в базу!");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(2, "Найти работника в базе по ID", (stems, in) -> {
                try {
                    System.out.print("Введите ID работника: ");
                    int id = in.nextInt();
                    System.out.println();
                    Main.printouts(stems.executeQuery("SELECT * FROM people WHERE id = " + id));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(3, "Найти работника в базе по его имени", (stems, in) -> {
                try {
                    System.out.print("Введите имя работника: ");
                    String name = in.next();
                    Main.printouts(stems.executeQuery("SELECT * FROM people WHERE firstname = '" + name + "'"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(4, "Найти работника в базе по его дате рождения", (stems, in) -> {
                try {
                    System.out.print("Введите дату рождения работника: ");
                    String date_of_birth = in.next();
                    Main.printouts(stems.executeQuery("SELECT * FROM people WHERE date_of_birth = '"
                            + date_of_birth + "'"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(5, "Изменить информацию в базе о работнике", (stems, in) -> {
                changeMenu.printAllMenu();
                System.out.print("Выберите пункт, который желаете изменить: ");
                int selectedOption = in.nextInt();
                changeMenu.run(selectedOption, stmt, in);
            });
            mainMenu.addItem(6, "Удалить работника", (stems, in) -> {
                try {
                    System.out.print("ID работника: ");
                    String id = in.next();
                    stems.executeUpdate(String.format("DELETE FROM people WHERE id = %s", id));
                    System.out.printf("Работник с ID = %s был успешно удалён из базы!", id);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(7, "Получить общую зарплату по базе работников", (stems, in) -> {
                try {
                    ResultSet res = stems.executeQuery("SELECT SUM(salary) FROM people;");
                    res.next();
                    System.out.println(String.format("Общая зарплата по базе равна %s у.е.", res.getString(1)));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            mainMenu.addItem(8, "Выйти", ((stems, in) -> {
                System.out.println("Сессия завершена!");
            }));

            Scanner in = new Scanner(System.in);
            do {
                mainMenu.printAllMenu();
                System.out.println();
                System.out.print("Что желаете сделать: ");
                int selectedOption = in.nextInt();
                if (selectedOption == 0 || selectedOption == 8) {
                    System.out.println();
                    System.out.println("Сессия завершена!");
                    break;
                }
                mainMenu.run(selectedOption, stmt, in);
            } while (true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}