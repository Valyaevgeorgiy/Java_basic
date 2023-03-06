package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private static int counter = 0;
    public static boolean isExit = true;
    public static boolean isEnd = false;
    public static int choice;
    private int[] idArray;
    private static int id;

    private static ArrayList<String> nameArray = new ArrayList<>();
    private static String name = "";

    private static ArrayList<String> surNameArray = new ArrayList<>();
    private static String surName = "";

    private static ArrayList<Integer> birthYearArray = new ArrayList<>();
    private static int birthYear;

    private static ArrayList<String> birthPlaceArray = new ArrayList<>();
    private static String birthPlace = "";

    private static ArrayList<Integer> salaryArray = new ArrayList<>();
    private static int salary;

    private static ArrayList<String> famStatusArray = new ArrayList<>();
    private static String famStatus = "";

    public static String getName(int id) {
        return nameArray.get(id - 1);
    }

    public static void setName(String name, int id) {
        if (nameArray.isEmpty()) {
            nameArray.add(name);
        } else {
            nameArray.set(id - 1, name);
        }
    }

    public static String getSurName(int id) {
        return surNameArray.get(id - 1);
    }

    public static void setSurName(String surName, int id) {
        if (surNameArray.isEmpty()) {
            surNameArray.add(surName);
        } else {
            surNameArray.set(id - 1, surName);
        }
    }

    public static String getBirthPlace(int id) {
        return birthPlaceArray.get(id - 1);
    }

    public static void setBirthPlace(String birthPlace, int id) {
        if (birthPlaceArray.isEmpty()) {
            birthPlaceArray.add(birthPlace);
        } else {
            birthPlaceArray.set(id - 1, birthPlace);
        }
    }

    public static String getFamStatus(int id) {
        return famStatusArray.get(id - 1);
    }

    public static void setFamStatus(String famStatus, int id) {
        if (famStatusArray.isEmpty()) {
            famStatusArray.add(famStatus);
        } else {
            famStatusArray.set(id - 1, famStatus);
        }
    }

    public static int getBirthYear(int id) {
        return birthYearArray.get(id - 1);
    }

    public static void setBirthYear(int birthYear, int id) {
        if (birthYearArray.isEmpty()) {
            birthYearArray.add(birthYear);
        } else {
            birthYearArray.set(id - 1, birthYear);
        }
    }

    public static int getSalary(int id) {
        return salaryArray.get(id - 1);
    }

    public static void setSalary(int salary, int id) {
        if (salaryArray.isEmpty()) {
            salaryArray.add(salary);
        } else {
            salaryArray.set(id - 1, salary);
        }
    }

    public static int getCounter() {
        return counter;
    }

    public static void getFullInfo(int id) {
        String infName = getName(id);
        String infSurName = getSurName(id);
        int infBirthYear = getBirthYear(id);
        String infBirthPlace = getBirthPlace(id);
        int infSalary = getSalary(id);
        String infFamStatus = getFamStatus(id);

        System.out.println("Имя: "+infName+"\nФамилия: "+infSurName+"\nГод рождения: "+infBirthYear
                +"\nМесто рождения: "+infBirthPlace+"\nЗарплата: "+infSalary+"\nСемейное положение: "+infFamStatus);
    }

    public static void getFullInfoByName(String name) {
        for (int i = 0; i < nameArray.size(); i++) {
            if (nameArray.get(i).toLowerCase().equals(name.toLowerCase())) {
                Employee.getFullInfo(i + 1);
            }
        }
    }

    public static void getFullInfoByBirthYear(int birthYear) {
        for (int i = 0; i < birthYearArray.size(); i++) {
            if (birthYearArray.get(i).equals(birthYear)) {
                Employee.getFullInfo(i + 1);
            }
        }
    }

    public static void getFullSalary() {
        int finalSalary = 0;
        for (int i = 0; i < salaryArray.size(); i++) {
            finalSalary = finalSalary + salaryArray.get(i);
        }
        System.out.println(finalSalary+" у.е.");
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Добро пожаловать в архив информации сотрудников фирмы!");
        System.out.println("Выберите из списка, что желаете выполнить:");
        while (isExit) {
            System.out.println(
                    """
                        ================ Главное меню =============
                        1 - Ввести любое количество сотрудников.
                        2 - Искать сотрудника по id и печатать его информацию.
                        3 - Искать сотрудника по имени или году рождения.
                        4 - Изменить любую информацию о сотруднике.
                        5 - Рассчитать общую сумму денег, которые сотрудники получают в качестве заработной платы.
                        6 - Завершить поиск в архиве (exit).
                    """);
            choice = in.nextInt();
            if (choice == 1){
                do {
                    System.out.println("Ввод данных сотрудника: ");
                    id++;
                    System.out.print("Ввод имени => ");
                    name = in.next();
                    Employee.nameArray.add(name);

                    System.out.print("Ввод фамилии => ");
                    surName = in.next();
                    Employee.surNameArray.add(surName);

                    System.out.print("Ввод года рождения => ");
                    birthYear = in.nextInt();
                    Employee.birthYearArray.add(birthYear);

                    System.out.print("Ввод места рождения => ");
                    birthPlace = in.next();
                    Employee.birthPlaceArray.add(birthPlace);

                    System.out.print("Ввод зарплаты => ");
                    salary = in.nextInt();
                    Employee.salaryArray.add(salary);

                    System.out.print("Ввод семейного положения => ");
                    famStatus = in.next();
                    Employee.famStatusArray.add(famStatus);

                    counter++;

                    System.out.println("Информация о новом сотруднике (№ "+counter+"):");
                    System.out.println("Имя: "+name+"\nФамилия: "+surName+"\nГод рождения: "+birthYear
                            +"\nМесто рождения: "+birthPlace+"\nЗарплата: "+salary
                            +"\nСемейное положение: "+famStatus);

                    System.out.print("Желаете добавить ещё одного? (true/false) => ");
                    boolean end = in.nextBoolean();
                    if (end) {
                        continue;
                    } else {
                        break;
                    }
                } while (Employee.getCounter() < 100);
                System.out.println("Добавление сотрудников приостановлено!");
            } else if (choice == 2) {
                do {
                    System.out.print("Введите id (от 1 до 100) для поиска сотрудника => ");
                    int searchId = in.nextInt();
                    Employee.getFullInfo(searchId);

                    System.out.print("Желаете продолжить поиск? (true/false) => ");
                    isEnd = in.nextBoolean();
                } while (isEnd);
            } else if (choice == 3) {
                isEnd = true;
                do {
                    System.out.println("Что вы знаете о сотруднике?\nИмя (введите 1) или год рождения (введите 2)?");
                    int searchNum = in.nextInt();
                    if (searchNum == 1){
                        System.out.print("Введите имя сотрудника (с заглавной буквы): ");
                        String searchName = in.next();
                        Employee.getFullInfoByName(searchName);
                    } else if (searchNum == 2) {
                        System.out.print("Введите год рождения: ");
                        int searchBirthYear = in.nextInt();
                        Employee.getFullInfoByBirthYear(searchBirthYear);
                    }

                    System.out.print("Желаете продолжить поиск? (true/false) => ");
                    isEnd = in.nextBoolean();
                } while (isEnd);
            } else if (choice == 4) {
                System.out.print("Введите id (от 1 до 100) для поиска сотрудника => ");
                int searchId = in.nextInt();

                System.out.println("На данный момент об этом сотруднике следующая информация: ");
                Employee.getFullInfo(searchId);

                System.out.println(
                        """
                            Какую информацию желаете изменить об этом сотруднике?
                            1 - Имя.
                            2 - Фамилия.
                            3 - Год рождения.
                            4 - Место рождения.
                            5 - Зарплата.
                            6 - Семейное положение.
                        """);
                int setPoint = in.nextInt();
                if (setPoint == 1) {
                    System.out.print("На какое имя желаете поменять: ");
                    String newName = in.next();
                    Employee.setName(newName, searchId);
                } else if (setPoint == 2) {
                    System.out.print("На какую фамилию желаете поменять: ");
                    String newSurName = in.next();
                    Employee.setSurName(newSurName, searchId);
                } else if (setPoint == 3) {
                    System.out.print("На какой год рождения желаете поменять: ");
                    int newBirthYear = in.nextInt();
                    Employee.setBirthYear(newBirthYear, searchId);
                } else if (setPoint == 4) {
                    System.out.print("На какое место рождения желаете поменять: ");
                    String newBirthPlace = in.next();
                    Employee.setBirthPlace(newBirthPlace, searchId);
                } else if (setPoint == 5) {
                    System.out.print("На какую зарплату желаете поменять: ");
                    int newSalary = in.nextInt();
                    Employee.setSalary(newSalary, searchId);
                } else if (setPoint == 6) {
                    System.out.print("На какое семейное положение желаете поменять: ");
                    String newFamStatus = in.next();
                    Employee.setFamStatus(newFamStatus, searchId);
                }

                System.out.println("После изменений информация о сотруднике приобрела следующий вид: ");
                Employee.getFullInfo(searchId);
            } else if (choice == 5) {
                System.out.println("Общая выручка сотрудников:");
                Employee.getFullSalary();
            } else if (choice == 6) {
                isExit = false;
            } else {
                System.out.println("Неправильно определён номер в меню. Попробуйте снова!");
            }
        }
        System.out.println("До новых встреч!!!");
    }
}
