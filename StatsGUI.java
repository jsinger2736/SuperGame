import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class StatsGUI extends JFrame{
 private JPanel grid, north;
 JLabel name, level, experience, health, mana, equippedWeapon, equippedArmor, equippedHelmet, equippedAccessory, speed, normaldam, slashdam, stabdam, bashdam, normalarm, slasharm, stabarm, basharm, accuracy, dodge;

 public StatsGUI(){
  Container container = getContentPane();
  
  grid = new JPanel();
  grid.setLayout(new GridLayout(18,2,5,6));
  level = new JLabel("playerlevel");
  experience = new JLabel("exp/totalexp");
  health = new JLabel("health/totalhealth");
  mana = new JLabel("mana/totalmana");
  equippedWeapon = new JLabel("weapon");
  equippedArmor = new JLabel("armor");
  equippedHelmet = new JLabel("helmet");
  equippedAccessory = new JLabel("accessory");
  speed = new JLabel("speed");
  normaldam = new JLabel("normaldamage");
  slashdam = new JLabel("slashdamage");
  stabdam = new JLabel("stabdamage");
  bashdam = new JLabel("bashdamage");
  normalarm = new JLabel("normalarmor");
  slasharm = new JLabel("slasharmor");
  stabarm = new JLabel("stabarmor");
  basharm = new JLabel("basharmor");
  accuracy = new JLabel("accuracy");
  dodge = new JLabel("dodge %");
  grid.add(new JLabel("Level: ", SwingConstants.LEFT));
  grid.add(level);
  grid.add(new JLabel("Experience: ", SwingConstants.LEFT));
  grid.add(experience);
  grid.add(new JLabel("Health: ", SwingConstants.LEFT));
  grid.add(health);
  //grid.add(new JLabel("Mana: ", SwingConstants.LEFT));
  //grid.add(mana);
  grid.add(new JLabel("Equipped Weapon: ", SwingConstants.LEFT));
  grid.add(equippedWeapon);
  grid.add(new JLabel("Equipped Armor: ", SwingConstants.LEFT));
  grid.add(equippedArmor);
  grid.add(new JLabel("Equipped Helmet: ", SwingConstants.LEFT));
  grid.add(equippedHelmet);
  grid.add(new JLabel("Equipped Accessory: ", SwingConstants.LEFT));
  grid.add(equippedAccessory);
  grid.add(new JLabel("Speed: ", SwingConstants.LEFT));
  grid.add(speed);
  grid.add(new JLabel("Normal Damage: ", SwingConstants.LEFT));
  grid.add(normaldam);
  grid.add(new JLabel("Slash Damage: ", SwingConstants.LEFT));
  grid.add(slashdam);
  grid.add(new JLabel("Stab Damage: ", SwingConstants.LEFT));
  grid.add(stabdam);
  grid.add(new JLabel("Bash Damage: ", SwingConstants.LEFT));
  grid.add(bashdam);
  grid.add(new JLabel("Normal Armor: ", SwingConstants.LEFT));
  grid.add(normalarm);
  grid.add(new JLabel("Slash Armor: ", SwingConstants.LEFT));
  grid.add(slasharm);
  grid.add(new JLabel("Stab Armor: ", SwingConstants.LEFT));
  grid.add(stabarm);
  grid.add(new JLabel("Bash Armor: ", SwingConstants.LEFT));
  grid.add(basharm);
  grid.add(new JLabel("Accuracy: ", SwingConstants.LEFT));
  grid.add(accuracy);
  grid.add(new JLabel("Dodge: ", SwingConstants.LEFT));
  grid.add(dodge);
  grid.setBorder(BorderFactory.createEmptyBorder(0,5,10,10));
  north = new JPanel();
  name = new JLabel("PlayerName", SwingConstants.RIGHT);
  north.add(name);
  container.add(north, BorderLayout.NORTH);
  container.add(grid, BorderLayout.CENTER);

  setTitle("Stats");
  pack();
  setLocationRelativeTo(null);
  Background.game.update();
  setVisible(true);
 }

}