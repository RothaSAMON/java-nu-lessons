package Java3;

import javax.swing.*;

public class Main extends JFrame {
    
    public Main() {
        // Set up the frame
        setTitle("Graphics Panel Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Create and add the GraphicsPanel
        GraphicsPanel panel = new GraphicsPanel();
        add(panel);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
