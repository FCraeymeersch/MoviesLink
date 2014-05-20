package com.MoviesLink;

import javax.swing.*; 

import java.awt.*; 
import java.awt.event.*;
  
   public class ExempleBouton 
   {
   
      public static void main (String args[]) 
      {
         MaFenetre fen = new MaFenetre(); 
         fen.setVisible(true);
         
         fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      } 
   }
   
   class MaFenetre extends JFrame implements ActionListener 
   { 
      private JButton boutonQuitter;
   
      public MaFenetre() // le constructeur
      { 
  		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		this.setLocationRelativeTo(null);
         setTitle ("EXEMPLE BOUTON QUITTER"); 
         setSize (300, 200) ; 
         getContentPane().setLayout(new FlowLayout());
      
         boutonQuitter = new JButton("QUITTER"); 
         getContentPane().add(boutonQuitter); 
         boutonQuitter.addActionListener(this);
      
      
      }
   
      public void actionPerformed( ActionEvent ev )
      {
         Object ae = ev.getSource();
         if(ae == boutonQuitter) 
         {
            //System.out.println("Traitement de FIN");
            System.exit(0);
         } 
      }
   }