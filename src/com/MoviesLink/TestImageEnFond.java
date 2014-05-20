package com.MoviesLink;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
 
public class TestImageEnFond extends JButton { // !!! on doit étendre le composant dans lequel on veut insérer une image de fond
     
    private Image img;
    private String imageName;
    private String nameVideo;
	//public Image image = getToolkit().getImage("D:/Films/Series/Arrow/Saison 1/metadata/AOW.SF1.01.zone-telechargement.com.avi.jpg");
     
    //Un constructeur pour choisir plus simplement l'image
    public TestImageEnFond(Image imageName, String videoName) {
        img = imageName;
        if(videoName.length()>19)
        {
        	nameVideo=videoName.substring(0, 19)+"...";
        }
        else{
        nameVideo = videoName;
        }
    }
     
    //On doit redéfinir la méthode paintComponent() pour les composant swing !!! et paint() pour awt
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img == null)
        	return;
        
        g.setColor(Color.WHITE);
        g.drawImage(img, 0, 0, 150, 221, this);
        g.drawString(nameVideo, 10, (getHeight()-10));
    }
 /*
    public static void main(String[] args) {
         
        //Mise en place d'une JFrame de test
       JFrame f = new JFrame("test");
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setLocationRelativeTo(null);
       f.setSize(400,225);
        
       //Creation du JButton avec une image en fond
       TestImageEnFond butTest =  new TestImageEnFond("D:\\Films\\Series\\Arrow\\Saison 1\\metadata\\AOW.SF1.01.zone-telechargement.com.avi.jpg");
        
       f.getContentPane().add(butTest);  //on attache le composant au panel par default
        
        
       f.setVisible(true);
    }
 */
}