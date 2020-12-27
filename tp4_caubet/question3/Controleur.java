package question3;

import question3.tp3.PileI;
import question3.tp3.PilePleineException;
import question3.tp3.PileVideException;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Décrivez votre classe Controleur ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Controleur extends JPanel implements ActionListener {

    private JButton push, add, sub, mul, div, clear;
    private PileModele<Integer> pile;
    private JTextField donnee;

    public Controleur(PileModele<Integer> pile)  {
        super();
        this.pile = pile;
        this.donnee = new JTextField(8);

        this.push = new JButton("push");
        this.add = new JButton("+");
        this.sub = new JButton("-");
        this.mul = new JButton("*");
        this.div = new JButton("/");
        this.clear = new JButton("[]");

        setLayout(new GridLayout(2, 1));
        add(donnee);
        donnee.addActionListener(this);
        JPanel boutons = new JPanel();
        boutons.setLayout(new FlowLayout());
        boutons.add(push);  push.addActionListener(this);
        boutons.add(add);   add.addActionListener(this);
        boutons.add(sub);   sub.addActionListener(this);
        boutons.add(mul);   mul.addActionListener(this);
        boutons.add(div);   div.addActionListener(this);
        boutons.add(clear); clear.addActionListener(this);
        add(boutons);
        boutons.setBackground(Color.red);
        actualiserInterface();
    }

    public void actualiserInterface() {
        
        if(pile.taille() == 0){
        push.setEnabled(true);    
        add.setEnabled(false);
        sub.setEnabled(false);
        mul.setEnabled(false);
        div.setEnabled(false);
        clear.setEnabled(false);
        
        }
        
        if(pile.taille() == 1){
        push.setEnabled(true);    
        add.setEnabled(false);
        sub.setEnabled(false);
        mul.setEnabled(false);
        div.setEnabled(false);
        clear.setEnabled(true);
        
        }
        
        if((pile.taille() > 1) && (pile.taille() != pile.capacite())){
        push.setEnabled(true);    
        add.setEnabled(true);
        sub.setEnabled(true);
        mul.setEnabled(true);
        div.setEnabled(true);
        clear.setEnabled(true);
        
        }
        
        if(pile.taille() == pile.capacite()){
        push.setEnabled(false);    
        add.setEnabled(true);
        sub.setEnabled(true);
        mul.setEnabled(true);
        div.setEnabled(true);
        clear.setEnabled(true);
        
        }   
    }

    private Integer operande() throws NumberFormatException {
       
        return Integer.parseInt(donnee.getText());
       
    }

    // à compléter
    // en cas d'exception comme division par zéro, 
    // mauvais format de nombre suite à l'appel de la méthode operande
    // la pile reste en l'état (intacte)
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("push")) {
            try{
            this.pile.empiler(operande());
            actualiserInterface();
        }catch(Exception exc){/*System.out.println("yo");*/}
        }
        
        if (ae.getActionCommand().equals("+")) {
            try{
                if(pile.taille()>1){
                    Integer a = pile.depiler();
                    Integer b = pile.depiler();
                    this.pile.empiler(a+b);
                    actualiserInterface();
                }
        }catch(Exception exc){/*System.out.println("yo");*/}
        }
        
        if (ae.getActionCommand().equals("-")) {
            try{
                if(pile.taille()>1){
                    Integer a = pile.depiler();
                    Integer b = pile.depiler();
                    this.pile.empiler(b-a);
                    actualiserInterface();
                }
        }catch(Exception exc){/*System.out.println("yo");*/}
        }
        
        if (ae.getActionCommand().equals("*")) {
            try{
                if(pile.taille()>1){
                    Integer a = pile.depiler();
                    Integer b = pile.depiler();
                    this.pile.empiler(a*b);
                    actualiserInterface();
                }
        }catch(Exception exc){/*System.out.println("yo");*/}
        }
        
        if (ae.getActionCommand().equals("/")) {
            try{
                if((pile.taille()>1) && (pile.sommet() != 0)){
                    Integer a = pile.depiler();
                    Integer b = pile.depiler();                   
                    this.pile.empiler(b / a);
                    actualiserInterface();
                }
        }catch(Exception exc){/*System.out.println("yo");*/}
        }
        
        if (ae.getActionCommand().equals("[]")) {
            try{
                while(pile.taille() > 0){
                    pile.depiler();
                    actualiserInterface();
                }
                
        }catch(Exception exc){/*System.out.println("yo");*/}
        }
    }
}
