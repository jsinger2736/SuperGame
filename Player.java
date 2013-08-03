import java.io.*;
import java.util.*;

public class Player{
 public String name = "PlayerName";
 public int level = 0;
 public int experience = 0;
 public int experiencetolevel = 0;
 public int maxhealth = 1;
 public int health = 1;
 public int maxmana = 0;
 public int mana = 0;
 public int location = 0;
 public int money = 0;
 public int normaldamage = 0;
 public int slashdamage = 0;
 public int stabdamage = 0;
 public int bashdamage = 0;
 public int normalarmor = 0;
 public int slasharmor = 0;
 public int stabarmor = 0;
 public int basharmor = 0;
 public int speed = 0;
 public double accuracy = 0;
 public double dodge = 0;
 LevelGUI levelGUI = new LevelGUI();
 StoreGUI storeGUI;
 ChestGUI chestGUI;
 boolean go = true;
 public Weapon equippedWeapon = new Weapon(0);
 public Armor equippedArmor = new Armor(0);
 public Helmet equippedHelmet = new Helmet(0);
 public Accessory equippedAccessory = new Accessory(0);
 public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
 public ArrayList<Armor> armor = new ArrayList<Armor>();
 public ArrayList<Item> items = new ArrayList<Item>();
 public ArrayList<Helmet> helmets = new ArrayList<Helmet>();
 public ArrayList<Accessory> accessories = new ArrayList<Accessory>(); 
 public int[] bestiary = new int[25];
 public Chest chest = new Chest();
 public int[] bleeding = {0,0}; // [amount, turn]
 public int weakened = 0; // turn
 public boolean stunned = false;
 public int[] quests = new int[25];
 public int[] locations = new int[50];

 //for... I don't know if I'll need it actually, so just in case.
 public Player(){
 }

 //for new character
 public Player(String gname){
  name = gname;
  level = 1;
  experience = 0;
  experiencetolevel = 35;
  maxhealth = 20;
  health = 20;
  location = 0;
  money = 0;
  normaldamage = 3;
  slashdamage = 3;
  stabdamage = 3;
  bashdamage = 3;
  normalarmor = 0;
  speed = 3;
  accuracy = .98;
  dodge = .05;
  //bestiary[0] = 1;
  chest = new Chest();//////////////////////
 }

 //for loading a character
 public Player(String lname, int llevel, int lexperience, int lexperiencetolevel, int lmaxhealth, int lhealth, int lmaxmana, int lmana, int llocation, int lmoney, int lnormaldamage, int lslashdamage, int lstabdamage, int lbashdamage, int lnormalarmor, int lslasharmor, int lstabarmor, int lbasharmor, int lspeed, double laccuracy, double ldodge, Weapon lequippedWeapon, Armor lequippedArmor, Helmet lequippedHelmet, Accessory lequippedAccessory, ArrayList<Weapon> lweapons, ArrayList<Armor> larmor, ArrayList<Helmet> lhelmets, ArrayList<Accessory> laccessories, ArrayList<Item> litems, int[] lbestiary, Chest lchest, int[] lquests, int[] llocations){
  name = lname;
  level = llevel;
  experience = lexperience;
  experiencetolevel = lexperiencetolevel;
  maxhealth = lmaxhealth;
  health = lhealth;
  maxmana = lmaxmana;
  mana = lmana;
  location = llocation;
  money = lmoney;
  normaldamage = lnormaldamage;
  slashdamage = lslashdamage;
  stabdamage = lstabdamage;
  bashdamage = lbashdamage;
  normalarmor = lnormalarmor;
  slasharmor = lslasharmor;
  stabarmor = lstabarmor;
  basharmor = lbasharmor;
  speed = lspeed;
  accuracy = laccuracy;
  dodge = ldodge;
  equippedWeapon = lequippedWeapon;
  equippedArmor = lequippedArmor;
  equippedHelmet = lequippedHelmet;
  equippedAccessory = lequippedAccessory;
  weapons = lweapons;
  armor = larmor;
  helmets = lhelmets;
  accessories = laccessories;
  items = litems;
  bestiary = lbestiary;
  chest = lchest;
  quests = lquests;
  locations = llocations;
 }

 public boolean inventoryContains(int type, int identity, int quantity){
  boolean output = true;
  if (type==1){
   ArrayList<Weapon> inventory = new ArrayList<Weapon>(weapons);
   for (int i=0; i<quantity; i++){
    if (inventory.contains(new Weapon(identity))){
     inventory.remove(new Weapon(identity));
    } else {
     output = false;
     break;
    }
   } 
  } else if (type==2){
   ArrayList<Armor> inventory = new ArrayList<Armor>(armor);
   for (int i=0; i<quantity; i++){
    if (inventory.contains(new Armor(identity))){
     inventory.remove(new Armor(identity));
    } else {
     output = false;
     break;
    }
   } 
  } else if (type==3){
   ArrayList<Item> inventory = new ArrayList<Item>(items);
   for (int i=0; i<quantity; i++){
    if (inventory.contains(new Item(identity))){
     inventory.remove(new Item(identity));
    } else {
     output = false;
     break;
    }
   } 
  } else if (type==4){
   ArrayList<Helmet> inventory = new ArrayList<Helmet>(helmets);
   for (int i=0; i<quantity; i++){
    if (inventory.contains(new Helmet(identity))){
     inventory.remove(new Helmet(identity));
    } else {
     output = false;
     break;
    }
   } 
  } else if (type==5){
   ArrayList<Accessory> inventory = new ArrayList<Accessory>(accessories);
   for (int i=0; i<quantity; i++){
    if (inventory.contains(new Accessory(identity))){
     inventory.remove(new Accessory(identity));
    } else {
     output = false;
     break;
    }
   } 
  } else {
   output = false;
  }
  return output;
 }

 public double chance(int type){
  if (type==2){
   return (equippedWeapon.bleedChance+equippedAccessory.bleedChance);
  } else if (type==3){
   return (equippedWeapon.weakenChance+equippedAccessory.weakenChance);
  } else if (type==4){
   return (equippedWeapon.stunChance+equippedAccessory.stunChance);
  }
  System.out.println("Musta been a normal attack somehow");
  return 0;
 }

 public double resist(int type){
  if (type==2){
   return equippedAccessory.bleedResist;
  } else if (type==3){
   return equippedAccessory.weakenResist;
  } else if (type==4){
   return equippedAccessory.stunResist;
  }
  System.out.println("Musta been a normal attack somehow");
  return 0;
 }

 public int damageby(int type){
  int damage;
  if (type==2){
   damage = (slashdamage+equippedWeapon.slashdamage);
  } else if (type==3){
   damage = (stabdamage+equippedWeapon.stabdamage);
  } else if (type==4){
   damage = (bashdamage+equippedWeapon.bashdamage);
  } else {
   damage = normaldamage+equippedWeapon.normaldamage;
  }
  if (damage<0){
   damage = 0;
  }
  return damage;
 }

 public int damageto(int amount, int type){
  double x;
  if (weakened>0){
   x = .75;
  } else {
   x = 1;
  }
  if (type==2){
   amount = amount - (int)((slasharmor+equippedArmor.slasharmor+equippedWeapon.slasharmor+equippedHelmet.slasharmor)*x);
  } else if (type==3){
   amount = amount - (int)((stabarmor+equippedArmor.stabarmor+equippedWeapon.stabarmor+equippedHelmet.stabarmor)*x);
  } else if (type==4){
   amount = amount - (int)((basharmor+equippedArmor.basharmor+equippedWeapon.basharmor+equippedHelmet.basharmor)*x);
  } else {
   amount = amount - (int)((normalarmor+equippedArmor.normalarmor+equippedWeapon.normalarmor+equippedHelmet.normalarmor)*x);
  }
  if (amount<=0){
   amount=1;
  }
  health = health-amount;
  if (health<0){
   health = 0;
  }
  Background.addText("You take "+amount+" damage.");
  return amount;
 }

 public void experience(int exp){
  Background.addText("You gained "+exp+" experience!");
  experience = experience + exp;
  Background.spacer();
  while (true){
   if (experience >= experiencetolevel){
    level++;
    experience = experience-experiencetolevel;
    experiencetolevel = (int)(experiencetolevel*1.3);
    Background.addText("You leveled up to level "+level+" and gained 2 health.");
    maxhealth = maxhealth+2;
    health = health+2;
    Background.spacer();
    /*Background.addText("You leveled up to level "+level+"!");
    Background.spacer();
    levelGUI.startup();
    go = true;
    while (true){
     Background.game.setButton(1,"Continue");
     Background.game.setButton(2,"Level Up");
     Background.wait(2);
     if (Background.choice==1){
      if (go){
       Background.addText("You must choose a stat to increase before you can continue.");
      } else {
       break;
      }
     } else {
      if (go){
       levelGUI.setVisible(true);
      } else {
       Background.addText("You've already selected a stat to increase.");
      }
     }
    }
    maxhealth = maxhealth+1;
    health = health +1;
    String string = "You also gained 1 health just for leveling!";
    Background.game.update();
    Background.addText(string);
    Background.spacer();*/
   } else {
    break;
   }
  }
 }

 public void chest(){
  go = true;
  //chestGUI = new ChestGUI();
  if (chestGUI==null){
   chestGUI = new ChestGUI();
  }
  chestGUI.setVisible(true);
  while (true){
   Background.game.setButton(1,"Continue");
   Background.game.setButton(2,"Chest");
   for (int i=3; i<13; i++){
    Background.game.setButton(i,"");
   }
   Background.wait(2);
   if (Background.choice==2){
    chestGUI.setVisible(true);
   } else {
    break;
   }
  }
  chestGUI.setVisible(false);
 }

 public void store(int x){
  go = true;
  storeGUI = new StoreGUI(x);
  while (true){
   Background.game.setButton(1,"Continue");
   Background.game.setButton(2,"Store");
   for (int i=3; i<13; i++){
    Background.game.setButton(i,"");
   }
   Background.wait(2);
   if (Background.choice==2){
    storeGUI.setVisible(true);
   } else {
    break;
   }
  }
  storeGUI.setVisible(false);
 }

 public void rest(){
  health=maxhealth;
  mana=maxmana;
  Background.addText("You're back to full health.");
  Background.spacer();
 }

 public boolean takeMoney(int x){
  if (x<=money){
   money = money-x;
   return true;
  } else {
   Background.game.makeMessage("Lack O' Money", "You don't have enough gold.");
   return false;
  }
 }

 public boolean addToInventory(int type, int identity, int quantity){
  if (identity==0){
   return false;
  }
  Thingy added = new Thingy();
  if (type==1){
   added = new Weapon(identity);
  } else if (type==2){
   added = new Armor(identity);
  } else if (type==3){
   added = new Item(identity);
  } else if (type==4){
   added = new Helmet(identity);
  } else if (type==5){
   added = new Accessory(identity);
  }
  boolean full = false;
  if (type==1){
   if (weapons.size()<6-quantity){
    //weapons.add(new Weapon(identity));
    //Background.game.update();
    //return true;
   } else {
    full = true;
   }
  } else if (type==2){
   if (armor.size()<6-quantity){
    //armor.add(new Armor(identity));
    //Background.game.update();
    //return true;
   } else {
    full = true;
   }
  } else if (type==3){
   if (items.size()<16-quantity){
    //items.add(new Item(identity));
    //Background.game.update();
    //return true;
   } else {
    full = true;
   }
  } else if (type==4){
   if (helmets.size()<6-quantity){
   } else {
    full = true;
   }
  } else if (type==5){
   if (accessories.size()<6-quantity){
   } else {
    full = true;
   }
  } else {
   System.out.println("Specified item type not found.");
   return false;
  }
  if (full){
   String string = "Your ";
   if (type==1){
    string = string+"weapon";
   } else if (type==2){
    string = string+"armor";
   } else if (type==3){
    string = string+"item";
   } else if (type==4){
    string = string+"helmet";
   } else if (type==5){
    string = string+"item";
   }
   string = string+" inventory is too full.";
   Background.game.makeMessage("Inventory Full",string);
   return false;
  } else {
   //for (int i=0; i<quantity; i++){
    if (type==1){
     if (weapons.size()<6-quantity){
      for (int j=0; j<quantity; j++){
       weapons.add(new Weapon(identity));
      }
     }
    } else if (type==2){
     if (armor.size()<6-quantity){
      for (int j=0; j<quantity; j++){
       armor.add(new Armor(identity));
      }
     }
    } else if (type==3){
     if (items.size()<16-quantity){
      for (int j=0; j<quantity; j++){
       items.add(new Item(identity));
      }
     }
    } else if (type==4){
     if (helmets.size()<6-quantity){
      for (int j=0; j<quantity; j++){
       helmets.add(new Helmet(identity));
      }
     }
    } else if (type==5){
     if (accessories.size()<6-quantity){
      for (int j=0; j<quantity; j++){
       accessories.add(new Accessory(identity));
      }
     }
    }
   //}
   return true;
  }
 }
 
 public void pickUp(int type, int identity){ //type 1 == weapon, type 2 == armor, type 3 == item
  Thingy added = new Thingy();
  if (type==1){
   added = new Weapon(identity);
  } else if (type==2){
   added = new Armor(identity);
  } else if (type==3){
   added = new Item(identity);
  } else if (type==4){
   added = new Helmet(identity);
  } else if (type==5){
   added = new Accessory(identity);
  }
  Background.addText("Would you like to put the "+added.name+" in your inventory?");
  if (!Background.yesno()){
   return;
  }
  while (true){
   boolean full = false;
   if (type==1){
    if (weapons.size()<5){
     weapons.add(new Weapon(identity));
     Background.game.update();
     break;
    } else {
     full = true;
    }
   } else if (type==2){
    if (armor.size()<5){
     armor.add(new Armor(identity));
     Background.game.update();
     break;
    } else {
     full = true;
    }
   } else if (type==3){
    if (items.size()<15){
     items.add(new Item(identity));
     Background.game.update();
     break;
    } else {
     full = true;
    }
   } else if (type==4){
    if (helmets.size()<5){
     helmets.add(new Helmet(identity));
     Background.game.update();
     break;
    } else {
     full = true;
    }
   } else if (type==5){
    if (accessories.size()<5){
     accessories.add(new Accessory(identity));
     Background.game.update();
     break;
    } else {
     full = true;
    }
   } else {
    System.out.println("Specified item type not found.");
   }
   if (full){
    Background.addText("That part of your inventory is full, drop something of that type. Would you like to try to pick it up now?");
    if (Background.yesno()){
     continue;
    } else {
     break;
    }
   }
  }
 }

 public void save(){
  String n = System.getProperty("line.separator");
  FileWriter fWriter = null;
  BufferedWriter writer = null;
  File savefile = new File("SaveFiles");
  if (!savefile.exists()){
   savefile.mkdir();
  }
  try{
   fWriter = new FileWriter("SaveFiles/"+name+".txt",false);
   writer = new BufferedWriter(fWriter);
   writer.write(name+n+level+n+experience+n+experiencetolevel+n+maxhealth+n+health+n+maxmana+n+mana+n+location+n+money+n+normaldamage+n+slashdamage+n+stabdamage+n+bashdamage+n+normalarmor+n+slasharmor+n+stabarmor+n+basharmor+n+speed+n+accuracy+n+dodge);
   writer.write(n+equippedWeapon.identity+n+equippedArmor.identity+n+equippedHelmet.identity+n+equippedAccessory.identity);
   writer.write(n+"W");
   for (int i=0; i<weapons.size(); i++){
    writer.write(n+weapons.get(i).identity);
   }
   writer.write(n+"A");
   for (int i=0; i<armor.size(); i++){
    writer.write(n+armor.get(i).identity);
   }
   writer.write(n+"I");
   for (int i=0; i<items.size(); i++){
    writer.write(n+items.get(i).identity);
   }
   writer.write(n+"H");
   for (int i=0; i<helmets.size(); i++){
    writer.write(n+helmets.get(i).identity);
   }
   writer.write(n+"A");
   for (int i=0; i<accessories.size(); i++){
    writer.write(n+accessories.get(i).identity);
   }
   writer.write(n+"B");
   for (int i=0; i<bestiary.length; i++){
    writer.write(n+bestiary[i]);
   }
   writer.write(n+"C");
   for (int i=0; i<chest.weapons.size(); i++){
    writer.write(n+chest.weapons.get(i).identity);
   }
   writer.write(n+"C");
   for (int i=0; i<chest.armor.size(); i++){
    writer.write(n+chest.armor.get(i).identity);
   }
   writer.write(n+"C");
   for (int i=0; i<chest.items.size(); i++){
    writer.write(n+chest.items.get(i).identity);
   }
   writer.write(n+"C");
   for (int i=0; i<chest.helmets.size(); i++){
    writer.write(n+chest.helmets.get(i).identity);
   }
   writer.write(n+"C");
   for (int i=0; i<chest.accessories.size(); i++){
    writer.write(n+chest.accessories.get(i).identity);
   }
   writer.write(n+"Q");
   for (int i=0; i<quests.length; i++){
    writer.write(n+quests[i]);
   }
   writer.write(n+"L");
   for (int i=0; i<locations.length; i++){
    writer.write(n+locations[i]);
   }
   writer.write(n+"D");
   writer.close();
  } catch (Exception e){
   System.out.println("Save failed.");
  }
 }
}