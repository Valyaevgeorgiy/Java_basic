package org.example.code;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
public class Example6 extends JFrame implements TableModelListener {
    Container container;
    JTable table;
    JScrollPane pane;
    JComboBox<String> branchList;
    public Example6() {
        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        table = new JTable(new MyTableModel());
        table.setRowHeight(50);
        /*Creating combo box*/
        branchList = new JComboBox<String>();
        branchList.addItem("CSE");
        branchList.addItem("IT");
        branchList.addItem("Mech");
        branchList.addItem("Civil");
        branchList.addItem("EEE");
        /*Setting table editor as combo box*/
        TableColumn tc = table.getColumnModel().getColumn(4);
        tc.setCellEditor(new DefaultCellEditor(branchList));
        pane = new JScrollPane(table);
        container.add(pane);
        table.getModel().addTableModelListener(this);
    }
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        Object data = model.getValueAt(row, column);
        System.out.println("row " + row + " column " + column + " data " + data);
    }
    public static void main(String[] args) {
        Example6 frame = new Example6();
        frame.setTitle("JTable Example");
        frame.setVisible(true);
        frame.setSize(700, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class MyTableModel extends AbstractTableModel {
    String[] columnNames = {"First Name", "Last Name", "Age", "City", "Branch", "Topper"};
    private Object[][] data = {
            {"Ram", "Kumar", 22, "Delhi", "CSE", new Boolean(true)},
            {"Mohan", "Nath", 24, "Mumbai", "IT", new Boolean(false)},
            {"Rita", "Singh", 22, "Kolkata", "ETC", new Boolean(true)},
            {"Anita", "Sharma", 21, "Bhopal", "MECH", new Boolean(false)}
    };
    public int getColumnCount() {
        return columnNames.length;
    }
    public int getRowCount() {
        return data.length;
    }
    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    public boolean isCellEditable(int row, int col) {
        return true;
    }
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}