package com.MoviesLink;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.Charset;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Settings extends JPanel {
	
	CardLayout cl;
	JPanel content;
	Main MainFrame;
	
    JButton bouton = new JButton(" < ");

	public Settings(final MainFrame main, String fichier){
        super();
		this.setLayout(new FlowLayout());
        this.cl = main.cl;
        this.content = main.content;
        this.setBackground(new Color(83,83,83));

		JLabel title = new JLabel(main.table.getString("window.settings"));
		title.setForeground(Color.WHITE);

	    this.add(bouton);
		this.add(title);
		
		
	    
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent event){
	    		//Via cette instruction, on passe au prochain conteneur de la pile
	    		content.remove(1);
	    		content.add("listVideos",  new ListVideo(main, (new File("D:/Films")), 1));
	    		cl.next(content);
	    		revalidate();
	    	}
	    });
	}
}