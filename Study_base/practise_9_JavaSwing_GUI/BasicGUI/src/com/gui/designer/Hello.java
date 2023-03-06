package com.gui.designer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hello {
    private JPanel jpnlHello;
    private JButton jbtnShowMessage;
    private JTextField jtxtfMessage;
    public Hello()
    {
        jbtnShowMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jpnlHello, jtxtfMessage.getText());
            }
        });
    }

    public static void main(String args [])
    {
        Hello hello=new Hello();
        JFrame frame =new JFrame("Hello");
        frame.setContentPane(hello.jpnlHello);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
