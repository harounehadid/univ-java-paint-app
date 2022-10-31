import javax.swing.*;
import java.awt.*;

public class Graphics extends JFrame {
    JFrame frame;
    
    public Graphics() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Paint SW3");

        JButton button = new JButton("Welcome :)");
        button.setBounds(200, 150, 90, 50);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        frame.setSize(500, 600);

        frame.setLayout(null);

        frame.setVisible(true);
    }
}
