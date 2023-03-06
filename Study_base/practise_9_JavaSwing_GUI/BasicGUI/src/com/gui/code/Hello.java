package com.gui.code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hello extends JFrame {
    private JPanel jpnlHello;
    private JButton jbtnShowMessage;
    private JTextField jtxtfMessage;

    public Hello()
    {
        jbtnShowMessage=new JButton("Show Message");
        jbtnShowMessage.setBounds(10,10,75,35);
        jtxtfMessage=new JTextField("Hello World");
        jtxtfMessage.setBounds(90,10,200,35);
        jbtnShowMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jpnlHello, jtxtfMessage.getText());

            }
        });
        jpnlHello=new JPanel();
        jpnlHello.setLayout(null);//Можно не использовать LayoutManager
        jpnlHello.add(jbtnShowMessage);
        jpnlHello.add(jtxtfMessage);

        setContentPane(jpnlHello);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(350,100));
        setResizable(false);
    }

    public static void main(String [] args)
    {
        Hello hello=new Hello();
        hello.setVisible(true);
    }
}
