import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class ChestGUI extends JFrame{
 public int identity;
 private JPanel south, center/*, north*/;
 //public JLabel money;
 private JComboBox weapons, armor, helmets, accessories, items, chestWeapons, chestArmor, chestHelmets, chestAccessories, chestItems;
 private DefaultComboBoxModel dw, dsw, da, dsa, di, dsi, dh, dac, dsh, dsac;
 private JButton getWeapon, stashWeapon, getArmor, stashArmor, getHelmet, stashHelmet, getAccessory, stashAccessory, getItem, stashItem, exit;
 private ButtonHandler handler;
 private ExitHandler exitHandler;
 private SelectionHandler selectionHandler;
  
 public ChestGUI(/*int ident*/){ //uses Background.player.chest
  Container container = getContentPane();

  /*north = new JPanel();
  money = new JLabel("You currently have some amount of money.");
  north.add(money);
  container.add(north, BorderLayout.NORTH);*/
  
  center = new JPanel();
  center.setLayout(new GridLayout(6,5,15,5));
  handler = new ButtonHandler();
  selectionHandler = new SelectionHandler();
  chestWeapons = new JComboBox();
  dsw = new DefaultComboBoxModel();
  chestWeapons.setModel(dsw);
  chestWeapons.addActionListener(selectionHandler);
  getWeapon = new JButton("Get");
  getWeapon.addActionListener(handler);
  weapons = new JComboBox();
  dw = new DefaultComboBoxModel();
  weapons.setModel(dw);
  weapons.addActionListener(selectionHandler);
  stashWeapon = new JButton("Stash");
  stashWeapon.addActionListener(handler);
  chestArmor = new JComboBox();
  dsa = new DefaultComboBoxModel();
  chestArmor.setModel(dsa);
  chestArmor.addActionListener(selectionHandler);
  getArmor = new JButton("Get");
  getArmor.addActionListener(handler);
  armor = new JComboBox();
  da = new DefaultComboBoxModel();
  armor.setModel(da);
  armor.addActionListener(selectionHandler);
  stashArmor = new JButton("Stash");
  stashArmor.addActionListener(handler);

  chestHelmets = new JComboBox();
  dsh = new DefaultComboBoxModel();
  chestHelmets.setModel(dsh);
  chestHelmets.addActionListener(selectionHandler);
  getHelmet = new JButton("Get");
  getHelmet.addActionListener(handler);
  helmets = new JComboBox();
  dh = new DefaultComboBoxModel();
  helmets.setModel(dh);
  helmets.addActionListener(selectionHandler);
  stashHelmet = new JButton("Stash");
  stashHelmet.addActionListener(handler);

  chestAccessories = new JComboBox();
  dsac = new DefaultComboBoxModel();
  chestAccessories.setModel(dsac);
  chestAccessories.addActionListener(selectionHandler);
  getAccessory = new JButton("Get");
  getAccessory.addActionListener(handler);
  accessories = new JComboBox();
  dac = new DefaultComboBoxModel();
  accessories.setModel(dac);
  accessories.addActionListener(selectionHandler);
  stashAccessory = new JButton("Stash");
  stashAccessory.addActionListener(handler);

  chestItems = new JComboBox();
  dsi = new DefaultComboBoxModel();
  chestItems.setModel(dsi);
  chestItems.addActionListener(selectionHandler);
  getItem = new JButton("Get");
  getItem.addActionListener(handler);
  items = new JComboBox();
  di = new DefaultComboBoxModel();
  items.setModel(di);
  items.addActionListener(selectionHandler);
  stashItem = new JButton("Stash");
  stashItem.addActionListener(handler);
  center.add(new JLabel("Type", SwingConstants.CENTER));
  center.add(new JLabel("Chest", SwingConstants.CENTER));
  center.add(new JLabel("", SwingConstants.CENTER));
  center.add(new JLabel("Inventory", SwingConstants.CENTER));
  center.add(new JLabel("", SwingConstants.CENTER));
  center.add(new JLabel("Weapons: "));
  center.add(chestWeapons);
  center.add(getWeapon);
  center.add(weapons);
  center.add(stashWeapon);
  center.add(new JLabel("Armor: "));
  center.add(chestArmor);
  center.add(getArmor);
  center.add(armor);
  center.add(stashArmor);
  center.add(new JLabel("Helmets: "));
  center.add(chestHelmets);
  center.add(getHelmet);
  center.add(helmets);
  center.add(stashHelmet);
  center.add(new JLabel("Accessories: "));
  center.add(chestAccessories);
  center.add(getAccessory);
  center.add(accessories);
  center.add(stashAccessory);
  center.add(new JLabel("Items: "));
  center.add(chestItems);
  center.add(getItem);
  center.add(items);
  center.add(stashItem);
  center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  container.add(center, BorderLayout.CENTER);
  updateChest();
  Background.game.update();

  south = new JPanel();
  exit = new JButton("Exit");
  south.add(exit);
  exitHandler = new ExitHandler();
  exit.addActionListener(exitHandler);
  container.add(south, BorderLayout.SOUTH);

  setTitle("Chest - Yes, it's bigger on the inside.");
  pack();
  setLocationRelativeTo(null);
  //setVisible(true);
 }

 public void updateChest(){

  dsw.removeAllElements();
  String[] array = new String[Background.player.chest.weapons.size()];
  for (int i=0; i<Background.player.chest.weapons.size(); i++){
   array[i]=Background.player.chest.weapons.get(i).name;
   dsw.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsw.addElement(array[0]);
  }

  dsa.removeAllElements();
  array = new String[Background.player.chest.armor.size()];
  for (int i=0; i<Background.player.chest.armor.size(); i++){
   array[i]=Background.player.chest.armor.get(i).name;
   dsa.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsa.addElement(array[0]);
  }

  dsi.removeAllElements();
  array = new String[Background.player.chest.items.size()];
  for (int i=0; i<Background.player.chest.items.size(); i++){
   array[i]=Background.player.chest.items.get(i).name;
   dsi.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsi.addElement(array[0]);
  }

  dsh.removeAllElements();
  array = new String[Background.player.chest.helmets.size()];
  for (int i=0; i<Background.player.chest.helmets.size(); i++){
   array[i]=Background.player.chest.helmets.get(i).name;
   dsh.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsh.addElement(array[0]);
  }

  dsac.removeAllElements();
  array = new String[Background.player.chest.accessories.size()];
  for (int i=0; i<Background.player.chest.accessories.size(); i++){
   array[i]=Background.player.chest.accessories.get(i).name;
   dsac.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsac.addElement(array[0]);
  }

  dw.removeAllElements();
  array = new String[Background.player.weapons.size()];
  for (int i=0; i<Background.player.weapons.size(); i++){
   array[i]=Background.player.weapons.get(i).name;
   dw.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dw.addElement(array[0]);
  }

  da.removeAllElements();
  array = new String[Background.player.armor.size()];
  for (int i=0; i<Background.player.armor.size(); i++){
   array[i]=Background.player.armor.get(i).name;
   da.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   da.addElement(array[0]);
  }

  di.removeAllElements();
  array = new String[Background.player.items.size()];
  for (int i=0; i<Background.player.items.size(); i++){
   array[i]=Background.player.items.get(i).name;
   di.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   di.addElement(array[0]);
  }

  dh.removeAllElements();
  array = new String[Background.player.helmets.size()];
  for (int i=0; i<Background.player.helmets.size(); i++){
   array[i]=Background.player.helmets.get(i).name;
   dh.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dh.addElement(array[0]);
  }

  dac.removeAllElements();
  array = new String[Background.player.accessories.size()];
  for (int i=0; i<Background.player.accessories.size(); i++){
   array[i]=Background.player.accessories.get(i).name;
   dac.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dac.addElement(array[0]);
  }

  revalidate();
  repaint();
  pack();
 }

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   try {
    if (source==getWeapon){
     if (Background.player.addToInventory(1,Weapon.identifier(chestWeapons.getSelectedItem().toString()),1)){
      Background.player.chest.weapons.remove(chestWeapons.getSelectedIndex());
     }
    } else if (source==stashWeapon){
     if (!weapons.getSelectedItem().toString().equals("none")){
      Background.player.chest.weapons.add(new Weapon(Weapon.identifier(weapons.getSelectedItem().toString())));
      Background.player.weapons.remove(weapons.getSelectedIndex());
     }
    } else if (source==getArmor){
     if (Background.player.addToInventory(2,Armor.identifier(chestArmor.getSelectedItem().toString()),1)){
      Background.player.chest.armor.remove(chestArmor.getSelectedIndex());
     }
    } else if (source==stashArmor){
     if (!armor.getSelectedItem().toString().equals("none")){
      Background.player.chest.armor.add(new Armor(Armor.identifier(armor.getSelectedItem().toString())));
      Background.player.armor.remove(armor.getSelectedIndex());
     }
    } else if (source==getHelmet){
     if (Background.player.addToInventory(4,Helmet.identifier(chestHelmets.getSelectedItem().toString()),1)){
      Background.player.chest.helmets.remove(chestHelmets.getSelectedIndex());
     }
    } else if (source==stashHelmet){
     if (!helmets.getSelectedItem().toString().equals("none")){
      Background.player.chest.helmets.add(new Helmet(Helmet.identifier(helmets.getSelectedItem().toString())));
      Background.player.helmets.remove(helmets.getSelectedIndex());
     }
    } else if (source==getAccessory){
     if (Background.player.addToInventory(5,Accessory.identifier(chestAccessories.getSelectedItem().toString()),1)){
      Background.player.chest.accessories.remove(chestAccessories.getSelectedIndex());
     }
    } else if (source==stashAccessory){
     if (!accessories.getSelectedItem().toString().equals("none")){
      Background.player.chest.accessories.add(new Accessory(Accessory.identifier(accessories.getSelectedItem().toString())));
      Background.player.accessories.remove(accessories.getSelectedIndex());
     }
    } else if (source==getItem){
     if (Background.player.addToInventory(3,Item.identifier(chestItems.getSelectedItem().toString()),1)){
      Background.player.chest.items.remove(chestItems.getSelectedIndex());
     }
    } else if (source==stashItem){
     if (!items.getSelectedItem().toString().equals("none")){
      Background.player.chest.items.add(new Item(Item.identifier(items.getSelectedItem().toString())));
      Background.player.items.remove(items.getSelectedIndex());
     }
    }
   } catch (Exception exception){
   }
   Background.game.update();
  }
 }
 private class ExitHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Background.player.go = false;
   Background.game.update();
   setVisible(false);
   Background.game.update();
   setVisible(false);
  }
 }
 private class SelectionHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){

  }
 }

}