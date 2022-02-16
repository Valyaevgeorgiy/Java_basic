
public class Student {

    private String name;
    private String secondName;
    private String lastName;

    public String getName() { // getter класса Student
        return name;
    }

    public String getSecondName() { // getter класса Student
        return secondName;
    }

    public String getLastName() { // getter класса Student
        return lastName;
    }

    public void setFullName(String name, String secName, String lasName) { // setterы класса Student
        this.name = name;
        this.secondName = secName;
        this.lastName = lasName;
    }

    // Чтобы вызвать эти методы, потребуется создание в другом классе экземпляра
    // этого класса

    public String printFullName() {
        return ("Your Full Name:" + " " + this.secondName + " " + this.name + " " + this.lastName);
    }
}