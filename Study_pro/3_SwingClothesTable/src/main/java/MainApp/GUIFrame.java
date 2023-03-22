package MainApp;

import LibraryClothes.Element;
import LibraryClothes.HeadWear;
import LibraryClothes.OuterWear;
import LibraryClothes.UnderWear;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class GUIFrame extends JFrame {
    private MainManager mainManager;
    private JPanel panelAction;
    private JPanel panelInformation;
    private JButton buttonAdd;
    private JButton buttonDel;
    private JButton buttonFind;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTextField textFieldID;
    private JTextField textFieldName;
    private JTextField textFieldLastDate;
    private JTextField textFieldCount;
    private JTextField textFieldCountType;
    private JTextField textFieldComment;
    private JButton buttonCopyAll;
    private JButton buttonCopySelect;

    private JRadioButton radioButtonHeadWear;
    private JRadioButton radioButtonUnderWear;
    private JRadioButton radioButtonOuterWear;

    public GUIFrame(MainManager mainManager) {
        super("Система учёта товаров для магазина одежды");
        this.mainManager = mainManager;

        // Создаем панель управления
        panelAction = new JPanel();

        // Задаем паттерн расположения
        panelAction.setLayout(new FlowLayout());

        // Панель с кнопками
        JPanel panelNew = new JPanel();

        // Задаем паттерн расположения
        panelNew.setLayout(new BoxLayout(panelNew, BoxLayout.Y_AXIS));

        // Создаем кнопки и листенеры для них
        buttonAdd = new JButton("Добавить запись");
        buttonAdd.addActionListener(new ButtonAdd());

        buttonDel = new JButton("Удалить запись");
        buttonDel.addActionListener(new ButtonDel());

        textField = new JTextField();
        buttonFind = new JButton("Найти");
        buttonFind.addActionListener(new ButtonFind());

        buttonCopyAll = new JButton("В буфер обмена все");
        buttonCopyAll.addActionListener(new ButtonCopyAll());

        buttonCopySelect = new JButton("В буфер обмена выделенные");
        buttonCopySelect.addActionListener(new ButtonCopySelect());

        // Добавляем кнопки на панель
        panelNew.add(buttonAdd);
        panelNew.add(buttonDel);
        panelNew.add(textField);
        panelNew.add(buttonFind);
        panelNew.add(buttonCopyAll);
        panelNew.add(buttonCopySelect);

        // Добавляем панель с кнопками в панель управления
        panelAction.add(panelNew);
        getContentPane().add(BorderLayout.EAST, panelAction);

        // Создаём таблицу с данными
        model = new DefaultTableModel();
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Создаём столбцы таблицы
        model.addColumn("ID");
        model.addColumn("Название");
        model.addColumn("Дата завоза");
        model.addColumn("Количество");

        //Задаём листенер для выделенного элемента
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.addListSelectionListener(new MyListSelect());

        //Добавляем скролбар
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        getContentPane().add(BorderLayout.CENTER, scrollPane);

        // Панель с информацией
        panelInformation = new JPanel();

        // Панель с данными
        JPanel panelElements = new JPanel();

        // Поля для ввода
        textFieldID = new JTextField(20);
        textFieldName = new JTextField();
        textFieldLastDate = new JTextField();
        textFieldCount = new JTextField();
        textFieldComment = new JTextField();
        textFieldCountType = new JTextField();

        JPanel radioButton = new JPanel();
        radioButtonOuterWear = new JRadioButton("Верхняя одежда");
        radioButtonOuterWear.addActionListener(new RadioButtonOuterWear());

        radioButtonUnderWear = new JRadioButton("Нижняя одежда");
        radioButtonUnderWear.addActionListener(new RadioButtonUnderWear());

        radioButtonHeadWear = new JRadioButton("Головной убор");
        radioButtonHeadWear.addActionListener(new RadioButtonHeadWear());

        radioButtonOuterWear.setSelected(true);
        radioButton.add(radioButtonOuterWear);
        radioButton.add(radioButtonUnderWear);
        radioButton.add(radioButtonHeadWear);

        GridLayout experimentLayout = new GridLayout(8, 2, 5, 5);
        panelElements.setLayout(experimentLayout);
        panelElements.add(new JLabel("ID"));
        panelElements.add(textFieldID);
        panelElements.add(new JLabel("Название"));
        panelElements.add(textFieldName);
        panelElements.add(new JLabel("Дата завоза"));
        panelElements.add(textFieldLastDate);
        panelElements.add(new JLabel("Количество"));
        panelElements.add(textFieldCount);
        panelElements.add(new JLabel("Единицы измерения (1 тип - штуки, 2 тип - десятки) :"));
        panelElements.add(textFieldCountType);
        panelElements.add(new JLabel("Комментарий"));
        panelElements.add(textFieldComment);

        panelElements.add(new JPanel());
        panelElements.add(radioButton);
        panelInformation.setLayout(new FlowLayout());
        panelInformation.add(panelElements);

        getContentPane().add(BorderLayout.SOUTH, panelInformation);
        setSize(1000, 500);
        setVisible(true);
    }

    private class MyListSelect implements ListSelectionListener {
        @Override public void valueChanged(ListSelectionEvent e) {
            // Получаем список выбранных записей
            int[] ss = table.getSelectedRows();

            if (ss.length == 1) {
                int id = (Integer) model.getValueAt(ss[0], 0);
                Element element = mainManager.getElement(id);
                if (element == null) return;

                textFieldID.setText(String.valueOf(element.getId()));
                textFieldName.setText(element.getName());

                textFieldCount.setText(element.getCount());
                textFieldLastDate.setText(String.valueOf(element.getShelfLife()));
                textFieldComment.setText(element.getComment());

                if (element.getClass() == OuterWear.class) {
                    radioButtonOuterWear.setSelected(true);
                    radioButtonUnderWear.setSelected(false);
                    radioButtonHeadWear.setSelected(false);
                } else if (element.getClass() == UnderWear.class) {
                    radioButtonUnderWear.setSelected(true);
                    radioButtonOuterWear.setSelected(false);
                    radioButtonHeadWear.setSelected(false);
                } else if (element.getClass() == HeadWear.class) {
                    radioButtonHeadWear.setSelected(true);
                    radioButtonUnderWear.setSelected(false);
                    radioButtonOuterWear.setSelected(false);
                }
            } else {
                textFieldID.setText("");
                textFieldName.setText("");
                textFieldCount.setText("");
                textFieldLastDate.setText("");
                textFieldComment.setText("");
            }
        }
    }

    private void CreateTable() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        mainManager.start();
        Element element = mainManager.getNext();
        while (element != null) {
            Object[] addRow = new Object[]{element.getId(), element.getName(), element.getShelfLife(), element.getCount()};
            model.addRow(addRow);
            element = mainManager.getNext();
        }
    }

    private class ButtonAdd implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            if (radioButtonOuterWear.isSelected()) {
                mainManager.addOuterWear(Integer.parseInt(textFieldID.getText()), textFieldName.getText(),
                        LocalDate.parse(textFieldLastDate.getText()), Integer.parseInt(textFieldCount.getText()),
                        Integer.parseInt(textFieldCountType.getText()), textFieldComment.getText());
            } else if (radioButtonUnderWear.isSelected()) {
                mainManager.addUnderWear(Integer.parseInt(textFieldID.getText()), textFieldName.getText(),
                        LocalDate.parse(textFieldLastDate.getText()), Integer.parseInt(textFieldCount.getText()),
                        Integer.parseInt(textFieldCountType.getText()), textFieldComment.getText());
            } else if (radioButtonHeadWear.isSelected()) {
                mainManager.addHeadWear(Integer.parseInt(textFieldID.getText()), textFieldName.getText(),
                        LocalDate.parse(textFieldLastDate.getText()), Integer.parseInt(textFieldCount.getText()),
                        Integer.parseInt(textFieldCountType.getText()), textFieldComment.getText());
            }

            CreateTable();
        }
    }

    // Обработчик события от кнопки "UnderWear"
    private class RadioButtonUnderWear implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            radioButtonUnderWear.setSelected(true);
            radioButtonHeadWear.setSelected(false);
            radioButtonOuterWear.setSelected(false);
        }
    }

    // Обработчик события от кнопки "HeadWear"
    private class RadioButtonHeadWear implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            radioButtonHeadWear.setSelected(true);
            radioButtonOuterWear.setSelected(false);
            radioButtonUnderWear.setSelected(false);
        }
    }

    // Обработчик события от кнопки "OuterWear"
    private class RadioButtonOuterWear implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            radioButtonOuterWear.setSelected(true);
            radioButtonHeadWear.setSelected(false);
            radioButtonUnderWear.setSelected(false);
        }
    }

    private class ButtonDel implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            if (ss.length == 1) {
                int idSelect = (Integer) model.getValueAt(ss[0], 0);
                model.removeRow(ss[0]);
                mainManager.removeElement(idSelect);
            }
        }
    }

    private class ButtonFind implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            if (model.getRowCount() == 0) { CreateTable(); }
            if (textField.getText().isEmpty()) { CreateTable(); return; }

            ArrayList<Element> items = mainManager.findElement(textField.getText());
            table.clearSelection();

            for (int i = 0; i < items.size(); i++) {
                for (int j = 0; j < table.getRowCount(); j++) {
                    if ((Integer) table.getValueAt(j, 0) == items.get(i).getId()) {
                        table.addRowSelectionInterval(j, j);
                    }
                }
            }
        }
    }

    private class ButtonCopyAll implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            String str = "Список элементов одежды:\n";
            for (int i = 0; i < mainManager.size(); i++) {
                Element element = mainManager.getElementFromIndex(i);
                str = str + element.getInfo() + "\n";
            }

            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }

    private class ButtonCopySelect implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            int[] ss = table.getSelectedRows();
            String str = "Список элементов одежды:\n";
            for (int i = 0; i < ss.length; i++) {
                int id = (Integer) model.getValueAt(ss[i], 0);
                Element element = mainManager.getElement(id);
                str = str + element.getInfo() + "\n";
            }

            StringSelection selection = new StringSelection(str);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}
