package org.example;

public class Employee extends Human{
    public int employeeId;

    public Employee(int id, String name, String surname, String middleName, String address, int employeeId) {
        super(id, name, surname, middleName, address);
        this.employeeId = employeeId;

    }
}
