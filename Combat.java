


public class Combat{
 static Creature[] creature;
 static String string;
 static int x = 0, j = 0;

 //add bleeding from slash attacks, weakening from stab attacks, and stunning from bash attacks (possibilities, are determined by the weapon)

 public static int combat(boolean boss, int[][] creatureinfo){//return 1 won, 2 fled, 3 died        input {(type, level), (type, level)...}
  if (!boss){
   creature = new Creature[creatureinfo.length];
   for (int i=0; i<creatureinfo.length; i++){
    creature[i] = new Creature(creatureinfo[i][0], creatureinfo[i][1]);
   }
   x = creatureinfo.length;
   string = "A ";
   for (int i=0; i<x; i++){
    if (i>0){
     string = string + "and a ";
    }
    string = string +/* "level "+creature[i].level+" "+*/creature[i].name+" ";
   }
   string = string + "appears!";
   Background.addText(string);
   boolean aggressive = false;
   for (int i=0; i<x; i++){
    if (creature[i].aggressive){
     aggressive=true;
    }
   }
   int playerspeed = Background.player.speed;
   int creaturespeed;
   creaturespeed = 0;
   for (int i=0; i<x; i++){
    if (creature[i].health!=0){
     creaturespeed = creaturespeed + creature[i].speed;
    }
   }
   creaturespeed = creaturespeed/x;
   int[] choice;
   boolean defend, flee;
   Background.game.update();
   Background.spacer();
   if (!aggressive){
    Background.addText("You are not being attacked. Would you like to enter combat?");
    if (!Background.yesno()){
     string = "You don't disturb the creature";
     if (x>1){
      string = string+"s.";
     } else {
      string = string+".";
     }
     Background.addText(string);
     Background.spacer();
     x=0;
     Background.game.update();
     return 2;
    }
   }
   while (true){
    creaturespeed = 0;
    int ghdf = 0;
    for (int i=0; i<x; i++){
     if (creature[i].health!=0){
      creaturespeed = creaturespeed + creature[i].speed;
      ghdf++;
     }
    }
    creaturespeed = creaturespeed/ghdf;
    defend = false;
    flee = false;
    choice = new int[]{0,10};
    choice = Background.attack(x);
    if (choice[0]==5){
     defend = true;
     Background.player.stunned = false;
    }
    if (choice[0]==6){
     flee = true;
    }
    //start combat ish
    if (playerspeed>=creaturespeed){                      //player first
     if (choice[0]==1 || choice[0]==2 || choice[0]==3 || choice[0]==4){
      playerdoesdamage(choice[1],choice[0],false);
      Background.game.update();
     } else if (choice[0]==6){
      if (playerflee()){
       Background.game.update();
       x=0;
       refresh();
       return 2;
      }
     }
     checkcreature(choice[1]);
     j = 0;
     for (int i=0; i<x; i++){
      if (creature[i].health!=0){
       creaturedoesdamage(i, creature[i].damagetype(), defend);
       Background.game.update();
      } else {
       j++;
       Background.game.update();
      }
      if (!checkplayer()){
       x=0;
       refresh();
       return 3;
      }
     }
     if (j==x){
      break;
     }
    } else if (creaturespeed>playerspeed){                //creature first
     for (int i=0; i<x; i++){
      if (creature[i].health!=0){
       creaturedoesdamage(i, creature[i].damagetype(), defend);
      }
      Background.game.update();
      if (!checkplayer()){
       x=0;
       refresh();
       return 3;
      }
     }
     if (choice[0]==1 || choice[0]==2 || choice[0]==3 || choice[0]==4){
      playerdoesdamage(choice[1],choice[0],false);
      Background.game.update();
     } else if (choice[0]==6){
      if (playerflee()){
       x=0;
       refresh();
       return 2;
      }
     }
     checkcreature(choice[1]);
     j=0;
     for (int i=0; i<x; i++){
      if (creature[i].health==0){
       j++;
       Background.game.update();
      }
     }
     if (j==x){
      break;
     }
    }
   }
   int experience = 0;
   for (int i=0; i<x; i++){
    experience = experience+creature[i].experience;
   }
   Background.player.experience(experience);
   for (int i=0; i<x; i++){
    int[] drop = creature[i].drop(Math.random());
    if (drop[0]!=0 && drop[1]!=0){
     if (drop[0]==1){
      Weapon weapon = new Weapon(drop[1]);
      Background.addText("The "+creature[i].name+" dropped "+weapon.name+".");
     } else if (drop[0]==2){
      Armor armor = new Armor(drop[1]);
      Background.addText("The "+creature[i].name+" dropped "+armor.name+".");
     } else if (drop[0]==3){
      Item item = new Item(drop[1]);
      Background.addText("The "+creature[i].name+" dropped "+item.name+".");
     } else if (drop[0]==4){
      Background.addText("The "+creature[i].name+" dropped "+drop[1]+" gold.");
     }
     Background.spacer();
     if (drop[0]!=4){
      Background.player.pickUp(drop[0],drop[1]);
     } else {
      Background.player.money = Background.player.money+drop[1];
     }
    }
    Background.player.bestiary[creature[i].identity]++;
   }
   x=0;
   Background.game.update();
   refresh();
   return 1;
  } else if (boss){
   Background.addText("This is a boss combat. It's not done yet. Actually, it's not really even started yet.");
  }
  x=0;
  refresh();
  return 1;
 }

 static void creaturedoesdamage(int creaturenum, int type, boolean defend){
  if (!creature[creaturenum].stunned){
   String string = "The "+creature[creaturenum].name+" attacks";
   if (type==1){
    string = string+" you.";
   } else if (type==2){
    string = string+" with a slashing motion.";
   } else if (type==3){
    string = string+" by stabbing at you.";
   } else if (type==4){
    string = string+" by attempting to bash you.";
   }
   Background.addText(string);
   Background.spacer();
   double chance = Math.random();
   if (chance<=(1-(Background.player.dodge+Background.player.equippedAccessory.dodgeBoost))*creature[creaturenum].accuracy){
    int damage = creature[creaturenum].damageby(type);
    if (defend){
     damage = (int)(damage/2);
    }
    damage = Background.player.damageto(damage,type);
    if (type==2){
     if (Math.random()<(creature[creaturenum].bleedChance*(1-Background.player.resist(type)))){
      Background.spacer();
      Background.player.bleeding = new int[]{(int)(damage/4), 3};
      if (Background.player.bleeding[0]<1){
       Background.player.bleeding[0]=1;
      }
      Background.addText("The "+creature[creaturenum].name+"'s attack causes you to begin bleeding.");
     }
    } else if (type==3){
     if (Math.random()<(creature[creaturenum].weakenChance*(1-Background.player.resist(type)))){
      Background.spacer();
      Background.player.weakened = 3;
      //Background.player.basearmor = (int)(Background.player.basearmor*.74);
      //Background.player.slasharmor = (int)(Background.player.slasharmor*.74);
      //Background.player.stabarmor = (int)(Background.player.stabarmor*.74);
      //Background.player.basharmor = (int)(Background.player.basharmor*.74);
      Background.addText("The "+creature[creaturenum].name+"'s attack weakens you.");
     }
    } else if (type==4){
     if (Math.random()<(creature[creaturenum].stunChance*(1-Background.player.resist(type)))){
      Background.spacer();
      Background.player.stunned = true;
      Background.addText("The "+creature[creaturenum].name+"'s attack stuns you.");
     }
    }
   } else{
    Background.addText("The "+creature[creaturenum].name+"'s attack misses.");
   }
   Background.spacer();
  } else {
   creature[creaturenum].stunned = false;
   Background.addText("The "+creature[creaturenum].name+" is stunned, but seems to be pulling itself together.");
   Background.spacer();
  }
 }

 static void playerdoesdamage(int creaturenum, int type, boolean defend){
  if (!Background.player.stunned){
   String string = "You attack the "+creature[creaturenum].name;
   if (type==1){
    string = string+".";
   } else if (type==2){
    string = string+" by slashing at it.";
   } else if (type==3){
    string = string+" by stabbing at it.";
   } else if (type==4){
    string = string+" with blunt force.";
   }
   Background.addText(string);
   Background.spacer();
   double chance = Math.random();
   if (chance<=(1-creature[creaturenum].dodge)*Background.player.accuracy){
    int damage = Background.player.damageby(type);
    if (defend){
     damage = (int)(damage/2);
    }
    damage = creature[creaturenum].damageto(damage,type);
    if (type==2){
     if (Math.random()<(Background.player.chance(type)*(1-creature[creaturenum].bleedResist))){
      Background.spacer();
      creature[creaturenum].bleeding = new int[]{(int)(damage/4), 3};
      if (creature[creaturenum].bleeding[0]<1){
       creature[creaturenum].bleeding[0]=1;
      }
      Background.addText("Your attack causes the "+creature[creaturenum].name+" to begin bleeding.");
     }
    } else if (type==3){
     if (Math.random()<(Background.player.chance(type)*(1-creature[creaturenum].weakenResist))){
      Background.spacer();
      creature[creaturenum].weakened = 3;
      //creature[creaturenum].basearmor = (int)(creature[creaturenum].basearmor*.74);
      //creature[creaturenum].slasharmor = (int)(creature[creaturenum].slasharmor*.74);
      //creature[creaturenum].stabarmor = (int)(creature[creaturenum].stabarmor*.74);
      //creature[creaturenum].basharmor = (int)(creature[creaturenum].basharmor*.74);
      Background.addText("Your attack weakens the "+creature[creaturenum].name+".");
     }
    } else if (type==4){
     if (Math.random()<(Background.player.chance(type)*(1-creature[creaturenum].stunResist))){
      Background.spacer();
      creature[creaturenum].stunned = true;
      Background.addText("Your attack stuns the "+creature[creaturenum].name+".");
     }
    }
    if (Math.random()>Background.player.equippedWeapon.durability){
     Background.spacer();
     Background.addText("Your "+Background.player.equippedWeapon.name+" breaks as you attack.");
     Background.player.equippedWeapon = new Weapon(0);
    }
   } else {
    Background.addText("Your attack misses.");
   }
   Background.spacer();
  } else {
   Background.player.stunned = false;
   Background.addText("You're stunned for the moment, but you shake it off.");
   Background.spacer();
  }
 }

 static boolean creatureflee(int creaturenum){
  if (!creature[creaturenum].stunned){
   double fleechance = creature[creaturenum].speed/(1.75*Background.player.speed)+.1;
   double stopchance = Math.random();
   if (fleechance>=stopchance){
    Background.addText("The "+creature[creaturenum].name+" escapes you.");
    Background.spacer();
    return true;
   } else {
    Background.addText("The "+creature[creaturenum].name+" isn't able to escape.");
    Background.spacer();
    return false;
   }
  } else {
   creature[creaturenum].stunned = false;
   Background.addText("The "+creature[creaturenum].name+" is stunned, but seems to be pulling itself together.");
   return false;
  }
 }

 static boolean playerflee(){
  if (!Background.player.stunned){
   int creaturespeed = 0;
   for (int i=0; i<x; i++){
    creaturespeed = creaturespeed+creature[i].speed;
   }
   Background.game.update();
   creaturespeed = creaturespeed/x;
   double fleechance = (double)Background.player.speed/(double)(1.75*creaturespeed)+.1;
   double stopchance = Math.random();
   if (fleechance>=stopchance){
    Background.addText("You escaped!");
    Background.spacer();
    return true;
   } else {
    if (x==1){
     Background.addText("The "+creature[0].name+" stop you from escaping.");
    } else if (x>=2){
     Background.addText("The creatures stop you from escaping.");
    }
    Background.spacer();
    return false;
   }
  } else {
   Background.player.stunned = false;
   Background.addText("You're stunned for the moment, and don't get far before you're stopped from fleeing.");
   return false;
  }
 }

 static boolean checkcreature(int creaturenum){//true if alive, false if dead
  if (creaturenum==10){
   return true;
  }
  if (creature[creaturenum].health>0){
   if (creature[creaturenum].weakened>0){
    creature[creaturenum].weakened--;
    if (creature[creaturenum].weakened==0){
     //creature[creaturenum].basearmor = (int)(creature[creaturenum].basearmor*1.35);
     //creature[creaturenum].slasharmor = (int)(creature[creaturenum].slasharmor*1.35);
     //creature[creaturenum].stabarmor = (int)(creature[creaturenum].stabarmor*1.35);
     //creature[creaturenum].basharmor = (int)(creature[creaturenum].basharmor*1.35);
     Background.addText("The "+creature[creaturenum].name+" recovers from its weakened state.");
    }
   }
   if (creature[creaturenum].bleeding[1]>0){
    creature[creaturenum].health = creature[creaturenum].health-creature[creaturenum].bleeding[0];
    if (creature[creaturenum].health<0){
     creature[creaturenum].health=0;
    }
    Background.addText("The "+creature[creaturenum].name+" takes "+creature[creaturenum].bleeding[0]+" damage from bleeding.");
    Background.spacer();
    creature[creaturenum].bleeding[1]--;
    if (creature[creaturenum].bleeding[1]==0){
     creature[creaturenum].bleeding[0]=0;
     Background.addText("The "+creature[creaturenum].name+" stops bleeding.");
     Background.spacer();
    }
   }
  }
  if (creature[creaturenum].health==0){
   Background.addText("You defeated the "+creature[creaturenum].name+"!");
   Background.spacer();
   return false;
  } else {
   return true;
  }
 }

 static boolean checkplayer(){//true if alive, false if dead
  if (Background.player.health>0){
   if (Background.player.weakened>0){
    Background.player.weakened--;
    if (Background.player.weakened==0){
     //Background.player.basearmor = (int)(Background.player.basearmor*1.35);
     //Background.player.slasharmor = (int)(Background.player.slasharmor*1.35);
     //Background.player.stabarmor = (int)(Background.player.stabarmor*1.35);
     //Background.player.basharmor = (int)(Background.player.basharmor*1.35);
     Background.addText("You recover from your weakened state.");
    }
   }
   if (Background.player.bleeding[1]>0){
    Background.player.health = Background.player.health-Background.player.bleeding[0];
    if (Background.player.health<0){
     Background.player.health=0;
    }
    Background.addText("You take "+Background.player.bleeding[0]+" damage from bleeding.");
    Background.spacer();
    Background.player.bleeding[1]--;
    if (Background.player.bleeding[1]==0){
     Background.player.bleeding[0]=0;
     Background.addText("You stop bleeding.");
     Background.spacer();
    }
   }
  }
  if (Background.player.health==0){
   if (x==1){
    Background.addText("The "+creature[0].name+" defeated you!");
   } else {
    Background.addText("You've been defeated.");
   }
   Background.spacer();
   return false;
  } else {
   return true;
  }
 }

 public static void refresh(){
  Background.player.bleeding = new int[]{0,0};
  Background.player.weakened = 0;
  Background.player.stunned = false;
 }

}