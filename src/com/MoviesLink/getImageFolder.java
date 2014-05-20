package com.MoviesLink;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getImageFolder {
	public String folder;
	public String f;
	public String imageUrl;
	public String url;
	
    public getImageFolder(String folder) {
        f = folder;
        if(!testExist(f))
        {
        	try {
				downloadImg(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
        }
    }
    
    public boolean testExist(String f){
    	File folder = new File(f+"/folder.jpg"); 
    	if(folder.exists()) {
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    
    public static void lireSite(String url) throws MalformedURLException, IOException
    {
    	//String regex = "\\<img src=\"([^\\\"]*)\"([^\\<]*)\\>";
    	String regex = "(?i)(<img src=\")(.+?)(\".*? >)";
        
        Scanner sc = new Scanner(new URL("http://subscene.com/subtitles/arrow-second-season").openStream());
        
        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            if (line.matches(regex))
            {
                Scanner sc2 = new Scanner(line);
                sc2.findInLine(regex);
                
                MatchResult result = sc2.match();
                System.out.printf("%s\t",result.group(1));
                sc2.close();
            }
        }
        sc.close();
    }
    
    public void downloadImg(String f) throws IOException{
    	
    	String serie = f.substring(0,f.lastIndexOf("\\"));
    	System.out.println(serie);
    	serie = serie.substring(serie.lastIndexOf("\\")+1,serie.length());
    	System.out.println(serie.toLowerCase());
        URL yahoo = new URL("http://seriesinfos.com/show.php?i="+serie.toLowerCase());
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;

    	int limit=0;
        while ((inputLine = in.readLine()) != null) {
            //System.out.println(inputLine);
        	
        	Pattern p = Pattern.compile(".*<img[^>]*src=\"([^\"]*)",Pattern.CASE_INSENSITIVE);
        	Matcher m = p.matcher(inputLine);
        	while (m.find())
        	{
            	if(limit<1){
	        		//String text = m.group(1).substring(0,m.group(1).lastIndexOf("154-"));
	        		//String text2 = m.group(1).substring((m.group(1).lastIndexOf("154-")+4),m.group(1).length()-4);;
	        	    //String word0 = text+text2+".jpg";
	        		String word0=m.group(1);
	        	    System.out.println(word0.toString());
	        	    imageUrl=word0.toString();
	        		saveImage(imageUrl, f+"/folder.jpg");
	        		limit++;
            	}
        	}
        }
        in.close();
    	//lireSite(null);
        
        return;
    }

	public static void saveImage(String imageUrl, String f) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(f);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}
    
    public String imgFolder(String f){
    	//System.out.println(f+ " - "+ testExist(f));
    	return f;
    }

}
