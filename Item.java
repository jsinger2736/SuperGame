


public class Item extends Thingy{
 //int type = 3;
 //int identity = 0;
 //String name = "item";
 //int buy = 0;
 //int sell = 0; // about 3/4 of buy price
 //int[][] recipe;
 //int[][] crafts;

 //bananas(quest), fur

 public Item(int x){
  setType(3);
  setIdentity(x);
  if (x==0){
   setName("none");
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
  } else if (x==1){
   setName("Jagged Rock");
   buy = 5;
   sell = 3;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{1,2},{1,5},{3,5}});
   description = "A rock with a sharp jagged edge.";
   info = "Used for crafting.";
  } else if (x==2){
   setName("Length of Rope");
   buy = 4;
   sell = 3;
   recipeSuccess = "You braid the Thread together to create a Length of Rope.";
   setRecipe(new int[][]{{3,5,1},{3,5,1},{3,5,1}});
   setCrafts(new int[][]{{1,3},{1,5},{2,4},{1,6},{5,4}});
   description = "A piece of braided rope.";
   info = "Used for crafting.";
  } else if (x==3){
   setName("Pointy Rock");
   buy = 3;
   sell = 2;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{1,3}});
   description = "A small rock with a pointed tip.";
   info = "Used for crafting.";
  } else if (x==4){
   setName("Wool");
   buy = 3;
   sell = 2;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{2,2},{3,5},{3,6}});
   description = "A fluffy white poofy clump of stuff.";
   info = "Used for crafting.";
  } else if (x==5){
   setName("Thread");
   buy = 1;
   sell = 1;
   recipeAmount = 4;
   recipeSuccess = "You use the Jagged Rock to scrape the Wool apart and create 4 Thread.";
   setRecipe(new int[][]{{3,4,1},{3,1,0}});
   setCrafts(new int[][]{{2,1},{3,2},{2,6},{4,1},{5,2},{5,3},{2,8},{5,5}});
   description = "A long strand of thread.";
   info = "Used for crafting.";
  } else if (x==6){
   setName("Wool Cloth");
   buy = 5;
   sell = 3;
   recipeAmount = 2;
   recipeSuccess = "You fuse the two Wool together to make two Wool Cloth.";
   setRecipe(new int[][]{{3,4,1},{3,4,1}});
   setCrafts(new int[][]{{2,1},{3,10},{4,1}});
   description = "A piece of cloth somehow made by banging two piece of wool together.";
   info = "Used for crafting.";
  } else if (x==7){
   setName("Tortoise Shell");
   buy = 6;
   sell = 4;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{2,4}});
   description = "The empty shell of a tortoise.";
   info = "Used for crafting.";
  } else if (x==8){
   setName("Feather");
   buy = 1;
   sell = 1;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{5,1},{3,10},{4,2}});
   description = "A soft feather.";
   info = "Used for crafting.";
  } else if (x==9){
   //setName("Pfffffffffffffffft");
   //buy = 3;
   //sell = 2;
   //recipeSuccess = "You mash the Feathers together and make a Pfffffffffffffffft, whatever that is.";
   //setRecipe(new int[][]{{3,8,1},{3,8,1},{3,8,1}});
   //setCrafts(new int[][]{});
   //description = "A bunch of feathers mashed together that makes a funny noise when you blow on it.";
   //info = "Has absolutely no use.";
  } else if (x==10){
   setName("Woolen Pillow");
   buy = 4;
   sell = 3;
   recipeSuccess = "You stuff the Wool Cloth with Feather, and create a Woolen Pillow.";
   setRecipe(new int[][]{{3,6,1},{3,8,1},{3,8,1}});
   setCrafts(new int[][]{{2,3}});
   description = "A down-filled pillow. Kinda comfortable if you like wool.";
   info = "Nice if youre sleepy, but mainly used for crafting.";
  } else if (x==11){
   setName("Ram's Horn");
   buy = 3;
   sell = 2;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{2,5},{5,4},{4,3}});
   description = "A horn from a ram that's curled up on itself.";
   info = "Used for crafting.";
  } else if (x==12){
   setName("Boar Tusk");
   buy = 3;
   sell = 2;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{2,7}});
   description = "A sharp tusk from a wild boar.";
   info = "Used for crafting.";
  } else if (x==13){
   setName("Boar Hide");
   buy = 4;
   sell = 3;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{2,6}});
   description = "The hide of a wild boar.";
   info = "Used for crafting.";
  } else if (x==14){
   setName("Rounded Rock");
   buy = 5;
   sell = 4;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{1,6}});
   description = "A hard, palm-sized, round rock.";
   info = "Used for crafting.";
  } else if (x==15){
   setName("Leather");////////////////////////////////trade boar hides(and others) to an npc for leather
   buy = 7;
   sell = 5;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
   description = "A piece of tough leather.";
   info = "Used for crafting.";
  } else if (x==16){
   setName("Brown Bear Hide");
   buy = 5;
   sell = 3;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{5,3},{2,8}});
   description = "The hide of a brown bear.";
   info = "Used for crafting.";
  } else if (x==17){
   setName("Bear Claw");
   buy = 5;
   sell = 3;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{{5,2},{1,7}});
   description = "The curved claw of a bear.";
   info = "Used for crafting.";
  } else {
   setName("item"+x);
   buy = 4;
   sell = 3;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
  }
 }

 public static int identifier(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Jagged Rock")){
   return 1;
  } else if (name.equals("Length of Rope")){
   return 2;
  } else if (name.equals("Pointy Rock")){
   return 3;
  } else if (name.equals("Wool")){
   return 4;
  } else if (name.equals("Thread")){
   return 5;
  } else if (name.equals("Wool Cloth")){
   return 6;
  } else if (name.equals("Tortoise Shell")){
   return 7;
  } else if (name.equals("Feather")){
   return 8;
  //} else if (name.equals("Pfffffffffffffffft")){
  // return 9;
  } else if (name.equals("Woolen Pillow")){
   return 10;
  } else if (name.equals("Ram's Horn")){
   return 11;
  } else if (name.equals("Boar Tusk")){
   return 12;
  } else if (name.equals("Boar Hide")){
   return 13;
  } else if (name.equals("Rounded Rock")){
   return 14;
  } else if (name.equals("Leather")){
   return 15;
  } else if (name.equals("Brown Bear Hide")){
   return 16;
  } else if (name.equals("Bear Claw")){
   return 17;
  } else {
   return -1;
  }
 }

 public static int total = 1;
}