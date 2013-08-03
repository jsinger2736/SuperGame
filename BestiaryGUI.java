import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class BestiaryGUI extends JFrame{
 private JPanel north, center, centerLeft, centerRight;
 private JComboBox list;
 private DefaultComboBoxModel dl;
 private CreatureHandler creatureHandler;
 private JLabel name, defeated, description, tendency, offensive, defensive, drops;

 public BestiaryGUI(){
  Container container = getContentPane();
  creatureHandler = new CreatureHandler();

  north = new JPanel();
  list = new JComboBox();
  dl = new DefaultComboBoxModel();
  list.setModel(dl);
  list.addActionListener(creatureHandler);
  north.add(list);
  container.add(north, BorderLayout.NORTH);
  
  center = new JPanel();
  //center.setLayout(new GridLayout(5,2,10,10));
  centerLeft = new JPanel();
  centerLeft.setLayout(new GridLayout(7,1,10,10));
  centerLeft.setBorder(BorderFactory.createEmptyBorder(0,5,5,7));
  centerRight = new JPanel();
  centerRight.setLayout(new GridLayout(7,1,10,10));
  centerRight.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));
  name = new JLabel("");
  defeated = new JLabel("");
  description = new JLabel("");
  tendency = new JLabel("");
  offensive = new JLabel("");
  defensive = new JLabel("");
  drops = new JLabel("");
  centerLeft.add(new JLabel("Name:"));
  centerRight.add(name);
  centerLeft.add(new JLabel("Defeated: "));
  centerRight.add(defeated);
  centerLeft.add(new JLabel("Description:"));
  centerRight.add(description);
  centerLeft.add(new JLabel("Tendency:"));
  centerRight.add(tendency);
  centerLeft.add(new JLabel("Offensive:"));
  centerRight.add(offensive);
  centerLeft.add(new JLabel("Defensive:"));
  centerRight.add(defensive);
  centerLeft.add(new JLabel("Drops:"));
  centerRight.add(drops);
  center.add(centerLeft);
  center.add(centerRight);
  container.add(center, BorderLayout.CENTER);

  //updateBestiary();
  
  setTitle("Bestiary");
  setSize(new Dimension(300,200));
  //pack();
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateBestiary(){
  Creature creature;
  String selected = " ";
  try{
   selected = list.getSelectedItem().toString();
  } catch (Exception exception){
  }
  dl.removeAllElements();
  boolean blah = false;
  for (int i=0; i<Background.player.bestiary.length; i++){
   creature = new Creature(i,1);
   if (Background.player.bestiary[i]>=1){
    blah = true;
    dl.addElement(creature.name);
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
 }

 private class CreatureHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   try{
    if (list.getSelectedItem().toString().equals("none")){
     return;
    }
    Creature creature = new Creature(Creature.identify(list.getSelectedItem().toString()),1);
    name.setText(creature.name);
    defeated.setText(Background.player.bestiary[creature.identity]+"");
    description.setText(creature.description);
    if (creature.aggressive){
     tendency.setText("Aggressive.");
    } else {
     tendency.setText("Passive.");
    }
    offensive.setText(creature.offensive);
    defensive.setText(creature.defensive);
    drops.setText(creature.drops);
    pack();
   } catch (Exception exception){
   }
  }
 }









}









