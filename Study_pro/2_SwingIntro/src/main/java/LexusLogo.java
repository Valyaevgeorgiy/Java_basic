import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LexusLogo extends JPanel {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 300, 300); // Фон - белый прямоугольник
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, 260, 220);
        g.drawOval(20, 20, 220, 180); // Эллипс

        g.drawArc(160, 23, 100, 100, 0, 72);
        g.drawLine(180, 30, 100, 120); // первая линия
        g.drawLine(100, 120, 238, 120); // вторая линия

        g.drawLine(160, 23, 57, 140); // третья линия
        g.drawLine(57, 140, 232, 140); // четвёртая линия
        g.setFont(g.getFont().deriveFont(30.0f));
        g.drawString("LEXUS", 80, 255); // Текст
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lexus Logo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new LexusLogo());
        frame.setVisible(true);
    }
}
