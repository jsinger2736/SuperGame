


public class Armor extends Thingy{
 int normalarmor = 0;
 int slasharmor = 0;
 int stabarmor = 0;
 int basharmor = 0;
 //int buy = 0;
 //int sell = 0; // about 3/4 of buy price

 public Armor(int x){
  setType(2);
  setIdentity(x);
  if (x==0){
   setName("none");
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 0;
   sell = 0;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
  } else if (x==1){
   setName("Woolen Vest");
   normalarmor = 1;
   slasharmor = 1;
   stabarmor = 1;
   basharmor = 0;
   buy = 10;
   sell = 6;
   recipeSuccess = "You sew the Wool Cloths with Thread to create a Woolen Vest.";
   setRecipe(new int[][]{{3,6,1},{3,6,1},{3,5,1}});
   setCrafts(new int[][]{{2,2},{2,3}});
   description = "A thin vest made from sewing a bit of cloth together the right way.";
   info = "Provides minor protection from everything but bashing.";
  } else if (x==2){
   setName("Padded Woolen Vest");
   normalarmor = 1;
   slasharmor = 1;
   stabarmor = 1;
   basharmor = 1;
   buy = 13;
   sell = 9;
   recipeSuccess = "You stuff the Woolen Vest with more Wool to create a Padded Woolen Vest.";
   setRecipe(new int[][]{{2,1,1},{3,4,1}});
   setCrafts(new int[][]{{2,5}});
   description = "A regular woolen vest, but a little cushionier.";
   info = "Provides minor protection from everything.";
  } else if (x==3){
   setName("Pillow Vest");
   normalarmor = 1;
   slasharmor = 2;
   stabarmor = 1;
   basharmor = 3;
   buy = 15;
   sell = 12;
   recipeSuccess = "You stuff the Woolen Vest with a Woolen Pillow to create a Pillow Vest.";
   setRecipe(new int[][]{{2,1,1},{3,10,1}});
   setCrafts(new int[][]{});
   description = "Makes you look fat.";
   info = "Provides minor protection from normal and stabbing attacks, little protection from slashing, and fair protection from bashing.";
  } else if (x==4){
   setName("Shoddy Shell Armor");
   normalarmor = 2;
   slasharmor = 2;
   stabarmor = 2;
   basharmor = 2;
   buy = 18;
   sell = 14;
   recipeSuccess = "You connect the two Tortoise Shells with the Length of Rope to make Shoddy Shell Armor.";
   setRecipe(new int[][]{{3,7,1},{3,7,1},{3,2,1}});
   setCrafts(new int[][]{});
   description = "It looks weird, but it does the job. Also, you should come out of your shell.";
   info = "Provides little protection from all types of attack.";
  } else if (x==5){
   setName("Ram Costume");
   normalarmor = 2;
   slasharmor = 2;
   stabarmor = 1;
   basharmor = 2;
   buy = 20;
   sell = 15;
   recipeSuccess = "You put a Ram's Horn on each shoulder of the Padded Woolen Vest and your Ram Costume is complete.";
   setRecipe(new int[][]{{3,11,1},{3,11,1},{2,2,1}});
   setCrafts(new int[][]{});
   description = "My oh my, it makes you look just like a ram. A two legged, not-very-furry ram, but a ram nonetheless.";
   info = "Provides little protection from normal, slashing, and bashing attacks, and minor protection from stabbing.";
  } else if (x==6){
   setName("Boar Hide Armor");
   normalarmor = 2;
   slasharmor = 2;
   stabarmor = 1;
   basharmor = 1;
   buy = 16;
   sell = 12;
   recipeSuccess = "You sew pieces of Boar Hide together with the Thread to make Boar Hide Armor.";
   setRecipe(new int[][]{{3,13,1},{3,13,1},{3,5,1}});
   setCrafts(new int[][]{{2,7}});
   description = "A bit furry, and a bit stinky, but it gets the job done.";
   info = "Provides little protection from normal and slashing attacks, and minor protection from stabbing and bashing.";
  } else if (x==7){
   setName("Boar Costume");
   normalarmor = 3;
   slasharmor = 2;
   stabarmor = 2;
   basharmor = 2;
   buy = 22;
   sell = 16;
   recipeSuccess = "You put a Boar Tusk on each shoulder of the Boar Hide Armor to create a Boar Costume.";
   setRecipe(new int[][]{{3,12,1},{3,12,1},{2,6,1}});
   setCrafts(new int[][]{});
   description = "Makes you look just like a boar, but not. It's quite stunning really.";
   info = "Provides fair protection from normal attacks, and little protection from slashing, stabbing, and bashing.";
  } else if (x==8){
   setName("Brown Bear Armor");
   normalarmor = 2;
   slasharmor = 2;
   stabarmor = 1;
   basharmor = 2;
   buy = 19;
   sell = 11;
   recipeSuccess = "You sew pieces of the Brown Bear Hide together with Thread to make Brown Bear Hide Armor.";
   setRecipe(new int[][]{{3,5,1},{3,16,1},{3,16,1}});
   setCrafts(new int[][]{{2,7}});
   description = "Armor composed of thick brown hair. It'll keep you warm in the winter.";
   info = "Provides little protection from normal, slashing, and bashing attacks, and provides minor protection from stabbing.";
  } else {
   setName("armor"+x);
   normalarmor = 1;
   slasharmor = 1;
   stabarmor = -1;
   basharmor = 0;
   buy = 8+x;
   sell = 6+x;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
  }
 }



 public static int identifier(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Woolen Vest")){
   return 1;
  } else if (name.equals("Padded Woolen Vest")){
   return 2;
  } else if (name.equals("Pillow Vest")){
   return 3;
  } else if (name.equals("Shoddy Shell Armor")){
   return 4;
  } else if (name.equals("Ram Costume")){
   return 5;
  } else if (name.equals("Boar Hide Armor")){
   return 6;
  } else if (name.equals("Boar Costume")){
   return 7;
  } else if (name.equals("Brown Bear Armor")){
   return 8;
  } else {
   return -1;
  }
 }

 public static int total = 1;
}