import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class MainApp extends JFrame {
    JLabel pictLabel = new JLabel("Hello, Swing!");
    MainApp() {
        setupGUI();
    }

    void setupGUI() {
        Container c = this.getContentPane();
        c.add(pictLabel);
        this.setSize(320, 200);
        this.setVisible(true);
    }
}

class BorderLayoutDemo extends JFrame {
    public static void main(String[] args) {
        BorderLayoutDemo bid = new BorderLayoutDemo();
        bid.setLayout(new BorderLayout(10, 10));
        bid.add(new Button("NORTH"), BorderLayout.NORTH);
        bid.add(new Button("SOUTH"), BorderLayout.SOUTH);
        bid.add(new Button("EAST"), BorderLayout.EAST);
        bid.add(new Button("WEST"), BorderLayout.WEST);
        bid.add(new Button("CENTER"), BorderLayout.CENTER);
        bid.setSize(200, 200);
        bid.setVisible(true);
    }
}

class BorderLasyoutDemo2 extends JFrame {
    public static void main(String[] args) {
        BorderLasyoutDemo2 bid2 = new BorderLasyoutDemo2();
        bid2.setLayout(new BorderLayout(10, 10));
        bid2.add(new JButton("NORTH"), BorderLayout.NORTH);
        bid2.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        bid2.add(new JButton("EAST"), BorderLayout.EAST);
        bid2.add(new JButton("WEST"), BorderLayout.WEST);
        bid2.add(new JButton("CENTER"), BorderLayout.CENTER);
        bid2.setSize(200, 200);
        bid2.setVisible(true);
    }
}

class GridLayoutDemo extends JFrame {
    public static void main(String[] args) {
        GridLayoutDemo gid = new GridLayoutDemo();
        gid.setLayout(new GridLayout(2, 3));
        gid.add(new JButton("ONE"));
        gid.add(new JButton("TWO"));
        gid.add(new JButton("THREE"));
        gid.add(new JButton("FOUR"));
        gid.add(new JButton("FIVE"));
        gid.setSize(600, 400);
        gid.setVisible(true);
    }
}

class ClosableJFrame extends JFrame {
    JButton top = new JButton("Say Hello");
    JButton bottom = new JButton("Say Goodbye");
    JTextArea jta = new JTextArea();

    //MyWindowListener myWindowListener;

}

public class Main {
    public static void main(String[] args) {
        MainApp mapp = new MainApp();
    }
}
