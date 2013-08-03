import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class StoreGUI extends JFrame{
 public int identity;
 private JPanel south, center, north;
 public JLabel money;
 private JComboBox weapons, armor, helmets, accessories, items, storeWeapons, storeArmor, storeHelmets, storeAccessories, storeItems;
 private DefaultComboBoxModel dw, dsw, da, dsa, di, dsi, dh, dsh, dac, dsac;
 private JButton buyWeapon, sellWeapon, buyArmor, sellArmor, buyHelmet, sellHelmet, buyAccessory, sellAccessory, buyItem, sellItem, exit;
 private ButtonHandler handler;
 private ExitHandler exitHandler;
 private SelectionHandler selectionHandler;
 private Store store;


 public StoreGUI(int ident){
  store = new Store(ident);
  Container container = getContentPane();

  north = new JPanel();
  money = new JLabel("You currently have some amount of money.");
  north.add(money);
  container.add(north, BorderLayout.NORTH);
  
  center = new JPanel();
  center.setLayout(new GridLayout(6,5,15,5));
  handler = new ButtonHandler();
  selectionHandler = new SelectionHandler();
  storeWeapons = new JComboBox();
  dsw = new DefaultComboBoxModel();
  storeWeapons.setModel(dsw);
  storeWeapons.addActionListener(selectionHandler);
  buyWeapon = new JButton("Buy");
  buyWeapon.addActionListener(handler);
  weapons = new JComboBox();
  dw = new DefaultComboBoxModel();
  weapons.setModel(dw);
  weapons.addActionListener(selectionHandler);
  sellWeapon = new JButton("Sell");
  sellWeapon.addActionListener(handler);
  storeArmor = new JComboBox();
  dsa = new DefaultComboBoxModel();
  storeArmor.setModel(dsa);
  storeArmor.addActionListener(selectionHandler);
  buyArmor = new JButton("Buy");
  buyArmor.addActionListener(handler);
  armor = new JComboBox();
  da = new DefaultComboBoxModel();
  armor.setModel(da);
  armor.addActionListener(selectionHandler);
  sellArmor = new JButton("Sell");
  sellArmor.addActionListener(handler);

  storeHelmets = new JComboBox();
  dsh = new DefaultComboBoxModel();
  storeHelmets.setModel(dsh);
  storeHelmets.addActionListener(selectionHandler);
  buyHelmet = new JButton("Buy");
  buyHelmet.addActionListener(handler);
  helmets = new JComboBox();
  dh = new DefaultComboBoxModel();
  helmets.setModel(dh);
  helmets.addActionListener(selectionHandler);
  sellHelmet = new JButton("Sell");
  sellHelmet.addActionListener(handler);

  storeAccessories = new JComboBox();
  dsac = new DefaultComboBoxModel();
  storeAccessories.setModel(dsac);
  storeAccessories.addActionListener(selectionHandler);
  buyAccessory = new JButton("Buy");
  buyAccessory.addActionListener(handler);
  accessories = new JComboBox();
  dac = new DefaultComboBoxModel();
  accessories.setModel(dac);
  accessories.addActionListener(selectionHandler);
  sellAccessory = new JButton("Sell");
  sellAccessory.addActionListener(handler);

  storeItems = new JComboBox();
  dsi = new DefaultComboBoxModel();
  storeItems.setModel(dsi);
  storeItems.addActionListener(selectionHandler);
  buyItem = new JButton("Buy");
  buyItem.addActionListener(handler);
  items = new JComboBox();
  di = new DefaultComboBoxModel();
  items.setModel(di);
  items.addActionListener(selectionHandler);
  sellItem = new JButton("Sell");
  sellItem.addActionListener(handler);
  center.add(new JLabel("Type", SwingConstants.CENTER));
  center.add(new JLabel("Store", SwingConstants.CENTER));
  center.add(new JLabel("Buy", SwingConstants.CENTER));
  center.add(new JLabel("Inventory", SwingConstants.CENTER));
  center.add(new JLabel("Sell", SwingConstants.CENTER));
  center.add(new JLabel("Weapons: "));
  center.add(storeWeapons);
  center.add(buyWeapon);
  center.add(weapons);
  center.add(sellWeapon);
  center.add(new JLabel("Armor: "));
  center.add(storeArmor);
  center.add(buyArmor);
  center.add(armor);
  center.add(sellArmor);
  center.add(new JLabel("Helmets: "));
  center.add(storeHelmets);
  center.add(buyHelmet);
  center.add(helmets);
  center.add(sellHelmet);
  center.add(new JLabel("Accessories: "));
  center.add(storeAccessories);
  center.add(buyAccessory);
  center.add(accessories);
  center.add(sellAccessory);
  center.add(new JLabel("Items: "));
  center.add(storeItems);
  center.add(buyItem);
  center.add(items);
  center.add(sellItem);
  center.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
  container.add(center, BorderLayout.CENTER);
  updateStore();
  Background.game.update();

  south = new JPanel();
  exit = new JButton("Exit Store");
  south.add(exit);
  exitHandler = new ExitHandler();
  exit.addActionListener(exitHandler);
  container.add(south, BorderLayout.SOUTH);

  setTitle("Store");
  pack();
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateStore(){

  dsw.removeAllElements();
  String[] array = new String[store.availableWeapons.size()];
  for (int i=0; i<store.availableWeapons.size(); i++){
   array[i]=store.availableWeapons.get(i).name;
   dsw.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsw.addElement(array[0]);
  }

  dsa.removeAllElements();
  array = new String[store.availableArmor.size()];
  for (int i=0; i<store.availableArmor.size(); i++){
   array[i]=store.availableArmor.get(i).name;
   dsa.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsa.addElement(array[0]);
  }

  dsh.removeAllElements();
  array = new String[store.availableHelmets.size()];
  for (int i=0; i<store.availableHelmets.size(); i++){
   array[i]=store.availableHelmets.get(i).name;
   dsh.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsh.addElement(array[0]);
  }

  dsac.removeAllElements();
  array = new String[store.availableAccessories.size()];
  for (int i=0; i<store.availableAccessories.size(); i++){
   array[i]=store.availableAccessories.get(i).name;
   dsac.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsac.addElement(array[0]);
  }

  dsi.removeAllElements();
  array = new String[store.availableItems.size()];
  for (int i=0; i<store.availableItems.size(); i++){
   array[i]=store.availableItems.get(i).name;
   dsi.addElement(array[i]);
  }
  if (array.length==0){
   array = new String[1];
   array[0] = "none";
   dsi.addElement(array[0]);
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

  money.setText("You currently have "+Background.player.money+" gold.");

  revalidate();
  repaint();
  pack();
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

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   try {
    if (source==buyWeapon){
     if (Background.player.money>=new Weapon(Weapon.identifier(storeWeapons.getSelectedItem().toString())).buy){
     //if (Background.player.takeMoney(new Weapon(Weapon.identifier(storeWeapons.getSelectedItem().toString())).buy)){
      if (Background.player.addToInventory(1,Weapon.identifier(storeWeapons.getSelectedItem().toString()),1)){
       //Background.player.weapons.add(new Weapon(Weapon.identifier(storeWeapons.getSelectedItem().toString())));
       Background.player.takeMoney(new Weapon(Weapon.identifier(storeWeapons.getSelectedItem().toString())).buy);
       store.availableWeapons.remove(storeWeapons.getSelectedIndex());
      }
     } else {
      Background.game.makeMessage("Lack O' Money", "You don't have enough gold.");
     }
    } else if (source==sellWeapon){
     if (!weapons.getSelectedItem().toString().equals("none")){
      Background.player.money = Background.player.money + (new Weapon(Weapon.identifier(weapons.getSelectedItem().toString())).sell);
      store.availableWeapons.add(new Weapon(Weapon.identifier(weapons.getSelectedItem().toString())));
      Background.player.weapons.remove(weapons.getSelectedIndex());
     }
    } else if (source==buyArmor){
     if (Background.player.money>=new Armor(Armor.identifier(storeArmor.getSelectedItem().toString())).buy){
     //if (Background.player.takeMoney(new Armor(Armor.identifier(storeArmor.getSelectedItem().toString())).buy)){
      if (Background.player.addToInventory(2,Armor.identifier(storeArmor.getSelectedItem().toString()),1)){
       //Background.player.armor.add(new Armor(Armor.identifier(storeArmor.getSelectedItem().toString())));
       Background.player.takeMoney(new Armor(Armor.identifier(storeArmor.getSelectedItem().toString())).buy);
       store.availableArmor.remove(storeArmor.getSelectedIndex());
      }
     } else {
      Background.game.makeMessage("Lack O' Money", "You don't have enough gold.");
     }
    } else if (source==sellArmor){
     if (!armor.getSelectedItem().toString().equals("none")){
      Background.player.money = Background.player.money + (new Armor(Armor.identifier(armor.getSelectedItem().toString())).sell);
      store.availableArmor.add(new Armor(Armor.identifier(armor.getSelectedItem().toString())));
      Background.player.armor.remove(armor.getSelectedIndex());
     }

    } else if (source==buyHelmet){
     if (Background.player.money>=new Helmet(Helmet.identifier(storeHelmets.getSelectedItem().toString())).buy){
      if (Background.player.addToInventory(4,Helmet.identifier(storeHelmets.getSelectedItem().toString()),1)){
       Background.player.takeMoney(new Helmet(Helmet.identifier(storeHelmets.getSelectedItem().toString())).buy);
       store.availableHelmets.remove(storeHelmets.getSelectedIndex());
      }
     } else {
      Background.game.makeMessage("Lack O' Money", "You don't have enough gold.");
     }
    } else if (source==sellHelmet){
     if (!helmets.getSelectedItem().toString().equals("none")){
      Background.player.money = Background.player.money + (new Helmet(Helmet.identifier(helmets.getSelectedItem().toString())).sell);
      store.availableHelmets.add(new Helmet(Helmet.identifier(helmets.getSelectedItem().toString())));
      Background.player.helmets.remove(helmets.getSelectedIndex());
     }

    } else if (source==buyAccessory){
     if (Background.player.money>=new Accessory(Accessory.identifier(storeAccessories.getSelectedItem().toString())).buy){
      if (Background.player.addToInventory(5,Accessory.identifier(storeAccessories.getSelectedItem().toString()),1)){
       Background.player.takeMoney(new Accessory(Accessory.identifier(storeAccessories.getSelectedItem().toString())).buy);
       store.availableAccessories.remove(storeAccessories.getSelectedIndex());
      }
     } else {
      Background.game.makeMessage("Lack O' Money", "You don't have enough gold.");
     }
    } else if (source==sellAccessory){
     if (!accessories.getSelectedItem().toString().equals("none")){
      Background.player.money = Background.player.money + (new Accessory(Accessory.identifier(accessories.getSelectedItem().toString())).sell);
      store.availableAccessories.add(new Accessory(Accessory.identifier(accessories.getSelectedItem().toString())));
      Background.player.accessories.remove(accessories.getSelectedIndex());
     }

    } else if (source==buyItem){
     if (Background.player.money>=new Item(Item.identifier(storeItems.getSelectedItem().toString())).buy){
     //if (Background.player.takeMoney(new Item(Item.identifier(storeItems.getSelectedItem().toString())).buy)){
      if (Background.player.addToInventory(3,Item.identifier(storeItems.getSelectedItem().toString()),1)){
       //Background.player.items.add(new Item(Item.identifier(storeItems.getSelectedItem().toString())));
       Background.player.takeMoney(new Item(Item.identifier(storeItems.getSelectedItem().toString())).buy);
       store.availableItems.remove(storeItems.getSelectedIndex());
      }
     } else {
      Background.game.makeMessage("Lack O' Money", "You don't have enough gold.");
     }
    } else if (source==sellItem){
     if (!items.getSelectedItem().toString().equals("none")){
      Background.player.money = Background.player.money + (new Item(Item.identifier(items.getSelectedItem().toString())).sell);
      store.availableItems.add(new Item(Item.identifier(items.getSelectedItem().toString())));
      Background.player.items.remove(items.getSelectedIndex());
     }
    }
   } catch (Exception exception){
   }
   Background.game.update();
   //updateStore();
  }
 }

 private class SelectionHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==storeWeapons){
    try{
     buyWeapon.setText(Integer.toString(new Weapon(Weapon.identifier(storeWeapons.getSelectedItem().toString())).buy)+" gold");
    } catch (Exception exception){
     buyWeapon.setText("");
    }
   } else if (source==weapons){
    try{
     sellWeapon.setText(Integer.toString(new Weapon(Weapon.identifier(weapons.getSelectedItem().toString())).sell)+" gold");
    } catch (Exception exception){
     sellWeapon.setText("");
    }
   } else if (source==storeArmor){
    try{
     buyArmor.setText(Integer.toString(new Armor(Armor.identifier(storeArmor.getSelectedItem().toString())).buy)+" gold");
    } catch (Exception exception){
     buyArmor.setText("");
    }
   } else if (source==armor){
    try{
     sellArmor.setText(Integer.toString(new Armor(Armor.identifier(armor.getSelectedItem().toString())).sell)+" gold");
    } catch (Exception exception){
     sellArmor.setText("");
    }

   } else if (source==storeHelmets){
    try{
     buyHelmet.setText(Integer.toString(new Helmet(Helmet.identifier(storeHelmets.getSelectedItem().toString())).buy)+" gold");
    } catch (Exception exception){
     buyHelmet.setText("");
    }
   } else if (source==helmets){
    try{
     sellHelmet.setText(Integer.toString(new Helmet(Helmet.identifier(helmets.getSelectedItem().toString())).sell)+" gold");
    } catch (Exception exception){
     sellHelmet.setText("");
    }

   } else if (source==storeAccessories){
    try{
     buyAccessory.setText(Integer.toString(new Accessory(Accessory.identifier(storeAccessories.getSelectedItem().toString())).buy)+" gold");
    } catch (Exception exception){
     buyAccessory.setText("");
    }
   } else if (source==accessories){
    try{
     sellAccessory.setText(Integer.toString(new Accessory(Accessory.identifier(accessories.getSelectedItem().toString())).sell)+" gold");
    } catch (Exception exception){
     sellAccessory.setText("");
    }

   } else if (source==storeItems){
    try{
     buyItem.setText(Integer.toString(new Item(Item.identifier(storeItems.getSelectedItem().toString())).buy)+" gold");
    } catch (Exception exception){
     buyItem.setText("");
    }
   } else if (source==items){
    try{
     sellItem.setText(Integer.toString(new Item(Item.identifier(items.getSelectedItem().toString())).sell)+" gold");
    } catch (Exception exception){
     sellItem.setText("");
    }
   }
  }
 }

}