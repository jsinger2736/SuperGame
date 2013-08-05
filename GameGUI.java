import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;

public class GameGUI extends JFrame{
 private JMenuBar menubar;
 private JMenu file, info, extra;
 private JMenuItem newgame, savegame, loadgame, exit, gameinfo, creatureinfo, iteminfo, hint;
 private JPanel north, west, innerwest, /*westgrid,*/ east, innereast, eastgrid, south, southgrid, southtop, southbot, blah;
 public JPanel fullPicture;
 private JLabel location, charname, charlevel, charhealth, charmana/*, enemname, enemlevel, enemhealth, enemmana*/;
 private JLabel[] enemname, enemlevel, enemhealth;
 private JPanel[] westgrid;
 private JButton stats, inventory;
 private JButton[] choices;
 public JTextArea centerTA;
 private JScrollPane centerSP, enemSP, pictureSP;
 public static ImagePanel picture;
 private JSplitPane centerSplit;
 private MenuHandler menuHandler;
 private ButtonHandler buttonHandler;
 private StatsGUI statsGUI;
 public InventoryGUI inventoryGUI;
 private BestiaryGUI bestiaryGUI;
 private ItemInfoGUI itemInfoGUI;
 private static boolean b;

 public GameGUI(){
  Container container = getContentPane();
  container.setLayout(new BorderLayout());

  //creating the menubar
  menubar = new JMenuBar();
  menuHandler = new MenuHandler();
  file = new JMenu("File");
  newgame = new JMenuItem("New Game");
  newgame.addActionListener(menuHandler);
  savegame = new JMenuItem("Save Game");
  savegame.addActionListener(menuHandler);
  loadgame = new JMenuItem("Load Game");
  loadgame.addActionListener(menuHandler);
  exit = new JMenuItem("Exit");
  exit.addActionListener(menuHandler);
  //file.add(newgame);
  file.add(savegame);
  //file.add(loadgame);
  file.add(exit);
  info = new JMenu("Info");
  gameinfo = new JMenuItem("Game Info");
  gameinfo.addActionListener(menuHandler);
  creatureinfo = new JMenuItem("Bestiary");
  creatureinfo.addActionListener(menuHandler);
  iteminfo = new JMenuItem("Item Info");
  iteminfo.addActionListener(menuHandler);
  info.add(gameinfo);
  info.add(creatureinfo);
  info.add(iteminfo);
  extra = new JMenu("Extra");
  hint = new JMenuItem("Hint");
  hint.addActionListener(menuHandler);
  extra.add(hint);
  menubar.add(file);
  menubar.add(info);
  menubar.add(extra);
  setJMenuBar(menubar);

  //create north panel, gives location
  north = new JPanel();
  north.setLayout(new FlowLayout());
  location = new JLabel("CurrentLocation");
  north.add(new JLabel("Location: ",SwingConstants.RIGHT));
  north.add(location);
  container.add(north, BorderLayout.NORTH);

  //create east panel
  east = new JPanel();
  innereast = new JPanel();
  innereast.setLayout(new BoxLayout(innereast, BoxLayout.Y_AXIS));
  charname = new JLabel("PlayerName");
  charlevel = new JLabel("PlayerLevel");
  charname.setBorder(BorderFactory.createEmptyBorder(12,0,8,0));
  charlevel.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
  eastgrid = new JPanel();
  eastgrid.setLayout(new GridLayout(1,2,5,8));
  charhealth = new JLabel("CharHealth");
  //charmana = new JLabel("CharMana");
  eastgrid.add(new JLabel("Health: ",SwingConstants.RIGHT));
  eastgrid.add(charhealth);
  //eastgrid.add(new JLabel("Mana: ",SwingConstants.RIGHT));
  //eastgrid.add(charmana);
  charname.setAlignmentX(Component.CENTER_ALIGNMENT);
  charlevel.setAlignmentX(Component.CENTER_ALIGNMENT);
  innereast.add(charname);
  innereast.add(charlevel);
  innereast.add(eastgrid);
  east.add(innereast);
  container.add(east, BorderLayout.EAST);

  //create west panel
  west = new JPanel();
  innerwest = new JPanel();
  enemSP = new JScrollPane(west,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  enemSP.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
  /*
  innerwest.setLayout(new BoxLayout(innerwest, BoxLayout.Y_AXIS));
  enemname = new JLabel("EnemyName");
  enemlevel = new JLabel("EnemyLevel");
  enemname.setBorder(BorderFactory.createEmptyBorder(5,0,8,0));
  enemlevel.setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
  westgrid = new JPanel();
  westgrid.setLayout(new GridLayout(2,2,5,8));
  enemhealth = new JLabel("EnemyHealth");
  enemmana = new JLabel("EnemyMana");
  westgrid.add(new JLabel("Health: ",SwingConstants.RIGHT));
  westgrid.add(enemhealth);
  westgrid.add(new JLabel("Mana: ",SwingConstants.RIGHT));
  westgrid.add(enemmana);
  enemname.setAlignmentX(Component.CENTER_ALIGNMENT);
  enemlevel.setAlignmentX(Component.CENTER_ALIGNMENT);
  //eastgrid.setAlignmentX(Component.CENTER_ALIGNMENT);
  innerwest.add(enemname);
  innerwest.add(enemlevel);
  innerwest.add(westgrid);*/
  //blah = new JPanel();
  //blah.setSize(new Dimension(100,100));
  //west.add(blah);
  
  innerwest.setBorder(BorderFactory.createEmptyBorder(0,45,0,45));
  west.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
  west.add(innerwest);
  container.add(enemSP, BorderLayout.WEST);

  //create panel for multiple creatures

  //create center panel
  try{
   picture = new ImagePanel(ImageIO.read(new File("pictures/Village Square.jpg")));
  } catch (IOException e){
  }
  fullPicture = new JPanel();
  fullPicture.setLayout(new BorderLayout());
  fullPicture.add(picture, BorderLayout.CENTER);
  pictureSP = new JScrollPane(fullPicture,
   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
   JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
  pictureSP.setPreferredSize(new Dimension(450,203));
  centerTA = new JTextArea(6,30);
  centerTA.setEditable(false);
  centerTA.setLineWrap(true);
  centerTA.setWrapStyleWord(true);
  centerSP = new JScrollPane(centerTA,
   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
   JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  centerSP.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5,10,10,10), BorderFactory.createLineBorder(Color.gray)));
  centerSP.setPreferredSize(new Dimension(450,125));
  centerSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,pictureSP,centerSP);
  container.add(centerSplit, BorderLayout.CENTER);

  //create south panel
  south = new JPanel();
  buttonHandler = new ButtonHandler();
  south.setLayout(new BoxLayout(south, BoxLayout.Y_AXIS));
  southtop = new JPanel();
  stats = new JButton("Stats");
  stats.addActionListener(buttonHandler);
  inventory = new JButton("Inventory");
  inventory.addActionListener(buttonHandler);
  southtop.add(stats);
  southtop.add(inventory);
  southtop.setBorder(BorderFactory.createEmptyBorder(0,0,7,0));
  southgrid = new JPanel();
  southgrid.setLayout(new GridLayout(3,4,5,5));
  choices = new JButton[12];
  for (int i=0; i<12; i++){
   choices[i] = new JButton(" ");
   choices[i].addActionListener(buttonHandler);
   southgrid.add(choices[i]);
  }
  south.add(southtop);
  south.add(southgrid);
  south.setBorder(BorderFactory.createEmptyBorder(0,40,10,40)); //0,80,10,80
  container.add(south, BorderLayout.SOUTH);

  //set title, and housekeeping such  
  setTitle("Game");
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  pack();
  setLocationRelativeTo(null);
  update();
  west.setSize(new Dimension(100,50));
  setVisible(true);
 }

 private class MenuHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==newgame){
    //System.out.println("newgame");
    makeMessage("New Game","This function is currently unavailable.");
   } else if (source==savegame){
    //System.out.println("savegame");
    Background.player.save();
   } else if (source==loadgame){
    //System.out.println("loadgame");
    makeMessage("Load Game","This function is currently unavailable.");
   } else if (source==exit){
    System.exit(0);
   } else if (source==gameinfo){
    //System.out.println("gameinfo");
    makeMessage("Game Info","This game is currently untitled.\nIt was begun on 4/19/13, and is still a work in progress.\nBy Jimmy Singer.");
   } else if (source==creatureinfo){
    //System.out.println("creatureinfo");
    //makeMessage("Bestiary","This function is currently unavailable.");
    if (bestiaryGUI==null){
     bestiaryGUI = new BestiaryGUI();
    } else {
     bestiaryGUI.setVisible(true);
    }
   } else if (source==iteminfo){
    //System.out.println("iteminfo");
    //makeMessage("Item Info","This function is currently unavailable.");
    if (itemInfoGUI==null){
     itemInfoGUI = new ItemInfoGUI();
    } else {
     itemInfoGUI.setVisible(true);
    }
   } else if (source==hint){
    //System.out.println("hint");
    makeMessage("Hint","This function is currently unavailable.");
   }
   update();
  }
 }

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==stats){
    //System.out.println(" stats");
    if (statsGUI==null){
     statsGUI = new StatsGUI();
    } else {
     statsGUI.setVisible(true);
    }
   } else if (source==inventory){
    //System.out.println(" inventory");
    if (inventoryGUI==null){
     inventoryGUI = new InventoryGUI();
    } else {
     inventoryGUI.setVisible(true);
    }
   }
   for (int i=0; i<12; i++){
    if (source==choices[i]){
     //System.out.println(" button "+(i+1));
     Background.buttonClicked(i+1);
    }
   }
   update();
  }
 }

 public void setButton(int numbah, String newbutton){
  for (int i=0; i<12; i++){
   if (numbah==i+1){
    choices[i].setText(newbutton);
   }
  }
 }

 public void makeMessage(String title, String message){
  JOptionPane.showMessageDialog(null, message, title, 1);
 }

 public void update(){
  location.setText(Location.identifier(Background.player.location));
  charname.setText(Background.player.name);
  charlevel.setText("Level "+Background.player.level);
  charhealth.setText(Background.player.health+"/"+Background.player.maxhealth);
  //charmana.setText(Background.player.mana+"/"+Background.player.maxmana);
  try{
   //statsGUI.
   statsGUI.name.setText(Background.player.name);
   statsGUI.level.setText(Background.player.level+"");
   statsGUI.experience.setText(Background.player.experience+"/"+Background.player.experiencetolevel);
   statsGUI.health.setText(Background.player.health+"/"+Background.player.maxhealth);
   statsGUI.mana.setText(Background.player.mana+"/"+Background.player.maxmana);
   statsGUI.equippedWeapon.setText(Background.player.equippedWeapon.name);
   statsGUI.equippedArmor.setText(Background.player.equippedArmor.name);
   statsGUI.equippedHelmet.setText(Background.player.equippedHelmet.name);
   statsGUI.equippedAccessory.setText(Background.player.equippedAccessory.name);
   statsGUI.speed.setText(Background.player.speed+Background.player.equippedAccessory.speedBoost+"");
   statsGUI.normaldam.setText(Background.player.damageby(1)+"");
   statsGUI.slashdam.setText(Background.player.damageby(2)+"");
   statsGUI.stabdam.setText(Background.player.damageby(3)+"");
   statsGUI.bashdam.setText(Background.player.damageby(4)+"");
   double xyz;
   if (Background.player.weakened>0){
    xyz = .75;
   } else {
    xyz = 1;
   }
   statsGUI.normalarm.setText((int)((Background.player.normalarmor+Background.player.equippedArmor.normalarmor+Background.player.equippedWeapon.normalarmor+Background.player.equippedHelmet.normalarmor)*xyz)+"");
   statsGUI.slasharm.setText((int)((Background.player.slasharmor+Background.player.equippedArmor.slasharmor+Background.player.equippedWeapon.slasharmor+Background.player.equippedHelmet.slasharmor)*xyz)+"");
   statsGUI.stabarm.setText((int)((Background.player.stabarmor+Background.player.equippedArmor.stabarmor+Background.player.equippedWeapon.stabarmor+Background.player.equippedHelmet.stabarmor)*xyz)+"");
   statsGUI.basharm.setText((int)((Background.player.basharmor+Background.player.equippedArmor.basharmor+Background.player.equippedWeapon.basharmor+Background.player.equippedHelmet.basharmor)*xyz)+"");
   statsGUI.accuracy.setText(Background.player.accuracy+"%");
   statsGUI.dodge.setText((double)Math.round((Background.player.dodge+Background.player.equippedAccessory.dodgeBoost)*1000)/1000+"%");
   statsGUI.pack();
  } catch(Exception e) {
  }
  try{
   int x = Combat.x;
   if (x==0){
    b=false;
   }
   if (x>0 && !b){
    innerwest.removeAll();
    innerwest.setBorder(BorderFactory.createEmptyBorder(0,0,0,3));
    enemname = new JLabel[x];
    //enemlevel = new JLabel[x];
    enemhealth = new JLabel[x];
    westgrid = new JPanel[x];
    innerwest.setLayout(new BoxLayout(innerwest, BoxLayout.Y_AXIS));
    for (int i=0; i<x; i++){
     enemname[i] = new JLabel(Combat.creature[i].name);
     //enemlevel[i] = new JLabel("Level "+Combat.creature[i].level);
     enemname[i].setBorder(BorderFactory.createEmptyBorder(12,0,8,0));
     //enemlevel[i].setBorder(BorderFactory.createEmptyBorder(0,0,8,0));
     westgrid[i] = new JPanel();
     westgrid[i].setLayout(new GridLayout(1,2,5,8));
     enemhealth[i] = new JLabel(Combat.creature[i].health+"/"+Combat.creature[i].maxhealth);
     //enemmana = new JLabel("EnemyMana");
     westgrid[i].add(new JLabel("Health: ",SwingConstants.RIGHT));
     westgrid[i].add(enemhealth[i]);
     westgrid[i].setBorder(BorderFactory.createEmptyBorder(0,7,10,0));
     //westgrid.add(new JLabel("Mana: ",SwingConstants.RIGHT));
     //westgrid.add(enemmana);
     enemname[i].setAlignmentX(Component.CENTER_ALIGNMENT);
     //enemlevel[i].setAlignmentX(Component.CENTER_ALIGNMENT);
     innerwest.add(enemname[i]);
     //innerwest.add(enemlevel[i]);
     innerwest.add(westgrid[i]);
    }
    innerwest.revalidate();
    innerwest.repaint();
    enemSP.revalidate();
    enemSP.repaint();
    b=true;
   } else if (b){
    for (int i=0; i<x; i++){
     enemname[i].setText(Combat.creature[i].name);
     //enemlevel[i].setText("Level "+Combat.creature[i].level);
     enemhealth[i].setText(Combat.creature[i].health+"/"+Combat.creature[i].maxhealth);
    }
   } else {
    innerwest.removeAll();
    innerwest.setBorder(BorderFactory.createEmptyBorder(0,51,0,45));
   }
  } catch(Exception e){
   innerwest.removeAll();
   e.printStackTrace();
  }
  try{
   inventoryGUI.updateInventory();
  } catch(Exception e) {
  }
  try{
   Background.player.storeGUI.updateStore();
  } catch(Exception e) {
  }
  try{
   bestiaryGUI.updateBestiary();
  } catch(Exception e) {
  }
  try{
   itemInfoGUI.updateItemInfo();
  } catch(Exception e) {
  }
  try{
   Background.player.chestGUI.updateChest();
  } catch(Exception e){
  }
  try{
   if (!Background.combat){
    fullPicture.removeAll();
    fullPicture.setLayout(new BorderLayout());
    JPanel breeeh;
    breeeh = new JPanel();
    breeeh.setPreferredSize(new Dimension(130,200));
    fullPicture.add(breeeh,BorderLayout.WEST);
    fullPicture.add(picture,BorderLayout.CENTER);
    fullPicture.revalidate();
    pictureSP.setViewportView(fullPicture);
   } else {
   }
  } catch(Exception e){
  }
 }

 public static void main(String[] args){
  new GameGUI();
 }
}




























