package Java3;

import javax.swing.*;
import java.awt.*;

public class GraphicsPanel extends JPanel {
    
    // public GraphicsPanel() {
        
    // }
    
    public void paint(Graphics g) {
        // Font font=new Font("Arial", Font.BOLD, 20);
        // g.setFont(font);
        g.drawString("Hello Graphics", 20, 100);
    }
}
