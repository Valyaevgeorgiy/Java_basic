import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LambdaFunc {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add("David");

        ArrayList<String> filteredNames = (ArrayList<String>) names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());

        System.out.println(filteredNames);
    }
}
