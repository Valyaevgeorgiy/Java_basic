import java.util.*;

public class Set {

    public static void main(String[] args) {
        var set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add(new Integer(4));
        set.add(new Float(5.0f));
        set.add("two");
        set.add(new Integer(4));
        System.out.println(set);
    }

}
