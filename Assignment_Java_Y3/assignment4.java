package Assignment_Java_Y3;

import javax.swing.*;
import java.awt.*;

public class assignment4 extends JPanel {
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    // Draw a rectangle with black border (10 pixels gap from panel borders)
    g.setColor(Color.BLACK);
    int gap = 10;
    g.drawRect(gap, gap, getWidth() - (gap * 2), getHeight() - (gap * 2));
    
    // Draw a string with pink color half way from top and bottom
    String text = "Hello Graphics";
    Font font = new Font("Arial", Font.PLAIN, 20);
    g.setFont(font);
    
    // Use FontMetrics to measure font properties
    FontMetrics fm = g.getFontMetrics(font);
    int stringWidth = fm.stringWidth(text);
    int fontHeight = fm.getHeight();
    
    // Position text in the center (halfway from top and bottom)
    int x = (getWidth() - stringWidth) / 2;
    int y = (getHeight() + fontHeight) / 2;
    
    g.setColor(Color.PINK);
    g.drawString(text, x, y);
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise 4 - Rectangle and FontMetrics");
    assignment4 panel = new assignment4();
    
    frame.add(panel);
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}