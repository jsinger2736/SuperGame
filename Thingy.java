


public class Thingy{
 int[][] crafts, recipe;
 int identity, type;
 String name, recipeSuccess, description = "", info = "";
 int recipeAmount = 1, buy = 0, sell = 0;

 @Override
 public boolean equals(Object object){
  boolean sameSame = false;
  if (object != null && object instanceof Thingy){
   sameSame = this.name == ((Thingy) object).name;
  }
  return sameSame;
 }
 public void setCrafts(int[][] x){
  crafts = x;
 }
 public void setRecipe(int[][] x){
  recipe = x;
 }
 public void setIdentity(int x){
  identity = x;
 }
 public void setType(int x){
  type = x;
 }
 public void setName(String x){
  name = x;
 }
}