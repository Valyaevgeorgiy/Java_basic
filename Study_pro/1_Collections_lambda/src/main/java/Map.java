import java.util.Collection;
import java.util.HashMap;

public class Map {

    public static void main(String[] args) {
        var map = new HashMap<String, String>();
        map.put("one", "1st");
        map.put("second", new Integer(2).toString());
        map.put("third", "3rd");
        map.put("third", "III");
        var keys = map.keySet();
        Collection vals = map.values();
        var entries = map.entrySet();
        System.out.println(keys + "\n" + vals + "\n" + entries);
    }

}
