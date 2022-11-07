package Task3;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MainClass {

    public static boolean isEnd = true;
    public static int choice;
    public static void main(String[] args) {

        /*
        Используйте класс PriorityQueue и интерфейс Comparator для создания программы, организующей
        очередь клиентов в компании. Программа упорядочивает клиентов в порядке их прибытия, но
        клиенты, прибывающие из дальних городов, имеют более высокий приоритет, чем местные жители
        или близлежащие города. Ваша программа должна позволять оператору вводить информацию о
        клиентах и вызвать клиентов в правильном порядке для их обслуживания.
        */

        Scanner in = new Scanner(System.in);
        PriorityQueue<Client> queueClients = new PriorityQueue<Client>(10, new ClientComparator());
        Client numberOne = new Client("Edge", (float) 100.00);
        queueClients.add(numberOne);

        Client numberTwo = new Client("Max", (float) 100.00);
        queueClients.add(numberTwo);

        Client numberThree = new Client("Daniel", (float) 2389.10);
        queueClients.add(numberThree);

        Client numberFour = new Client("Nikolas", (float) 456.89);
        queueClients.add(numberFour);

        Client numberFive = new Client("Elton", (float) 35.75);
        queueClients.add(numberFive);

        while (isEnd) {
            System.out.println("""
                        ================ Меню =============
                        1 - Вывести весь список клиентов.
                        2 - Обслужить клиента.
                        3 - Добавить нового клиента.
                        4 - Выйти.
                    """);
            System.out.print("Выберите пункт из меню выше: ");
            choice = in.nextInt();
            switch (choice) {
                case (1) -> {
                    System.out.println();
                    System.out.println("Очередь клиентов выстроена следующим образом:");
                    for (Client client: queueClients) {
                        System.out.println("(" + client.getName() + ", " + client.getDist() + ")");
                    }
                    System.out.println();
                }
                case (2) -> {
                    System.out.println();
                    Client nowCl = queueClients.poll();
                    System.out.print("Обслужен следующий клиент: ");
                    System.out.println("("+ nowCl.getName() + ", " + nowCl.getDist() + ")");
                    System.out.println();
                }
                case (3) -> {
                    System.out.println();
                    System.out.print("Введите имя нового клиента: ");
                    String name = in.next();
                    System.out.print("Введите расстояние до офиса компании для клиента: ");
                    Float dist = Float.parseFloat(in.next());
                    try {
                        queueClients.add(new Client(name, dist));
                        System.out.println("Клиент был успешно добавлен в очередь!");
                    } catch (Exception e) {
                        System.out.print("Добавление произошло неуспешно вследствие следующей ошибки: ");
                        e.printStackTrace();
                    }
                    System.out.println();
                }
                default -> {
                    System.out.println("Выход выполнен успешно!");
                    isEnd = false;
                }
            }
        }

    }

}
