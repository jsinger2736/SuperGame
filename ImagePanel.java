import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ImagePanel extends JPanel{
 public Image img;

 public ImagePanel(Image img){
  this.img = img;
 }
 public void paintComponent(Graphics g){
  super.paintComponent(g);
  g.drawImage(img,0,0,this);
 }
}