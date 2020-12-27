package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IHMQuestion2_2 extends JFrame {

    private JButton boutonA = new JButton("A");
    private JButton boutonB = new JButton("B");
    private JButton boutonC = new JButton("C");

    private TextArea contenu = new TextArea(30, 80);

 
    public IHMQuestion2_2() {
        super("IHM Question2_2");
        JPanel enHaut = new JPanel();
        enHaut.add(boutonA);
        enHaut.add(boutonB);
        enHaut.add(boutonC);
        setLayout(new BorderLayout(5, 5));
        add("North", enHaut);
        add("Center", contenu); // contenu sera transmis aux observateurs, voir
                                // la description des constructeurs
        setLocation(150,150);pack();show();
        enHaut.setBackground(Color.magenta);
        
        //creation des JButtonObserver
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
        
        //creation des JMouseObserver
        JMouseObserver jmo1 = new JMouseObserver("jmo1",this.getContenu());
        JMouseObserver jmo2 = new JMouseObserver("jmo2",this.getContenu());
        JMouseObserver jmo3 = new JMouseObserver("jmo3",this.getContenu());
        
        //attachement des observateurs
        this.addMouseObserver2A(jmo1);
        this.addMouseObserver2B(jmo2);
        this.addMouseObserver2C(jmo3);
        
    }
    
     public static void main(String[] args){
        new IHMQuestion2_1();
        new IHMQuestion2_2();
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
    
    /**
     * ajoute un mouseListener a boutonA
     */
    public void addMouseObserver2A(JMouseObserver jmo){
        boutonA.addMouseListener(jmo);
    }
    
    /**
     * ajoute un mouseListener a boutonB
     */
    public void addMouseObserver2B(JMouseObserver jmo){
        boutonB.addMouseListener(jmo);
    }
    
    /**
     * ajoute un mouseListener a boutonC
     */
    public void addMouseObserver2C(JMouseObserver jmo){
        boutonC.addMouseListener(jmo);
    }

}
