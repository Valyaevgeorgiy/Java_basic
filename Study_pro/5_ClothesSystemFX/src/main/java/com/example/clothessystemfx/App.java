package com.example.clothessystemfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Shop shop;

    @Override
    public void start(Stage primaryStage) throws Exception {
        shop = new Shop(10000);

        primaryStage.setTitle("Магазин одежды");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label nameLabel = new Label("Название:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        Label priceLabel = new Label("Цена:");
        TextField priceField = new TextField();
        gridPane.add(priceLabel, 0, 1);
        gridPane.add(priceField, 1, 1);

        Label quantityLabel = new Label("Количество:");
        TextField quantityField = new TextField();
        gridPane.add(quantityLabel, 0, 2);
        gridPane.add(quantityField, 1, 2);

        Label manufacturerLabel = new Label("Производитель:");
        TextField manufacturerField = new TextField();
        gridPane.add(manufacturerLabel, 0, 3);
        gridPane.add(manufacturerField, 1, 3);

        Label materialLabel = new Label("Материал:");
        TextField materialField = new TextField();
        gridPane.add(materialLabel, 0, 4);
        gridPane.add(materialField, 1, 4);

        Label partsLabel = new Label("Количество тканевых материалов:");
        TextField partsField = new TextField();
        gridPane.add(partsLabel, 0, 5);
        gridPane.add(partsField, 1, 5);

        Button addSoftClothesButton = new Button("Добавить элемент одежды");
        addSoftClothesButton.setOnAction(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String manufacturer = manufacturerField.getText();
            String material = materialField.getText();

            SoftClothes softClothes = new SoftClothes(name, price, quantity, manufacturer, material);

            shop.addProduct(softClothes);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Элемент одежды добавлен!");
            alert.setHeaderText(null);
            alert.setContentText("Элемент одежды добавлен в систему учёта товаров магазина!");
            alert.showAndWait();
        });
        gridPane.add(addSoftClothesButton, 0, 6);

        Button addConstructorButton = new Button("Добавить конструктор");
        addConstructorButton.setOnAction(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String manufacturer = manufacturerField.getText();
            int numberOfParts = Integer.parseInt(partsField.getText());

            Constructors constructor = new Constructors(name, price, quantity, manufacturer, numberOfParts);
            shop.addProduct(constructor);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Конструктор добавлен");
            alert.setHeaderText(null);
            alert.setContentText("Конструктор добавлен в магазин!");
            alert.showAndWait();
        });
        gridPane.add(addConstructorButton, 1, 6);

        Button showStatsButton = new Button("Статистика");
        showStatsButton.setOnAction(e -> {
            showStatistics(shop);
        });
        gridPane.add(showStatsButton, 0, 7);

        Button showTableButton = new Button("Показать таблицу");
        showTableButton.setOnAction(e -> {
            createTable(shop);
        });
        gridPane.add(showTableButton, 1, 7);

        Scene scene = new Scene(gridPane, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showStatistics(Shop shop) {
        if (shop.getProducts() == null) {
            System.out.println("Магазин пуст");
            return;
        }
        int totalQuantity = 0;
        double totalCost = 0;
        for (Product product : shop.getProducts()) {
            if (product != null) {
                totalQuantity += product.getQuantity();
                totalCost += product.getPrice() * product.getQuantity();
            }
        }
        System.out.println(totalCost);
        String message = String.format("Общее количество товаров: %d\nОбщая стоимость: %.2f", totalQuantity, totalCost);
        System.out.println(message);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Статистика");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void createTable(Shop shop) {
        // Создание таблицы с товарами
        String[] columnNames = {"Название", "Цена", "Количество", "Производитель", "Дополнительно"};
        Object[][] rowData = new Object[shop.getProducts().length][5];
        for (int i = 0; i < shop.getProducts().length; i++) {
            if (shop.getProducts()[i] != null) {
                rowData[i][0] = shop.getProducts()[i].getName();
                rowData[i][1] = shop.getProducts()[i].getPrice();
                rowData[i][2] = shop.getProducts()[i].getQuantity();
                rowData[i][3] = shop.getProducts()[i].getManufacturer();
                if (shop.getProducts()[i] instanceof SoftClothes) {
                    rowData[i][4] = "Материал: " + ((SoftClothes) shop.getProducts()[i]).getMaterial();
                } else if (shop.getProducts()[i] instanceof Constructors) {
                    rowData[i][4] = "Количество тканевых материалов: " + ((Constructors) shop.getProducts()[i]).getNumberOfParts();
                }
            }
        }
        TableView table = new TableView();
        table.setEditable(true);

        for (int i = 0; i < columnNames.length; i++) {
            TableColumn col = new TableColumn(columnNames[i]);
            col.setCellValueFactory(new javafx.scene.control.cell.MapValueFactory(columnNames[i]));
            table.getColumns().add(col);
        }

        for (int i = 0; i < rowData.length; i++) {
            if (rowData[i][0] != null) {
                java.util.Map<String, Object> row = new java.util.HashMap<>();
                for (int j = 0; j < columnNames.length; j++) {
                    row.put(columnNames[j], rowData[i][j]);
                }
                table.getItems().add(row);
            }
        }

        // Создание панели для таблицы с возможностью скроллинга
        ScrollPane scrollPane = new ScrollPane();

        scrollPane.setContent(table);

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        // scrollPane.setPrefViewportWidth(500);
        // scrollPane.setPrefViewportHeight(500);

        // Создание нового окна для отображения таблицы
        Stage tableStage = new Stage();
        tableStage.setTitle("Таблица с товарами");
        tableStage.setScene(new Scene(scrollPane, 1000, 500));
        tableStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}