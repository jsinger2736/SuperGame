import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class CompanionGUI extends JFrame{
 private JPanel companion1, companion2, companion1picholder, companion2picholder;
 private ImagePanel companion1pic, companion2pic;
 private JLabel companion1name, companion2name;
 private JButton companion1button, companion2button;
 private DismissHandler dismissHandler;

 public CompanionGUI(){
  Container container = getContentPane();
  container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
  dismissHandler = new DismissHandler();
  
  companion1 = new JPanel();
  companion1.setLayout(new BoxLayout(companion1, BoxLayout.Y_AXIS));
  companion1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  companion1picholder = new JPanel();
  companion1picholder.setLayout(new BorderLayout());
  //companion1picholder.setPreferredSize(new Dimension(200,200));
  try {
   companion1pic = new ImagePanel(ImageIO.read(new File("pictures/none.jpg")));
  } catch (IOException e){
   System.out.println("\"pictures\" file either missing or empty.");
  }
  companion1picholder.add(companion1pic, BorderLayout.CENTER);
  companion1name = new JLabel("None");
  companion1name.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
  companion1name.setAlignmentX(Component.CENTER_ALIGNMENT);
  companion1button = new JButton("Dismiss");
  companion1button.addActionListener(dismissHandler);
  //companion1button.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
  companion1button.setAlignmentX(Component.CENTER_ALIGNMENT);
  companion1.add(companion1picholder);
  companion1.add(companion1name);
  companion1.add(companion1button);
  container.add(companion1);
  
  companion2 = new JPanel();
  companion2.setLayout(new BoxLayout(companion2, BoxLayout.Y_AXIS));
  companion2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  companion2picholder = new JPanel();
  companion2picholder.setPreferredSize(new Dimension(200,200));
  try {
   companion2pic = new ImagePanel(ImageIO.read(new File("pictures/none.jpg")));
  } catch (IOException e){
   System.out.println("\"pictures\" file either missing or empty.");
  }
  companion2picholder.add(companion2pic);
  companion2name = new JLabel("None");
  companion2name.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
  companion1name.setAlignmentX(Component.CENTER_ALIGNMENT);
  companion2button = new JButton("Dismiss");
  companion2button.addActionListener(dismissHandler);
  //companion2button.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
  companion2button.setAlignmentX(Component.CENTER_ALIGNMENT);
  companion2.add(companion2picholder);
  companion2.add(companion2name);
  companion2.add(companion2button);
  container.add(companion2);

  setTitle("Companions");
  //setSize(new Dimension(300,200));
  //pack();
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateCompanionGUI(){
  companion1.removeAll();
  int number = 0;
  if (!Background.player.currentCompanions[0].name.equals("none")){
   try {
    companion1pic = new ImagePanel(ImageIO.read(new File("pictures/"+Background.player.currentCompanions[0].name+".jpg")));
   } catch (Exception exception){
    try{
     companion1pic = new ImagePanel(ImageIO.read(new File("pictures/none.jpg")));
     System.out.println("companion1 jpg not found.");
    } catch (IOException exc){
     System.out.println("\"pictures\" file either missing or empty.");
    }
   }
   companion1name.setText(Background.player.currentCompanions[0].name);
   companion1picholder.removeAll();
   companion1picholder.add(companion1pic, BorderLayout.CENTER);
   companion1picholder.revalidate();
   companion1.add(companion1picholder);
   companion1.add(companion1name);
   companion1.add(companion1button);
   number++;
  }
  companion1.revalidate();

  companion2.removeAll();
  if (!Background.player.currentCompanions[1].name.equals("none")){
   try {
    companion2pic = new ImagePanel(ImageIO.read(new File("pictures/"+Background.player.currentCompanions[1].name+".jpg")));
   } catch (Exception exception){
    try{
     companion2pic = new ImagePanel(ImageIO.read(new File("pictures/none.jpg")));
     System.out.println("companion2 jpg not found.");
    } catch (IOException exc){
     System.out.println("\"pictures\" file either missing or empty.");
    }
   }
   companion2name.setText(Background.player.currentCompanions[1].name);
   companion2.add(companion2picholder);
   companion2.add(companion2name);
   companion2.add(companion2button);
   number++;
  }
  companion2.revalidate();

  
  setSize(new Dimension(number*256,300));
  if (number==0){
   setVisible(false);
  }
 }

 private class DismissHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==companion1button){
    Background.player.companions[Companion.identify(Background.player.currentCompanions[0].name)][0]=1;
    Background.player.currentCompanions[0]=new Companion(0);
    if (!Background.player.currentCompanions[1].name.equals("none")){
     Background.player.currentCompanions[0]=Background.player.currentCompanions[1];
     Background.player.currentCompanions[1]=new Companion(0);
    }
   } else if (source==companion2button){
    Background.player.companions[Companion.identify(Background.player.currentCompanions[1].name)][0]=1;
   }
   Background.game.update();
  }
 }
}