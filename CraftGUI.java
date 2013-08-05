import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class CraftGUI extends JFrame{
 private JPanel center, south;
 private JComboBox ingredient1, ingredient2, ingredient3;
 private DefaultComboBoxModel d1, d2, d3;
 private JButton craft;
 private JLabel outcome;
 private ButtonHandler handler;
 private SelectionHandler selectionHandler;
 private boolean updating = false;

 public CraftGUI(){
  Container container = getContentPane();
  handler = new ButtonHandler();

  center = new JPanel();
  center.setLayout(new GridLayout(2,4,15,5));
  selectionHandler = new SelectionHandler();
  ingredient1 = new JComboBox();
  d1 = new DefaultComboBoxModel();
  ingredient1.setModel(d1);
  ingredient1.addActionListener(selectionHandler);
  ingredient2 = new JComboBox();
  d2 = new DefaultComboBoxModel();
  ingredient2.setModel(d2);
  ingredient2.addActionListener(selectionHandler);
  ingredient3 = new JComboBox();
  d3 = new DefaultComboBoxModel();
  ingredient3.setModel(d3);
  ingredient3.addActionListener(selectionHandler);
  outcome = new JLabel("", SwingConstants.CENTER);
  center.add(new JLabel("First Ingredient", SwingConstants.CENTER));
  center.add(new JLabel("Second Ingredient", SwingConstants.CENTER));
  center.add(new JLabel("Third Ingredient", SwingConstants.CENTER));
  center.add(new JLabel("Outcome", SwingConstants.CENTER));
  center.add(ingredient1);
  center.add(ingredient2);
  center.add(ingredient3);
  center.add(outcome);
  center.setBorder(BorderFactory.createEmptyBorder(5,15,0,10));
  container.add(center, BorderLayout.CENTER);

  south = new JPanel();
  craft = new JButton("Craft");
  craft.addActionListener(handler);
  south.add(craft);
  container.add(south, BorderLayout.SOUTH);
  

  updateCrafting();
  updateCrafting();
  setTitle("Crafting");
  pack();
  //setSize(new Dimension(500,160));
  setLocation(Background.game.inventoryGUI.getX(),Background.game.inventoryGUI.getY()+30);
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public void updateCrafting(){
  updating=true;
  //System.out.println("updateCrafting is starting");
  String[] stuffArray = new String[Background.player.weapons.size()+Background.player.armor.size()+Background.player.items.size()+Background.player.helmets.size()+Background.player.accessories.size()+1];
  stuffArray[0] = "none";
  for (int i=0; i<Background.player.weapons.size(); i++){
   stuffArray[i+1]=Background.player.weapons.get(i).name;
  }
  for (int i=0; i<Background.player.armor.size(); i++){
   stuffArray[i+Background.player.weapons.size()+1]=Background.player.armor.get(i).name;
  }
  for (int i=0; i<Background.player.helmets.size(); i++){
   stuffArray[i+Background.player.weapons.size()+Background.player.armor.size()+1]=Background.player.helmets.get(i).name;
  }
  for (int i=0; i<Background.player.accessories.size(); i++){
   stuffArray[i+Background.player.weapons.size()+Background.player.armor.size()+Background.player.helmets.size()+1]=Background.player.accessories.get(i).name;
  }
  for (int i=0; i<Background.player.items.size(); i++){
   stuffArray[i+Background.player.weapons.size()+Background.player.armor.size()+Background.player.helmets.size()+Background.player.accessories.size()+1]=Background.player.items.get(i).name;
  }
  if (ingredient1.getSelectedIndex()==0){
   d1.removeAllElements();
   for (int i=0; i<stuffArray.length; i++){
     d1.addElement(stuffArray[i]);
   }
  }
  if (ingredient2.getSelectedIndex()==0){
   if (ingredient1.getSelectedIndex()!=0){
    d2.removeAllElements();
    for (int i=0; i<stuffArray.length; i++){
     if (i!=ingredient1.getSelectedIndex()){
      d2.addElement(stuffArray[i]);
     }
    }
   } else {
    d2.removeAllElements();
   }
  }
  if (ingredient1.getSelectedIndex()==0){
   d2.removeAllElements();
  }
  if (ingredient3.getSelectedIndex()==0){
   if (ingredient1.getSelectedIndex()!=0 && ingredient2.getSelectedIndex()!=0){
    d3.removeAllElements();
    for (int i=0; i<stuffArray.length; i++){
     int adjustment;
     if (ingredient1.getSelectedIndex()<=ingredient2.getSelectedIndex()){
      adjustment = 1;
     } else {
      adjustment = 0;
     }
     if (i!=ingredient1.getSelectedIndex() && i!=ingredient2.getSelectedIndex()+adjustment){
      d3.addElement(stuffArray[i]);
     }
    }
   }
  }
  if (ingredient1.getSelectedIndex()==0 || ingredient2.getSelectedIndex()==0){
   d3.removeAllElements();
  }
  if (ingredient1.getSelectedItem()==null){
   d1.addElement("none");
  }
  if (ingredient2.getSelectedItem()==null){
   d2.addElement("none");
  }
  if (ingredient3.getSelectedItem()==null){
   d3.addElement("none");
  }
  updating=false;
 }

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   int ingnumber = 0;
   int type1 = 0, type2 = 0, type3 = 0;
   boolean correct = false, missing = false;
   Thingy firstIngredient, secondIngredient, thirdIngredient, creation=new Thingy();
   if (ingredient1.getSelectedIndex()!=0){
    ingnumber = 1;
    if (ingredient2.getSelectedIndex()!=0){
     ingnumber = 2;
     if (ingredient3.getSelectedIndex()!=0){
      ingnumber = 3;
     }
    }
   }
   if (ingnumber==0 || ingnumber==1){
    return;
   }
   if (Weapon.identifier(ingredient1.getSelectedItem().toString())==-1){
    if (Armor.identifier(ingredient1.getSelectedItem().toString())==-1){
     if (Item.identifier(ingredient1.getSelectedItem().toString())==-1){
      if (Helmet.identifier(ingredient1.getSelectedItem().toString())==-1){
       if (Accessory.identifier(ingredient1.getSelectedItem().toString())==-1){
        System.out.println("first ingredient was unidentifiable, sorry");
        return;
       } else {
        firstIngredient = new Accessory(Accessory.identifier(ingredient1.getSelectedItem().toString()));
        type1 = 5;
       }
      } else {
       firstIngredient = new Helmet(Helmet.identifier(ingredient1.getSelectedItem().toString()));
       type1 = 4;
      }
     } else {
      firstIngredient = new Item(Item.identifier(ingredient1.getSelectedItem().toString()));
      type1 = 3;
     }
    } else {
     firstIngredient = new Armor(Armor.identifier(ingredient1.getSelectedItem().toString()));
     type1 = 2;
    }
   } else {
    firstIngredient = new Weapon(Weapon.identifier(ingredient1.getSelectedItem().toString()));
    type1 = 1;
   }
   if (Weapon.identifier(ingredient2.getSelectedItem().toString())==-1){
    if (Armor.identifier(ingredient2.getSelectedItem().toString())==-1){
     if (Item.identifier(ingredient2.getSelectedItem().toString())==-1){
      if (Helmet.identifier(ingredient2.getSelectedItem().toString())==-1){
       if (Accessory.identifier(ingredient2.getSelectedItem().toString())==-1){
        System.out.println("second ingredient was unidentifiable, sorry");
        return;
       } else {
        secondIngredient = new Accessory(Accessory.identifier(ingredient2.getSelectedItem().toString()));
        type2 = 5;
       }
      } else {
       secondIngredient = new Helmet(Helmet.identifier(ingredient2.getSelectedItem().toString()));
       type2 = 4;
      }
     } else {
      secondIngredient = new Item(Item.identifier(ingredient2.getSelectedItem().toString()));
      type2 = 3;
     }
    } else {
     secondIngredient = new Armor(Armor.identifier(ingredient2.getSelectedItem().toString()));
     type2 = 2;
    }
   } else {
    secondIngredient = new Weapon(Weapon.identifier(ingredient2.getSelectedItem().toString()));
    type2 = 1;
   }
   int iterations1 = firstIngredient.crafts.length;
   int iterations2 = secondIngredient.crafts.length;
   blah:
   if (ingnumber==2){
    for (int i=0; i<iterations1; i++){
     for (int j=0; j<iterations2; j++){
      if (firstIngredient.crafts[i][0]==secondIngredient.crafts[j][0] && firstIngredient.crafts[i][1]==secondIngredient.crafts[j][1]){
       if (firstIngredient.crafts[i][0]==1){
        creation = new Weapon(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==2){
        creation = new Armor(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==3){
        creation = new Item(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==4){
        creation = new Helmet(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==5){
        creation = new Accessory(firstIngredient.crafts[i][1]);
       }
       if (creation.recipe.length!=2){
        continue;
       }
       if (type1==1){
        if (!Background.player.weapons.contains(firstIngredient)){
         missing = true;
         continue;
        }
       } else if (type1==2){
        if (!Background.player.armor.contains(firstIngredient)){
         missing = true;
         continue;
        }
       } else if (type1==3){
        if (!Background.player.items.contains(firstIngredient)){
         missing = true;
         continue;
        }
       } else if (type1==4){
        if (!Background.player.helmets.contains(firstIngredient)){
         missing = true;
         continue;
        }
       } else if (type1==5){
        if (!Background.player.accessories.contains(firstIngredient)){
         missing = true;
         continue;
        }
       }
       if (type2==1){
        if (!Background.player.weapons.contains(secondIngredient)){
         missing = true;
         continue;
        }
       } else if (type2==2){
        if (!Background.player.armor.contains(secondIngredient)){
         missing = true;
         continue;
        }
       } else if (type2==3){
        if (!Background.player.items.contains(secondIngredient)){
         missing = true;
         continue;
        }
       } else if (type2==4){
        if (!Background.player.helmets.contains(secondIngredient)){
         missing = true;
         continue;
        }
       } else if (type2==5){
        if (!Background.player.accessories.contains(secondIngredient)){
         missing = true;
         continue;
        }
       }
       ArrayList<Thingy> creationRecipe = new ArrayList<Thingy>();
       if (creation.recipe[0][0]==1){
        creationRecipe.add(new Weapon(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==2){
        creationRecipe.add(new Armor(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==3){
        creationRecipe.add(new Item(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==4){
        creationRecipe.add(new Helmet(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==5){
        creationRecipe.add(new Accessory(creation.recipe[0][1]));
       }
       if (creation.recipe[1][0]==1){
        creationRecipe.add(new Weapon(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==2){
        creationRecipe.add(new Armor(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==3){
        creationRecipe.add(new Item(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==4){
        creationRecipe.add(new Helmet(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==5){
        creationRecipe.add(new Accessory(creation.recipe[1][1]));
       }
       if (creationRecipe.contains(firstIngredient)){
        creationRecipe.remove(firstIngredient);
        if (creationRecipe.contains(secondIngredient)){
         creationRecipe.remove(secondIngredient);
        } else {
         continue;
        }
       } else {
        continue;
       }
       if (!Background.player.addToInventory(creation.type,creation.identity,creation.recipeAmount)){
        break blah;
       }
       try{
        if (creation.recipe[0][2]==1){
         if (creation.recipe[0][0]==1){
          Background.player.weapons.remove(new Weapon(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==2){
          Background.player.armor.remove(new Armor(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==3){
          Background.player.items.remove(new Item(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==4){
          Background.player.helmets.remove(new Helmet(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==5){
          Background.player.accessories.remove(new Accessory(creation.recipe[0][1]));
         }
        }
        if (creation.recipe[1][2]==1){
         if (creation.recipe[1][0]==1){
          Background.player.weapons.remove(new Weapon(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==2){
          Background.player.armor.remove(new Armor(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==3){
          Background.player.items.remove(new Item(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==4){
          Background.player.helmets.remove(new Helmet(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==5){
          Background.player.accessories.remove(new Accessory(creation.recipe[1][1]));
         }
        }
        Background.game.makeMessage("Success",/*"You made "+creation.recipeAmount+" "+creation.name+"."*/creation.recipeSuccess);
        correct=true;
        Background.game.inventoryGUI.craftGUI.setVisible(false);
        Background.game.update();
        return;
       } catch (Exception exception){
        exception.printStackTrace();
        try{
         if (creation.type==1){
          Background.player.weapons.remove(creation);
         } else if (creation.type==2){
          Background.player.armor.remove(creation);
         } else if (creation.type==3){
          Background.player.items.remove(creation);
         } else if (creation.type==4){
          Background.player.helmets.remove(creation);
         } else if (creation.type==5){
          Background.player.accessories.remove(creation);
         }
        } catch (Exception bleh){
         System.out.println("Hm, creation wasn't in your inventory to take out when there was an exception..");
        }
        Background.game.makeMessage("Woops","Appears one of the ingredients are no longer in your inventory.");
       }
      } else {
      }
     }
    }
   } else if (ingnumber==3){
    if (Weapon.identifier(ingredient3.getSelectedItem().toString())==-1){
     if (Armor.identifier(ingredient3.getSelectedItem().toString())==-1){
      if (Item.identifier(ingredient3.getSelectedItem().toString())==-1){
       if (Helmet.identifier(ingredient3.getSelectedItem().toString())==-1){
        if (Accessory.identifier(ingredient3.getSelectedItem().toString())==-1){
         System.out.println("third ingredient was unidentifiable, sorry");
         return;
        } else {
         thirdIngredient = new Accessory(Accessory.identifier(ingredient3.getSelectedItem().toString()));
         type3 = 5;
        }
       } else {
        thirdIngredient = new Helmet(Helmet.identifier(ingredient3.getSelectedItem().toString()));
        type3 = 4;
       }
      } else {
       thirdIngredient = new Item(Item.identifier(ingredient3.getSelectedItem().toString()));
       type3 = 3;
      }
     } else {
      thirdIngredient = new Armor(Armor.identifier(ingredient3.getSelectedItem().toString()));
      type3 = 2;
     }
    } else {
     thirdIngredient = new Weapon(Weapon.identifier(ingredient3.getSelectedItem().toString()));
     type3 = 1;
    }
    int iterations3 = thirdIngredient.crafts.length;
    for (int i=0; i<iterations1; i++){
     for (int j=0; j<iterations2; j++){
      for (int m=0; m<iterations3; m++){
      if (firstIngredient.crafts[i][0]==secondIngredient.crafts[j][0] && firstIngredient.crafts[i][1]==secondIngredient.crafts[j][1] 
          && firstIngredient.crafts[i][0]==thirdIngredient.crafts[m][0] && firstIngredient.crafts[i][1]==thirdIngredient.crafts[m][1]){
       if (firstIngredient.crafts[i][0]==1){
        creation = new Weapon(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==2){
        creation = new Armor(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==3){
        creation = new Item(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==4){
        creation = new Helmet(firstIngredient.crafts[i][1]);
       } else if (firstIngredient.crafts[i][0]==5){
        creation = new Accessory(firstIngredient.crafts[i][1]);
       }
       if (creation.recipe.length!=3){
        continue;
       }
       if (type1==1){
        if (!Background.player.weapons.contains(firstIngredient)){
         System.out.println("Missing first type 1");
         missing = true;
         continue;
        }
       } else if (type1==2){
        if (!Background.player.armor.contains(firstIngredient)){
         System.out.println("Missing first type 2");
         missing = true;
         continue;
        }
       } else if (type1==3){
        if (!Background.player.items.contains(firstIngredient)){
         System.out.println("Missing first type 3");
         missing = true;
         continue;
        }
       } else if (type1==4){
        if (!Background.player.helmets.contains(firstIngredient)){
         System.out.println("Missing first type 4");
         missing = true;
         continue;
        }
       } else if (type1==5){
        if (!Background.player.accessories.contains(firstIngredient)){
         System.out.println("Missing first type 5");
         missing = true;
         continue;
        }
       }
       if (type2==1){
        if (!Background.player.weapons.contains(secondIngredient)){
         System.out.println("Missing second type 1");
         missing = true;
         continue;
        }
       } else if (type2==2){
        if (!Background.player.armor.contains(secondIngredient)){
         System.out.println("Missing second type 2");
         missing = true;
         continue;
        }
       } else if (type2==3){
        if (!Background.player.items.contains(secondIngredient)){
         System.out.println("Missing second type 3");
         missing = true;
         continue;
        }
       } else if (type2==4){
        if (!Background.player.helmets.contains(secondIngredient)){
         System.out.println("Missing second type 4");
         missing = true;
         continue;
        }
       } else if (type2==5){
        if (!Background.player.accessories.contains(secondIngredient)){
         System.out.println("Missing second type 5");
         missing = true;
         continue;
        }
       }
       if (type3==1){
        if (!Background.player.weapons.contains(thirdIngredient)){
         System.out.println("Missing third type 1");
         missing = true;
         continue;
        }
       } else if (type3==2){
        if (!Background.player.armor.contains(thirdIngredient)){
         System.out.println("Missing third type 2");
         missing = true;
         continue;
        }
       } else if (type3==3){
        if (!Background.player.items.contains(thirdIngredient)){
         System.out.println("Missing third type 3");
         missing = true;
         continue;
        }
       } else if (type3==4){
        if (!Background.player.helmets.contains(thirdIngredient)){
         System.out.println("Missing third type 4");
         missing = true;
         continue;
        }
       } else if (type3==5){
        if (!Background.player.accessories.contains(thirdIngredient)){
         System.out.println("Missing third type 5");
         missing = true;
         continue;
        }
       }

       ArrayList<Thingy> creationRecipe = new ArrayList<Thingy>();
       if (creation.recipe[0][0]==1){
        creationRecipe.add(new Weapon(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==2){
        creationRecipe.add(new Armor(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==3){
        creationRecipe.add(new Item(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==4){
        creationRecipe.add(new Helmet(creation.recipe[0][1]));
       } else if (creation.recipe[0][0]==5){
        creationRecipe.add(new Accessory(creation.recipe[0][1]));
       }
       if (creation.recipe[1][0]==1){
        creationRecipe.add(new Weapon(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==2){
        creationRecipe.add(new Armor(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==3){
        creationRecipe.add(new Item(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==4){
        creationRecipe.add(new Helmet(creation.recipe[1][1]));
       } else if (creation.recipe[1][0]==5){
        creationRecipe.add(new Accessory(creation.recipe[1][1]));
       }
       if (creation.recipe[2][0]==1){
        creationRecipe.add(new Weapon(creation.recipe[2][1]));
       } else if (creation.recipe[2][0]==2){
        creationRecipe.add(new Armor(creation.recipe[2][1]));
       } else if (creation.recipe[2][0]==3){
        creationRecipe.add(new Item(creation.recipe[2][1]));
       } else if (creation.recipe[2][0]==4){
        creationRecipe.add(new Helmet(creation.recipe[2][1]));
       } else if (creation.recipe[2][0]==5){
        creationRecipe.add(new Accessory(creation.recipe[2][1]));
       }
       if (creationRecipe.contains(firstIngredient)){
        creationRecipe.remove(firstIngredient);
        if (creationRecipe.contains(secondIngredient)){
         creationRecipe.remove(secondIngredient);
         if (creationRecipe.contains(thirdIngredient)){
          creationRecipe.remove(thirdIngredient);
         } else {
          continue;
         }
        } else {
         continue;
        }
       } else {
        continue;
       }
       if (!Background.player.addToInventory(creation.type,creation.identity,creation.recipeAmount)){
        break blah;
       }
       try{
        if (creation.recipe[0][2]==1){
         if (creation.recipe[0][0]==1){
          Background.player.weapons.remove(new Weapon(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==2){
          Background.player.armor.remove(new Armor(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==3){
          Background.player.items.remove(new Item(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==4){
          Background.player.helmets.remove(new Helmet(creation.recipe[0][1]));
         } else if (creation.recipe[0][0]==5){
          Background.player.accessories.remove(new Accessory(creation.recipe[0][1]));
         }
        }
        if (creation.recipe[1][2]==1){
         if (creation.recipe[1][0]==1){
          Background.player.weapons.remove(new Weapon(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==2){
          Background.player.armor.remove(new Armor(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==3){
          Background.player.items.remove(new Item(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==4){
          Background.player.helmets.remove(new Helmet(creation.recipe[1][1]));
         } else if (creation.recipe[1][0]==5){
          Background.player.accessories.remove(new Accessory(creation.recipe[1][1]));
         }
        }
        if (creation.recipe[2][2]==1){
         if (creation.recipe[2][0]==1){
          Background.player.weapons.remove(new Weapon(creation.recipe[2][1]));
         } else if (creation.recipe[2][0]==2){
          Background.player.armor.remove(new Armor(creation.recipe[2][1]));
         } else if (creation.recipe[2][0]==3){
          Background.player.items.remove(new Item(creation.recipe[2][1]));
         } else if (creation.recipe[2][0]==4){
          Background.player.helmets.remove(new Helmet(creation.recipe[2][1]));
         } else if (creation.recipe[2][0]==5){
          Background.player.accessories.remove(new Accessory(creation.recipe[2][1]));
         }
        }
        Background.game.makeMessage("Success",creation.recipeSuccess);
        correct=true;
        Background.game.inventoryGUI.craftGUI.setVisible(false);
        Background.game.update();
        return;
       } catch (Exception exception){
        exception.printStackTrace();
        try{
         if (creation.type==1){
          Background.player.weapons.remove(creation);
         } else if (creation.type==2){
          Background.player.armor.remove(creation);
         } else if (creation.type==3){
          Background.player.items.remove(creation);
         } else if (creation.type==4){
          Background.player.helmets.remove(creation);
         } else if (creation.type==5){
          Background.player.accessories.remove(creation);
         }
        } catch (Exception bleh){
         System.out.println("Hm, creation wasn't in your inventory to take out when there was an exception..");
        }
        Background.game.makeMessage("Woops","Appears one of the ingredients are no longer in your inventory.");
       }
      } else {
      }

      }
     }
    }

   }
   if (missing){
    System.out.println("missing somehow");
    Background.game.makeMessage("Woops","One of your ingredients is no longer in your inventory");
    correct=true;
   }
   if (correct){
    Background.game.inventoryGUI.craftGUI.setVisible(false);
   } else {
    Background.game.makeMessage("Darn","Nothing was created.");
   }
   Background.game.update();
  }
 }

 private class SelectionHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==ingredient1){
    try{
     ingredient2.setSelectedIndex(0);
     ingredient3.setSelectedIndex(0);
    } catch (Exception exception){
    }
   } else if (source==ingredient2){
    try{
     ingredient3.setSelectedIndex(0);
    } catch (Exception exception){
    }
   } else if (source==ingredient3){

   }
   if (!updating){
    updateCrafting();
   }
  }
 }


}