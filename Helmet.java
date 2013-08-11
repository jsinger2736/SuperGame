


public class Helmet extends Thingy{
 int normalarmor = 0;
 int slasharmor = 0;
 int stabarmor = 0;
 int basharmor = 0;

 public Helmet(int x){
  setType(4);
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
   description = "";
   info = "";
  } else if (x==1){
   setName("Woolen Cap");
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 1;
   buy = 6;
   sell = 4;
   recipeSuccess = "You sew the Wool Cloth with the thread to create a Woolen Cap.";
   setRecipe(new int[][]{{3,6,1},{3,5,1}});
   setCrafts(new int[][]{{4,3}});
   description = "A thin helmet that looks more like a wig than armor.";
   info = "Provides minor protection against bashing.";
  } else if (x==2){
   setName("Feather Headdress");
   normalarmor = 0;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 0;
   buy = 6;
   sell = 4;
   recipeSuccess = "You attach the Feathers to the Long Stick to create a Feather Headdress.";
   setRecipe(new int[][]{{3,8,1},{3,8,1},{1,4,1}});
   setCrafts(new int[][]{});
   description = "It's a feathered stick you put on your head. Wearing it, you look like you're pretending to be chief.";
   info = "Provides no armor value.";
  } else if (x==3){
   setName("Ramicorn Cap");
   normalarmor = 1;
   slasharmor = 0;
   stabarmor = 0;
   basharmor = 1;
   buy = 9;
   sell = 6;
   recipeSuccess = "You attach the Ram's Horn to the top of the Woolen Cap to create a Ramicorn Cap.";
   setRecipe(new int[][]{{3,11,1},{4,1,1}});
   setCrafts(new int[][]{});
   description = "A thin helmet made of wool with the horn of a ram attached. One word, ridiculous.";
   info = "Provides minor protection against both normal and bashing attacks.";
  } else {
   setName("helmet"+x);
   normalarmor = 1;
   slasharmor = 1;
   stabarmor = -1;
   basharmor = 0;
   buy = 8+x;
   sell = 6+x;
   setRecipe(new int[][]{});
   setCrafts(new int[][]{});
   description = "";
   info = "";
  }
 }


 public static int identifier(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Woolen Cap")){
   return 1;
  } else if (name.equals("Feather Headdress")){
   return 2;
  } else if (name.equals("Ramicorn Cap")){
   return 3;
  } else {
   return -1;
  }
 }

}