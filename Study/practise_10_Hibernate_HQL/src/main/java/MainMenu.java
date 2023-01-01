import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    static final String DATABASE_URL = "jdbc:sqlite:mydb.db";
    private static SessionFactory sessionFactory;

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
        Menu menu = new Menu();
        Menu changeMenu = new Menu();
        Menu changeDepartmentMenu = new Menu();

        Configuration configuration = new Configuration();
        configuration.configure("hibernate/xml/hibernate.cfg.xml");
//
        // Create Session Factory
        sessionFactory = configuration.buildSessionFactory();

        // Initialize Session Object
        Session session = sessionFactory.openSession();

        // Get all entries using Criteria API
        System.out.println("Getting all people using the Criteria API");
        List<Employee> entriesCriteria=CommonMethods.getAllEntriesUsingCriteriaApi(session, Employee.class);
        for(Employee entry:entriesCriteria)
        {
            System.out.println(entry);
        }

        // Get all entries using HQL
        System.out.println("Getting all people using JPQL");
        List<Employee> entriesHQL= CommonMethods.getAllEntriesUsingHQL(session, Employee.class);
        for(Employee entry:entriesHQL)
        {
            System.out.println(entry);
        }

        System.out.println("Get all departments using JPQL");
        List<Department> entriesDepartmentHQL= CommonMethods.getAllDepartmentsUsingHQL(session, Department.class);
        for(Department entry:entriesDepartmentHQL)
        {
            System.out.println(entry);
        }
//        //session.close();

        ManageEmployee manageEmployee = new ManageEmployee();

        manageEmployee.getAllEmployees(session);
        manageEmployee.getAllDepartments(session);
//
//        System.out.println("Get employee by ID");
//        manageEmployee.getEmployeeById(1);
//        manageEmployee.getEmployeeByName("John");
//        manageEmployee.getEmployeeByBirthDate("2000-01-01");

        try {
            changeMenu.addItem(1, "id", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новый ID сотрудника: ");
                int newId = in.nextInt();
                manageEmployee.updateEmployeeId(sessionFactory.openSession(), existingId, newId);
            });
            changeMenu.addItem(2, "name", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новое имя сотрудника: ");
                String newName = in.next();
                manageEmployee.updateEmployeeName(sessionFactory.openSession(), existingId, newName);
            });
            changeMenu.addItem(3, "surname", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новую фамилию сотрудника: ");
                String newSurname = in.next();
                manageEmployee.updateEmployeeSurname(sessionFactory.openSession(), existingId, newSurname);
            });
            changeMenu.addItem(4, "date_of_birth", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новую дату рождения сотрудника: ");
                String newDate = in.next();
                manageEmployee.updateEmployeeBirthdate(sessionFactory.openSession(), existingId, newDate);
            });
            changeMenu.addItem(5, "place_of_birth", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новое место рождения сотрудника: ");
                String newPlace = in.next();
                manageEmployee.updateEmployeeBirthplace(sessionFactory.openSession(), existingId, newPlace);
            });
            changeMenu.addItem(6, "salary", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новую зарплату сотрудника: ");
                int newSalary = in.nextInt();
                manageEmployee.updateEmployeeSalary(sessionFactory.openSession(), existingId, newSalary);
            });
            changeMenu.addItem(7, "department", (stems, in) -> {
                System.out.println("Введите ID сотрудника, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новый департамент: ");
                int newId = in.nextInt();
                manageEmployee.updateEmployeeDepartment(sessionFactory.openSession(), existingId, newId);
            });
            changeDepartmentMenu.addItem(1, "id", (stems, in) -> {
                System.out.println("Введите ID департамента, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новое название департамента: ");
                String newName = in.next();
                manageEmployee.updateDepartmentTitle(sessionFactory.openSession(), existingId, newName);
            });
            changeDepartmentMenu.addItem(2, "description", (stems, in) -> {
                System.out.println("Введите ID департамента, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите новое описание департамента: ");
                String newName = in.next();
                manageEmployee.updateDepartmentDescription(sessionFactory.openSession(), existingId, newName);
            });
            changeDepartmentMenu.addItem(3, "director", (stems, in) -> {
                System.out.println("Введите ID департамента, чтобы изменить: ");
                int existingId = in.nextInt();
                System.out.println("Введите ID нового директора департамента: ");
                int directorId = in.nextInt();
                Session session3 = sessionFactory.openSession();
                Employee employee3 = (Employee) session3.get(Employee.class, directorId);
                manageEmployee.updateDepartmentDirector(sessionFactory.openSession(), existingId, employee3);
            });
            menu.addItem(1, "Добавить сотрудника", (stems, in) -> {
                System.out.print("firstname: ");
                String firstname = in.next();
                System.out.print("surname: ");
                String surname = in.next();
                System.out.print("date_of_birth: ");
                String date_of_birth = in.next();
                System.out.print("place_of_birth: ");
                String place_of_birth = in.next();
                System.out.print("salary: ");
                int salary = in.nextInt();
                System.out.print("department: ");
                int department = in.nextInt();
                Session session1 = sessionFactory.openSession();

                Department department1 = (Department) session1.get(Department.class, department);
                Integer newEmployeeId = manageEmployee.addEmployee(session1, firstname, surname, date_of_birth, place_of_birth, salary, department1);

                System.out.printf("Сотрудник ID: %d успешно добавлен в базу!%n", newEmployeeId);
            });
            menu.addItem(2, "Получить сотрудника по ID", (stems, in) -> {
                System.out.print("Введите ID: ");
                manageEmployee.getEmployeeById(sessionFactory.openSession(), in.nextInt());
            });
            menu.addItem(3, "Получить сотрудника по имени", (stems, in) -> {
                System.out.print("Введите Имя: ");
                manageEmployee.getEmployeeByName(sessionFactory.openSession(), in.next());
            });
            menu.addItem(4, "Получить сотрудника по дате рождения", (stems, in) -> {
                System.out.print("Введите дату рождения: ");
                manageEmployee.getEmployeeByBirthDate(sessionFactory.openSession(), in.next());
            });
            menu.addItem(5, "Изменить информацию о сотруднике", (stems, in) -> {
                changeMenu.printMenu();
                System.out.print(": ");
                int selectedOption = in.nextInt();
                changeMenu.run(selectedOption, null, in);
            });
            menu.addItem(6, "Удалить сотрудника", (stems, in) -> {
                System.out.println("Введите ID сотрудника чтобы удалить: ");
                manageEmployee.deleteEmployee(sessionFactory.openSession(), in.nextInt());
            });
            menu.addItem(7, "Получить общую зарплату", (stems, in) -> {
                manageEmployee.getAllSalary(sessionFactory.openSession());
            });
            menu.addItem(8, "Получить сотрудников по департаменту", (stems, in) -> {
                System.out.println("Введите ID департамента: ");
                manageEmployee.getEmployeesByDepartment(sessionFactory.openSession(), in.nextInt());
            });
            menu.addItem(9, "Вывести все информации о департаменте", (stems, in) -> {
                System.out.println("Введите ID департамента: ");
                manageEmployee.getDepartmentById(sessionFactory.openSession(), in.nextInt());
            });
            menu.addItem(10, "Изменить любую информацию о департаменте.", (stems, in) -> {
                changeDepartmentMenu.printMenu();
                System.out.print(": ");
                int selectedOption = in.nextInt();
                changeDepartmentMenu.run(selectedOption, null, in);
            });
            Scanner in = new Scanner(System.in);
            do {
                menu.printMenu();
                //System.out.print("");
                int selectedOption = in.nextInt();
                menu.run(selectedOption, sessionFactory, in);
            } while (true);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

}