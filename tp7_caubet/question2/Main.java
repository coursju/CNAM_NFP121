package question2;
import java.util.*;

import java.awt.event.*;
import javax.swing.JButton;
import java.lang.reflect.*;
import java.awt.Canvas;

/**
 * Décrivez votre classe Main ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Main
{
   public Main(){
    Test test = new Test();
    appel(test);
    try{
                //JButton jb = new JButton();
                Canvas jb = new Canvas();
            MouseListener obs = new MouseListener(){
                    public void 	mouseClicked(MouseEvent e){}
                    public void 	mouseEntered(MouseEvent e){}
                    public void 	mouseExited(MouseEvent e){}
                    public void 	mousePressed(MouseEvent e){}
                    public void 	mouseReleased(MouseEvent e){}
                };
            question2.ParIntrospection.delierSourceEtListener(jb, obs);
            //System.out.println((jb.getActionListeners().length==1) +"  class obs="+obs.getClass().getInterfaces()[0].getSimpleName());
            System.out.println(jb.getClass().getSimpleName());
        }catch(Exception e){}
    }
    
   public void appel(Object t){
    System.out.println(t.getClass().getName() + "  " + t.getClass().getMethods().toString());
    }
}
