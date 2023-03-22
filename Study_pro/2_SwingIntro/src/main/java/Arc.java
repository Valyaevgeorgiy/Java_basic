import javax.swing.*;
import java.awt.*;

public class Arc extends JFrame {

    public Arc() {
        setTitle("Drawing an Arc Example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawArc(50, 50, 100, 100, 0, 90); // рисует дугу
    }

    public static void main(String[] args) {
        new Arc();
    }
}
