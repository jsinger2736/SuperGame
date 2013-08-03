import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class ObtainItemGUI extends JFrame{
 private JPanel bottom;
 private JButton yes, no;
 private JLabel question;
 private ButtonHandler buttonHandler;

 public ObtainItemGUI(Thingy thingy){
  Container container = getContentPane();
  container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
  
  question = new JLabel("Would you like to put the "+thingy.name+" in your inventory?");
  container.add(question);
  bottom = new JPanel();
  bottom.setLayout(new GridLayout(1,2,10,10));
  yes = new JButton("Yes");
  yes.addActionListener(buttonHandler);
  no = new JButton("No");
  no.addActionListener(buttonHandler);
  bottom.add(yes);
  bottom.add(no);

  setTitle("Add to Inventory");
  pack();
  setLocationRelativeTo(null);
  //setVisible(true);
 }

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==yes){
    
   } else if (source==no){
    
   }
  }
 }



}