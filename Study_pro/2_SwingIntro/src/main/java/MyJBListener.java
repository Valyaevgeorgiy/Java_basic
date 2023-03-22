import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJBListener implements ActionListener {
    JTextArea jta;
    MyJBListener(JTextArea jta) {
        this.jta = jta;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Say Hello")) {
            jta.append("Hello \n");
        }
        else if (e.getActionCommand().equals("Say GoodBye")) {
            jta.append("GoodBye \n");
        }
    }
}

