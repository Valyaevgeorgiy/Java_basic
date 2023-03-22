package MainApp;

import MainApp.GUIFrame;
import MainApp.MainManager;
import LibraryClothes.OuterWear;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MainManager mainManager = new MainManager();
        OuterWear ow = new OuterWear(1, "Куртка", LocalDate.now(), 3, 1, "");
        mainManager.addOuterWear(ow);
        mainManager.addOuterWear(2, "Бомбер", LocalDate.now(), 4, 1, "");
        mainManager.addHeadWear(3, "Кепка", LocalDate.now(), 2, 1, "");
        mainManager.addUnderWear(4, "Джоггеры", LocalDate.now(), 10, 1, "");
        new GUIFrame(mainManager);
    }
}