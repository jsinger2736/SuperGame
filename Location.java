


public class Location{
 int choice;
 int result;
 int identity;
 int stage = 1;
 double chance;


 public Location(){           //add Location.info based on the number input. Then make LocationInfoGUI and add to info menu
 }

 public int Tutorial(){//0
  Background.addText("Welcome to the tutorial "+Background.player.name+"!");
  Background.spacer();
  Background.addText("This is where you'll learn the basics of the game. Would you like to continue or skip through this?");
  Background.game.setButton(1,"Continue");
  Background.game.setButton(2,"Skip");
  Background.wait(2);
  if (Background.choice==2){
   Background.addText("Ok then, good luck with your adventure.");
   Background.spacer();
   return 1;
  }
  Background.addText("First we'll start with the menus at the top of the window.");
  Background.spacer();
  Background.addText("Under file you will find \"Save Game\" and \"Exit\". You can save or exit at any time during the game.");
  Background.spacer();
  Background.addText("Next is the info tab, under which you will find \"Game Info\", \"Bestiary\" and \"Item Info\".");
  Background.spacer();
  Background.addText("\"Game Info\" gives information about the game itself, \"Bestiary\" will give information about any of the creatures you have encountered, and \"Item Info\" gives information about the items either equipped to you or in your inventory.");
  Background.spacer();
  Background.addText("The last menu option is \"Hint\" under the Extra tab. That contains a small hint about what you should do next.");
  Background.spacer();
  Background.addText("Above this text is your current location. Right now you're in the tutorial, so it says Tutorial.");
  Background.spacer();
  Background.addText("To the right of this text screen is your character name, your level, your current and max health, and your current and max mana.");
  Background.spacer();
  Background.addText("To the left is the same information, but for whoever you happen to be in combat with. Currently you are not in combat, so it merely says \"Enemy\"");
  Background.spacer();
  Background.addText("There are two buttons underneath this text screen, which are Stats and Inventory.");
  Background.spacer();
  Background.addText("Clicking Stats creates a window that holds information about your character. You can move that window to the side and keep it open during your adventure.");
  Background.spacer();
  Background.addText("Clicking Inventory will bring up another window that contains your inventory, which is where items you find or buy go. From there you can equip weapons or armor, and use other items. In order to unequip a weapon or armor, equip \"none\".");
  Background.spacer();
  Background.addText("Now let's get the basics of combat down. During combat you'll have the options to attack, defend, or flee.");
  Background.spacer();
  Background.addText("If you choose to defend, whatever damage done to you that turn is halved.");
  Background.spacer();
  Background.addText("If you choose to flee, you will attempt to flee, but there is a chance that your enemy may prevent you from escaping. Having a higher speed than your enemy will increase your chances of successfully escaping.");
  Background.spacer();
  Background.addText("If you choose to attack, you will then be given five new options. You will be given four types of attack, and a chance to go back to be able to defend or flee.");
  Background.spacer();
  Background.addText("The four types of attack are normal, slash, stab, and bash. Some weapons do more damage by one type than another, and some weapons actually decrease the amount of damage you do in a certain type. Different armors also may resist more damage from some types of attacks than others.");
  Background.spacer();
  Background.addText("Be careful in how you choose to attack, for some enemies have strengths and weaknesses, which you will learn.");
  Background.spacer();
  Background.addText("Some creatures are harder to hit than others, and some creatures are more or less accurate than others, so keep that in mind as well.");
  Background.spacer();
  Background.addText("Also, whoever has higher speed acts first during combat.");
  Background.spacer();
  Background.addText("Let's give you a quick test-run in combat. Try to figure out what type of attack the creature is weak to.");
  Background.spacer();
  while (result!=1){
   result = encounter(false,new int[][]{{0,1}},1);
   Background.player.rest();
  }
  Background.addText("Good job! Looks like the Tutorial Creature dropped a weapon. Try equipping it in your inventory and see how much damage you do once it's equipped. Remember, to unequip it you just equip \"none\".");
  Background.spacer();
  Background.addText("Next you should learn how the store works. In the store you will be able to buy items that the store has, and sell items that are in your inventory. The price of each item will be on the buy or sell button next to the item.");
  Background.spacer();
  Background.addText("Here's 50 gold and another Sharp Stick to test out the store.");
  Background.player.money = Background.player.money + 50;
  Background.player.pickUp(1,1);
  Background.game.update();
  Background.spacer();
  Background.player.store(0);
  Background.addText("For now, this is the end of the tutorial. Good luck on your adventure!");
  Background.spacer();
  Background.player.money = 0;
  Background.player.weapons.clear();
  Background.player.armor.clear();
  Background.player.items.clear();
  Background.player.equippedWeapon = new Weapon(0);
  Background.player.equippedArmor = new Armor(0);
  Background.player.pickUp(1,1);
  return 1;
 }

 public int Arena(){ //1
  if (Background.player.locations[1]==0){
   Background.addText("Welcome to the Arena, where you'll progress through various stages, with increasingly difficult opponents. Defeat them for the opportunity to move on. Enjoy yourself.");
   Background.spacer();
   Background.player.locations[1]=1;
  } else {
   Background.addText("Welcome to the "+Location.identifier(1)+". There are currently eight stages.");
   Background.spacer();
  }
  while (true){
   result = encounter(false,new int[][]{{1,1},{1,1}},0);
   if (result==3){
    return -1;
   }
   if (stage==1){
    result = encounter(false,new int[][]{{3,1}},1);
   } else if (stage==2){
    result = encounter(false,new int[][]{{2,1},{3,1}},1);
   } else if (stage==3){
    result = encounter(false,new int[][]{{4,1}},1);
   } else if (stage==4){
    result = encounter(false,new int[][]{{5,1}},1);
   } else if (stage==5){
    result = encounter(false,new int[][]{{5,1},{2,1}},1);
   } else if (stage==6){
    result = encounter(false,new int[][]{{6,1},{3,1}},1);
   } else if (stage==7){
    result = encounter(false,new int[][]{{6,1},{4,1}},1);
   } else if (stage==8){
    result = encounter(false,new int[][]{{6,1},{5,1},{2,1}},1);
    if (result==3){
     return -1;
    } else if (result==1){
     Background.addText("Good job defeating the eighth stage! For now that is that final stage, so congratulations.");
    }
   }
   if (result==3){
    return -1;
   }
   safearea(true,true,true,1);
   if (stage<9){
    /*if (result==1){
     Background.addText("Would you like to move on to the next stage?");
     if (Background.yesno()){
      stage++;
     } else {
      if (stage>1){
       Background.addText("Would you like to go back a stage?");
       if (Background.yesno()){
        stage--;
       }
      }
     }
    }*/
   //} else {
    Background.addText("What stage would you like to go to? Or you can exit back to the village.");
    Background.game.setButton(1,"stage 1");
    Background.game.setButton(2,"stage 2");
    Background.game.setButton(3,"stage 3");
    Background.game.setButton(4,"stage 4");
    Background.game.setButton(5,"stage 5");
    Background.game.setButton(6,"stage 6");
    Background.game.setButton(7,"stage 7");
    Background.game.setButton(8,"stage 8");
    Background.game.setButton(9,"Go to Village");
    Background.wait(9);
    if (Background.choice==1){
     stage = 1;
    } else if (Background.choice==2){
     stage = 2;
    } else if (Background.choice==3){
     stage = 3;
    } else if (Background.choice==4){
     stage = 4;
    } else if (Background.choice==5){
     stage = 5;
    } else if (Background.choice==6){
     stage = 6;
    } else if (Background.choice==7){
     stage = 7;
    } else if (Background.choice==8){
     stage = 8;
    } else if (Background.choice==9){
     return 2;
    }
   }
  }
 }

 public int VillagePlaza(){ //2
  if (Background.player.locations[2]==0){
   Background.addText("You're in the central plaza of a small village. Scattered around you are semi-primitive huts, and further than those are the trees of a forest surrounding the village.");
   Background.player.locations[2]=1;
  } else {
   Background.addText("You're in the "+Location.identifier(2)+".");
  }
  Background.spacer();
  while (true){
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Talk to "+new Npc(1).name);
   Background.game.setButton(2,"Enter "+Location.identifier(3));
   Background.game.setButton(3,"Enter "+Location.identifier(4));
   Background.game.setButton(4,"Go to "+Location.identifier(8));
   Background.game.setButton(5,"Go to "+Location.identifier(5));
   Background.game.setButton(6,"Go to "+Location.identifier(10));
   Background.game.setButton(7,"Go to "+Location.identifier(11));
   Background.game.setButton(8,"Go to "+Location.identifier(1));
   Background.wait(8);
   if (Background.choice==1){
    Background.addText("You approach "+new Npc(1).name+".");
    Background.spacer();
    new Npc(1).interact();
   } else if (Background.choice==2){
    return 3;
   } else if (Background.choice==3){
    return 4;
   } else if (Background.choice==4){
    return 8;
   } else if (Background.choice==5){
    return 5;
   } else if (Background.choice==6){
    return 10;
   } else if (Background.choice==7){
    return 11;
   } else if (Background.choice==8){
    return 1;
   }
  }
 }

 public int ChieftainsHut(){ //3
  if (Background.player.locations[3]==0){
   Background.addText("The inside of the "+Location.identifier(3)+" is a single large open room with a firepit in the center.");
   Background.player.locations[3]=1;
  } else {
   Background.addText("You're in the "+Location.identifier(3)+".");
  }
  Background.spacer();
  while (true){
   if (Background.player.quests[2]!=3){
    Background.addText("What would you like to do?");
    Background.game.setButton(1,"Talk to "+new Npc(2).name);
    Background.game.setButton(2,"Talk to "+new Npc(4).name);
    Background.game.setButton(3,"Exit "+Location.identifier(3));
    Background.wait(3);
    if (Background.choice==1){
     Background.addText("You approach "+new Npc(2).name+".");
     Background.spacer();
     new Npc(2).interact();
    } else if (Background.choice==2){
     Background.addText("You approach "+new Npc(4).name+".");
     Background.spacer();
     new Npc(4).interact();
    } else if (Background.choice==3){
     return 2;
    }
   } else {
    Background.addText("What would you like to do?");
    Background.game.setButton(1,"Talk to "+new Npc(4).name);
    Background.game.setButton(2,"Exit "+Location.identifier(3));
    Background.wait(2);
    if (Background.choice==1){
     Background.addText("You approach "+new Npc(4).name+".");
     Background.spacer();
     new Npc(4).interact();
    } else if (Background.choice==2){
     return 2;
    }
   }    
  }
 }

 public int RandomHut(){ //4
  if (Background.player.locations[4]==0){
   Background.addText("This is a place to rest and to access your chest.");
   Background.player.locations[4]=1;
  } else {
   Background.addText("You're in the "+Location.identifier(4)+".");
  }
  Background.spacer();
  while (true){
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Rest");
   Background.game.setButton(2,"Access Chest");
   Background.game.setButton(3,"Exit "+Location.identifier(4));
   Background.wait(3);
   if (Background.choice==1){
    Background.player.rest();
   } else if (Background.choice==2){
    Background.player.chest();
   } else if (Background.choice==3){
    return 2;
   }
  }
 }

 public int VillageRiver(){ //5
  if (Background.player.locations[5]==0){
   Background.addText("This has ducks. That's about it for now.");
   Background.player.locations[5]=1;
  } else {
   Background.addText("You're at the "+Location.identifier(5)+".");
  }
  Background.spacer();
  while (true){
   if (Math.random()<.5){
    result = encounter(false,new int[][]{{3,1}},.8);
   } else {
    result = encounter(false,new int[][]{{3,1},{3,1}},.7);
   }
   if (result==3){
    return -1;
   }
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Wander around here");
   Background.game.setButton(2,"Go to "+Location.identifier(2));
   Background.game.setButton(3,"Go to "+Location.identifier(6));
   Background.wait(3);
   if (Background.choice==1){
    Background.addText("You wander around the "+Location.identifier(5));
    Background.spacer();
   } else if (Background.choice==2){
    return 2;
   } else if (Background.choice==3){
    return 6;
   }
  }
 }

 public int VillageBridge(){ //6
  if (Background.player.locations[6]==0){
   Background.addText("You're standing on a bridge made out of wooden planks. It crosses the river that the village is adjacent to.");
   Background.player.locations[6]=1;
  } else {
   Background.addText("You're on the "+Location.identifier(6)+".");
  }
  Background.spacer();
  if (Background.player.quests[0]<1){
   Background.addText("Abul approaches you.");
   new Npc(3).interact();
  }
  while (true){
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Talk to "+new Npc(3).name);
   Background.game.setButton(2,"Go to "+Location.identifier(5));
   Background.game.setButton(3,"Go to "+Location.identifier(7));
   Background.wait(3);
   if (Background.choice==1){
    Background.addText("You approach "+new Npc(3).name+".");
    Background.spacer();
    new Npc(3).interact();
   } else if (Background.choice==2){
    return 5;
   } else if (Background.choice==3){
    return 7;
   }
  }
 }

 public int FarRiverbank(){ //7
  if (Background.player.quests[3]==0){
   Background.player.quests[3]=1;
  }
  if (Background.player.locations[7]==0){
   Background.addText("You're across the river from the village. The trees of the forest come close to the water.");
   Background.player.locations[7]=1;
  } else {
   Background.addText("You're at the "+Location.identifier(7)+".");
  }
  Background.spacer();
  while (true){
   result = encounter(false,new int[][]{{6,1}},.4);
   if (result==0){
    result = encounter(false,new int[][]{{3,1}},.8);
   }
   if (result==3){
    return -1;
   }
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Wander around here");
   Background.game.setButton(2,"Go to "+Location.identifier(6));
   Background.wait(2);
   if (Background.choice==1){
    Background.addText("You wander around the "+Location.identifier(7));
    Background.spacer();
   } else if (Background.choice==2){
    return 6;
   }
  }
 }

 public int VillageTempleEntrance(){ //8
  if (Background.player.locations[8]==0){
   Background.addText("The doors to the stone temple are carved with the fantastic images of many different creatures. Some look harmless, while others are depicted viciously.");
   Background.player.locations[8]=1;
  } else {
   Background.addText("You're at the "+Location.identifier(8)+".");
  }
  Background.spacer();
  if (true){
   Background.addText("There are guards barring entry to the temple.");
   Background.spacer();
  }
  while (true){
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Attempt to enter");
   Background.game.setButton(2,"Wait around");
   Background.game.setButton(3,"Go to "+Location.identifier(2));
   Background.wait(3);
   if (Background.choice==1){
    result = encounter(false,new int[][]{{7,1},{7,1}},1);
    if (result==3){
     return -1;
    } else if (result==1){
     return 9;
    }
   } else if (Background.choice==2){
    Background.addText("You just stand there.");
    Background.spacer();
   } else if (Background.choice==3){
    return 2;
   }
  }
 }

 public int VillageTemple(){ //9
  if (Background.player.locations[9]==0){
   Background.addText("The inside of the temple is lit by many torches burning in sconches on the walls. The room you're in is enormous in size.");
   Background.player.locations[9]=1;
  } else {
   Background.addText("You're in the "+Location.identifier(9)+".");
  }
  Background.spacer();
  result = encounter(false,new int[][]{{7,1},{7,1},{7,1},{7,1}},.6);
  if (result==3){
   return -1;
  }
  while (true){
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Wait around");
   Background.game.setButton(2,"Exit to "+Location.identifier(8));
   Background.wait(2);
   if (Background.choice==1){
    Background.addText("You just stand there.");
    Background.spacer();
   } else if (Background.choice==2){
    return 8;
   }
  }
 }

 public int SouthForest(){ //10
  if (Background.player.locations[10]==0){
   Background.addText("The forest south of the village is composed of tall, thick trees with large spanses of little undergrowth between them.");
   Background.player.locations[10]=1;
  } else {
   Background.addText("You're in the "+Location.identifier(10)+".");
  }
  Background.spacer();
  while (true){
   if (Background.player.quests[2]==3){
    Background.addText("You see two boar scratching at the bottom of a tree.");
    Background.spacer();
    Background.addQuote("Hey "+Background.player.name+"! I tried to pet them, but they didn't like it much so I climbed up here. Would you mind helping me out?");
    if (Background.yesno()){
     result = encounter(false,new int[][]{{5,1},{5,0}},1);
     if (result==3){
      return -1;
     }
     new Npc(2).interact();
    } else {
     Background.addQuote("Well, I hope you come back soon, I'm getting kind of hungry.");
     Background.spacer();
    }
   } else if (Background.player.quests[2]==5 && !Background.player.inventoryContains(5,5,1)){
    Background.addText("There's a Wooden Doll on the ground, but there's a boar who seems to be guarding it. Try to get to the doll?");
    if (Background.yesno()){
     result = encounter(false,new int[][]{{5,3}},1);
     if (result==3){
      return -1;
     } else if (result==1){
      Background.player.pickUp(5,5);
     }
    } else {
     Background.addText("The boar watches you, but you keep your distance from it.");
     Background.spacer();
    }
   } else {
    chance = Math.random();
    if (chance<.4){
     Background.addText("You find a Thick Stick laying on the ground.");
     Background.spacer();
     Background.player.pickUp(1,1);
    } else if (chance<.6){
     Background.addText("You find a Long Stick laying on the ground.");
     Background.spacer();
     Background.player.pickUp(1,4);
    }
    result = encounter(false, new int[][]{{5,1}},.2);
    if (result==0){
     result = encounter(false, new int[][]{{2,1}},.5);
    }
    if (result==3){
     return -1;
    }
   }
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Wander around here");
   Background.game.setButton(2,"Go to "+Location.identifier(2));
   Background.game.setButton(3,"Go to "+Location.identifier(11));
   Background.wait(3);
   if (Background.choice==1){
    Background.addText("You wander around the "+Location.identifier(10)+".");
    Background.spacer();
   } else if (Background.choice==2){
    return 2;
   } else if (Background.choice==3){
    return 11;
   }
  }
 }

 public int EastForest(){ //11
  if (Background.player.locations[11]==0){
   Background.addText("The trees of the "+Location.identifier(11)+" are spaced widely apart, and through it you can make out the rock of a cliff face further east.");
   Background.player.locations[11]=1;
  } else {
   Background.addText("You're in the "+Location.identifier(11)+".");
  }
  Background.spacer();
  while (true){
   chance = Math.random();
   if (chance<.2){
    Background.addText("There's a Thick Stick on the ground near you.");
    Background.spacer();
    Background.player.pickUp(1,1);
   } else if (chance<.6){
    Background.addText("There's a Long Stick on the ground near you.");
    Background.spacer();
    Background.player.pickUp(1,4);
   }
   result = encounter(false, new int[][]{{4,1}},.15);
   if (result==0){
    result = encounter(false, new int[][]{{2,1}},.5);
   }
   if (result==3){
    return -1;
   }
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Wander around here");
   Background.game.setButton(2,"Go to "+Location.identifier(2));
   Background.game.setButton(3,"Go to "+Location.identifier(10));
   Background.game.setButton(4,"Go to "+Location.identifier(12));
   Background.wait(4);
   if (Background.choice==1){
    Background.addText("You wander around a bit.");
    Background.spacer();
   } else if (Background.choice==2){
    return 2;
   } else if (Background.choice==3){
    return 10;
   } else if (Background.choice==4){
    return 12;
   }
  }
 }

 public int CliffBase(){ //12
  if (Background.player.locations[12]==0){
   Background.addText("The stone cliff is at an incredibly steep angle, with many loose rocks fallen or ready to do so.");
   Background.player.locations[12]=1;
  } else {
   Background.addText("You're at the "+Location.identifier(12)+".");
  }
  Background.spacer();
  while (true){
   result = encounter(false,new int[][]{{4,1}},.5);
   if (result==0){
    result = encounter(false,new int[][]{{4,1},{4,1}},.5);
   }
   if (result==3){
    return -1;
   }
   Background.addText("What would you like to do?");
   Background.game.setButton(1,"Walk along the cliff");
   Background.game.setButton(2,"Pick up a rock");
   Background.game.setButton(3,"Attempt to climb");
   Background.game.setButton(4,"Go to "+Location.identifier(11));
   Background.wait(4);
   if (Background.choice==1){
    Background.addText("You walk along the cliff.");
    Background.spacer();
   } else if (Background.choice==2){
    Background.addText("Which type of rock?");
    Background.game.setButton(1,new Item(1).name);
    Background.game.setButton(2,new Item(3).name);
    Background.game.setButton(3,new Item(14).name);
    Background.game.setButton(4,"Nevermind");
    for (int i=5; i<13; i++){
     Background.game.setButton(i,"");
    }
    Background.wait(4);
    if (Background.choice==1){
     if (Background.player.addToInventory(3,1,1)){
      Background.addText("You picked up the "+new Item(1).name+".");
      Background.spacer();
     }
    } else if (Background.choice==2){
     if (Background.player.addToInventory(3,3,1)){
      Background.addText("You picked up the "+new Item(3).name+".");
      Background.spacer();
     }
    } else if (Background.choice==3){
     if (Background.player.addToInventory(3,14,1)){
      Background.addText("You picked up the "+new Item(14).name+".");
      Background.spacer();
     }
    }
   } else if (Background.choice==3){
    Background.addText("You try to climb the cliff, but don't get far before your handholds fall away. You fall and land hard on the rocks below, taking a bit of damage.");
    if (Math.random()<.5){
     Background.player.health = Background.player.health-1;
    } else {
     Background.player.health = Background.player.health-2;
    }
    Background.spacer();
   } else if (Background.choice==4){
    return 11;
   }
  }
 }

 public int encounter(boolean boss, int[][] creature, double chance){  //creature = {(type, level), (type, level),...}
  //returning 0=no encounter, 1=won, 2=fled, 3=died
  double randomencounter = Math.random();
  if (randomencounter<=chance){
   int combatresult = Combat.combat(boss,creature);
   if (combatresult==2){
    return 2;
   } else if (combatresult==1){
    return 1;
   } else if (combatresult==3){
    return 3;
   } else {
    return 0;
   }
  } else {
   return 0;
  }
 } //end of encounter

 public void safearea(boolean rest, boolean chest, boolean store, int storenum){
  if (rest){
   Background.addText("Would you like to rest?");
   if (Background.yesno()){
    Background.player.rest();
   }
  }
  if (chest){
   Background.addText("Would you like to open your chest?");
   if (Background.yesno()){
    Background.player.chest();
   }
  }
  if (store){
   Background.addText("Would you like to visit the store?");
   if (Background.yesno()){
    Background.player.store(storenum);
   }
  }

 }

 public static String identifier(int x){ //lol, it's backwards... oh well, should be fine.
  if (x==-1){
   return "Afterlife";
  } else if (x==0){
   return "Tutorial";
  } else if (x==1){
   return "Arena";
  } else if (x==2){
   return "Village Square";
  } else if (x==3){
   return "Chieftain's Hut";
  } else if (x==4){
   return "Guest Hut";
  } else if (x==5){
   return "Village River";
  } else if (x==6){
   return "Village Bridge";
  } else if (x==7){
   return "Far Riverbank";
  } else if (x==8){
   return "Temple Entrance";
  } else if (x==9){
   return "Village Temple";
  } else if (x==10){
   return "South Forest";
  } else if (x==11){
   return "East Forest";
  } else if (x==12){
   return "Cliff Base";
  } else {
   System.out.println("Location not identified");
   return "Location";
  }
 }

}