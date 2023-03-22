package LibraryClothes;

import java.time.LocalDate;

public class OuterWear extends Element {
    protected int count;
    protected int countType;

    public OuterWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment) {
        super(id, name, shelfLife, comment);
        this.count = count;
        this.countType = countType;
    }

    @Override
    public String getInfo() {
        String finalstr = String.format("Верхняя одежда\n ID:%s\n Название:%s\n Дата завоза:%s\n Количество:%s%s\n Комментарий:%s\n----",
                id, name, shelfLife, count, countId.get(countType), comment);
        return finalstr;
    }

    @Override
    public String getCount() {
        return String.format("%s %s", count, countId.get(countType));
    }
}
