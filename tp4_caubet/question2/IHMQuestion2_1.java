
package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IHMQuestion2_1 extends JFrame {

    private JButton boutonA = new JButton("A");
    private JButton boutonB = new JButton("B");
    private JButton boutonC = new JButton("C");

    private TextArea contenu = new TextArea(30, 80);

 
    public IHMQuestion2_1() {
        super("IHM Question2_1");
        JPanel enHaut = new JPanel();
        enHaut.add(boutonA);
        enHaut.add(boutonB);
        enHaut.add(boutonC);
        setLayout(new BorderLayout(5, 5));
        add("North", enHaut);
        add("Center", contenu); // contenu sera transmis aux observateurs, voir
                                // la description des constructeurs
        enHaut.setBackground(Color.blue);
        setLocation(100,100);
        pack();show();
        
        //creation des observateurs
        JButtonObserver jbo1 = new JButtonObserver("jbo1",this.getContenu());
        JButtonObserver jbo2 = new JButtonObserver("jbo2",this.getContenu());
        JButtonObserver jbo3 = new JButtonObserver("jbo3",this.getContenu());

        //attachement des observateurs
        this.addObserver2A(jbo1);
        this.addObserver2A(jbo2);
        this.addObserver2A(jbo3);
        
        this.addObserver2B(jbo1);
        this.addObserver2B(jbo2);
        
        this.addObserver2C(jbo1);
      
    }
    
    public static void main(String[] args){
        new IHMQuestion2_1();
    }

    /**
     * ajoute un observateur a boutonA
     */
    public void addObserver2A(JButtonObserver jbo){
        boutonA.addActionListener(jbo);
    }
    
    /**
     * ajoute un observateur a boutonB
     */
    public void addObserver2B(JButtonObserver jbo){
        boutonB.addActionListener(jbo);
    }
    
    /**
     * ajoute un observateur a boutonC
     */
    public void addObserver2C(JButtonObserver jbo){
        boutonC.addActionListener(jbo);
    }
    
    /**
     * renvoi contenu
     */
    public TextArea getContenu(){
        return contenu;
    }
}
