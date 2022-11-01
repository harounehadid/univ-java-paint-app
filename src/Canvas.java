import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;

public class Canvas extends JComponent {
    private Image image;
    private Graphics2D graphics;
    private int prevX;
    private int prevY;
    private int curX;
    private int curY;
    private int strokeSize = 20;

    public Canvas() {
        // setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // Save the coordinates on prevX and prevY upon pressing
                prevX = e.getX();
                prevY = e.getY();

                if (graphics != null) {
                    graphics.fillOval(prevX, prevY, strokeSize, strokeSize);
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                // Save coordinates x and y when mouse is dragged
                curX = e.getX();
                curY = e.getY();

                if (graphics != null) {
                    graphics.fillOval(curX, curY, strokeSize, strokeSize);
                    
                    // Refresh component to display changes
                    repaint();

                    prevX = curX;
                    prevY = curY;
                }
            }
        });
    }

    // This is special method that gets called -by GUI system- wheneve it is needed
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            graphics = (Graphics2D)image.getGraphics();

            // Enable antialiasing
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            graphics.setStroke(new BasicStroke(strokeSize));

            // Clear draw area
            clear();
        }

        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        graphics.setPaint(Color.white);

        // Fill the entire canvas with white to clear it
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        graphics.setPaint(Color.black);

        repaint();
    }

    public void paintInRed() {
        graphics.setPaint(Color.red);
        System.out.println("I'm working");
    }

    public void squareStroke() {
        
    }
}
