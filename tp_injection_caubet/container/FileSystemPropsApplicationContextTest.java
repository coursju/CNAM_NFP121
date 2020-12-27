package container;

import java.io.*;
import java.util.*;
import java.net.URL;
import javax.swing.JFrame;

public class FileSystemPropsApplicationContextTest extends junit.framework.TestCase{

    public void testLectureFichierProperties() throws Exception{
        InputStream inputStream = new FileInputStream(new File("./container/README.TXT"));
        FileSystemPropsApplicationContext ctx = new FileSystemPropsApplicationContext(inputStream);

        List<String> liste = new ArrayList<String>();
        for(String bean : ctx){
            liste.add(bean);
        }
        assertTrue(liste.contains("list"));
        assertTrue(liste.contains("set"));

        assertEquals(ArrayList.class, ctx.getType("list"));
        assertEquals(TreeSet.class, ctx.getType("set"));

        assertTrue(liste.contains("martin_fowler"));
        assertEquals(FileSystemPropsApplicationContext.class, ctx.getType("martin_fowler"));

        FileSystemPropsApplicationContext ctxExemples = ctx.getBean("martin_fowler");

        liste.clear();
        for(String bean : ctxExemples){
            liste.add(bean);
        }

        assertTrue(liste.contains("movieLister"));
        martin_fowler.MovieLister lister = ctxExemples.getBean("movieLister");
        List<martin_fowler.Movie> movies = lister.moviesDirectedBy("Sergio Leone");
        assertEquals("Once Upon a Time in the West",movies.get(0).getTitle());

    }

    public void testLectureURLFichierProperties() throws Exception{
        try{
            // System.out.println("Tentative de lecture de la configuration sur le web en:\n");
            // System.out.println("\thttp://jfod.cnam.fr/progAvancee/config.props.txt");
            InputStream inputStream = new URL("http://jfod.cnam.fr/progAvancee/config.props.txt").openStream();
            FileSystemPropsApplicationContext ctx = new FileSystemPropsApplicationContext(inputStream);

            List<String> liste = new ArrayList<String>();
            for(String bean : ctx){
                liste.add(bean);
            }
            assertTrue(liste.size()>0);
            // System.out.println("liste de beans obtenue");
            // System.out.println("\t" +liste);
            
            FileSystemPropsApplicationContext ctxExemples = ctx.getBean("martin_fowler");

            liste.clear();
            for(String bean : ctxExemples){
                liste.add(bean);
            }

            assertTrue(liste.contains("movieLister"));
            martin_fowler.MovieLister lister = ctxExemples.getBean("movieLister");
            List<martin_fowler.Movie> movies = lister.moviesDirectedBy("Sergio Leone");
            assertEquals("Once Upon a Time in the West",movies.get(0).getTitle());
        }catch(Exception e){
            System.err.println("échec au test en ligne 41 de " + this.getClass().getName() + "\n\tPour ce test facultatif: lecture d'un fichier de config sur le web.");
        }
    }

}
