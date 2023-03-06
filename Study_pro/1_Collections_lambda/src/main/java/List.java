import java.util.ArrayList;
import java.util.Locale;

public class List {

    public static void main(String[] args) {
        //List list = new ArrayList();
        var list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        //list.add(new Integer(4));
        //list.add(new Float(5.0f));
        list.add("two");
        //list.add(new Integer(4));
        System.out.println(list);
    }
}
