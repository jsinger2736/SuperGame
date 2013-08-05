


public class Npc{
 int identity = 0;
 static String name = "";

 public Npc(int x){
  identity = x;
  if (x==0){
   name = "none";
  } else if (x==1){
   name = "Chieftain";
  } else if (x==2){
   name = "Lilia";
  } else if (x==3){
   name = "Abul";
  } else if (x==4){
   name = "Babu";
  } else {
   name = "npc"+x;
  }
 }

 public void interact(){
  Background.changePic(2,identity);
  Background.game.update();
  if (identity==1){          //Chieftain
   if (Background.player.quests[1]!=0){
    Background.addQuote("Greetings "+Background.player.name+".");
    Background.spacer();
   }
   if (Background.player.quests[1]==0){
    Background.addQuote("Hello "+Background.player.name+", I don't believe we've met before.");
    Background.spacer();
    Background.addQuote("I am "+name+", and I am the chieftain of this village.");
    Background.spacer();
    Background.addQuote("If you're looking for something to do here, my daughter, "+new Npc(2).name+" has been asking around for help with something. She refuses to tell me what it is though.");
    Background.spacer();
    Background.addQuote("If you'd like to help her, my daughter is in the "+Location.identifier(3)+".");
    Background.spacer();
    Background.addQuote("Perhaps later I myself will have something for you to do.");
    Background.player.quests[1] = 1;
    Background.spacer();
   } else if (Background.player.quests[1]==1){
    Background.addQuote("My daughter wants someone to help her with something. She's in the "+Location.identifier(3)+".");
    Background.spacer();
   } else if (Background.player.quests[1]==2){
    Background.addQuote("I heard you helped my daughter out with whatever it was she wanted, I appreciate it.");
    Background.spacer();
    Background.addQuote("Because you helped her, I feel I can help you out a bit. Do you already know the basics of crafting?");
    if (!Background.yesno()){
     Background.addQuote("Well, it's not really that difficult. In order to craft all you need are the basic materials and the ingenuity to figure out how they go together.");
     Background.spacer();
     Background.addQuote("For example, one of the easiest things to do is to sharpen a Long Stick with a Jagged Rock to make a Pointed Stick.");
     Background.spacer();
     Background.addQuote("In order to do that all you need to do is to have both ingredients in your inventory, and then select them in the crafting menu, and select craft.");
     Background.spacer();
     Background.addQuote("If an ingredient is consumed, then it is removed from your inventory, but ingredients are not always consumed. In making a Pointed Stick, the Long Stick is used up but the Jagged Rock is not.");
     Background.spacer();
     Background.addQuote("When crafting you also need to make sure your inventories are not full. Remember your weapon, armor, helmet, and accessory inventories can only hold up to 5 objects, while your Item inventory can hold up to 15 objects.");
     Background.spacer();
     Background.addQuote("Let's try to practice crafting a bit now.");
     Background.spacer();
    } else {
     Background.addQuote("Ok, that saves a bit of time then. We'll just skip to actually doing it then.");
     Background.spacer();
    }
    Background.addQuote("Go collect a Long Stick from the forest and a Jagged Rock from the "+Location.identifier(12)+" past the "+Location.identifier(11)+", then craft a Pointed Stick using them.");
    Background.spacer();
    Background.addQuote("Once you've done that bring it back to me and I'll help you craft a more complicated object.");
    Background.player.quests[1]=3;
    Background.spacer();
   } else if (Background.player.quests[1]==3){
    Background.addQuote("Have you made the Pointed Stick? May I see it?");
    if (Background.yesno()){
     if (Background.player.inventoryContains(1,2,1)){
      Background.addQuote("Good job making the Pointed Stick! With that it's a bit easier to take care of the tortoises that you'll find out in the forest. They're fairly weak to stabbing.");
      Background.spacer();
      Background.addQuote("Now that you've done that, let's step it up a notch. I'll teach you how to create Woolen Armor.");
      Background.spacer();
      Background.addQuote("To make Woolen Armor, you'll need to first get three Wool from the rams to the east. Make sure you also hold on to your Jagged Rock.");
      Background.spacer();
      Background.addQuote("Be careful fighting the rams though, their bashing attacks have a good chance of stunning you, preventing you from attacking or fleeing your next turn. I'd advise you to only attempt to take on one at a time.");
      Background.spacer();
      Background.addQuote("Come back to me when you have three Wool and a Jagged Rock.");
      Background.player.quests[1]=4;
      Background.spacer();
     } else {
      Background.addQuote("You don't have one, come back to me when you do.");
      Background.spacer();
     }
    } else {
     Background.addQuote("Remember, the Long Stick can be found in the forest and the Jagged Rock can be found at the "+Location.identifier(12)+", which is past the "+Location.identifier(11)+".");
     Background.spacer();
    }
   } else if (Background.player.quests[1]==4){
    Background.addQuote("Do you have the three Wool and a Jagged Rock?");
    Background.spacer();
    if (Background.yesno()){
     if (Background.player.inventoryContains(3,4,3) && Background.player.inventoryContains(3,1,1)){
      Background.addQuote("Good! Hope the rams didn't give you too much trouble. Now, take one of the Wools and craft it together with the Jagged Rock, that'll make you four Thread.");
      while (true){
       Background.addQuote("Tell me when you've done that.");
       Background.game.setButton(1,"Done");
       Background.game.setButton(2,"Instructions");
       Background.game.setButton(3,"Leave");
       Background.wait(3);
       if (Background.choice==1){
        if (Background.player.inventoryContains(3,5,4)){
         Background.player.quests[1]=5;
         break;
        } else {
         Background.addQuote("You don't have the four Thread.");
         Background.spacer();
        }
       } else if (Background.choice==2){
        Background.addQuote("Craft one Wool with a Jagged Rock, and make four Thread.");
        Background.spacer();
       } else if (Background.choice==3){
        return;
       }
      }
      Background.addQuote("Ok, now you have some Thread, but we're also going to need some Wool Cloth to finish up the Woolen Vest.");
      Background.spacer();
      Background.addQuote("To make the Wool Cloth, craft two Wool together. Let me know once you've done that.");
      Background.spacer();
      while (true){
       Background.game.setButton(1,"Done");
       Background.game.setButton(2,"Instructions");
       Background.game.setButton(3,"Leave");
       Background.wait(3);
       if (Background.choice==1){
        if (Background.player.inventoryContains(3,6,2)){
         break;
        } else {
         Background.addQuote("You don't have the two Wool Cloth.");
         Background.spacer();
        }
       } else if (Background.choice==2){
        Background.addQuote("Craft two Wool together in order to make two Wool Cloth.");
        Background.spacer();
       } else if (Background.choice==3){
        return;
       }
      }
      Background.addQuote("Alright good, now there's only one step left.");
      Background.spacer();
      Background.addQuote("All you have to do now is craft two Wool Cloth with a Thread, and you'll have yourself a Woolen Vest.");
      Background.spacer();
      Background.addQuote("That Woolen Vest will give you a bit of protection, but it's not nearly what you'll need in order to be able to face much more than the weakest of creatures.");
      Background.spacer();
      Background.addQuote("In order to make stronger weapons and armor, and even helmets and accessories, you'll need to experiment with crafting and see what you can create. Perhaps though you'll get lucky and someone will give you a recipe, you never know.");
      Background.spacer();
      Background.addQuote("If you'd like to make the Woolen Vest a bit better, you can pad it with some more Wool.");
      Background.spacer();
      Background.addQuote("I hoped all this information helped a bit. If you want to learn about other things you should talk to "+new Npc(4).name+" in the "+Location.identifier(3)+", he knows his way around combat better than anyone I know.");
      Background.player.quests[4]=1;
      Background.player.quests[1]=6;
      Background.spacer();
     } else {
      Background.addQuote("Hm, you don't actually have them. Come back when you do.");
      Background.spacer();
     }
    } else {
     Background.addQuote("You'll be able to get the Wool from rams near the "+Location.identifier(12)+", which is beyond the "+Location.identifier(11)+".");
     Background.spacer();
    }
   } else if (Background.player.quests[1]==5){
    if (Background.player.inventoryContains(3,5,4)){
     Background.addQuote("Ok, now you have some Thread, but we're also going to need some Wool Cloth to finish up the Woolen Vest.");
     Background.spacer();
     Background.addQuote("To make the Wool Cloth, craft two Wool together. Let me know once you've done that.");
     Background.spacer();
     while (true){
      Background.game.setButton(1,"Done");
      Background.game.setButton(2,"Instructions");
      Background.game.setButton(3,"Leave");
      Background.wait(3);
      if (Background.choice==1){
       if (Background.player.inventoryContains(3,6,2)){
        break;
       } else {
        Background.addQuote("You don't have the two Wool Cloth.");
        Background.spacer();
       }
      } else if (Background.choice==2){
       Background.addQuote("Craft two Wool together in order to make two Wool Cloth.");
       Background.spacer();
      } else if (Background.choice==3){
       return;
      }
     }
     Background.addQuote("Alright good, now there's only one step left.");
     Background.spacer();
     Background.addQuote("All you have to do now is craft two Wool Cloth with a Thread, and you'll have yourself a Woolen Vest.");
     Background.spacer();
     Background.addQuote("That Woolen Vest will give you a bit of protection, but it's not nearly what you'll need in order to be able to face much more than the weakest of creatures.");
     Background.spacer();
     Background.addQuote("In order to make stronger weapons and armor, and even helmets and accessories, you'll need to experiment with crafting and see what you can create. Perhaps though you'll get lucky and someone will give you a recipe, you never know.");
     Background.spacer();
     Background.addQuote("If you'd like to make the Woolen Vest a bit better, you can pad it with some more Wool.");
     Background.spacer();
     Background.addQuote("I hoped all this information helped a bit. If you want to learn about other things you should talk to "+new Npc(4).name+" in the "+Location.identifier(3)+", he knows his way around combat better than anyone I know.");
     Background.player.quests[4]=1;
     Background.player.quests[1]=6;
     Background.spacer();
    } else {
     Background.addQuote("You need to have four thread. Go ahead an pull a piece of Wool apart with a Jagged Rock, then speak to me again.");
     Background.spacer();
    }
   } else if (Background.player.quests[1]==6){
    Background.addQuote("You should talk to Babu in the "+Location.identifier(3)+", he'll be able to teach you the things you should know about combat.");
    Background.spacer();
   } else if (Background.player.quests[1]==7){
    Background.addQuote("I have nothing for you to do right now, "+Background.player.name+".");
    Background.spacer();
   } else if (Background.player.quests[1]==8){
    Background.addQuote("I hear you have shown "+new Npc(4).name+" that you can handle yourself out in the wild. Perhaps you can help me out with something.");
    Background.spacer();
    Background.addQuote("My daughter left to the south a little while ago, saying she was exploring. Normally I wouldn't worry, but she generally comes back once she gets hungry and she hasn't been back for a few hours.");
    Background.spacer();
    Background.addQuote("I'd go myself, but I'm busy doing chieftany things at the moment. Just check to make sure she's ok and let me know so I can put my mind at ease.");
    Background.player.quests[1]=8;
    Background.spacer();
   } else if (Background.player.quests[1]==9){
    Background.addQuote("I'm starting to wonder how my daughter's doing, can you go check on her for me? She went to the "+Location.identifier(10)+".");
    Background.spacer();
   } else if (Background.player.quests[1]==10){
    Background.addQuote("My daughter let me know what you did, and again, thank you for helping her.");
    Background.spacer();
    Background.addQuote("As a bit of recompense, I will tell you of armor you can make from Boar Hide.");
    Background.spacer();
    Background.addQuote("Take two Boar Hide and a Thread and craft them, and you will get Boar Hide Armor. Craft that with two Boar Tusks and you will be able to make an even better piece of armor.");
    Background.spacer();
    Background.addQuote("I hope that information is helpful to you.");
    Background.player.quests[1]=10;
    Background.spacer();
   } else if (Background.player.quests[1]==11){
    Background.addQuote("Thank you again for saving "+new Npc(2).name+".");
    Background.spacer();
   }

  } else if (identity==2){           //Lilia
   if (Background.player.quests[2]!=0 && Background.player.quests[2]!=3){
    Background.addQuote("Hi "+Background.player.name+".");
    Background.spacer();
   }
   if (Background.player.quests[2]==0){
    Background.addQuote("Hi "+Background.player.name+", my name's "+name+".");
    Background.spacer();
    Background.addQuote("Do you think you could help me? I want to make my dad a Feather Headdress as a gift.");
    Background.spacer();
    Background.addQuote("I don't actually have the things I need for it though. Do you think you could get me two Feathers and a Long Stick?");
    Background.spacer();
    Background.addQuote("You can get the Feathers from the Ducks near the river. You can probably find a Long Stick somewhere around the forest too.");
    Background.spacer();
    Background.addQuote("Thank you so much, my dad's gonna really like it when I'm done.");
    Background.player.quests[2]=1;
    Background.spacer();
   } else if (Background.player.quests[2]==1){
    Background.addQuote("Do you have the stuff for me to make my dad a Feather Headdress?");
    Background.spacer();
    if (Background.player.inventoryContains(1,4,1) && Background.player.inventoryContains(3,8,2)){
     Background.addQuote("Awesome, you do have them. Can you give them to me?");
     if (Background.yesno()){
      if (Background.player.inventoryContains(1,4,1) && Background.player.inventoryContains(3,8,2)){
       Background.player.weapons.remove(new Weapon(4));
       Background.player.items.remove(new Item(8));
       Background.player.items.remove(new Item(8));
       Background.addQuote("Thank you "+Background.player.name+"!");
       Background.player.quests[2]=2;
       Background.player.quests[1]=2;
       Background.spacer();
       Background.addQuote("Since you got me these I'll let you know how to make a Feather Headdress of your own.");
       Background.spacer();
       Background.addQuote("All you have to do is put the two Feathers on a Long Stick the right way and you've got a Feather Headdress!");
       Background.spacer();
       Background.addQuote("I'll see you later "+Background.player.name+".");
       Background.spacer();
      } else {
       Background.addQuote("Hey where'd they go? I just saw that you had them..");
      }
     } else {
      Background.addQuote("Aw okay...");
      Background.spacer();
     }
    } else {
     Background.addQuote("All I need is two Feathers from the Ducks near the river, and a Long Stick from the forest.");
     Background.spacer();
    }
   } else if (Background.player.quests[2]==2){
    Background.addQuote("Thank you for helping me make my dad the Feather Headdress.");
    Background.spacer();
   } else if (Background.player.quests[2]==3){
    Background.addQuote("Hi "+Background.player.name+". I'm glad you're here. Boars don't seem to like me as much lately. I used to be able to pet them.");
    Background.spacer();
    Background.addQuote("I don't think I'll try petting them again though, these ones didn't like it much.");
    Background.spacer();
    Background.addQuote("Anyways, thank you for saving me. I think I'm gonna run back home now, I'll see you later!");
    Background.player.quests[2]=4;
    Background.player.quests[1]=10;
    Background.spacer();
   } else if (Background.player.quests[2]==4){
    Background.addQuote("Thank you for saving me! I really appreciate it and all, but I think I dropped my Wooden Doll when the boars had me up in the tree.");
    Background.spacer();
    Background.addQuote("Would you go get it for me? I don't want to do it myself.");
    if (Background.yesno()){
     Background.addText("Hooray! Thank you! I'll be here waiting for you to get back!");
     Background.player.quests[2]=5;
     Background.spacer();
    } else {
     Background.addQuote("Aw, fine then. I'll just go get it myself.");
     Background.spacer();
     Background.addQuote("Well, I guess I won't, dad wouldn't be very happy about it if I did. If you change your mind will you please let me know?");
     Background.spacer();
    }
   } else if (Background.player.quests[2]==5){
    Background.addQuote("Did you find my doll? Is she okay?");
    if (Background.yesno()){
     if (Background.player.inventoryContains(5,5,1)){
      Background.addQuote("Oh yay! You got my Wooden Doll for me! This is mine right? I can't really tell. She says she is mine though, so it's ok.");
      Background.spacer();
      Background.addQuote("This is as far as I've gotten now, now i gotta go fix the whole wooden vest thingy... bleh");
     } else {
      Background.addQuote("Where is she? I don't see her. You lied, you don't really have her, do you?");
      Background.spacer();
      Background.addQuote("Go get her for me, pretty please.");
      Background.spacer();
     }
    } else {
     Background.addQuote("Well please hurry, she's all by herself in the "+Location.identifier(10)+" and the boar don't seem to be very happy. I'm afraid she'll get hurt.");
     Background.spacer();
    }
   }

  } else if (identity==3){           //Abul
   Background.addQuote("Greetings "+Background.player.name+".");
   Background.spacer();
   if (Background.player.quests[3]==0){
    if (Background.player.quests[0]<2){
     Background.addQuote("I must warn you, the creatures past this bridge are much more powerful than those around the village.");
     Background.spacer();
     Background.addQuote("Before you go on, you should make sure you are well-equipped.");
     Background.spacer();
    }
   } else if (Background.player.quests[3]==1){
    Background.addQuote("I hope you are doing well. Good luck out there.");
    Background.spacer();
   }

  } else if (identity==4){          //Babu
   Background.addQuote("Good day "+Background.player.name+".");
   Background.spacer();
   if (Background.player.quests[4]==0){
    Background.addQuote("I am in charge of this village's guard. We do our best to keep the surrounding areas safe, and if you ever need anything you may come to me for help.");
    Background.spacer();
   } else if (Background.player.quests[4]==1){
    Background.addQuote("I hear you wish to learn about combat. Learning the basics is easy, but mastering the subtleties can take minutes.");
    Background.player.quests[1]=7;
    Background.spacer();
    Background.addQuote("First though, you'll need better weapons than merely a Pointed Stick. Do you know how to make a Makeshift Axe, Makeshift Spear, and Makeshift Mace?");
    if (Background.yesno()){
     Background.addQuote("Very good then, to prove it I wish you to show me one of each. Then I will teach you more.");
     Background.player.quests[4]=2;
     Background.spacer();
    } else {
     Background.addQuote("Alright then, that's what you came to me for.");
     Background.spacer();
     Background.addQuote("Firstly, Lengths of Rope are needed to create each of them. To make a Length of Rope you must weave three Threads together. It's fairly simple really.");
     Background.spacer();
     Background.addQuote("In order to make a Makeshift Axe, you must use a Length of Rope to tie a Jagged Rock near the end of a Thick Stick.");
     Background.spacer();
     Background.addQuote("To make a Makeshift Spear, you use a Length of Rope to tie a Pointed Rock to the end of a Long Stick.");
     Background.spacer();
     Background.addQuote("And to create a Makeshift Mace, you use a Length of Rope to tie a Rounded Rock onto the end of a Thick Stick.");
     Background.spacer();
     Background.addQuote("That is your first lesson. Show me one of each weapon and we'll move onto the next lesson.");
     Background.player.quests[4]=2;
     Background.spacer();
    }
   } else if (Background.player.quests[4]==2){
    Background.addQuote("Do you have a Makeshift Axe, Makeshift Spear, and a Makeshift Mace?");
    if (Background.yesno()){
     if (Background.player.inventoryContains(1,3,1) && Background.player.inventoryContains(1,5,1) && Background.player.inventoryContains(1,6,1)){
      Background.addQuote("Ah, you did a very good job on those! Those are not the best of weapons but they serve their purpose. They break fairly easily, so will occasionally need to be replaced.");
      Background.spacer();
      Background.addQuote("Using the Makeshift Spear will boost the damage you do when attacking normally or by stabbing, and when you stab with the Makeshift Spear your target will occasionally be weakened.");
      Background.spacer();
      Background.addQuote("Weakening is an effect from stabbing that certain weapons can cause. When a combatant is weakened, all of its armor is decreased by 25%. If you have no armor, then weakening has no effect, but if you have one or more armor then at least one armor is taken away. Weakening lasts about two turns.");
      Background.spacer();
      Background.addQuote("The Makeshift Axe boosts your damage when attacking by slashing or bashing, and has a chance of causing bleeding when you slash with it, and a small chance of stunning when you use it to bash.");
      Background.spacer();
      Background.addQuote("Bleeding is an effect that can be caused by slashing with certain items. Bleeding causes a combatant to loose 25% of the damage initially done each turn for three turns, with a minimum of at least one damage per turn.");
      Background.spacer();
      Background.addQuote("The Makeshift Mace boosts your damage when attacking normally or by bashing. It also has a chance of stunning your target when you use it to bash.");
      Background.spacer();
      Background.addQuote("Stunning is an effect that is sometimes caused by bashing a combatant with certain weapons. When a combatant is stunned they are prevented from attacking or fleeing for one turn, however they may still defend, which decreases all incoming damage by a half.");
      Background.spacer();
      Background.addQuote("Every weapon is different, so be sure to look them up in the Item Info menu to see what they can do.");
      Background.spacer();
      Background.addQuote("The Item Info menu gives information about any objects that you own, including those equipped, in your inventory, or in your chest.");
      Background.spacer();
      Background.addQuote("Now that you've learned about weapons, it's time for you to try them out. Go out and kill a total of three boar and four tortoises. Boar can be found in the "+Location.identifier(10)+", and tortoises can be found in either forest.");
      Background.spacer();
      Background.addQuote("You can keep track of how many you have killed by looking at the Bestiary menu, and selecting a creature. The bestiary will also give other information such as a decription, their strengths and weaknesses, and what they tend to drop when they are defeated.");
      Background.spacer();
      Background.addQuote("The Bestiary can come in quite handy, especially when you must know how to defeat or defend against a certain enemy, or need to gather materials for crafting.");
      Background.spacer();
      Background.addQuote("Remember unless you've been stunned, you always have the ability to attempt to flee a combat. It can be a wise decision if you are outmatched in a fight.");
      Background.spacer();
      Background.addQuote("Now go and come back once you have killed three boar and four tortoises. I will be waiting.");
      Background.player.quests[4]=3;
      Background.spacer();
     } else {
      Background.addQuote("I don't see them. Come back to me when you have all three weapons in your inventory to show me.");
      Background.spacer();
     }
    } else {
     Background.addQuote("Do you need me to tell you how to make them again?");
     if (Background.yesno()){
      Background.addQuote("Firstly, Lengths of Rope are needed to create each of them. To make a Length of Rope you must weave three Threads together.");
      Background.spacer();
      Background.addQuote("In order to make a Makeshift Axe, you must use a Length of Rope to tie a Jagged Rock near the end of a Thick Stick.");
      Background.spacer();
      Background.addQuote("To make a Makeshift Spear, you use a Length of Rope to tie a Pointed Rock to the end of a Long Stick.");
      Background.spacer();
      Background.addQuote("And to create a Makeshift Mace, you use a Length of Rope to tie a Rounded Rock onto the end of a Thick Stick.");
      Background.spacer();
      Background.addQuote("Now go make one of each and show them to me.");
      Background.spacer();
     } else {
      Background.addQuote("Alright then, let me know when you've created the weapons and we'll continue.");
      Background.spacer();
     }
    }
   } else if (Background.player.quests[4]==3){
    Background.addQuote("Have you defeated at least three boar and four tortoises yet?");
    if (Background.yesno()){
     if (Background.player.bestiary[5]>=3 && Background.player.bestiary[2]>=4){
      Background.addQuote("Very good job indeed! I hope those creatures didn't give you too much trouble, for there are far stronger beasts out there.");
      Background.spacer();
      Background.addQuote("You have proven to me that you are able to take care of yourself, I believe "+new Npc(1).name+" has started asking for you.");
      Background.player.quests[4]=4;
      Background.player.quests[1]=8;
      Background.player.quests[2]=3;
      Background.spacer();
     } else {
      Background.addQuote("Ah, I don't believe that it is the truth. From my sources I've heard you've only managed to defeat "+Background.player.bestiary[5]+" boar and "+Background.player.bestiary[2]+" tortoises. Come back when you've defeated more.");
      Background.spacer();
     }
    } else {
     Background.addQuote("Boar can be found sometimes in the "+Location.identifier(10)+", and tortoises are found in either forest. Remember you can keep track of how many you've defeated by using the Bestiary.");
     Background.spacer();
    }
   } else if (Background.player.quests[4]==4){
    Background.addQuote("Keep practicing and you'll get better at combat. Make sure to upgrade your equipment as well.");
    Background.spacer();
   }
  }
  Background.changePic(1,Background.player.location);
  Background.game.update();
 }

 public int identifier(String name){
  if (name.equals("none")){
   return 0;
  } else if (name.equals("Chief")){
   return 1;
  } else if (name.equals("Lilia")){
   return 2;
  } else if (name.equals("Abul")){
   return 3;
  } else if (name.equals("Babu")){
   return 4;
  } else {
   return 0;
  }
 }

}