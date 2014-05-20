package com.MoviesLink;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFile {
    public static void main(final String[] args) {
        /*
         * Etape 1 : r�cup�ration d'une instance de la classe "DocumentBuilderFactory"
         */
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            	
        try {
            /*
             * Etape 2 : cr�ation d'un parseur
             */
            final DocumentBuilder builder = factory.newDocumentBuilder();
			
		    /*
		     * Etape 3 : cr�ation d'un Document
		     */
		    final Document document= builder.parse(new File("./require/config.xml"));
				
		    //Affiche du prologue
		    System.out.println("*************PROLOGUE************");
		    System.out.println("version : " + document.getXmlVersion());
		    System.out.println("encodage : " + document.getXmlEncoding());		
	        System.out.println("standalone : " + document.getXmlStandalone());
						
		    /*
		     * Etape 4 : r�cup�ration de l'Element racine
		     */
		    final Element racine = document.getDocumentElement();
			
		    //Affichage de l'�l�ment racine
		    System.out.println("\n*************RACINE************");
		    System.out.println(racine.getNodeName());
			
		    /*
		     * Etape 5 : r�cup�ration des r�pertoires
		     */
		    final NodeList racineNoeuds = racine.getChildNodes();
		    final int nbRacineNoeuds = racineNoeuds.getLength();
		    
		    final NodeList countRepertoire = racine.getElementsByTagName("repertoire");
		    final int nbRepertoire = countRepertoire.getLength();
				
		    for (int i = 0; i<nbRepertoire; i++) {
		        if(countRepertoire.item(i).getNodeType() == Node.ELEMENT_NODE) {
		            final Element repertoire = (Element) racine.getElementsByTagName("repertoire").item(i);//racineNoeuds.item(i);
					
				    //Affichage d'un r�pertoire
				    System.out.println("\n*************REPERTOIRE************");
				    System.out.println("Type : " + repertoire.getAttribute("type"));
					
			    	    /*
				     * Etape 6 : r�cup�ration de la categorie et du path
				     */
				    final Element categorie = (Element) repertoire.getElementsByTagName("categorie").item(0);
				    final Element path = (Element) repertoire.getElementsByTagName("path").item(0);
							
				    //Affichage du nom et du pr�nom
				    System.out.println("categorie : " + categorie.getTextContent());
				    System.out.println("path : " + path.getTextContent());
			    
		        }				
		    }			
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }		
    }
}