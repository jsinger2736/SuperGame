import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Chest{
 public ArrayList<Weapon> weapons = new ArrayList<Weapon>();
 public ArrayList<Armor> armor = new ArrayList<Armor>();
 public ArrayList<Item> items = new ArrayList<Item>();
 public ArrayList<Helmet> helmets = new ArrayList<Helmet>();
 public ArrayList<Accessory> accessories = new ArrayList<Accessory>();
 
 public Chest(){

 }

 public Chest(ArrayList<Weapon> v, ArrayList<Armor> w, ArrayList<Item> x, ArrayList<Helmet> y, ArrayList<Accessory> z){
  weapons = v;
  armor = w;
  items = x;
  helmets = y;
  accessories = z;
 }
 
}