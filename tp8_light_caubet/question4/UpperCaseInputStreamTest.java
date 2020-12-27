package question4;

import java.io.*;
import java.net.*;

public class UpperCaseInputStreamTest extends junit.framework.TestCase{

  public void testAccès_README_TXT(){
    try{
      InputStream is = new BufferedInputStream( new FileInputStream(new File("README.TXT")));
	    int c = is.read();
	    assertTrue(" erreur de lecture ???", c!= -1);
	    is.close();
	  }catch(Exception e){
	    fail(" Erreur sur ce fichier : README.TXT ??? " + e.getMessage());
	   }
	}
	
	public void testUpperCase_README_TXT() throws Exception{
        InputStream is = new UpperCaseInputStream(new BufferedInputStream( new FileInputStream(new File("README.TXT"))));   // déclaration à décorer ....
    
	  int c = is.read();
	  while( c != -1){
	      //System.out.println((char)c);
	    assertTrue("erreur !, '" + Character.valueOf((char)c) + "' ne semble pas être une majuscule ...", Character.isUpperCase((char)c) || (char)c==' ');  
	    c = is.read();
	  }

	  is.close();
	}
	
	public void testPushPackUpperCase_README_TXT() throws Exception{ 
	PushbackInputStream is = new PushbackInputStream(new UpperCaseInputStream(new BufferedInputStream( new FileInputStream(new File("README.TXT")))));
        int c = is.read();
        assertTrue((char)c == 'T');
        c = is.read();
        assertTrue((char)c == 'E');
        is.unread('r');
        c = is.read();
        //System.out.println("****"+(char)c);
        assertTrue((char)c == 'r');
	}
}
