import java.util.*;



public class Store{
 ArrayList<Weapon> availableWeapons = new ArrayList<Weapon>();
 ArrayList<Armor> availableArmor = new ArrayList<Armor>();
 ArrayList<Item> availableItems = new ArrayList<Item>();
 ArrayList<Helmet> availableHelmets = new ArrayList<Helmet>();
 ArrayList<Accessory> availableAccessories = new ArrayList<Accessory>();

 public Store(int x){
  if (x==0){
   availableWeapons.add(new Weapon(1));
   availableArmor.add(new Armor(1));
   availableArmor.add(new Armor(1));
   availableItems.add(new Item(1));
  } else if (x==1){
   for (int i=1; i<7; i++){
    availableWeapons.add(new Weapon(i));
   }
   for (int i=1; i<8; i++){
    if (i!=5 && i!=7 && i!=3){
     availableArmor.add(new Armor(i));
    }
   }
   for (int i=1; i<18; i++){
    if (i!=9 && i!=10 && i!=6 && i!=2){
     availableItems.add(new Item(i));
    }
   }
   for (int i=1; i<2; i++){
    if (i!=0){
     availableHelmets.add(new Helmet(i));
    }
   }
   for (int i=1; i<2; i++){
    if (i!=0){
     availableAccessories.add(new Accessory(i));
    }
   }
  }

 }


}