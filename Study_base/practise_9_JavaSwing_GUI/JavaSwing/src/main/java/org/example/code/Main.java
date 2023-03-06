package org.example.code;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Main {
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USERNAME = "root";
    static final String PASSWORD = "Admin01.01";
    static final String[] column_name = new String[]{
            "id",
            "firstname",
            "surname",
            "date_of_birth",
            "place_of_birth",
            "salary",
            "marital_status"};

    public static void main(String[] args) throws SQLException {

        try {
            Connection conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mydb.people;");
            System.out.println("Start");
            JFrame registration = new JFrame("Registration");
            System.out.println("Connection established!");

            registration.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Container container = registration.getContentPane();
            container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            container.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();

            JLabel login_text = new JLabel("login");
            JTextField login = new JTextField(15);
            JLabel password_text = new JLabel("password");
            JPasswordField password = new JPasswordField(15);
            JButton button_log_in = new JButton("Log in");

            constraints.insets = new Insets(5, 5, 5, 5);

            constraints.gridx = 0;
            constraints.gridy = 0;
            constraints.anchor = GridBagConstraints.LINE_END;
            container.add(login_text, constraints);
            constraints.gridx = 1;
            constraints.gridy = 0;
            container.add(login, constraints);
            constraints.gridx = 0;
            constraints.gridy = 1;
            container.add(password_text, constraints);
            constraints.gridx = 1;
            constraints.gridy = 1;
            container.add(password, constraints);
            constraints.gridx = 0;
            constraints.gridy = 2;
            container.add(button_log_in, constraints);

            registration.pack();
            registration.setLocationRelativeTo(null);

            registration.setVisible(true);

            /* -------------- Main Page -------------- */

            JFrame main_frame = new JFrame("Main window");
            Container main_page_panel = main_frame.getContentPane();
            main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



            /* ---------- Create components ---------- */
            JPanel admin_panel = new JPanel();
            JPanel search = new JPanel();
            JPanel change = new JPanel();
            JPanel stats = new JPanel();

            /* -------------- Admin panel ------------ */
            JLabel add_user_label = new JLabel("Логин пользователя");
            JTextField add_user = new JTextField(15);

            ButtonGroup user_type_group = new ButtonGroup();
            JRadioButton rb_administration = new JRadioButton("Администратор", true);
            JRadioButton rb_editor = new JRadioButton("Редактор", false);
            JRadioButton rb_viewer = new JRadioButton("Зритель", false);
            user_type_group.add(rb_administration);
            user_type_group.add(rb_editor);
            user_type_group.add(rb_viewer);

            JLabel user_password_label = new JLabel("Пароль");
            JTextField user_password = new JPasswordField(15);

            JButton create_user = new JButton("Создать пользователя");

            JButton del_user = new JButton("Удалить пользователя");

            /* -------------- search panel ------------ */
            JLabel employee_id_text = new JLabel("ID пользователя(ей)");
            JTextField employee_id = new JTextField(15);
            JLabel employee_name_text = new JLabel("Имя пользователя");
            JTextField employee_name = new JTextField(15);
            JLabel employee_data_br_text = new JLabel("Дата рождения пользователя");
            JTextField employee_data_br = new JTextField(15);

            ButtonGroup search_type_group = new ButtonGroup();
            JRadioButton rb_employee_id = new JRadioButton("Поиск по ID", true);
            JRadioButton rb_employee_name = new JRadioButton("Поик по имени", false);
            JRadioButton rb_employee_data_br = new JRadioButton("Поиск по дате рождения", false);
            search_type_group.add(rb_employee_id);
            search_type_group.add(rb_employee_name);
            search_type_group.add(rb_employee_data_br);

            JButton search_user = new JButton("Найти сотрудника");

            Object[] columnsHeader = new String[]{
                    "ID",
                    "Имя",
                    "Фамилия",
                    "Дата рождения",
                    "Место рождения",
                    "Зарплата",
                    "Семейное положение"
            };

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.setColumnIdentifiers(columnsHeader);


            JTable table_search = new JTable(tableModel);
            JScrollPane sp_search = new JScrollPane(table_search);

            /* -------------- change panel ------------ */
            JTextField change_employee_id = new JTextField(15);
            JTextField change_id = new JTextField(15);
            JTextField change_name = new JTextField(15);
            JTextField change_lsname = new JTextField(15);
            JTextField change_dfb = new JTextField(15);
            JTextField change_pfb = new JTextField(15);
            JTextField salary = new JTextField(15);
            JCheckBox family_st = new JCheckBox();

            class ChangeTable extends AbstractTableModel {
                private String[] columnName = {"ID сотрудника",
                        "ID",
                        "Имя",
                        "Фамилия",
                        "Дата рождения",
                        "Место рождения",
                        "Зарплата",
                        "Семейное положение"};
                public static Object[] data = {"", "", "", "", "", "", "", Boolean.FALSE};

                @Override
                public String getColumnName(int column) {
                    return columnName[column];
                }

                @Override
                public int getRowCount() {
                    return 1;
                }

                // Количество столбцов
                @Override
                public int getColumnCount() {
                    return columnName.length;
                }

                // Тип хранимых в столбцах данных
                @Override
                public Class<?> getColumnClass(int column) {
                    return getValueAt(0, column).getClass();
                }

                @Override
                public boolean isCellEditable(int row, int col) {
                    return true;
                }
                // Функция определения данных ячейки

                @Override
                public Object getValueAt(int row, int col) {
                    return data[col];
                }

                @Override
                public void setValueAt(Object aValue, int row, int col) {
                    data[col] = aValue;
                    fireTableCellUpdated(row, col);
                }
            }
            JPanel change_buttons = new JPanel();

            JButton save_new_button = new JButton("Добавить сотрудника");
            JButton save_change_button = new JButton("Изменить информацию");
            JButton dell_button = new JButton("Удалить сотрудника");

            /* -------------- change panel ------------ */

            JLabel summ_salary_text = new JLabel("Сумма зарплат сотрудников");
            JLabel summ_salary = new JLabel("0");

            JButton get_info = new JButton("Получить информацию о сотрудниках");

            /* ------------ Add components ----------- */

            /* -------------- Admin panel ------------ */

            admin_panel.setLayout(new GridBagLayout());
            admin_panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            GridBagConstraints constraints_main_page_admin = new GridBagConstraints();

            constraints_main_page_admin.insets = new Insets(5, 5, 5, 5);

            constraints_main_page_admin.gridx = 0;
            constraints_main_page_admin.gridy = 0;
            constraints_main_page_admin.anchor = GridBagConstraints.LINE_START;
            admin_panel.add(add_user_label, constraints_main_page_admin);

            constraints_main_page_admin.gridx = 1;
            constraints_main_page_admin.gridy = 0;
            constraints_main_page_admin.gridwidth = 1;
            admin_panel.add(add_user, constraints_main_page_admin);
            constraints_main_page_admin.gridwidth = 1;

            constraints_main_page_admin.gridx = 0;
            constraints_main_page_admin.gridy = 1;
            admin_panel.add(rb_administration, constraints_main_page_admin);
            constraints_main_page_admin.gridx = 1;
            constraints_main_page_admin.gridy = 1;
            admin_panel.add(rb_editor, constraints_main_page_admin);
            constraints_main_page_admin.gridx = 2;
            constraints_main_page_admin.gridy = 1;
            admin_panel.add(rb_viewer, constraints_main_page_admin);

            constraints_main_page_admin.gridx = 0;
            constraints_main_page_admin.gridy = 2;
            constraints_main_page_admin.anchor = GridBagConstraints.LINE_START;
            admin_panel.add(user_password_label, constraints_main_page_admin);

            constraints_main_page_admin.gridx = 1;
            constraints_main_page_admin.gridy = 2;
            constraints_main_page_admin.gridwidth = 1;
            admin_panel.add(user_password, constraints_main_page_admin);
            constraints_main_page_admin.gridwidth = 1;

            constraints_main_page_admin.gridx = 0;
            constraints_main_page_admin.gridy = 3;
            admin_panel.add(create_user, constraints_main_page_admin);
            constraints_main_page_admin.gridx = 1;
            constraints_main_page_admin.gridy = 3;
            admin_panel.add(del_user, constraints_main_page_admin);

            /* -------------- search panel ------------ */

            search.setLayout(new GridBagLayout());
            search.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            GridBagConstraints constraints_search = new GridBagConstraints();

            constraints_search.insets = new Insets(5, 5, 5, 5);

            constraints_search.gridx = 0;
            constraints_search.gridy = 0;
            search.add(rb_employee_id, constraints_search);
            constraints_search.gridx = 1;
            constraints_search.gridy = 0;
            search.add(rb_employee_name, constraints_search);
            constraints_search.gridx = 2;
            constraints_search.gridy = 0;
            search.add(rb_employee_data_br, constraints_search);

            constraints_search.gridx = 0;
            constraints_search.gridy = 1;
            constraints_search.anchor = GridBagConstraints.LINE_START;
            search.add(employee_id_text, constraints_search);

            constraints_search.gridx = 1;
            constraints_search.gridy = 1;
            search.add(employee_id, constraints_search);

            constraints_search.gridx = 0;
            constraints_search.gridy = 2;
            search.add(employee_name_text, constraints_search);

            constraints_search.gridx = 1;
            constraints_search.gridy = 2;
            search.add(employee_name, constraints_search);

            constraints_search.gridx = 0;
            constraints_search.gridy = 3;
            search.add(employee_data_br_text, constraints_search);

            constraints_search.gridx = 1;
            constraints_search.gridy = 3;
            search.add(employee_data_br, constraints_search);

            constraints_search.gridx = 0;
            constraints_search.gridy = 4;
            search.add(search_user, constraints_search);

            constraints_search.gridx = 0;
            constraints_search.gridy = 5;
            constraints_search.gridwidth = 5;
            search.add(sp_search, constraints_search);



            /* -------------- change panel ------------ */
            change.setLayout(new BorderLayout());
            JTable changes_table = new JTable(new ChangeTable());
            TableColumn tc;
            tc = changes_table.getColumnModel().getColumn(0);
            tc.setCellEditor(new DefaultCellEditor(change_employee_id));
            tc = changes_table.getColumnModel().getColumn(1);
            tc.setCellEditor(new DefaultCellEditor(change_id));
            tc = changes_table.getColumnModel().getColumn(2);
            tc.setCellEditor(new DefaultCellEditor(change_name));
            tc = changes_table.getColumnModel().getColumn(3);
            tc.setCellEditor(new DefaultCellEditor(change_lsname));
            tc = changes_table.getColumnModel().getColumn(4);
            tc.setCellEditor(new DefaultCellEditor(change_dfb));
            tc = changes_table.getColumnModel().getColumn(5);
            tc.setCellEditor(new DefaultCellEditor(change_pfb));
            tc = changes_table.getColumnModel().getColumn(6);
            tc.setCellEditor(new DefaultCellEditor(salary));
            tc = changes_table.getColumnModel().getColumn(7);
            tc.setCellEditor(new DefaultCellEditor(family_st));
            changes_table.setRowHeight(25);
            change.add(new JScrollPane(changes_table));
            change_buttons.add(save_new_button);
            change_buttons.add(save_change_button);
            change_buttons.add(dell_button);
            change.add(change_buttons, BorderLayout.SOUTH);

            /* -------------- stats panel ------------- */
            stats.setLayout(new GridBagLayout());
            stats.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

            GridBagConstraints constraints_stats = new GridBagConstraints();

            constraints_stats.insets = new Insets(5, 5, 5, 5);
            constraints_stats.anchor = GridBagConstraints.LINE_START;
            constraints_stats.gridx = 0;
            constraints_stats.gridy = 0;
            stats.add(summ_salary_text, constraints_stats);
            constraints_stats.gridx = 1;
            constraints_stats.gridy = 0;
            stats.add(summ_salary, constraints_stats);

            constraints_stats.gridx = 0;
            constraints_stats.gridy = 1;
            stats.add(get_info, constraints_stats);


            JTabbedPane jtabbedPane = new JTabbedPane();
            jtabbedPane.addTab("Поиск", search);
            jtabbedPane.addTab("Администрирование", admin_panel);
            jtabbedPane.addTab("Изменить информацию", change);
            jtabbedPane.addTab("Информация о сотрудниках", stats);

            main_page_panel.add(jtabbedPane);


            main_frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);


            main_frame.setSize(700, 800);
            main_frame.setLocationRelativeTo(null);
            main_frame.setVisible(false);

            main_frame.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                    System.out.println("JFrame has  been  made visible first  time");
                }

                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("JFrame is closing.");
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    System.out.println("JFrame is closed.");
                }

                @Override
                public void windowIconified(WindowEvent e) {
                    System.out.println("JFrame is  minimized.");
                }

                @Override
                public void windowDeiconified(WindowEvent e) {
                    System.out.println("JFrame is restored.");
                }

                @Override
                public void windowActivated(WindowEvent e) {
                    System.out.println("JFrame is activated.");
                }

                @Override
                public void windowDeactivated(WindowEvent e) {
                    System.out.println("JFrame is deactivated.");
                }
            });
            button_log_in.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String checkQuery = String.format("""
                            SELECT * FROM people_login WHERE login = "%s" AND password = "%s"
                            """, login.getText(), password.getText());
                    System.out.println(checkQuery);
                    try {
                        ResultSet rs = stmt.executeQuery(checkQuery);
                        if (rs.next()) {
                            System.out.println("Успешный логин");
                            System.out.println(rs.getString("role"));

                            switch (Integer.parseInt(rs.getString("role"))) {
                                case 0: {
                                    System.out.println("Admin");
                                    jtabbedPane.setEnabledAt(0, true);
                                    jtabbedPane.setEnabledAt(1, true);
                                    jtabbedPane.setEnabledAt(2, true);
                                    jtabbedPane.setEnabledAt(3, true);
                                    break;
                                }
                                case 1: {
                                    System.out.println("Editor");
                                    jtabbedPane.setEnabledAt(0, true);
                                    jtabbedPane.setEnabledAt(1, false);
                                    jtabbedPane.setEnabledAt(2, true);
                                    jtabbedPane.setEnabledAt(3, true);
                                    break;
                                }
                                case 2: {
                                    System.out.println("Viewer");
                                    jtabbedPane.setEnabledAt(0, true);
                                    jtabbedPane.setEnabledAt(1, false);
                                    jtabbedPane.setEnabledAt(2, false);
                                    jtabbedPane.setEnabledAt(3, true);
                                    break;
                                }
                            }

                            main_frame.setVisible(true);

                            registration.setVisible(false);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            search_user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println();
                    while (tableModel.getRowCount() > 0) {
                        tableModel.removeRow(0);
                    }
                    String query = "";
                    if (rb_employee_id.isSelected()) {
                        System.out.println("Найти по ID");
                        String id = employee_id.getText();
                        if (id.equals("")) {
                            query = "";
                        } else if (id.equals("all")) {
                            query = "SELECT * FROM mydb.people";
                        } else {
                            query = "SELECT * FROM mydb.people WHERE id in (" + id + ")";
                        }
                    } else if (rb_employee_name.isSelected()) {
                        System.out.println("Найти по Имени");
                        String name = employee_name.getText();
                        if (!name.equals("")) {
                            query = "SELECT * FROM mydb.people WHERE firstname in (\"" + String.join("\",\"", name.split(",")) + "\")";
                        }
                    } else if (rb_employee_data_br.isSelected()) {
                        System.out.println("Найти по Датк рождения");
                        String data_br = employee_data_br.getText();
                        if (!data_br.equals("")) {
                            query = "SELECT * FROM mydb.people WHERE date_of_birth in (\"" + String.join("\",\"", data_br.split(",")) + "\")";
                        }
                    }
                    if (!query.equals("")) {
                        try {
                            System.out.println(query);
                            ResultSet rs = stmt.executeQuery(query);
                            System.out.println("Done");
                            while (rs.next()) {
                                ArrayList<String> row = new ArrayList<>();
                                for (String col_name : column_name) {
                                    row.add(rs.getString(col_name));
                                }
                                tableModel.addRow(row.toArray());
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }

                }
            });

            create_user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!add_user.getText().equals("") && !user_password.getText().equals("")) {
                        String login = add_user.getText();
                        String password = user_password.getText();
                        String rol = "2";
                        if (rb_administration.isSelected()) {
                            rol = "0";
                        } else if (rb_editor.isSelected()) {
                            rol = "1";
                        } else if (rb_viewer.isSelected()) {
                            rol = "2";
                        }
                        String query = String.format("INSERT INTO `mydb`.`people_login` (`login`, `password`, `role`) VALUES ('%s', '%s', '%s');", login, password, rol);
                        try {
                            stmt.executeUpdate(String.format(query));
                            add_user.setText("");
                            user_password.setText("");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

            del_user.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!add_user.getText().equals("")) {
                        String login = add_user.getText();
                        String query = String.format("DELETE FROM `mydb`.`people_login` WHERE (`login` = '%s');", login);
                        try {
                            stmt.executeUpdate(String.format(query));
                            add_user.setText("");
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

            get_info.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String query = "SELECT SUM(mydb.people.salary) FROM mydb.people";
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            summ_salary.setText(String.format("%.3f", Float.parseFloat(rs.getString(1))));
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });

            save_new_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        stmt.executeUpdate(
                                String.format("INSERT IGNORE INTO People(" +
                                                "id, firstname, surname, date_of_birth, place_of_birth, salary, marital_status) " +
                                                "VALUE (%s,'%s','%s','%s','%s',%s,%s)",
                                        change_id.getText(),
                                        change_name.getText(),
                                        change_lsname.getText(),
                                        change_dfb.getText(),
                                        change_pfb.getText(),
                                        salary.getText(),
                                        family_st.isSelected()
                                ));
                        for (int i = 0; i < ChangeTable.data.length - 1; i++) {
                            changes_table.setValueAt("", 0, i);
                        }
                        changes_table.setValueAt(false, 0, 7);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            save_change_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!change_employee_id.getText().equals("")) {
                        String id = change_employee_id.getText();
                        if (!change_id.getText().equals("")) {
                            try {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET id = %s where id = %s", change_id.getText(), id));
                                changes_table.setValueAt("", 0, 1);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (!change_name.getText().equals("")) {
                            try {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET firstname = \"%s\" where id = %s", change_name.getText(), id));
                                changes_table.setValueAt("", 0, 2);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (!change_lsname.getText().equals("")) {
                            try {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET surname = \"%s\" where id = %s", change_lsname.getText(), id));
                                changes_table.setValueAt("", 0, 3);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (!change_dfb.getText().equals("")) {
                            try {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET date_of_birth = \"%s\" where id = %s", change_dfb.getText(), id));
                                changes_table.setValueAt("", 0, 4);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (!change_pfb.getText().equals("")) {
                            try {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET place_of_birth = \"%s\" where id = %s", change_pfb.getText(), id));
                                changes_table.setValueAt("", 0, 5);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        if (!salary.getText().equals("")) {
                            try {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET salary = %s where id = %s", salary.getText(), id));
                                changes_table.setValueAt("", 0, 6);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        try {
                            if (family_st.isSelected()) {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET marital_status = 1 where id = %s", id));
                                changes_table.setValueAt(false, 0, 7);
                            } else {
                                stmt.executeUpdate(
                                        String.format(
                                                "UPDATE mydb.people SET marital_status = 0 where id = %s", id));
                                changes_table.setValueAt(false, 0, 7);
                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        changes_table.setValueAt("", 0, 0);

                    }

                }
            });

            dell_button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = change_employee_id.getText();
                    try {
                        stmt.executeUpdate(
                                String.format(
                                        "DELETE FROM `mydb`.`people` WHERE id = %s;", id));
                        for (int i = 0; i < ChangeTable.data.length - 1; i++) {
                            changes_table.setValueAt("", 0, i);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            main_frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("JFrame is closing.");
                }
            }
            );



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (HeadlessException e) {
            throw new RuntimeException(e);
        }
    }
}