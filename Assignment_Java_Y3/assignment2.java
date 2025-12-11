package Assignment_Java_Y3;

import javax.swing.*;
import java.awt.FlowLayout;

public class assignment2 extends JFrame {
  
  JLabel name = new JLabel("Name");
  JTextField tfName = new JTextField(8);
  JButton ok = new JButton("Ok");
  
  public assignment2() {
    setLayout(new FlowLayout());
    add(name);
    add(tfName);
    add(ok);
    
    setSize(300, 150);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  public static void main(String[] args) {
    new assignment2();
  }
}
