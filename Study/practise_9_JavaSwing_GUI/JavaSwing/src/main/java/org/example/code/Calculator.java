package org.example.code;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
/***
 *
 * A simple calculator using Java Swing
 *
 */
public class Calculator {

    // Define different components
    private static JFrame frame;
    private static JTextField field;
    private static JPanel contentPanel;
    private static JPanel displayPanel;
    private static JPanel buttonPanel;
    private static boolean start = true;
    private static double result = 0;
    private static String lastCommand = "=";
    // Define the action listeners
    private static ActionListener insert = new InsertAction();
    private static ActionListener command = new CommandAction();
    public static void main(String[] args) {
        // Add the frame, panel and text field
        frame = new JFrame("Simple Calculator");
        field = new JTextField();
        contentPanel = new JPanel();
        displayPanel = new JPanel();
        buttonPanel = new JPanel();
        // Set the content panel
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.add(displayPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        // Set the result field
        field.setText("0");
        field.setHorizontalAlignment(SwingConstants.RIGHT);
        field.setEditable(false);
        field.setColumns(13);
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        // Set the buttons
        JButton number7 = new JButton("7");
        number7.addActionListener(insert);

        JButton number8 = new JButton("8");
        number8.addActionListener(insert);

        JButton number9 = new JButton("9");
        number9.addActionListener(insert);

        JButton divide = new JButton("/");
        divide.addActionListener(command);

        JButton number4 = new JButton("4");
        number4.addActionListener(insert);

        JButton number5 = new JButton("5");
        number5.addActionListener(insert);

        JButton number6 = new JButton("6");
        number6.addActionListener(insert);

        JButton multiply = new JButton("*");
        multiply.addActionListener(command);

        JButton number1 = new JButton("1");
        number1.addActionListener(insert);

        JButton number2 = new JButton("2");
        number2.addActionListener(insert);

        JButton number3 = new JButton("3");
        number3.addActionListener(insert);

        JButton subtract = new JButton("-");
        subtract.addActionListener(command);

        JButton number0 = new JButton("0");
        number0.addActionListener(insert);

        JButton dot = new JButton(".");

        JButton equal = new JButton("=");
        equal.addActionListener(command);

        JButton add = new JButton("+");
        add.addActionListener(command);

        // Add the buttons
        buttonPanel.add(number7);
        buttonPanel.add(number8);
        buttonPanel.add(number9);
        buttonPanel.add(divide);
        buttonPanel.add(number4);
        buttonPanel.add(number5);
        buttonPanel.add(number6);
        buttonPanel.add(multiply);
        buttonPanel.add(number1);
        buttonPanel.add(number2);
        buttonPanel.add(number3);
        buttonPanel.add(subtract);
        buttonPanel.add(number0);
        buttonPanel.add(dot);
        buttonPanel.add(equal);
        buttonPanel.add(add);
        // Settings for the frame
        frame.setLayout(new GridLayout(2, 1));
        frame.add(field);
        frame.add(contentPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    //Insert action on digital numbers
    private static class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();
            String text = field.getText();

            if (start) {
                field.setText("");
                start = false;
            }
            if (text.startsWith(".")) {
                field.setText("0" + field.getText() + input);
            } else if (text.startsWith("-0.") || text.startsWith("0.")) {
                field.setText(field.getText() + input);
            } else if (text.startsWith("-0")) {
                field.setText("-" + input);
            } else if (text.startsWith("0")) {
                field.setText(input);
            } else {
                field.setText(field.getText() + input);
            }
        }
    }
    // Command actions on +, -, *, /, =
    private static class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (start) {
                if (command.equals("-")) {
                    field.setText(command);
                    start = false;
                } else {
                    lastCommand = command;
                }
            } else {
                calculate(Double.parseDouble(field.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    /*
     * Real caculation function
     */
    public static void calculate(double x) {
        char operator = lastCommand.charAt(0);
        switch (operator) {
            case '+':
                result += x;
                break;
            case '-':
                result -= x;
                break;
            case '*':
                result *= x;
                break;
            case '/':
                result /= x;
                break;
            case '=':
                result = x;
                break;
        }
        field.setText("" + result);
    }
}