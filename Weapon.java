


public class Weapon extends Thingy{
 double durability = 1;
 int normaldamage = 0;
 int slashdamage = 0;
 int stabdamage = 0;
 int bashdamage = 0;
 double bleedChance = 0;
 double weakenChance = 0;
 double stunChance = 0;
 int normalarmor = 0;
 int slasharmor = 0;
 int stabarmor = 0;
 int basharmor = 0;
 //int buy = 0;
 //int sell = 0; // about 3/4 of buy price

 public Weapon(int x){
  setType(1);
  setIdentity(x);
  if (x==0){
   setName("none");
   durability = 1;
   normaldamage = 0;
   slashdamage = 0;
   stabdamage = 0;
   bashdamage = 0;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 0;
   sell = 0;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
  } else if (x==1){
   setName("Thick Stick");
   durability = .98;
   normaldamage = 0;
   slashdamage = -1;
   stabdamage = 0;
   bashdamage = 1;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 1;
   sell = 1;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{1,5},{1,6},{5,5}});
   description = "Ug have stick. Ug hit thing with stick.";
   info = "Does minor bash damage, but less slash damage.";
  } else if (x==2){
   setName("Pointed Stick");
   durability = .98;
   normaldamage = 0;
   slashdamage = 0;
   stabdamage = 1;
   bashdamage = -1;
   bleedChance = 0;
   weakenChance = .15;
   stunChance = 0;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 2;
   sell = 2;
   recipeSuccess = "You use the Jagged Rock to whittle an end of the Long Stick and create a Pointed Stick.";
   setRecipe(new int[][]{{1,4,1},{3,1,0}});
   setCrafts(new int[][]{});
   description = "One tip of the stick has been crudely sharpened.";
   info = "Does minor stab damage, but less bash damage.";
  } else if (x==3){
   setName("Makeshift Spear");
   durability = .98;
   normaldamage = 1;
   slashdamage = 0;
   stabdamage = 1;
   bashdamage = 0;
   bleedChance = 0;
   weakenChance = .35; //.2
   stunChance = 0;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 7;
   sell = 4;
   recipeSuccess = "You tie the Pointed Rock to the end of the Long Stick with the Length of Rope to create a Makeshift Spear.";
   setRecipe(new int[][]{{1,4,1},{3,3,1},{3,2,1}});
   setCrafts(new int[][]{});
   description = "A long stick with a pointed rock tied onto an end.";
   info = "Does minor normal and stab damage. Has a fair chance of weakening.";
  } else if (x==4){
   setName("Long Stick");
   durability = .98;
   normaldamage = 1;
   slashdamage = -1;
   stabdamage = 0;
   bashdamage = -1;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 1;
   sell = 1;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{1,2},{1,3},{4,2},{5,5}});
   description = "A stick. That is long. Maybe you could poke someone's eye with it.";
   info = "Does minor normal damage, but less slash and bash damage.";
  } else if (x==5){
   setName("Makeshift Axe");
   durability = .98;
   normaldamage = 0;
   slashdamage = 1;
   stabdamage = 0;
   bashdamage = 1;
   bleedChance = .35; //.15
   weakenChance = 0;
   stunChance = .2;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 7;
   sell = 4;
   recipeSuccess = "You fix the Jagged Rock near the end of the Thick Stick with a Length of Rope to create a Makeshift Axe.";
   setRecipe(new int[][]{{1,1,1},{3,1,1},{3,2,1}});
   setCrafts(new int[][]{});
   description = "A thick stick with a sharp rock tied near an end of it.";
   info = "Does minor slash and bash damage. Has a fair chance of causing bleeding, and a small chance of causing stunning.";
  } else if (x==6){
   setName("Makeshift Mace");
   durability = .98;
   normaldamage = 1;
   slashdamage = 0;
   stabdamage = 0;
   bashdamage = 1;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = .35; //.2
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 7;
   sell = 4;
   recipeSuccess = "You fix the Rounded Rock to the end of the Thick Stick with a Length of Rope to create a Makeshift Mace.";
   setRecipe(new int[][]{{1,1,1},{3,14,1},{3,2,1}});
   setCrafts(new int[][]{});
   description = "A thick stick with a round rock tied to an end of it.";
   info = "Does minor normal and bash damage. Has a fair chance to cause stunning.";
  } else if (x==7){
   setName("Clawed Gloves");
   durability = .985;
   normaldamage = 1;
   slashdamage = 2;
   stabdamage = 1;
   bashdamage = 0;
   bleedChance = 0.6;
   weakenChance = 0.75;
   stunChance = 0;
   normalarmor = 1;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 10;
   sell = 6;
   recipeSuccess = "You affix Bear Claws to the tips of the Bear Hide Gloves to create Clawed Gloves.";
   setRecipe(new int[][]{{3,17,1},{3,17,1},{5,3,1}});
   setCrafts(new int[][]{});
   description = "With these on you look like a vicious little teddy. Who likes to kill things.";
   info = "Does little slash, and minor normal and stab damage. Has a good chance of causing bleeding, and a very good chance to weaken. Also gives minor protection from normal attacks.";
  } else {
   setName("weapon"+x);
   normaldamage = 1;
   slashdamage = 2;
   stabdamage = 0;
   bashdamage = -2;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 4+x;
   sell = 2+x;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
  }
 }


 public static int identifier(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Thick Stick")){
   return 1;
  } else if (name.equals("Pointed Stick")){
   return 2;
  } else if (name.equals("Makeshift Spear")){
   return 3;
  } else if (name.equals("Long Stick")){
   return 4;
  } else if (name.equals("Makeshift Axe")){
   return 5;
  } else if (name.equals("Makeshift Mace")){
   return 6;
  } else if (name.equals("Clawed Gloves")){
   return 7;
  } else {
   return -1;
  }
 }

 public static int total = 3;
}