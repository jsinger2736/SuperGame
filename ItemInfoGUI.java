import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class ItemInfoGUI extends JFrame{
 private JPanel north, center, centerLeft, centerRight;
 private JComboBox list;
 private DefaultComboBoxModel dl;
 private ThingyHandler thingyHandler;
 private JLabel name, description, buy, sell, info;

 public ItemInfoGUI(){
  Container container = getContentPane();
  thingyHandler = new ThingyHandler();

  north = new JPanel();
  list = new JComboBox();
  dl = new DefaultComboBoxModel();
  list.setModel(dl);
  list.addActionListener(thingyHandler);
  north.add(list);
  container.add(north, BorderLayout.NORTH);
  
  center = new JPanel();
  //center.setLayout(new GridLayout(5,2,10,10));
  centerLeft = new JPanel();
  centerLeft.setLayout(new GridLayout(5,1,10,10));
  centerLeft.setBorder(BorderFactory.createEmptyBorder(0,5,5,7));
  centerRight = new JPanel();
  centerRight.setLayout(new GridLayout(5,1,10,10));
  centerRight.setBorder(BorderFactory.createEmptyBorder(0,0,5,5));
  name = new JLabel("");
  description = new JLabel("");
  buy = new JLabel("");
  sell = new JLabel("");
  info = new JLabel("");
  centerLeft.add(new JLabel("Name:"));
  centerRight.add(name);
  centerLeft.add(new JLabel("Description:"));
  centerRight.add(description);
  centerLeft.add(new JLabel("Info:"));
  centerRight.add(info);
  centerLeft.add(new JLabel("Buy:"));
  centerRight.add(buy);
  centerLeft.add(new JLabel("Sell:"));
  centerRight.add(sell);
  center.add(centerLeft);
  center.add(centerRight);
  container.add(center, BorderLayout.CENTER);

  //updateItemInfo();
  
  setTitle("Item Info");
  setSize(new Dimension(300,200));
  //pack();
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateItemInfo(){
  Thingy thingy;
  ArrayList<Thingy> thingyArray = new ArrayList<Thingy>();
  String selected = " ";
  try{
   selected = list.getSelectedItem().toString();
  } catch (Exception exception){
  }
  dl.removeAllElements();
  boolean blah = false;
  if (!Background.player.equippedWeapon.name.equals("none")){
   thingy = Background.player.equippedWeapon;
   thingyArray.add(thingy);
  }
  if (!Background.player.equippedArmor.name.equals("none")){
   thingy = Background.player.equippedArmor;
   thingyArray.add(thingy);
  }
  if (!Background.player.equippedHelmet.name.equals("none")){
   thingy = Background.player.equippedHelmet;
   thingyArray.add(thingy);
  }
  if (!Background.player.equippedAccessory.name.equals("none")){
   thingy = Background.player.equippedAccessory;
   thingyArray.add(thingy);
  }
  for (int i=0; i<Background.player.weapons.size(); i++){
   thingy = Background.player.weapons.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.armor.size(); i++){
   thingy = Background.player.armor.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.helmets.size(); i++){
   thingy = Background.player.helmets.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.accessories.size(); i++){
   thingy = Background.player.accessories.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.items.size(); i++){
   thingy = Background.player.items.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.chest.weapons.size(); i++){
   thingy = Background.player.chest.weapons.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.chest.armor.size(); i++){
   thingy = Background.player.chest.armor.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.chest.helmets.size(); i++){
   thingy = Background.player.chest.helmets.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.chest.accessories.size(); i++){
   thingy = Background.player.chest.accessories.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<Background.player.chest.items.size(); i++){
   thingy = Background.player.chest.items.get(i);
   if (!thingyArray.contains(thingy)){
    thingyArray.add(thingy);
   }
  }
  for (int i=0; i<thingyArray.size(); i++){
   dl.addElement(thingyArray.get(i).name);
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

 private class ThingyHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   try{
    if (list.getSelectedItem().toString().equals("none")){
    name.setText("None");
    description.setText("Chock full o' nothing.");
    info.setText("Does... nothing.");
    buy.setText("0 gold");
    sell.setText("0 gold");
    pack();
     return;
    }
    Thingy thingy;
    int type1;
    if (Weapon.identifier(list.getSelectedItem().toString())==-1){
     if (Armor.identifier(list.getSelectedItem().toString())==-1){
      if (Item.identifier(list.getSelectedItem().toString())==-1){
       if (Helmet.identifier(list.getSelectedItem().toString())==-1){
        if (Accessory.identifier(list.getSelectedItem().toString())==-1){
         System.out.println("first ingredient was unidentifiable, sorry");
         name.setText("");
         description.setText("");
         info.setText("");
         buy.setText("");
         sell.setText("");
         return;
        } else {
         thingy = new Accessory(Accessory.identifier(list.getSelectedItem().toString()));
         type1 = 5;
        }
       } else {
        thingy = new Helmet(Helmet.identifier(list.getSelectedItem().toString()));
        type1 = 4;
       }
      } else {
       thingy = new Item(Item.identifier(list.getSelectedItem().toString()));
       type1 = 3;
      }
     } else {
      thingy = new Armor(Armor.identifier(list.getSelectedItem().toString()));
      type1 = 2;
     }
    } else {
     thingy = new Weapon(Weapon.identifier(list.getSelectedItem().toString()));
     type1 = 1;
    }
    name.setText(thingy.name);
    description.setText(thingy.description);
    info.setText(thingy.info);
    buy.setText(thingy.buy+" gold");
    sell.setText(thingy.sell+" gold");
    pack();
   } catch (Exception exception){
   }
  }
 }









}









