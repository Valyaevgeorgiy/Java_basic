package MainApp;

import LibraryClothes.Element;
import LibraryClothes.HeadWear;
import LibraryClothes.OuterWear;
import LibraryClothes.UnderWear;

import java.time.LocalDate;
import java.util.ArrayList;

public interface GUIManager {

    public void addOuterWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment);
    void addOuterWear(OuterWear outerWear);

    public void addUnderWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment);
    void addUnderWear(UnderWear underWear);

    public void addHeadWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment);
    void addHeadWear(HeadWear headWear);

    public void removeElement(int id);
    public Element getElement(int id);

    public ArrayList<Element> findElement(String name);
    public ArrayList<Element> findElement(int id);
    public void printItems();
}
