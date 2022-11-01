import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {
    private final String color2 = "#f4f4f4";
    private final String framebgColor = "#f9f9f9";
    private Canvas canvas;
    private JFrame thisFrame;

    public Window() {
        super("Paint SW3");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setLayout(new BorderLayout(20, 20));
        this.setBackground(Color.decode(framebgColor));

        Container mainContainer = this.getContentPane();

        thisFrame = this;

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
        JMenuItem miFileSave = new JMenuItem("Save");
        JMenuItem miFileExport = new JMenuItem("Export");
        JMenuItem miFileExit = new JMenuItem("Exit");

        JMenu brushShapes = new JMenu("Brush Shapes");
        JMenuItem circleShape = new JMenuItem("Circle");
        JMenuItem squareShape = new JMenuItem("Square");

        brushShapes.add(circleShape);
        brushShapes.add(squareShape);

        JMenu colors = new JMenu("Colors");
        JMenuItem redColor = new JMenuItem("Red");
        JMenuItem greedColor = new JMenuItem("Green");
        JMenuItem blueColor = new JMenuItem("Blue");

        colors.add(redColor);
        colors.add(greedColor);
        colors.add(blueColor);

        JMenuItem miErase = new JMenuItem("Erase");
        JMenuItem miClear = new JMenuItem("Clear");

        fileMenu.add(miFileNew);
        fileMenu.add(miFileSave);
        fileMenu.add(miFileExport);
        fileMenu.add(miFileExit);

        editMenu.add(brushShapes);
        editMenu.add(colors);
        editMenu.add(miErase);
        editMenu.add(miClear);

        return menu;
    }

    private JPanel toolsPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode(color2));

        // Creating the buttons for the tools panel
        String btnsNames[] = {"S-Brush", "C-Brush", "Red", "Green", "Blue", "Erase", "Clear", "Exit"};
        ArrayList<JButton> panelBtns = new ArrayList<JButton>();

        for (int i = 0; i < btnsNames.length; i++) {
            String btnName = btnsNames[i];
            JButton newBtn = new JButton(btnName);

            newBtn.setMaximumSize(new Dimension(62, 85));

            newBtn.setBackground(Color.decode(color2));
            newBtn.setVerticalTextPosition(AbstractButton.BOTTOM);
            newBtn.setHorizontalTextPosition(AbstractButton.CENTER);

            Image icon;

            try {
                icon = ImageIO.read(getClass().getResource("images-icons/" + btnName + "-Icon.png"));
                newBtn.setIcon(new ImageIcon(icon));
            } catch (IOException e1) {
                System.out.println("Image not found!");
            }

            newBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (btnName == "S-Brush") canvas.squareStroke();
                    else if (btnName == "C-Brush") canvas.roundStroke();
                    else if (btnName == "Red") canvas.paintInRed();
                    else if (btnName == "Green") canvas.paintInGreen();
                    else if (btnName == "Blue") canvas.paintInBlue();
                    else if (btnName == "Erase") canvas.erase();
                    else if (btnName == "Clear") canvas.clear();
                    else if (btnName == "Exit") canvas.exit(thisFrame);
                }
            });
            
            panelBtns.add(newBtn);
        }

        // Setting layout depending on the number of buttons
        panel.setLayout(new FlowLayout(panelBtns.size()));

        // Adding buttons to the panel
        for (int i = 0; i < panelBtns.size(); i++) panel.add(panelBtns.get(i));

        return panel;
    }
}
