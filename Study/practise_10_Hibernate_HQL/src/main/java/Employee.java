import jakarta.persistence.*;

@Entity
@Table(name = "people")
public class Employee {
    @Id
    @GeneratedValue    (strategy=GenerationType.SEQUENCE,
            generator="users_seq")
    @SequenceGenerator (name="users_seq",
            sequenceName="SEQ_USER",
            allocationSize=5)
    @Column (name="id")
    int id;

    @Column(name = "firstname")
    String firstName;
    @Column(name = "surname")
    String surname;
    @Column(name = "date_of_birth")
    String birthDate;

    public Employee() {

    }
    public Employee(String firstName, String surname, String birthDate, String birthPlace, int salary, Department department) {
        this.firstName = firstName;
        this.surname = surname;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.salary = salary;
        this.department = department;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "place_of_birth")
    String birthPlace;

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    @Column(name = "salary")
    int salary;

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    // @Column(name = "department")
    // @JoinTable(name = "people",
    //         joinColumns = @JoinColumn(name = "id"),
    //         inverseJoinColumns = @JoinColumn(name = "department"))
    Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public void setAddress(String birthPlace) {
        this.birthPlace = birthPlace;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", birthplace='" + birthPlace + '\'' +
                ", salary='" + salary + '\'' +
                ", department='" + department.getId() + '\'' +
                '}';
    }
}
