import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LevelGUI extends JFrame{
 JPanel center;
 JLabel question;
 JButton health, speed, normaldamage, slashdamage, stabdamage, bashdamage, normalarmor, slasharmor, stabarmor, basharmor, /*accuracy,*/ dodge;
 ButtonHandler buttonHandler;
 
 public LevelGUI(){
  Container container = getContentPane();

  center = new JPanel();
  center.setBorder(BorderFactory.createEmptyBorder(5,20,10,20));

  buttonHandler = new ButtonHandler();

  question = new JLabel("     Select one stat to increase.     ");
  question.setAlignmentX(Component.CENTER_ALIGNMENT);
  health = new JButton("Health +2");
  health.setAlignmentX(Component.CENTER_ALIGNMENT);
  health.addActionListener(buttonHandler);
  speed = new JButton("Speed +1");
  speed.setAlignmentX(Component.CENTER_ALIGNMENT);
  speed.addActionListener(buttonHandler);
  normaldamage = new JButton("normal Damage +1");
  normaldamage.setAlignmentX(Component.CENTER_ALIGNMENT);
  normaldamage.addActionListener(buttonHandler);
  slashdamage = new JButton("Slash Damage +2");
  slashdamage.setAlignmentX(Component.CENTER_ALIGNMENT);
  slashdamage.addActionListener(buttonHandler);
  stabdamage = new JButton("Stab Damage +2");
  stabdamage.setAlignmentX(Component.CENTER_ALIGNMENT);
  stabdamage.addActionListener(buttonHandler);
  bashdamage = new JButton("Bash Damage +2");
  bashdamage.setAlignmentX(Component.CENTER_ALIGNMENT);
  bashdamage.addActionListener(buttonHandler);
  normalarmor = new JButton("normal Armor +1");
  normalarmor.setAlignmentX(Component.CENTER_ALIGNMENT);
  normalarmor.addActionListener(buttonHandler);
  slasharmor = new JButton("Slash Armor +2");
  slasharmor.setAlignmentX(Component.CENTER_ALIGNMENT);
  slasharmor.addActionListener(buttonHandler);
  stabarmor = new JButton("Stab Armor +2");
  stabarmor.setAlignmentX(Component.CENTER_ALIGNMENT);
  stabarmor.addActionListener(buttonHandler);
  basharmor = new JButton("Bash Armor +2");
  basharmor.setAlignmentX(Component.CENTER_ALIGNMENT);
  basharmor.addActionListener(buttonHandler);
  /*accuracy = new JButton("Accuracy +.... */
  dodge = new JButton("Dodge +2%");
  dodge.setAlignmentX(Component.CENTER_ALIGNMENT);
  dodge.addActionListener(buttonHandler);
  //startup();
  /*center.add(question);
  center.add(health);
  if (Background.player.level%3==0){
   center.add(speed);
  }
  center.add(basedamage);
  if (Background.player.level%2==0){
   center.add(slashdamage);
   center.add(stabdamage);
   center.add(bashdamage);
  }
  if (Background.player.level%2+1==0){
   center.add(basearmor);
   center.add(slasharmor);
   center.add(stabarmor);
   center.add(basharmor);
  }
  if (Background.player.level%3==0){
   center.add(dodge);
  }*/
  container.add(center, BorderLayout.CENTER);
 }

 public void startup(){
  int y = 0;
  center.removeAll();
  center.add(question);
  center.add(health);
  y = 2;
  if (Background.player.level%3==0){
   center.add(speed);
   y++;
  }
  center.add(normaldamage);
  y++;
  if (Background.player.level%2==0){
   center.add(slashdamage);
   center.add(stabdamage);
   center.add(bashdamage);
   y = y+3;
  }
  if ((Background.player.level+1)%2==0){
   center.add(normalarmor);
   center.add(slasharmor);
   center.add(stabarmor);
   center.add(basharmor);
   y = y+4;
  }
  if (Background.player.level%3==0){
   center.add(dodge);
   y++;
  }
  center.setLayout(new GridLayout(y,1,15,10));
  setTitle("Level Up");
  revalidate();
  repaint();
  pack();
  setLocationRelativeTo(null);
  setVisible(true);
 }

 private class ButtonHandler implements ActionListener{
  public void actionPerformed(ActionEvent e){
   Object source = e.getSource();
   if (source==health){
    System.out.println("health+2");
    Background.player.maxhealth=Background.player.maxhealth+2;
    Background.player.health=Background.player.health+2;
   } else if (source==speed){
    System.out.println("speed+1");
    Background.player.speed=Background.player.speed+1;
   } else if (source==normaldamage){
    System.out.println("all damage+1");
    Background.player.normaldamage=Background.player.normaldamage+1;
   } else if (source==slashdamage){
    System.out.println("slashdamage+2");
    Background.player.slashdamage=Background.player.slashdamage+2;
   } else if (source==stabdamage){
    System.out.println("stabdamage+2");
    Background.player.stabdamage=Background.player.stabdamage+2;
   } else if (source==bashdamage){
    System.out.println("bashdamage+2");
    Background.player.bashdamage=Background.player.bashdamage+2;
   } else if (source==normalarmor){
    System.out.println("normalarmor+1");
    Background.player.normalarmor=Background.player.normalarmor+1;
   } else if (source==slasharmor){
    System.out.println("slasharmor+2");
    Background.player.slasharmor=Background.player.slasharmor+2;
   } else if (source==stabarmor){
    System.out.println("stabarmor+2");
    Background.player.stabarmor=Background.player.stabarmor+2;
   } else if (source==basharmor){
    System.out.println("basharmor+2");
    Background.player.basharmor=Background.player.basharmor+2;
   } else if (source==dodge){
    System.out.println("dodge+2%");
    Background.player.dodge=Background.player.dodge+.02;
   }
   Background.player.go=false;
   Background.game.update();
   setVisible(false);
  }
 }
}