package com.MoviesLink;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ListVideo extends JPanel {
	CardLayout cl;
	JPanel content;
	Main MainFrame;
    JButton bouton = new JButton("Page 1");
    public String folder;
    JButton boutonRetour;
    String dossier;
    
    JPanel contenu = new JPanel();
    JPanel space = new JPanel();
    JPanel affiche = new JPanel();
    JPanel spaceBottom = new JPanel();
    
    int $i = 0;
    int component = 0;
    
	public ListVideo (final MainFrame main,File file, int level)
	{
        this.cl = main.cl;
        this.boutonRetour = main.boutonRetour;
        this.content = main.content;
        
        
        affiche.setLayout(new FlowLayout(FlowLayout.CENTER));
        affiche.add ( Box.createRigidArea (new Dimension (20, 0)) );
        affiche.setOpaque(false);
        
        space.setLayout(new BoxLayout(space, BoxLayout.LINE_AXIS));
        space.add ( Box.createRigidArea (new Dimension (0, 650)) );
        space.setOpaque(false);
        
        spaceBottom.setLayout(new BoxLayout(spaceBottom, BoxLayout.LINE_AXIS));
        spaceBottom.add ( Box.createRigidArea (new Dimension (0,75)) );
        spaceBottom.setOpaque(false);
        
        
	    for (final File f : file.listFiles())
	    {
	    	folder = f.getParent();
	    	dossier = f.getAbsolutePath().substring(0, f.toString().lastIndexOf("\\"));
	    	main.dossier = dossier.substring(0, dossier.lastIndexOf("\\"));
	        String name = f.getName();
	 
	        if (f.isDirectory() && !name.equals("metadata"))
	        {
		        
	        	ImageIcon image = new ImageIcon(f+"/folder.jpg");// définition de l'image de background
	        	ImageIcon icone = new ImageIcon(image.getImage().getScaledInstance(150, 221, Image.SCALE_DEFAULT));//200x289

	        	String label;
	        	//System.out.println(f.getParent());
	        	if(f.getParent().equals("D:\\Films") || f.getAbsolutePath().contains("Saison")){label=f.getName();}
	        	else if(f.getAbsolutePath().contains("3D") || f.getAbsolutePath().contains("HD")){label=f.getName();}
	        	else{label="";}
        		TestImageEnFond button$i =  new TestImageEnFond(getToolkit().getImage(f+"/folder.jpg"), label);
		        //JButton button$i = new JButton(label);//f.getName()
			    button$i.setOpaque(false);
			    button$i.setBorder(BorderFactory.createEmptyBorder());

			    button$i.setVerticalTextPosition(SwingConstants.BOTTOM);
			    button$i.setHorizontalTextPosition(SwingConstants.CENTER);
			    button$i.setForeground(Color.white);
			    
		        space.add ( Box.createRigidArea (new Dimension (0, 650)));
			    
		        if(f.getParent().equals("D:\\Films\\Series")){
		        	//System.out.println(f.getParent()+" - "+f.getAbsolutePath());
			        new getImageFolder(f.getAbsolutePath());
		        }
		        if(f.getAbsolutePath().contains("Series\\")){
		        	//System.out.println(f.getParent()+" - "+f.getAbsolutePath());
			        new getImageFolder(f.getAbsolutePath());
		        }

	        	button$i.setIcon(icone);

	    	    button$i.addActionListener(new ActionListener(){
	    	    	public void actionPerformed(ActionEvent event){
	    	    		if($i<1){component=0;}else{component=0;}
	    	    		//System.out.println(component);
	    	    		content.removeAll();
	    	    		content.add(new ListVideo(main, f, 1), BorderLayout.CENTER);
	    	    		//cl.next(content);
	    	    		content.repaint();
	    	    		main.revalidate();
	    	    	}
	    	    });
	            
	            affiche.add(button$i);
		        affiche.add ( Box.createRigidArea (new Dimension (20, 0)) );
	        }
	        else if(f.toString().endsWith(".avi")==true || f.toString().endsWith(".mkv")==true || f.toString().endsWith(".m2ts")==true)
	        {
	        	String label;
	    		//TestImageEnFond button$i =  new TestImageEnFond("./ressources/settings38.png", f.getName());
	        	if(f.getAbsolutePath().contains("3D") || f.getAbsolutePath().contains("HD") || f.getAbsolutePath().contains("SD")){label=f.getName().substring(0,(f.getName().toString().lastIndexOf(".")));}
	        	else{label="Ep "+($i+1);}
	        	JButton button$i = new JButton(label);

	    	    button$i.addActionListener(new ActionListener(){
	    	    	public void actionPerformed(ActionEvent event){
				    	main.Player(f.toString());
	    	    	}
	    	    });
	            
	            affiche.add(button$i);
	            //System.out.println (f.getName());
	        }
    		
	        $i++;
	    }

        
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        JScrollPane maFenetre = new JScrollPane(affiche);	
        affiche.setOpaque(false);
        maFenetre.getViewport().setOpaque(false);
        maFenetre.setBorder(null);
        maFenetre.setPreferredSize(new Dimension(3000,300));
        maFenetre.setOpaque(false);
        this.add(space, BorderLayout.NORTH);
        this.add(maFenetre, BorderLayout.CENTER);
        this.add(spaceBottom, BorderLayout.SOUTH);
        //this.setBackground(new Color(83,83,83));
		
		
	}
	
	public String Directorie(MainFrame main, String dossier){
		String Dossier = main.table.getString(dossier)+"/"; 
		return Dossier;
	}
	
	public void paintComponent(Graphics g){
		try 
		{
			Image flou = ImageIO.read(new File("./ressources/flou.png"));
		    Image img = ImageIO.read(new File(folder+"/backdrop.jpg"));
		    //g.drawImage(img, 0, 0, this);
		    //Pour une image de fond
		    g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    g.drawImage(flou, 0, 0, this.getWidth(), this.getHeight(), this);
		 } catch (IOException e) {
		      e.printStackTrace();
		 }     
	}
}