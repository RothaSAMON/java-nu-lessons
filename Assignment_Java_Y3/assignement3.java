package Assignment_Java_Y3;

import javax.swing.*;
import java.awt.*;

public class assignement3 extends JPanel {
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    // Set font and color for text
    Font font = new Font("Arial", Font.ITALIC, 20);
    g.setFont(font);
    g.setColor(Color.PINK);
    g.drawString("Graphics Methods Demo", 20, getHeight()/2);
    
    // drawRect - draws outline of rectangle
    g.setColor(Color.BLUE);
    g.drawRect(50, 50, 100, 60);
    
    // fillRect - draws filled rectangle
    g.setColor(Color.RED);
    g.fillRect(200, 50, 100, 60);
    
    // drawOval - draws outline of oval
    g.setColor(Color.GREEN);
    g.drawOval(50, 150, 100, 60);
    
    // fillOval - draws filled oval
    g.setColor(new Color(255, 165, 0));
    g.fillOval(200, 150, 100, 60);
  }
  
  public static void main(String[] args) {
    JFrame frame = new JFrame("Exercise 3 - Graphics Methods");
    assignement3 panel = new assignement3();
    
    frame.add(panel);
    frame.setSize(400, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
