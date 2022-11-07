import java.util.Locale;
import java.util.Scanner;

public class MainClass {
    // public static final values
    enum DayOfWeek {
        SATURDAY("Суббота"),
        SUNDAY("Воскресенье"),
        MONDAY("Понедельник"),
        TUESDAY("Вторник"),
        WEDNESDAY("Среда"),
        FRIDAY("Пятница");
        private String representation;
        private DayOfWeek(String representation) {
            this.representation = representation;
        }
        String getRepresentation() {
            return representation;
        };
    }

    public static void main(String[] args){
        DayOfWeek day = DayOfWeek.SATURDAY;
        System.out.println(day);
        System.out.print("Enter day of week: ");
        Scanner in = new Scanner(System.in);
        day = DayOfWeek.valueOf(in.next().toUpperCase(Locale.ROOT));
        switch (day) {
            case SUNDAY -> {
                System.out.println("Sunday is holiday.");
            }
            case SATURDAY -> {
                System.out.println("Saturday is holiday");
            }
            default -> {
                System.out.println(String.format("%s is holiday", day.toString()));
            }
        }

        DayOfWeek[] days = DayOfWeek.values();
        for (DayOfWeek myDay: days) {
            System.out.println(myDay + " " + myDay.ordinal() + " " + myDay.getRepresentation());
        }
    }
}
