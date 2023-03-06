// класс Person (int age, String firstName, String
// lastName, LocalDate birthDate, int weight);

// Задание
// 6) Уменьшение веса каждого объекта на 5, фильтрация по дате рождения
// меньшей, чем 3 февраля 1999, конкатенация фамилий в строку через пробел.

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Person {
    private int age;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private int weight;

    public Person(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight = weight;
    }

    public int getAge() { return age; }

    public String getFirstName() { return firstName; }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getWeight() {
        return weight;
    }

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();

        Random rand = new Random();
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Eva", "Alex", "Simon"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Lax", "Bronson"};

        for (int i = 0; i < 5; i++) {
            String firstName = firstNames[rand.nextInt(firstNames.length)];
            String lastName = lastNames[rand.nextInt(lastNames.length)];

            // генерация возраста от 18 до 30 лет
            int age = rand.nextInt(18) + 12;
            LocalDate dateOfBirth = LocalDate.now().minusYears(age).minusDays(365);

            // генерация веса от 50 до 110 кг
            int weight = rand.nextInt(60) + 50;

            Person person = new Person(age, firstName, lastName, dateOfBirth, weight);
            people.add(person);
        }

        System.out.println("\nПредставление первоначальных данных о студентах: ");
        for (Person person: people) {
            System.out.println(person.getFirstName() + " " + person.getLastName() + " (" + person.getAge() + " лет, родился(-ась) " + person.getBirthDate() + ", вес = " + person.getWeight() + " килограмм)");
        }

        // уменьшение веса на 5 у каждого студента
        ArrayList<Person> reducePersonWeights = (ArrayList<Person>) people.stream()
                .map(person -> {
                    return new Person(
                            person.getAge(),
                            person.getFirstName(),
                            person.getLastName(),
                            person.getBirthDate(),
                            person.weight - 5);
                }).collect(Collectors.toList());
        System.out.println("\nДанные о студентах после процедуры уменьшение веса: ");
        for (Person person: reducePersonWeights) {
            System.out.println(person.getFirstName() + " " + person.getLastName() + " (" + person.getAge() + " лет, родился(-ась) " + person.getBirthDate() + ", вес = " + person.getWeight() + " килограмм)");
        }

        // фильтрация по датам рождения и конкатенация фамилий в одной лямбда-функции
        LocalDate filterDate = LocalDate.of(1999, 2, 3);
        List<Person> filteredDatePersons = reducePersonWeights.stream()
                .filter(person -> person.getBirthDate().isBefore(filterDate))
                .collect(Collectors.toList());

        System.out.println("\nДанные после фильтрации по дате рождения: ");
        for (Person person: filteredDatePersons) {
            System.out.println(person.getFirstName() + " " + person.getLastName() + " (" + person.getAge() + " лет, родился(-ась) " + person.getBirthDate() + ", вес = " + person.getWeight() + " килограмм)");
        }

        List<String> LastNames = filteredDatePersons.stream()
                .map(Person::getLastName)
                .collect(Collectors.toList());

        System.out.println("\nДанные после конкатенации фамилий: ");
        String concatenatedLastNames = String.join(" ", LastNames);
        System.out.println(concatenatedLastNames);
    }
}
