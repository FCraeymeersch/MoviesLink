/*import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class MenuVideo extends JFrame {

	public MenuVideo(){
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(400, 500);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
	    //this.setContentPane(new Panneau());     
	    this.setContentPane(buildContent(""));   
	    this.setVisible(true);
	}
	
	public JPanel buildContent(String Fichier){
		String Files = "";
		System.out.println(Fichier.isEmpty());
		if(!Fichier.isEmpty()){Files=Fichier;}
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		final String[] listeFichiers; 
		File repertoire = new File("./Test/" +Files); 
		listeFichiers = repertoire.list();
		
		

		
		JButton[] tabButton = new JButton[listeFichiers.length] ;
		//String[] tabButtonLabels = {"toto", "tata", "", "titi"};
		for (int j=0 ; j<tabButton.length ; j++){
			final String nomFichier = listeFichiers[j];
			if(new File("./Test/"+nomFichier).isDirectory()){
				tabButton[j] = new JButton(nomFichier) ;
				panel.add(tabButton[j]);
				
			    tabButton[j].addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
					    //statusLabel.setText("Ok Button clicked.");
				    	Dossier dossier = new Dossier(nomFichier);
				    	dispose();
				    }       
			    });
			}
		}		 
		return panel;
	}
}*/
package com.MoviesLink;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame implements ActionListener{
	
	Locale locale = new Locale("fr", "FR");
	ResourceBundle table = ResourceBundle.getBundle ("com.MoviesLink.lang.language", locale);
 
    private int posX = 0;   //Position X de la souris au clic
    private int posY = 0;   //Position Y de la souris au clic

	private JButton boutonSetting;
	
    ImageIcon maximize;
    ImageIcon maximizeOver;
    ImageIcon restore;
    ImageIcon restoreOver;
	
	private JButton boutonMinimize;
	private JButton boutonMaximize;
	private JButton boutonQuitter;
	
	public JButton boutonRetour;
    public String dossier;

    public int component;
    
    MainFrame main=this;

	String fichier;
	public CardLayout cl = new CardLayout();
	JPanel globalContent = new JPanel();
	JPanel menu = new JPanel();
	JPanel content = new JPanel();
	
	JPanel settings;
    JPanel listVideo; 
	

    
	//Liste des noms de nos conteneurs pour la pile de cartes
	int indice = 0;

	public MainFrame(){	    

		/*
		 * définition des propriétés de la fenêtre
		 */
		this.setTitle("CardLayout");
		this.setSize(1000, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setAlwaysOnTop(true);
		this.setExtendedState(this.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		/*
		 * fin propriétés
		 */
		
		/*
		 * début panel - barre de titre + bouton "Quitter"
		 */
		// status bar
		JPanel statusPane = new JPanel();
		statusPane.setLayout(new BorderLayout());
		statusPane.setBackground(new Color(83,83,83));
		
		// espace icone et titre
		JPanel iconPane = new JPanel();
		iconPane.setLayout(new BoxLayout(iconPane, BoxLayout.LINE_AXIS));
		iconPane.setBorder(BorderFactory.createEmptyBorder());
		iconPane.setBackground(new Color(83,83,83));
		Image image = null;
		try {
			image = ImageIO.read(new File("./ressources/logo_low.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel viewer = new JLabel(new ImageIcon(image));
		JLabel title = new JLabel("   Movies'Link");
		title.setForeground(Color.WHITE);
		iconPane.add(viewer);
		iconPane.add(title);
		// fin icone et titre
	    
		// espace boutons
		JPanel boutonPane = new JPanel();
		boutonPane.setLayout(new BoxLayout(boutonPane, BoxLayout.LINE_AXIS));
		boutonPane.setBorder(BorderFactory.createEmptyBorder());
		
		// définition du bouton "Quitter"
	    boutonQuitter = new JButton(""); 
	    boutonQuitter.setBorder(BorderFactory.createEmptyBorder());// suppression des bords du bouton
	    ImageIcon quitter = new ImageIcon( "./ressources/quitter.png" );// définition de l'image de background
	    ImageIcon quitterOver = new ImageIcon( "./ressources/quitterOver.png" );// définition de l'image du rollover
	    // application des icones sur le bouton
	    boutonQuitter.setIcon(quitter);
	    boutonQuitter.setRolloverIcon(quitterOver);
	    boutonQuitter.setPressedIcon(quitterOver);
	    
	    // définition du bouton Minimize
	    boutonMinimize = new JButton(""); 
	    boutonMinimize.setBorder(BorderFactory.createEmptyBorder());// suppression des bords du bouton
	    ImageIcon minimize = new ImageIcon( "./ressources/minimize.png" );// définition de l'image de background
	    ImageIcon minimizeOver = new ImageIcon( "./ressources/minimizeOver.png" );// définition de l'image du rollover
	    // application des icones sur le bouton
	    boutonMinimize.setIcon(minimize);
	    boutonMinimize.setRolloverIcon(minimizeOver);
	    boutonMinimize.setPressedIcon(minimizeOver);
	    
	    // définition du bouton Maximize
	    boutonMaximize = new JButton(""); 
	    boutonMaximize.setBorder(BorderFactory.createEmptyBorder());// suppression des bords du bouton
	    maximize = new ImageIcon( "./ressources/maximize.png" );// définition de l'image de background
	    maximizeOver = new ImageIcon( "./ressources/maximizeOver.png" );// définition de l'image du rollover
	    restore = new ImageIcon( "./ressources/restore.png" );// définition de l'image de background
	    restoreOver = new ImageIcon( "./ressources/restoreOver.png" );// définition de l'image du rollover
	    // application des icones sur le bouton
	    boutonMaximize.setIcon(restore);
	    boutonMaximize.setRolloverIcon(restoreOver);
	    boutonMaximize.setPressedIcon(restoreOver);
	    
	    //ajout des boutons au jpanel "boutonPane"
	    boutonPane.add(boutonMinimize);
	    //boutonPane.add(boutonMaximize); 
	    boutonPane.add(boutonQuitter);
	    	    
	    // Ajout du jpanel "boutonPane" à la "StatusPane" 
	    statusPane.add(iconPane, BorderLayout.WEST); 
	    statusPane.add(boutonPane, BorderLayout.EAST); 
	    
	    // Action des boutons
	    boutonMinimize.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent ae)
	    	{
	    		setState(ICONIFIED);
	    	}
	    });
	    boutonMaximize.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent ae)
	    	{
		    	if(getExtendedState()==NORMAL)
		    	{
		    		setExtendedState(MAXIMIZED_BOTH);
			    	boutonMaximize.setIcon(restore);
			    	boutonMaximize.setRolloverIcon(restoreOver);
		    		repaint();
		    	}
		    	else
		    	{
		    		setExtendedState(NORMAL);
			    	boutonMaximize.setIcon(maximize);
			    	boutonMaximize.setRolloverIcon(maximizeOver);
		    		repaint();
		    	}
	    	}
	    });
	    boutonQuitter.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent ae)
		      {
			       System.exit(0);
		      }
		});
        
       statusPane.addMouseListener(new MouseAdapter() {
           @Override
           //on recupere les coordonnées de la souris
           public void mousePressed(MouseEvent e) {
        	   
        	   if(e.getClickCount()==2 && getExtendedState()==NORMAL)
        	   {
        		   setExtendedState(MAXIMIZED_BOTH);
			    	boutonMaximize.setIcon(restore);
			    	boutonMaximize.setRolloverIcon(restoreOver);
		    		repaint();
        	   }
        	   else if(e.getClickCount()<2 && getExtendedState()==NORMAL){
        		   posX = e.getX();    //Position X de la souris au clic
	               posY = e.getY();    //Position Y de la souris au clic
        	   }
           }
       });
       
      statusPane.addMouseMotionListener(new MouseMotionAdapter() {
          // A chaque deplacement on recalcul le positionnement de la fenetre
    	  @Override
    	  public void mouseDragged(MouseEvent e) {
    		  if(getExtendedState()==NORMAL)
    		  {
	              int depX = e.getX() - posX;
	              int depY = e.getY() - posY;
	              setLocation(getX()+depX, getY()+depY);
    		  }
    		  else{}
          }
      });
	    
	    /*
	     * Fin barre titre
	     */
      
      /*
       * Footer
       */

		
		// espace footer
		JPanel footerPane = new JPanel();
		footerPane.setLayout(new BorderLayout());
		footerPane.setBackground(new Color(83,83,83));
		
		// espace icone setting
		JPanel settingPane = new JPanel();
		settingPane.setLayout(new BoxLayout(settingPane, BoxLayout.LINE_AXIS));
		settingPane.setBorder(BorderFactory.createEmptyBorder());
		settingPane.setBackground(new Color(83,83,83));
		
		// icone setting
		boutonSetting = new JButton(""); 
		boutonSetting.setBackground(new Color(83,83,83));
	    boutonSetting.setBorder(BorderFactory.createEmptyBorder());// suppression des bords du bouton
	    
	    ImageIcon param = new ImageIcon( "./ressources/settings.png" );// définition de l'image de background
	    ImageIcon paramOver = new ImageIcon( "./ressources/settingsOver.png" );// définition de l'image du rollover
	    
	    // application des icones sur le bouton
	    boutonSetting.setIcon(param);
	    boutonSetting.setRolloverIcon(paramOver);
	    boutonSetting.setPressedIcon(paramOver);
	    
	    settingPane.add(boutonSetting);
	    
	    boutonSetting.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		content.removeAll();
	    		content.add(settings, BorderLayout.CENTER);
	    		content.revalidate();
	    	}
	    });
	    
	    footerPane.add(settingPane, BorderLayout.WEST); 
	    
	    /*
	     * Fin Footer
	     */
		
	    
	    /*
	     * Jpanel Dossier "Séries"
	     */
	    settings = new Settings(this, "Dossier 1");
	    listVideo = new ListVideo(this, (new File("D:/Films")), 1);

	    /*
	     * Définition du central content
	     */
	    content.setLayout(new BorderLayout());
	    content.add(listVideo,BorderLayout.CENTER);
	    content.setBackground(new Color(83,83,83));
	    /*
	     * Définition du Jpanel global Central
	     */
	    globalContent.setLayout(new BorderLayout());
	    globalContent.add(menu, BorderLayout.NORTH);
	    globalContent.add(content, BorderLayout.CENTER);
	    globalContent.setBackground(new Color(83,83,83));
	    
	    menu.setLayout(new BoxLayout(menu, BoxLayout.LINE_AXIS));
		menu.setBackground(new Color(83,83,83));
		
		
	    boutonRetour = new JButton("Retour");
	    //boutonRetour.setVisible(false);
	    
        boutonRetour.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		System.out.println("Dossier "+dossier);
	    	    
	    	    if(/*dossier.equals("D:/Films") || */dossier.equals("D:") || dossier.equals(null)){
	    	    	boutonRetour.setVisible(false);
	    	    	main.repaint();
	    	    	main.revalidate();
	    	    }else {
	    	    	boutonRetour.setVisible(true);
	    	    	main.repaint();
	    	    	main.revalidate();
	        	}

	    		content.removeAll();
	    		content.add(new ListVideo(main, new File(dossier), 1), BorderLayout.CENTER);
	    		content.revalidate();
	    		
	    	}
	    });
        
	    menu.add(boutonRetour);
	    
	    

	    
	    // lecture du fichier config
		//ReadXMLFile.main(null);
	
	    this.getContentPane().add(statusPane, BorderLayout.NORTH);
	    this.getContentPane().add(globalContent, BorderLayout.CENTER);
	    this.getContentPane().add(footerPane, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
	/*
	 * Début Player video
	 */
	public void Player(String string){
    		Runtime runtime = Runtime.getRuntime();
    		try {
    			Process p = runtime.exec(new String[] { "C:\\Program Files (x86)\\K-Lite Codec Pack\\Media Player Classic\\mpc-hc.exe", string, "/fullscreen" } );
    			System.out.println("Vidéo Lancée");
    			// reduire la fenêtre
	    		setState(ICONIFIED);
    			try {
					p.waitFor();
					System.out.println("Vidéo fermée");
					//restaurer la fenêtre
		    		setExtendedState(NORMAL);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	/*
	 * Fin Player Video
	 */

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}	

    
    /*
     * Traduction UTF-8
     */
    public String decode(String string){
  	  String str2 = new String(string.getBytes(),Charset.forName("UTF-8"));
  	  return str2;	  
    }
}