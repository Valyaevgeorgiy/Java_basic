package LibraryClothes;

import java.time.LocalDate;

public class UnderWear extends Element{
    protected int count;
    protected int countType;

    public UnderWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment) {
        super(id, name, shelfLife, comment);
        this.count = count;
        this.countType = countType;
    }

    @Override
    public String getInfo() {
        String finalstr = String.format("Нижняя одежда\n ID:%s\n Название:%s\n Дата завоза:%s\n Количество:%s%s\n Комментарий:%s\n----",
                id, name, shelfLife, count, countId.get(countType), comment);
        return finalstr;
    }

    @Override
    public String getCount() {
        return String.format("%s %s", count, countId.get(countType));
    }
}
