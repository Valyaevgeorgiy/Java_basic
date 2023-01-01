import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ManageEmployee {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {

        // Create Configuration
//        Configuration configuration = new Configuration();
//        configuration.configure("hibernate/xml/hibernate.cfg.xml");
//
//        // Create Session Factory
//        sessionFactory = configuration.buildSessionFactory();
//
//        // Initialize Session Object
//        Session session = sessionFactory.openSession();
//
//        // Get all entries using Criteria API
//        System.out.println("Getting all people using the Criteria API");
//        List<Employee> entriesCriteria=CommonMethods.getAllEntriesUsingCriteriaApi(session, Employee.class);
//        for(Employee entry:entriesCriteria)
//        {
//            System.out.println(entry);
//        }
//
//        // Get all entries using HQL
//        System.out.println("Getting all people using JPQL");
//        List<Employee> entriesHQL= CommonMethods.getAllEntriesUsingHQL(session, Employee.class);
//        for(Employee entry:entriesHQL)
//        {
//            System.out.println(entry);
//        }
//
//        System.out.println("Get all departments using JPQL");
//        List<Department> entriesDepartmentHQL= CommonMethods.getAllDepartmentsUsingHQL(session, Department.class);
//        for(Department entry:entriesDepartmentHQL)
//        {
//            System.out.println(entry);
//        }
//        session.close();
//
//        ManageEmployee manageEmployee = new ManageEmployee();
//
//        manageEmployee.getAllEmployees();
//        manageEmployee.getAllDepartments();
//
//        System.out.println("Get employee by ID");
//        manageEmployee.getEmployeeById(1);
//        manageEmployee.getEmployeeByName("John");
//        manageEmployee.getEmployeeByBirthDate("2000-01-01");
    }

    public void getAllEmployees(Session session) {
        Transaction tx = null;

        try {
//            tx = session.beginTransaction();
            List<Employee> employees = session.createQuery("FROM Employee").list();
            for (Employee employee : employees) {
                System.out.println("Employee:");
                System.out.println("\tID: " + employee.getId());
                System.out.println("\tFirst Name: " + employee.getFirstName());
                System.out.println("\tLast Name: " + employee.getSurname());
                System.out.println("\tBirth Date:" + employee.getBirthDate());
                System.out.println("\tBirth Place:" + employee.getBirthPlace());
                System.out.println("\tSalary:" + employee.getSalary());
                
                Department department = employee.getDepartment();
                if (department != null) {
                    System.out.println("\tDepartment: ");
                System.out.println("\t\tID: " + department.getId());
                System.out.println("\t\tTitle: " + department.getTitle());
                System.out.println("\t\tDescription: " + department.getDescription());
                System.out.println("\t\tDirector: " + department.getDirector());
                } else {
                    System.out.println("Департамент отсутствует");
                }
            }
//            tx.commit();
        } catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void getAllDepartments(Session session) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Department> departments = session.createQuery("FROM Department").list();
            for (Department department : departments) {
                System.out.println("Department:");
                System.out.println("\tID: " + department.getId());
                System.out.println("\tTitle: " + department.getTitle());
                System.out.println("\tDescription: " + department.getDescription());
                Employee director = department.getDirector();
                if (director != null) {
                    System.out.println("\tDirector: ");
                    System.out.println("\t\tID: " + director.getId());
                    System.out.println("\t\tFirstname: " + director.getFirstName());
                    System.out.println("\t\tSurname: " + director.getSurname());
                    System.out.println("\t\tSalary: " + director.getSalary());
                } else {
                    System.out.println("Директор отсутствует");
                }
                
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void getDepartmentById(Session session, int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Department> departments = session.createQuery(String.format("FROM Department WHERE id='%s'", id)).list();
            for (Department department : departments) {
                System.out.println("Department:");
                System.out.println("\tID: " + department.getId());
                System.out.println("\tTitle: " + department.getTitle());
                System.out.println("\tDescription: " + department.getDescription());
                
                Employee director = department.getDirector();
                if (director != null) {
                    System.out.println("\tDirector: ");
                    System.out.println("\t\tID: " + director.getId());
                    System.out.println("\t\tFirstname: " + director.getFirstName());
                    System.out.println("\t\tSurname: " + director.getSurname());
                    System.out.println("\t\tSalary: " + director.getSalary());
                } else {
                    System.out.println("\tDirector: ");
                    System.out.println("\t\tДиректор отсутствует");
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void updateDepartmentTitle(Session session, int id, String title){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Department department = (Department)session.get(Department.class, id);
            department.setTitle(title);
            session.update(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateDepartmentDescription(Session session, int id, String description){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Department department = (Department)session.get(Department.class, id);
            department.setDescription(description);
            session.update(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateDepartmentDirector(Session session, int id, Employee director){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Department department = (Department)session.get(Department.class, id);
            department.setDirector(director);
            session.save(department);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }



    public Integer addEmployee(Session session, String firstname, String surname, String birthDate, String birthPlace, int salary, Department department){
//        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer employeeID = null;

        try {
            tx = session.beginTransaction();
            Employee employee = new Employee(firstname, surname, birthDate, birthPlace, salary, department);
            employee.setBirthPlace(birthPlace);
            employee.setBirthDate(birthDate);
            employee.setSurname(surname);
            employeeID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    public void deleteEmployee(Session session, Integer employeeID){
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, employeeID);
            List<Department> departments = session.createQuery(String.format("FROM Department WHERE director='%s'", employeeID)).list();
            for (Department department: departments) {
                System.out.println(department);
                department.setDirector(null);
                //session.evict(department);
                //session.delete(department);
            }
            // session.evict(employee);
            // session.delete(employee);
            //session.detach(employee);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getEmployeeById(Session session, int employeeId) {
        Transaction tx = null;
        Employee employee = null;
        try {
            tx = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, employeeId);
            System.out.println(employee.toString());
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getEmployeeByName(Session session, String firstname) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Employee> employees = session.createQuery(String.format("FROM Employee WHERE firstName='%s'", firstname)).list();
            for (Employee each_employee: employees) {
                System.out.println(each_employee.toString());
            }

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getEmployeeByBirthDate(Session session, String birthDate) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Employee> employees = session.createQuery(String.format("FROM Employee WHERE birthDate='%s'", birthDate)).list();
            for (Employee each_employee: employees) {
                System.out.println(each_employee.toString());
            }

        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeId(Session session, Integer EmployeeID, int id){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Employee employee = (Employee)session.get(Employee.class, EmployeeID);

            Integer oldEmployeeId = employee.getId();
            session.evict(employee);
            employee.setId(id);
            session.save(employee);


            Query<Department> queryDeps = session.createQuery(String.format("FROM Department WHERE director='%d'", oldEmployeeId));
            List<Department> departments = queryDeps.getResultList();
            for (Department department: departments) {
                System.out.println(department.toString());
                department.setDirector(employee);
                session.save(department);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeName(Session session, Integer EmployeeID, String name ){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setFirstName(name);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeSurname(Session session, Integer EmployeeID, String surname ){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setSurname(surname);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeBirthdate(Session session, Integer EmployeeID, String birthdate){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setBirthDate(birthdate);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeBirthplace(Session session, Integer EmployeeID, String birthplace){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setBirthPlace(birthplace);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeSalary(Session session, Integer EmployeeID, int salary ){
//        Session session = session.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);
            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployeeDepartment(Session session, Integer EmployeeID, int departmentId ){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, EmployeeID);

            Department department = (Department)session.get(Department.class, departmentId);

            employee.setDepartment(department);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getAllSalary(Session session){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Long sumSalary = (Long) session.createQuery("SELECT sum(salary) from Employee")
                    .getSingleResult();
            System.out.println("Общая зарплата сотрудников: " + sumSalary);
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getEmployeesByDepartment(Session session, int departmentId){
        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            List<Employee> employees = session.createQuery(String.format("FROM Employee WHERE department='%s'", departmentId)).list();
            for (Employee employee : employees) {
                System.out.println("Employee:");
                System.out.println("\tID: " + employee.getId());
                System.out.println("\tFirst Name: " + employee.getFirstName());
                System.out.println("\tLast Name: " + employee.getSurname());
                System.out.println("\tBirth Date:" + employee.getBirthDate());
                System.out.println("\tBirth Place:" + employee.getBirthPlace());
                System.out.println("\tSalary:" + employee.getSalary());

                Department department = employee.getDepartment();
                System.out.println("\tDepartment: ");
                System.out.println("\t\tID: " + department.getId());
                System.out.println("\t\tTitle: " + department.getTitle());
                System.out.println("\t\tDescription: " + department.getDescription());
                System.out.println("\t\tDirector: " + department.getDirector());
            }
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}