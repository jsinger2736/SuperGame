import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class InventoryGUI extends JFrame{
 private JPanel north, center, south;
 private JLabel money;
 private JComboBox weapons, armor, helmets, accessories, items;
 private DefaultComboBoxModel dw, da, dh, dac, di;
 private JButton equipWeapon, equipArmor, useItem, dropWeapon, dropArmor, dropItem, equipHelmet, equipAccessory, dropHelmet, dropAccessory, craft;
 private ButtonHandler handler;
 public CraftGUI craftGUI;


 public InventoryGUI(){
  Container container = getContentPane();
  handler = new ButtonHandler();

  north = new JPanel();
  money = new JLabel("You have some amount of money.");
  north.add(money);
  container.add(north, BorderLayout.NORTH);

  south = new JPanel();
  craft = new JButton("Crafting");
  craft.addActionListener(handler);
  south.add(craft);
  container.add(south, BorderLayout.SOUTH);

  center = new JPanel();
  center.setLayout(new GridLayout(5,4,15,5));
  weapons = new JComboBox();
  dw = new DefaultComboBoxModel();
  weapons.setModel(dw);
  equipWeapon = new JButton("Equip");
  equipWeapon.addActionListener(handler);
  dropWeapon = new JButton("Drop");
  dropWeapon.addActionListener(handler);
  center.add(new JLabel("Weapons: "));
  center.add(weapons);
  center.add(equipWeapon);
  center.add(dropWeapon);
  armor = new JComboBox();
  da = new DefaultComboBoxModel();
  armor.setModel(da);
  equipArmor = new JButton("Equip");
  equipArmor.addActionListener(handler);
  dropArmor = new JButton("Drop");
  dropArmor.addActionListener(handler);
  center.add(new JLabel("Armor: "));
  center.add(armor);
  center.add(equipArmor);
  center.add(dropArmor);

  helmets = new JComboBox();
  dh = new DefaultComboBoxModel();
  helmets.setModel(dh);
  equipHelmet = new JButton("Equip");
  equipHelmet.addActionListener(handler);
  dropHelmet = new JButton("Drop");
  dropHelmet.addActionListener(handler);
  center.add(new JLabel("Helmets: "));
  center.add(helmets);
  center.add(equipHelmet);
  center.add(dropHelmet);

  accessories = new JComboBox();
  dac = new DefaultComboBoxModel();
  accessories.setModel(dac);
  equipAccessory = new JButton("Equip");
  equipAccessory.addActionListener(handler);
  dropAccessory = new JButton("Drop");
  dropAccessory.addActionListener(handler);
  center.add(new JLabel("Accessories: "));
  center.add(accessories);
  center.add(equipAccessory);
  center.add(dropAccessory);

  items = new JComboBox();
  di = new DefaultComboBoxModel();
  items.setModel(di);
  useItem = new JButton("Use");
  useItem.addActionListener(handler);
  useItem.setVisible(false);
  dropItem = new JButton("Drop");
  dropItem.addActionListener(handler);
  center.add(new JLabel("Items: "));
  center.add(items);
  center.add(useItem);
  center.add(dropItem);
  center.setBorder(BorderFactory.createEmptyBorder(5,10,10,10));
  container.add(center, BorderLayout.CENTER);
  updateInventory();

  setTitle("Inventory");
  //pack();
  setSize(new Dimension(500,268));
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateInventory(){
  dw.removeAllElements();
  String[] weaponsArray = new String[Background.player.weapons.size()];
  for (int i=0; i<Background.player.weapons.size(); i++){
   weaponsArray[i]=Background.player.weapons.get(i).name;
   dw.addElement(weaponsArray[i]);
  }
  weaponsArray = new String[1];
  weaponsArray[0] = "none";
  dw.addElement(weaponsArray[0]);

  da.removeAllElements();
  String[] armorArray = new String[Background.player.armor.size()];
  for (int i=0; i<Background.player.armor.size(); i++){
   armorArray[i]=Background.player.armor.get(i).name;
   da.addElement(armorArray[i]);
  }
  armorArray = new String[1];
  armorArray[0] = "none";
  da.addElement(armorArray[0]);

  dh.removeAllElements();
  String[] helmetsArray = new String[Background.player.helmets.size()];
  for (int i=0; i<Background.player.helmets.size(); i++){
   helmetsArray[i]=Background.player.helmets.get(i).name;
   dh.addElement(helmetsArray[i]);
  }
  helmetsArray = new String[1];
  helmetsArray[0] = "none";
  dh.addElement(helmetsArray[0]);

  dac.removeAllElements();
  String[] accessoriesArray = new String[Background.player.accessories.size()];
  for (int i=0; i<Background.player.accessories.size(); i++){
   accessoriesArray[i]=Background.player.accessories.get(i).name;
   dac.addElement(accessoriesArray[i]);
  }
  accessoriesArray = new String[1];
  accessoriesArray[0] = "none";
  dac.addElement(accessoriesArray[0]);

  di.removeAllElements();
  String[] itemsArray = new String[Background.player.items.size()];
  for (int i=0; i<Background.player.items.size(); i++){
   itemsArray[i]=Background.player.items.get(i).name;
   di.addElement(itemsArray[i]);
  }
  if (itemsArray.length==0){
   itemsArray = new String[1];
   itemsArray[0] = "none";
   di.addElement(itemsArray[0]);
  }
  money.setText("You have "+Background.player.money+" gold.");
  revalidate();
  repaint();
 }

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==equipWeapon){
    Weapon weaponchoice = new Weapon(Weapon.identifier(weapons.getSelectedItem().toString()));
    //System.out.println(weapons.getSelectedItem().toString());
    //System.out.println(Weapon.identifier(weapons.getSelectedItem().toString()));
    try{
     Background.player.weapons.remove(weapons.getSelectedIndex());
    } catch (Exception exception){
    }
    if (!Background.player.equippedWeapon.name.equals("none")){
     Background.player.weapons.add(Background.player.equippedWeapon);
    }
    Background.player.equippedWeapon = weaponchoice;
    //System.out.println("equip weapon"+weaponchoice.name);
   } else if (source==equipArmor){
    Armor armorchoice = new Armor(Armor.identifier(armor.getSelectedItem().toString()));
    //System.out.println(armor.getSelectedItem().toString());
    //System.out.println(Armor.identifier(armor.getSelectedItem().toString()));
    try{
     Background.player.armor.remove(armor.getSelectedIndex());
    } catch (Exception exception){
    }
    if (!Background.player.equippedArmor.name.equals("none")){
     Background.player.armor.add(Background.player.equippedArmor);
    }
    Background.player.equippedArmor = armorchoice;
    //System.out.println("equip armor"+armorchoice.name);

   } else if (source==equipHelmet){
    Helmet helmetchoice = new Helmet(Helmet.identifier(helmets.getSelectedItem().toString()));
    try{
     Background.player.helmets.remove(helmets.getSelectedIndex());
    } catch (Exception exception){
    }
    if (!Background.player.equippedHelmet.name.equals("none")){
     Background.player.helmets.add(Background.player.equippedHelmet);
    }
    Background.player.equippedHelmet = helmetchoice;

   } else if (source==equipAccessory){
    Accessory accessorychoice = new Accessory(Accessory.identifier(accessories.getSelectedItem().toString()));
    try{
     Background.player.accessories.remove(accessories.getSelectedIndex());
    } catch (Exception exception){
    }
    if (!Background.player.equippedAccessory.name.equals("none")){
     Background.player.accessories.add(Background.player.equippedAccessory);
    }
    Background.player.equippedAccessory = accessorychoice;

   } else if (source==useItem){

   } else if (source==dropWeapon){
    try{
     Background.player.weapons.remove(weapons.getSelectedIndex());
    } catch (Exception exception){
    }
   } else if (source==dropArmor){
    try{
     Background.player.armor.remove(armor.getSelectedIndex());
    } catch (Exception exception){
    }
   } else if (source==dropHelmet){
    try{
     Background.player.helmets.remove(helmets.getSelectedIndex());
    } catch (Exception exception){
    }
   } else if (source==dropAccessory){
    try{
     Background.player.accessories.remove(accessories.getSelectedIndex());
    } catch (Exception exception){
    }
   } else if (source==dropItem){
    try{
     Background.player.items.remove(items.getSelectedIndex());
    } catch (Exception exception){
    }
   } else if (source==craft){
     if (craftGUI!=null){
      craftGUI.setVisible(false);
     }
     craftGUI = new CraftGUI();
   }
   Background.game.update();
  }
 }

}