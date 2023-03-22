package MainApp;

import LibraryClothes.Element;
import LibraryClothes.HeadWear;
import LibraryClothes.OuterWear;
import LibraryClothes.UnderWear;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainManager implements GUIManager {
    private int index;
    private ArrayList<Element> listElement = new ArrayList();

    @Override
    public void addOuterWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment) {
        listElement.add(new OuterWear(id, name, shelfLife, count, countType, comment));
    }

    @Override
    public void addOuterWear(OuterWear outerWear) {
        listElement.add(outerWear);
    }

    @Override
    public void addUnderWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment) {
        listElement.add(new UnderWear(id, name, shelfLife, count, countType, comment));
    }

    @Override
    public void addUnderWear(UnderWear underWear) {
        listElement.add(underWear);
    }

    @Override
    public void addHeadWear(int id, String name, LocalDate shelfLife, int count, int countType, String comment) {
        listElement.add(new HeadWear(id, name, shelfLife, count, countType, comment));
    }

    @Override
    public void addHeadWear(HeadWear headWear) {
        listElement.add(headWear);
    }

    public void start() {
        this.index = 0;
    }

    public Element getNext() {
        if (listElement.size() > index) {
            index++;
            return listElement.get(index - 1);
        } else {
            return null;
        }
    }

    @Override
    public void removeElement(int id) {
        start();
        Element element = getNext();
        while (element != null) {
            if (element.getId() == id) {
                listElement.remove(index - 1);
                return;
            }
            element = getNext();
        }
    }

    @Override
    public Element getElement(int id) {
        start();
        Element element = getNext();
        while (element != null) {
            if (element.getId() == id) {
                return element;
            }
            element = getNext();
        }
        return null;
    }

    @Override
    public ArrayList<Element> findElement(String name) {
        ArrayList<Element> findElements = new ArrayList<>();
        start();
        Element element = getNext();
        while (element != null) {
            if (element.getName().indexOf(name) >= 0) {
                findElements.add(element);
            }
            element = getNext();
        }
        return findElements;
    }

    @Override
    public ArrayList<Element> findElement(int id) {
        ArrayList<Element> findElements = new ArrayList<>();
        start();
        Element element = getNext();
        while (element != null) {
            if (element.getId() == id) {
                findElements.add(element);
                return findElements;
            }
            element = getNext();
        }
        return findElements;
    }

    @Override
    public void printItems() {
        listElement.stream().forEach(x -> System.out.println(x.getInfo()));
    }

    public int size() {
        return listElement.size();
    }

    public Element getElementFromIndex(int index) {
        return listElement.get(index);
    }
}
