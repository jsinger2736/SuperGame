


public class Creature{
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

 public Creature(int creaturetype, int clevel){
  identity = creaturetype;
  if (creaturetype==0){ //for the Tutorial
   name = "Tutorial Creature";
   aggressive = true;
   level = 1;
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
   description = "The creature that is fought during the Tutorial.";
   offensive = "Has an equal chance for all types of attacks. Does large normal damage, but little slash or bash damage.";
   defensive = "Is strong against stabbing attacks, but weak to bashing attacks.";
   drops = "Always drops Thick Stick.";
  } else if (creaturetype==1){ //For game testing purposes
   name = "Test Creature";
   aggressive = true;
   level = clevel;
   maxhealth = 2*level + 3;
   health = maxhealth;
   normaldamage = 2*level;
   slashdamage = 3;
   stabdamage = 3;
   bashdamage = 3;
   normalarmor = 0;
   slasharmor = -2;
   stabarmor = 5;
   basharmor = 1;
   speed = 3;
   accuracy = .9;
   dodge = 0;
   slashchance = .2;
   stabchance = .2;
   bashchance = .2;
   experience = 10;
   drop1[0] = 1;
   drop1[1] = 2;
   dropchance1 = .35;
   drop2[0] = 2;
   drop2[1] = 2;
   dropchance2 = .35;
   description = "A creature that is used for testing the mechanics of the game.";
   offensive = "Attacks normally most of the time, but has fair chances to slash, stab, or bash as well.";
   defensive = "Is very strong against stabbing attacks, but is vulnerable to slashing.";
   drops = "Has an equal chance of dropping Pointed Stick or Padded Woolen Vest.";
  } else if (creaturetype==2){
   name = "Tortoise";
   aggressive = false;
   level = clevel;
   maxhealth = 2*level + 10;         //12 at one
   health = maxhealth;
   normaldamage = 1*level+2;         //3 at one
   slashdamage = 1*level;            //1 at one
   stabdamage = 1*level+1;           //2 at one
   bashdamage = 0;
   normalarmor = (int)(3+.6*level);  //3 at one
   slasharmor = (int)(5+.4*level);   //5 at one
   stabarmor = (int)(1+.5*level);    //1 at one
   basharmor = (int)(4+.5*level);    //4 at one
   speed = (int)(2+.3*level);        //2 at one
   accuracy = .85;
   dodge = 0;
   slashchance = 0;
   stabchance = .1;
   bashchance = .4;
   bleedChance = 0;
   weakenChance = .5;
   stunChance = .05;
   bleedResist = .5;
   weakenResist = 0;
   stunResist = .2;
   experience = 4+2*level;            //6 at one
   drop1[0] = 3;
   drop1[1] = 7;
   dropchance1 = .75;
   drop2[0] = 0;
   drop2[1] = 0;
   dropchance2 = .0;
   description = "A tough creature hidden inside a thick shell.";
   offensive = "Most often attacks normally or by bashing, but occasionally by stabbing. Has a fair chance of causing weakening, and a very small chance of stunning.";
   defensive = "Is strong against most attacks, but is slightly more vulnerable to stabbing. Is fairly resistant to bleeding, and has a small resistance to stunning.";
   drops = "Drops a Tortoise Shell.";
  } else if (creaturetype==3){
   name = "Duck";
   aggressive = false;
   level = clevel;
   maxhealth = 1*level + 4;                    //6 at one
   health = maxhealth;
   normaldamage = (int)(1+.4*level);           //1 at one
   slashdamage = (int)(1+.4*level);            //1 at one
   stabdamage = (int)(2+.4*level);              //2 at one
   bashdamage = (int)(1+.4*level);             //1 at one
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   speed = (int)(4+.35*level);                 //4 at one
   accuracy = .9;
   dodge = .2;
   slashchance = 0;
   stabchance = .5;
   bashchance = 0;
   bleedChance = 0;
   weakenChance = (int)(.165+.035*level);       //.2 at one
   stunChance = 0;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   experience = 1+level;                        //2 at one
   drop1[0] = 3;
   drop1[1] = 8;
   dropchance1 = .8;
   drop2[0] = 0;
   drop2[1] = 0;
   dropchance2 = .0;
   description = "A small bird that hangs around bodies of fresh-water.";
   offensive = "Attacks normally or by stabbing. Has a small chance of weakening.";
   defensive = "Has no type of armor, and is very weak.";
   drops = "Drops a Feather.";
  } else if (creaturetype==4){
   name = "Ram";
   aggressive = false;
   level = clevel;
   maxhealth = (int)(7+2.3*level);         //9 at one
   health = maxhealth;
   normaldamage = (int)(3.4+.5*level);     //3 at one
   slashdamage = 3;
   stabdamage = 3;
   bashdamage = (int)(4.5+.5*level);       //4 at one
   normalarmor = (int)(0+.5*level);        //0 at one
   slasharmor = (int)(0+.4*level);         //0 at one
   stabarmor = (int)(0+.5*level);          //0 at one
   basharmor = (int)(1+.7*level);          //0 at one
   speed = (int)(5+.3*level);              //5 at one
   accuracy = .9;
   dodge = .1;
   slashchance = 0;
   stabchance = 0;
   bashchance = .75;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = (int)(.385+.015*level);    //.4 at one
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   experience = (int)(4+1.2*level);        //5 at one
   drop1[0] = 3;
   drop1[1] = 4;
   dropchance1 = .65;
   drop2[0] = 3;
   drop2[1] = 11;
   dropchance2 = .15;
   description = "A wooly, four-legged animal that would like nothing more than to ram you in the.. stomach.";
   offensive = "Generally attacks by bashing, but occasionally attacks normally. Has a fair chance to cause stunning.";
   defensive = "Has minor protection from bashing.";
   drops = "Often drops Wool, and occasionally a Ram's Horn.";
  } else if (creaturetype==5){
   name = "Boar";
   aggressive = true;
   level = clevel;
   maxhealth = (int)(11+2.5*level);     //13              15 at one
   health = maxhealth;
   normaldamage = (int)(3.3+.6*level);    //3              4 at one
   slashdamage = (int)(3+.5*level);       //3              3 at one
   stabdamage = (int)(4.3+.65*level);       //4              5 at one
   bashdamage = (int)(4.3+.7*level);        //5              5 at one
   normalarmor = (int)(0+.4*level);       //              0 at one
   slasharmor = (int)(.5+.5*level);        //1              1 at one
   stabarmor = (int)(0+.4*level);         //              0 at one
   basharmor = (int)(.4+.5*level);         //1              0 at one
   speed = (int)(2.75+.25*level);            //              3 at one
   accuracy = .8;
   dodge = .05;
   slashchance = .2;
   stabchance = .2;
   bashchance = .3;
   bleedChance = .5;
   weakenChance = .2;
   stunChance = .2;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0.15;
   experience = (int)(4.5+1.5*level);      //6 at one
   drop1[0] = 3;
   drop1[1] = 13;
   dropchance1 = .65;
   drop2[0] = 3;
   drop2[1] = 12;
   dropchance2 = .15;
   description = "A pig with an attitude. And sharp tusks.";
   offensive = "Uses any type attack, but most often bashes. Has a small chance of causing weakening or stunning, and a fair chance of causing bleeding.";
   defensive = "Has minor protection from slashing and bashing. Also slightly resists bashing.";
   drops = "Often drops Boar Hide, and occasionally a Boar's Tusk.";
  } else if (creaturetype==6){
   name = "Brown Bear";
   aggressive = true;
   level = clevel;
   maxhealth = 17+3*level;              //20 at one
   health = maxhealth;
   normaldamage = (int)(5+.65*level);         //5 at one
   slashdamage = (int)(5+.68*level);          //5 at one
   stabdamage = 2;
   bashdamage = (int)(7+.72*level);           //7 at one
   normalarmor = (int)(2+.65*level);          //2 at one
   slasharmor = (int)(1+.55*level);           //1 at one
   stabarmor = (int)(0+.5*level);             //0 at one
   basharmor = (int)(1+.6*level);             //1 at one
   speed = (int)(3+.5*level);                 //3 at one
   accuracy = .78;
   dodge = .05;
   slashchance = .35;
   stabchance = 0;
   bashchance = .15;
   bleedChance = .6;
   weakenChance = 0;
   stunChance = .45;
   bleedResist = 0;
   weakenResist = 0.1;
   stunResist = 0.1;
   experience = 6+2*level;               //8 at one
   drop1[0] = 3;
   drop1[1] = 16;
   dropchance1 = .65;
   drop2[0] = 3;
   drop2[1] = 17;
   dropchance2 = .15;
   description = "Lions and tigers and BEARS OH MY. It's a bear. How bout... A medium-sized brown one.";
   offensive = "Most often attacks normally or by slashing, but occasionally bashes. Has a fair chance of causing bleeding, and a small chance of causing stunning.";
   defensive = "Has little protection from normal attacks, and minor protection against slashing and bashing. Very slightly resists bashing and weakening.";
   drops = "Often drops Brown Bear Hide, and occasionally drops a Bear Claw.";
  } else if (creaturetype==7){
   name = "Village Guard";
   aggressive = false;
   level = clevel;
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
   experience = 15;
   drop1[0] = 3;
   drop1[1] = 16;
   dropchance1 = 0;
   drop2[0] = 3;
   drop2[1] = 17;
   dropchance2 = 0;
   description = "They're guards. Of the village. Don't mess with them.";
   offensive = "They have an equal chance of using any type attack, and a small chance of all effects.";
   defensive = "Have good protection against every type of attack.";
   drops = "Currently nothing, sorry.";
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
  if (name.equals("Tutorial Creature")){
   return 0;
  } else if (name.equals("Test Creature")){
   return 1;
  } else if (name.equals("Tortoise")){
   return 2;
  } else if (name.equals("Duck")){
   return 3;
  } else if (name.equals("Ram")){
   return 4;
  } else if (name.equals("Boar")){
   return 5;
  } else if (name.equals("Brown Bear")){
   return 6;
  } else if (name.equals("Village Guard")){
   return 7;
  } else {
   return -1;
  }
 }


}