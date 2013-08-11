
public class Companion{
 public String name = "Unnamed";
 public boolean aggressive = true;
 public int identity = 0;
 public int level = 0;
 public int maxhealth = 1;
 public int health = 1;
 public int maxmana = 1;
 public int mana = 1;
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
 public double slashchance = 0;
 public double stabchance = 0;
 public double bashchance = 0;
 public int experience = 0;
 public int[] drop1 = {0,0};
 public double dropchance1 = 0;
 public int[] drop2 = {0,0};
 public double dropchance2 = 0;
 public String description = "", offensive = "", defensive = "", drops = "";
 public double bleedChance, weakenChance, stunChance;
 public double bleedResist, weakenResist, stunResist;
 public int[] bleeding = {0,0}; // [amount, turn]
 public int weakened = 0; // turn
 public boolean stunned = false;

 public Companion(int companiontype){
  identity = companiontype;
  if (companiontype==0){
   name = "none";
   aggressive = true;
   level = Background.player.level;
   maxhealth = 20;
   health = maxhealth;
   normaldamage = 3;
   slashdamage = 4;
   stabdamage = 5;
   bashdamage = 4;
   normalarmor = 0;
   slasharmor = 2;
   stabarmor = 1;
   basharmor = -2;
   speed = 3;
   accuracy = .9;
   dodge = 0;
   slashchance = .25;
   stabchance = .25;
   bashchance = .25;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   experience = 5;
   drop1[0] = 1;
   drop1[1] = 1;    //Thick Stick 100%
   dropchance1 = 1;
   drop2[0] = 2;
   drop2[1] = 1;    //Woolen Vest   0%
   dropchance2 = 0;
   description = "This is a non-existant companion.";
   offensive = "Obviously, has no offense.";
   defensive = "Surprisingly has no defense as well.";
   drops = "Always drops Thick Stick.";
  } else if (companiontype==1){
   name = "Village Guard";
   level = Background.player.level;
   maxhealth = 30;
   health = maxhealth;
   normaldamage = 9;
   slashdamage = 9;
   stabdamage = 9;
   bashdamage = 9;
   normalarmor = 5;
   slasharmor = 5;
   stabarmor = 5;
   basharmor = 5;
   speed = 4;
   accuracy = .85;
   dodge = .08;
   slashchance = .25;
   stabchance = .25;
   bashchance = .25;
   bleedChance = .3;
   weakenChance = .3;
   stunChance = .3;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   description = "They're a guard of the village who is willing to accompany you. They never grow stronger.";
   offensive = "They have an equal chance of using any type attack, and a small chance of all effects.";
   defensive = "Have good protection against every type of attack.";
   drops = "Nothing.";
  } else if (companiontype==2){
   name = "Abul";
   level = Background.player.level;
   maxhealth = 25+level;
   health = maxhealth;
   normaldamage = 4+level;
   slashdamage = 3+level;
   stabdamage = 2+level;
   bashdamage = 2+level;
   normalarmor = 3+level;
   slasharmor = 2+level;
   stabarmor = 2+level;
   basharmor = 1+level;
   speed = 3+(int)(.3*level);
   accuracy = .85;
   dodge = .08;
   slashchance = .15;
   stabchance = .35;
   bashchance = .2;
   bleedChance = .25;
   weakenChance = .5;
   stunChance = .35;
   bleedResist = 0.15;
   weakenResist = 0.15;
   stunResist = 0.4;
   description = "A member of the village guard.";
   offensive = "Attacks normally or by stabbing most of the time, and has a good chance to weaken.";
   defensive = "Stronger against normal attacks, weaker against bashing. Resists stunning.";
   drops = "Nothing.";
  }
 }

 public int damagetype(){
  double type = Math.random();
  if (type<=slashchance){
   return 2;
  } else if (type>slashchance && type<=slashchance+stabchance){
   return 3;
  } else if (type>slashchance+stabchance && type<=slashchance+stabchance+bashchance){
   return 4;
  } else {
   return 1;
  }
 }
  
 public int damageby(int type){
  if (type==2){
   return (slashdamage);
  } else if (type==3){
   return (stabdamage);
  } else if (type==4){
   return (bashdamage);
  } else {
   return normaldamage;
  }
 }

 public int damageto(int amount, int type){
  double x;
  if (weakened>0){
   x = .75;
  } else {
   x = 1;
  }
  if (type==2){
   amount = amount - (int)((slasharmor)*x);
  } else if (type==3){
   amount = amount - (int)((stabarmor)*x);
  } else if (type==4){
   amount = amount - (int)((basharmor)*x);
  } else {
   amount = amount - (int)(normalarmor*x);
  }
  if (amount<=0){
   amount=1;
  }
  health = health-amount;
  if (health<0){
   health = 0;
  }
  Background.addText("The "+name+" takes "+amount+" damage.");
  return amount;
 }

 public int[] drop(double x){ //return(type, item)
  if (x<dropchance1){
   return drop1;
  } else if (x<dropchance1+dropchance2){
   return drop2;
  } else {
   int[] none = {0,0};
   return none;
  }
 }

 public static int identify(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Village Guard")){
   return 1;
  } else if (name.equals("Abul")){
   return 2;
  } else {
   return -1;
  }
 }


}