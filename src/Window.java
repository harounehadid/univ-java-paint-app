import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame {
    private final String color2 = "#f4f4f4";
    private final String framebgColor = "#f9f9f9";
    private Canvas canvas;

    public Window() {
        super("Paint SW3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(Color.decode(framebgColor));

        Container mainContainer = this.getContentPane();

        // setPreferedSize is the function to use for app to open in prefered size

        // North panel
        JPanel northPanel = new JPanel();
        northPanel.setSize(new Dimension(this.getWidth(), 70));
        northPanel.setLayout(new BorderLayout());

        JMenuBar menuBar = menuBar();
        JPanel toolsPanel = toolsPanel();

        northPanel.add(BorderLayout.NORTH, menuBar);
        northPanel.add(BorderLayout.SOUTH, toolsPanel);  
        
        // Create the element to draw on
        canvas = new Canvas();

        mainContainer.add(BorderLayout.CENTER, canvas);      
        mainContainer.add(BorderLayout.NORTH, northPanel);

        this.setVisible(true);
    }

    private JMenuBar menuBar() {
        JMenuBar menu = new JMenuBar();
        menu.setBackground(Color.decode(color2));

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        menu.add(fileMenu);
        menu.add(editMenu);

        JMenuItem miFileNew = new JMenuItem("New");
        JMenuItem miFileExit = new JMenuItem("Exit");

        fileMenu.add(miFileNew);
        fileMenu.add(miFileExit);

        return menu;
    }

    private JPanel toolsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(color2));

        // Creating the buttons for the tools panel
        String btnsNames[] = {"S-Brush", "C-Brush", "Red", "Green", "Blue", "Clear", "Exit"};
        ArrayList<JButton> panelBtns = new ArrayList<JButton>();

        for (int i = 0; i < btnsNames.length; i++) {
            JButton newBtn = new JButton(btnsNames[i]);
            panelBtns.add(newBtn);
        }

        // Setting layout depending on the number of buttons
        panel.setLayout(new FlowLayout(panelBtns.size()));

        panelBtns.get(2).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.paintInRed();
            }
        });

        // Adding buttons to the panel
        for (int i = 0; i < panelBtns.size(); i++) panel.add(panelBtns.get(i));

        return panel;
    }
}
