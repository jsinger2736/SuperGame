


public class Accessory extends Thingy{
 int speedBoost;
 double dodgeBoost;
 double bleedChance, weakenChance, stunChance;
 double bleedResist, weakenResist, stunResist;

 public Accessory(int x){
  setType(5);
  setIdentity(x);
  if (x==0){
   setName("none");
   speedBoost = 0;
   dodgeBoost = 0;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   buy = 0;
   sell = 0;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
   description = "";
   info = "";
  } else if (x==1){
   setName("Pfffffffffffffffft");
   speedBoost = 0;
   dodgeBoost = .025;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   buy = 7;
   sell = 5;
   recipeSuccess = "You mash the Feathers together and make a Pfffffffffffffffft, whatever that is.";
   setRecipe(new int[][]{{3,8,1},{3,8,1},{3,8,1}});
   setCrafts(new int[][]{});
   description = "A bunch of feathers mashed together that makes a funny noise when you blow on it.";
   info = "Increases your chance of dodging an attack by 2.5%.";
  } else if (x==2){
   setName("Bear Claw Necklace");
   speedBoost = 0;
   dodgeBoost = .0;
   bleedChance = 0.1;
   weakenChance = 0;
   stunChance = 0.1;
   bleedResist = 0.1;
   weakenResist = 0;
   stunResist = 0.1;
   buy = 7;
   sell = 5;
   recipeSuccess = "You tie the Thread around the Bear Claw to make a Bear Claw Necklace.";
   setRecipe(new int[][]{{3,17,1},{3,5,1}});
   setCrafts(new int[][]{});
   description = "A bear claw with thread tied around it. It goes around your neck.";
   info = "Gives minor bonuses to bleed and stun chances, and gives minor resistances to bleeding and stunning.";
  } else if (x==3){
   setName("Bear Hide Gloves");
   speedBoost = 0;
   dodgeBoost = 0;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0.2;
   bleedResist = 0.2;
   weakenResist = 0;
   stunResist = 0;
   buy = 7;
   sell = 5;
   recipeSuccess = "You sew the Brown Bear Hide with the Thread into the shape of Bear Hide Gloves.";
   setRecipe(new int[][]{{3,16,1},{3,5,1}});
   setCrafts(new int[][]{{1,7}});
   description = "With these on you look like a cute fuzzy little teddy. Who likes to hit things.";
   info = "Gives little bonus to stun chance, and gives little resistance to bleeding.";
  } else if (x==4){
   setName("Ramicorn Horn");
   speedBoost = 1;
   dodgeBoost = 0;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0.2;
   bleedResist = 0;
   weakenResist = -0.1;
   stunResist = 0;
   buy = 7;
   sell = 5;
   recipeSuccess = "You attach the Ram's Horn to the Length of Rope to create a headpiece that is the Ramicorn Horn.";
   setRecipe(new int[][]{{3,2,1},{3,11,1}});
   setCrafts(new int[][]{});
   description = "While completely ridiculous looking, it does have a certain flair to it.";
   info = "Gives a small speed bonus, and a little bonus to your chance to stun, but makes you minorly more vulnerable to weakening.";
  } else if (x==5){
   setName("Wooden Doll");
   speedBoost = 0;
   dodgeBoost = 0;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   bleedResist = 0;
   weakenResist = .4;
   stunResist = 0;
   buy = 7;
   sell = 5;
   recipeSuccess = "You use the Thread to tie pieces of the Long Stick to the Thick Stick as arms and legs to create a Wooden Doll.";
   setRecipe(new int[][]{{1,1,1},{1,4,1},{3,5,1}});
   setCrafts(new int[][]{});
   description = "It brings out the little girl in you.";
   info = "Gives a fair resistance to weakening.";
  } else {
   setName("accessory"+x);
   speedBoost = 0;
   dodgeBoost = 0;
   bleedChance = 0;
   weakenChance = 0;
   stunChance = 0;
   bleedResist = 0;
   weakenResist = 0;
   stunResist = 0;
   buy = 1;
   sell = 1;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
   description = "";
   info = "";
  }
 }


 public static int identifier(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Pfffffffffffffffft")){
   return 1;
  } else if (name.equals("Bear Claw Necklace")){
   return 2;
  } else if (name.equals("Bear Hide Gloves")){
   return 3;
  } else if (name.equals("Ramicorn Horn")){
   return 4;
  } else if (name.equals("Wooden Doll")){
   return 5;
  } else {
   return -1;
  }
 }

}