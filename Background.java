import javax.swing.*;
import java.util.*;
import java.io.*;


public class Background{
 static GameGUI game;
 static Player player = new Player();
 static int choice, validChoices, choicex;
 static boolean go = false;
 static int result;
 static Location location = new Location();
 //static GamePlay gamePlay;




 public static void main(String[] args){
  game = new GameGUI();
  //gamePlay = new GamePlay();
  //loop:
  while (true){
   //gamePlay.run();
   gamePlay();
  }
 }

 public static void gamePlay(){
  player = new Player("PlayerName");
  game.update();
  addText("Hello and welcome to the in-progress version of this game.");
  /*spacer();
  addText("Would you like to start a new game or load a saved game?");*/
  game.setButton(1,"New Game");
  game.setButton(2,"Load Game");
  String name = "";
  while (true){
   wait(2);
   File saveFiles = null;
   String[] loaded = null;
   try{
    saveFiles = new File("SaveFiles");
    loaded = saveFiles.list();
    for (int i=0; i<loaded.length; i++){
     loaded[i]=loaded[i].substring(0,loaded[i].length()-4);
     //System.out.println(loaded[i]);
    }
   } catch (Exception exception){
    exception.printStackTrace();
    System.out.println("error in creating the list of saved files");
   }
   if (choice==1){
    name = JOptionPane.showInputDialog(null,"Character name: ","New Game",1);
    try{
     if (Arrays.asList(loaded).contains(name)){
      name = "";
      game.makeMessage("File Already Exists","Another character already has that name.\nPlease choose a different name.");
     }
    } catch (Exception exception){
     exception.printStackTrace();
     System.out.println("Problem with checking if game already exists");
    }
   } else {
    /*File saveFiles = new File("SaveFiles");
    String[] loaded = saveFiles.list();
    for (int i=0; i<loaded.length; i++){
     loaded[i]=loaded[i].substring(0,loaded[i].length()-4);
     //System.out.println(loaded[i]);
    }*/
    String str = null;
    try{
     str = (String)JOptionPane.showInputDialog(null,"Choose a file to load:","Load Game",JOptionPane.PLAIN_MESSAGE,null,loaded,loaded[0]);
    } catch (Exception exception){
     game.makeMessage("Huh","There are no saved games available to load.");
    }
    if (str!=null){
     load(str);
     name = player.name;
    }
   }
   if (name!=null && !name.equals("")){
    break;
   }
  }
  if (choice==1){
   player = new Player(name);
   addText("Don't forget to save often!");
   spacer();
  }
  game.update();
  try{
   /*if (choice==1){
    player.addToInventory(1,6,1);
    player.addToInventory(2,6,1);
    player.addToInventory(4,1,1);
    player.addToInventory(5,1,1);
   }*/
  } catch (Exception exception){
     exception.printStackTrace();
    System.out.println("problem with adding to inventory at the beginning");
  }
  //addText("I still forgot locationInfo, but I guess i'll do that eventually. But today I... continued the quests, fixed a crafting error, and added 2 items that involve the Ram's Horn. Enjoy!");
  //spacer();
  //addText("");
  //spacer();

  if (player.location==-1 || player.location==0){
   player.location=2;
  }
  int newplace = player.location;
  while (newplace!=-1){
   newplace = transition(newplace);
   if (newplace==-1){
    addText("Game Over "+Background.player.name+".");
    spacer();
   }
  }
  
 }

 public static int transition(int newplace){
  //Tutorial               = 0
  //Arena                  = 1
  //VillagePlaza           = 2
  //ChieftainsHut          = 3
  //RandomHut              = 4
  //VillageRiver           = 5
  //VillageBridge          = 6
  //FarRiverbank           = 7
  //VillageTempleEntrance  = 8
  //VillageTemple          = 9
  //SouthForest            = 10
  //EastForest             = 11
  //CliffBase              = 12
  int nextplace = 0;
  if (newplace==0){
   nextplace = location.Tutorial();
  } else if (newplace==1){
   nextplace = location.Arena();
  } else if (newplace==2){
   nextplace = location.VillagePlaza();
  } else if (newplace==3){
   nextplace = location.ChieftainsHut();
  } else if (newplace==4){
   nextplace = location.RandomHut();
  } else if (newplace==5){
   nextplace = location.VillageRiver();
  } else if (newplace==6){
   nextplace = location.VillageBridge();
  } else if (newplace==7){
   nextplace = location.FarRiverbank();
  } else if (newplace==8){
   nextplace = location.VillageTempleEntrance();
  } else if (newplace==9){
   nextplace = location.VillageTempleEntrance();
  } else if (newplace==10){
   nextplace = location.SouthForest();
  } else if (newplace==11){
   nextplace = location.EastForest();
  } else if (newplace==12){
   nextplace = location.CliffBase();
  } else {
   System.out.println("Probably forgot to add a location...");
  }

  player.location = nextplace;
  game.update();
  return nextplace;
 }

 public static void addText(String addition){ 
  game.centerTA.append("\n\n   ");
  game.centerTA.append(addition+" ");
  game.centerTA.setCaretPosition(game.centerTA.getDocument().getLength());
 }

 public static void addQuote(String addition){
  addition = "\""+addition+"\"";
  addText(addition);
 }

 public static void wait(int x){
  choicex=choice;
  validChoices = x;
  //System.out.println("waiting...");
  while (true){
   try{
    Thread.sleep(50);
   } catch (InterruptedException exception){
    Thread.currentThread().interrupt();
   }
   //System.out.println("waiting");
   if (go){
    //System.out.println("go got turned on, switching go off");
    go = false;
    break;
   }
  }
  //System.out.println("and wait should be done!");
 }

 public static void spacer(){
  game.update();
  for (int i=2; i<13; i++){
   game.setButton(i, " ");
  }
  game.setButton(1, "Continue");
  wait(1);
  //System.out.println("done waiting");
  choice=choicex;
 }

 public static boolean yesno(){
  game.setButton(1,"Yes");
  game.setButton(2,"No");
  wait(2);
  if (choice==1){
   return true;
  } else {
   return false;
  }
 }

 public static void buttonClicked(int x){
  if (x<=validChoices){
   choice = x;
   buttonPress();
  }
 }
 
 public static void buttonPress(){
  //System.out.println("buttonPress");
  go = true;
  //System.out.println("go is now true");
 }

 public static int[] attack(int x){ //return   result[0] 1 = normal attack, 2 = slash attack, 3 = stab attack, 4 = bash attack, 5 = defend, 6 = flee, result[1] is the creature
  combat:
  while (true){
   int[] result = {0,0};
   addText("What would you like to do?");
   for (int i=2; i<13; i++){
    game.setButton(i, " ");
   }
   game.setButton(1,"Attack");
   game.setButton(2,"Defend");
   game.setButton(3,"Flee");
   wait(3);
   if (choice==1){
    addText("How would you like to attack?");
    game.setButton(1,"Normal Attack");
    game.setButton(2,"Slash Attack");
    game.setButton(3,"Stab Attack");
    game.setButton(4,"Bash Attack");
    game.setButton(5,"Back");
    wait(5);
    if (choice==1){
     result[0] = 1;
    } else if (choice==2){
     result[0] = 2;
    } else if (choice==3){
     result[0] = 3;
    } else if (choice==4){
     result[0] = 4;
    } else if (choice==5){
     continue;
    }
    if (x>1){
     addText("Who are you attacking?");
     for (int i=0; i<12; i++){
      game.setButton(i+1,"");
     }
     for (int i=0; i<x; i++){
      game.setButton(i+1,Combat.creature[i].name);
     }
     game.setButton(x+1,"Back");
     while (true){
      wait(x+1);
      if (choice==x+1){
       continue combat;
      }
      if (Combat.creature[choice-1].health==0){
       Background.addText("That creature is already dead.");
      } else {
       break;
      }
     }
     for (int i=0; i<x; i++){
      if (choice == i+1){
       result[1] = i;
      }
     }
    }
   } else if (choice==2){
    addText("You brace yourself.");
    spacer();
    result[0] = 5;
   } else if (choice==3){
    addText("You attempt to flee.");
    spacer();
    result[0] = 6;
   }
   return result;
  }
 }

 public static void load(String name){
  try{
   FileInputStream fstream = new FileInputStream("SaveFiles/"+name+".txt");
   DataInputStream in = new DataInputStream(fstream);
   BufferedReader br = new BufferedReader(new InputStreamReader(in));
   name = br.readLine();
   int level = Integer.parseInt(br.readLine());
   int experience = Integer.parseInt(br.readLine());
   int experiencetolevel = Integer.parseInt(br.readLine());
   int maxhealth = Integer.parseInt(br.readLine());
   int health = Integer.parseInt(br.readLine());
   int maxmana = Integer.parseInt(br.readLine());
   int mana = Integer.parseInt(br.readLine());
   int location = Integer.parseInt(br.readLine());
   int money = Integer.parseInt(br.readLine());
   int normaldamage = Integer.parseInt(br.readLine());
   int slashdamage = Integer.parseInt(br.readLine());
   int stabdamage = Integer.parseInt(br.readLine());
   int bashdamage = Integer.parseInt(br.readLine());
   int normalarmor = Integer.parseInt(br.readLine());
   int slasharmor = Integer.parseInt(br.readLine());
   int stabarmor = Integer.parseInt(br.readLine());
   int basharmor = Integer.parseInt(br.readLine());
   int speed = Integer.parseInt(br.readLine());
   double accuracy = Double.parseDouble(br.readLine());
   double dodge = Double.parseDouble(br.readLine());
   Weapon equippedWeapon = new Weapon(Integer.parseInt(br.readLine()));
   Armor equippedArmor = new Armor(Integer.parseInt(br.readLine()));
   Helmet equippedHelmet = new Helmet(Integer.parseInt(br.readLine()));
   Accessory equippedAccessory = new Accessory(Integer.parseInt(br.readLine()));
   char W = br.readLine().charAt(0);
   ArrayList<Weapon> weapons = new ArrayList<Weapon>();
   ArrayList<Armor> armor = new ArrayList<Armor>();
   ArrayList<Helmet> helmets = new ArrayList<Helmet>();
   ArrayList<Accessory> accessories = new ArrayList<Accessory>();
   ArrayList<Item> items = new ArrayList<Item>();
   while (true){
    br.mark(20);
    if ('A'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    weapons.add(new Weapon(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(20);
    if ('I'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    armor.add(new Armor(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(20);
    if ('H'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    items.add(new Item(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(20);
    if ('A'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    helmets.add(new Helmet(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(20);
    if ('B'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    accessories.add(new Accessory(Integer.parseInt(br.readLine())));
   }
   int[] bestiary = new int[player.bestiary.length];
   for (int i=0; i<player.bestiary.length; i++){
    bestiary[i] = Integer.parseInt(br.readLine());
   }
   char C = br.readLine().charAt(0);
   Chest chest = new Chest();
   while (true){
    br.mark(500);
    if ('C'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    chest.weapons.add(new Weapon(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(500);
    if ('C'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    chest.armor.add(new Armor(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(500);
    if ('C'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    chest.items.add(new Item(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(500);
    if ('C'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    chest.helmets.add(new Helmet(Integer.parseInt(br.readLine())));
   }
   while (true){
    br.mark(500);
    if ('Q'==br.readLine().charAt(0)){
     break;
    }
    br.reset();
    chest.accessories.add(new Accessory(Integer.parseInt(br.readLine())));
   }
   int[] quests = new int[player.quests.length];
   for (int i=0; i<player.quests.length; i++){
    quests[i] = Integer.parseInt(br.readLine());
   }
   char L = br.readLine().charAt(0);
   int[] locations = new int[player.locations.length];
   for (int i=0; i<player.locations.length; i++){
    locations[i] = Integer.parseInt(br.readLine());
   }
   char D = br.readLine().charAt(0);
   in.close();
   player = new Player(name, level, experience, experiencetolevel, maxhealth, health, maxmana, mana, location, money, normaldamage, slashdamage, stabdamage, bashdamage, normalarmor, slasharmor, stabarmor, basharmor, speed, accuracy, dodge, equippedWeapon, equippedArmor, equippedHelmet, equippedAccessory, weapons, armor, helmets, accessories, items, bestiary, chest, quests, locations);
  } catch(Exception e){
   e.printStackTrace();
   System.err.println("Error: "+e.getMessage());
   Background.game.makeMessage("Error","Save file was not found.\nThe save file as you typed it either does not exist or has been moved.");
   //System.exit(0);
  }
 }



}




