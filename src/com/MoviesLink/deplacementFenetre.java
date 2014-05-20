package com.MoviesLink;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
 
public class deplacementFenetre extends JFrame {
	
	private JButton boutonIcon;
 
    private int posX = 0;   //Position X de la souris au clic
    private int posY = 0;   //Position Y de la souris au clic
 
    public deplacementFenetre() {
        setLocationRelativeTo(null);
        setUndecorated(true);
        setSize(500, 500);
        getContentPane().setBackground(Color.pink);
        
		JPanel statusPane = new JPanel();
		boutonIcon = new JButton("icon");
	    statusPane.add(boutonIcon);
	    this.getContentPane().add(statusPane, BorderLayout.NORTH);
         
        statusPane.addMouseListener(new MouseAdapter() {
            @Override
            //on recupere les coordonnées de la souris
            public void mousePressed(MouseEvent e) {
                posX = e.getX();    //Position X de la souris au clic
                posY = e.getY();    //Position Y de la souris au clic
            }
        });
         
        statusPane.addMouseMotionListener(new MouseMotionAdapter() {
            // A chaque deplacement on recalcul le positionnement de la fenetre
            @Override
            public void mouseDragged(MouseEvent e) {
                int depX = e.getX() - posX;
                int depY = e.getY() - posY;
                setLocation(getX()+depX, getY()+depY);
            }
        });
    }
 
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new deplacementFenetre().setVisible(true);
            }
        });
    }
 
}