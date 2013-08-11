import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class CompanionInfoGUI extends JFrame{
 private JPanel north, center, centerLeft, centerRight, west;
 private JComboBox list;
 private DefaultComboBoxModel dl;
 private CompanionHandler companionHandler;
 private JLabel name, defeated, description, offensive, defensive;
 private ImagePanel picture = null;

 public CompanionInfoGUI(){
  Container container = getContentPane();
  companionHandler = new CompanionHandler();

  north = new JPanel();
  list = new JComboBox();
  dl = new DefaultComboBoxModel();
  list.setModel(dl);
  list.addActionListener(companionHandler);
  north.add(list);
  container.add(north, BorderLayout.NORTH);

  west = new JPanel();
  west.setPreferredSize(new Dimension(200,200));
  west.setLayout(new BorderLayout());
  try{
   picture = new ImagePanel(ImageIO.read(new File("pictures/none.jpg")));
   west.add(picture, BorderLayout.CENTER);
  } catch (IOException e){
   System.out.println("pictures file either missing or empty");
  }
  container.add(west, BorderLayout.WEST);
  
  center = new JPanel();
  //center.setLayout(new GridLayout(5,2,10,10));
  centerLeft = new JPanel();
  centerLeft.setLayout(new GridLayout(7,1,10,10));
  centerLeft.setBorder(BorderFactory.createEmptyBorder(0,5,5,7));
  centerRight = new JPanel();
  centerRight.setLayout(new GridLayout(7,1,10,10));
  centerRight.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));
  Companion companion = new Companion(0);
  name = new JLabel(companion.name);
  defeated = new JLabel("0");
  description = new JLabel(companion.description);
  offensive = new JLabel(companion.offensive);
  defensive = new JLabel(companion.defensive);
  centerLeft.add(new JLabel("Name:"));
  centerRight.add(name);
  centerLeft.add(new JLabel("Defeated: "));
  centerRight.add(defeated);
  centerLeft.add(new JLabel("Description:"));
  centerRight.add(description);
  centerLeft.add(new JLabel("Offensive:"));
  centerRight.add(offensive);
  centerLeft.add(new JLabel("Defensive:"));
  centerRight.add(defensive);
  center.add(centerLeft);
  center.add(centerRight);
  container.add(center, BorderLayout.CENTER);

  //updateBestiary();
  
  setTitle("Companion Info");
  setSize(new Dimension(300,200));
  pack();
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateCompanionInfo(){
  Companion companion;
  String selected = " ";
  try{
   selected = list.getSelectedItem().toString();
  } catch (Exception exception){
  }
  dl.removeAllElements();
  boolean blah = false;
  for (int i=0; i<Background.player.companions.length; i++){
   companion = new Companion(i);
   if (Background.player.companions[i][0]>=1){
    blah = true;
    dl.addElement(companion.name);
   }
  }
  if (!blah){
   dl.addElement("none");
  }
  try{
   if (!selected.equals(" ")){
    list.setSelectedItem((Object)selected);
   }
  } catch (Exception exception){
  }
  try {
   String name = list.getSelectedItem().toString();
   name = "pictures/"+name+".jpg";
   picture = new ImagePanel(ImageIO.read(new File(name)));
  } catch (Exception exception){
   try{
    picture = new ImagePanel(ImageIO.read(new File("pictures/none.jpg")));
    System.out.println("companion jpg not found(companioninfo).");
   } catch (IOException exc){
    System.out.println("\"pictures\" file either missing or empty.");
   }
  }
  Companion companion2 = new Companion(Companion.identify(list.getSelectedItem().toString()));
  defeated.setText(Background.player.companions[companion2.identity][2]+"");
  try{
   west.removeAll();
   //west.setLayout(new BorderLayout());
   west.add(picture,BorderLayout.CENTER);
   west.revalidate();
  } catch (Exception exception){
  }
 }

 private class CompanionHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   try{
    if (list.getSelectedItem().toString().equals("none")){
     return;
    }
    Companion companion = new Companion(Companion.identify(list.getSelectedItem().toString()));
    name.setText(companion.name);
    defeated.setText(Background.player.companions[companion.identity][2]+"");
    description.setText(companion.description);
    offensive.setText(companion.offensive);
    defensive.setText(companion.defensive);
    updateCompanionInfo();
    pack();
   } catch (Exception exception){
   }
  }
 }


}