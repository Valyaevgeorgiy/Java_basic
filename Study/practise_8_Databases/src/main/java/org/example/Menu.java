package org.example;

import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// функциональный интерфейс
@FunctionalInterface
interface Myfunction {
    void run(Statement stems, Scanner in);
}

class Item {
    public String label;
    public Myfunction myfunction;
    public Item(String label, Myfunction myfunction) {
        this.label = label;
        this.myfunction = myfunction;
    }
}

public class Menu {
    HashMap<Integer, Item> menu = new HashMap<>();
    public void printAllMenu() {
        int length_max = 0;
        int last_index = 0;
        for (Map.Entry<Integer, Item> entry : menu.entrySet()) {
            length_max = Integer.max(entry.getValue().label.length(), length_max);
            last_index = entry.getKey();
        }
        length_max += Integer.toString(last_index).length() + 3;
        System.out.println();
        System.out.println("----------------МЕНЮ---------------");
        for (Map.Entry<Integer, Item> entry : menu.entrySet()) {
            System.out.printf("%s — %-20s%n", entry.getKey(), entry.getValue().label);
        }
        System.out.println("-----------------------------------");
    }

    public void addItem(int id, String label, Myfunction func) {
        menu.put(id, new Item(label, func));
    }

    public void run(int index, Statement statement, Scanner in) {
        menu.get(index).myfunction.run(statement, in);
    }
}
