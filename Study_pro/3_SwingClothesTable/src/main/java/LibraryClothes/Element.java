package LibraryClothes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

abstract public class Element {
    protected Map<Integer, String> countId = new HashMap<Integer, String>() {{
        put(1, "шт.");
        put(2, "дес.");
    }};
    protected int id;
    protected String name;
    protected int count;
    protected int countType;
    protected LocalDate shelfLife;
    protected String comment;

    public Element(int id, String name, LocalDate shelfLife, String comment) {
        this.id = id;
        this.name = name;
        this.shelfLife = shelfLife;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getShelfLife() {
        return shelfLife;
    }

    public String getComment() {
        return comment;
    }

    abstract public String getInfo();

    abstract public String getCount();
}
