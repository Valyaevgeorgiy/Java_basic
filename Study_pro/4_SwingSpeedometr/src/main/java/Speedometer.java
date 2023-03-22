import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Speedometer extends JFrame {
    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 480;
    public int currentSpeed = 0;
    public boolean isIncreasing = true;

    private DrawCanvas canvas;

    public Speedometer() {
        canvas = new DrawCanvas();
        int delay = 50;

        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (isIncreasing) {
                    currentSpeed += 5;
                    if (currentSpeed > 270) {
                        isIncreasing = false;
                    }
                } else {
                    currentSpeed -= 10;
                    if (currentSpeed <= 0) {
                        isIncreasing = true;
                    }
                }
                canvas.repaint();
            }
        };

        // Update screen each 50 milisec
        new Timer(delay, taskPerformer).start();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        Container cp = getContentPane();
        cp.add(canvas);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Спидометр (Валяев, № 6)");
        this.setVisible(true);
    }

    private class DrawCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            setBackground(Color.WHITE);

            BasicStroke lineBold = new BasicStroke(3);
            BasicStroke lineThin = new BasicStroke(2);

            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(lineBold);

            // Set line thickness

            // Calculate the center of the clock
            int centerX = CANVAS_WIDTH / 2;
            int centerY = CANVAS_HEIGHT / 2;

            // Calculate the radius of the clock
            int radius = Math.min(centerX, centerY) - 20;

            // Draw the clock face
            g.setColor(Color.BLACK);
            g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            // Draw the speedometer face
            g.setColor(Color.WHITE);
            g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            g.setColor(Color.BLACK);
            g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

            // Draw the speedometer markings
            for (int i = 0; i <= 180; i++) {
                g.setColor(Color.GRAY);
                g2.setStroke(lineThin);
                double angle = (i * 1.5) * Math.PI / 180 - 2.3;

                if (i < 140) {

                    int x1 = (int) (centerX + Math.sin(angle) * (radius - 34));
                    int y1 = (int) (centerY - Math.cos(angle) * (radius - 34));
                    int x2 = (int) (centerX + Math.sin(angle) * (radius - 41));
                    int y2 = (int) (centerY - Math.cos(angle) * (radius - 41));

                    g.drawLine(x1, y1, x2, y2);

                    g.setColor(Color.BLACK);
                    g2.setStroke(lineBold);
                    if (i % 20 == 0) {

                        x1 = (int) (centerX + Math.sin(angle) * (radius - 40));
                        y1 = (int) (centerY - Math.cos(angle) * (radius - 40));
                        x2 = (int) (centerX + Math.sin(angle) * (radius - 60));
                        y2 = (int) (centerY - Math.cos(angle) * (radius - 60));
                        g.drawLine(x1, y1, x2, y2);

                        Font font = new Font("Comfortaa", Font.BOLD, 24);
                        g.setFont(font);

                        String number = Integer.toString(i);
                        FontMetrics fm = g.getFontMetrics();
                        int stringWidth = fm.stringWidth(number);
                        int stringHeight = fm.getHeight();

                        // + 30
                        int x = (int) (centerX + Math.sin(angle) * (radius - 80) - stringWidth / 2);
                        int y = (int) (centerY - Math.cos(angle) * (radius - 80) + stringHeight / 2);
                        g.drawString(number, x, y);
                    } else if (i % 10 == 0) {
                        // + 30
                        x1 = (int) (centerX + Math.sin(angle) * (radius - 38));
                        y1 = (int) (centerY - Math.cos(angle) * (radius - 38));
                        x2 = (int) (centerX + Math.sin(angle) * (radius - 53));
                        y2 = (int) (centerY - Math.cos(angle) * (radius - 53));
                        g.drawLine(x1, y1, x2, y2);
                    }
                } else {
                    g.setColor(Color.RED);
                    g2.setStroke(lineBold);

                    // + 30
                    int x1 = (int) (centerX + Math.sin(angle) * (radius - 34));
                    int y1 = (int) (centerY - Math.cos(angle) * (radius - 34));
                    int x2 = (int) (centerX + Math.sin(angle) * (radius - 41));
                    int y2 = (int) (centerY - Math.cos(angle) * (radius - 41));

                    g.drawLine(x1, y1, x2, y2);

                    if (i % 20 == 0) {

                        // + 30
                        x1 = (int) (centerX + Math.sin(angle) * (radius - 40));
                        y1 = (int) (centerY - Math.cos(angle) * (radius - 40));
                        x2 = (int) (centerX + Math.sin(angle) * (radius - 70));
                        y2 = (int) (centerY - Math.cos(angle) * (radius - 70));
                        g.drawLine(x1, y1, x2, y2);

                        Font font = new Font("Comfortaa", Font.BOLD, 24);
                        g.setFont(font);

                        String number = Integer.toString(i);
                        FontMetrics fm = g.getFontMetrics();
                        int stringWidth = fm.stringWidth(number);
                        int stringHeight = fm.getHeight();

                        // + 40
                        int x = (int) (centerX + Math.sin(angle) * (radius - 90) - stringWidth / 2);
                        int y = (int) (centerY - Math.cos(angle) * (radius - 90) + stringHeight / 2);

                        g.drawString(number, x, y);
                    } else if (i % 10 == 0) {
                        // + 30
                        x1 = (int) (centerX + Math.sin(angle) * (radius - 38));
                        y1 = (int) (centerY - Math.cos(angle) * (radius - 38));
                        x2 = (int) (centerX + Math.sin(angle) * (radius - 53));
                        y2 = (int) (centerY - Math.cos(angle) * (radius - 53));
                        g.drawLine(x1, y1, x2, y2);
                    }
                }
            }

            // Draw the speedometer needle
            double speedAngle = 450 + (currentSpeed) * 1 * Math.PI / 180;
            int needleX = (int) (centerX + Math.sin(speedAngle) * (radius - 115));
            int needleY = (int) (centerY - Math.cos(speedAngle) * (radius - 115));
            g.setColor(Color.RED);
            g.drawLine(centerX, centerY, needleX, needleY);

            // Draw red circle in center like in real car speedometers
            g.setColor(Color.RED);
            int centerCircleRadius = radius / 14;
            g.fillOval(centerX - centerCircleRadius, centerY - centerCircleRadius, centerCircleRadius * 2, centerCircleRadius * 2);

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Speedometer();
            }
        });
    }
}