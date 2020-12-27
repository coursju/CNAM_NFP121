package question1;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class IHMPile extends JFrame implements ActionListener{
    private JTextField donnee = new JTextField(6);
    private JTextField sommet = new JTextField(6);
    private JLabel     contenu = new JLabel("[]");

    private Pile p;

    public IHMPile(){
        super("IHM Pile");
        JButton    boutonEmpiler = new JButton("empiler");
        JButton    boutonDepiler = new JButton("depiler");

        JPanel enHaut = new JPanel();
        enHaut.add(donnee);
        enHaut.add(boutonEmpiler);
        enHaut.add(boutonDepiler);
        enHaut.add(sommet);
        setLayout(new BorderLayout(5,5));
        add("North",enHaut);
        add("Center",contenu);
        enHaut.setBackground(Color.red);
        setLocation(100,100);
        pack();setVisible(true);
        boutonEmpiler.addActionListener(this);
        boutonDepiler.addActionListener(this);

        p = new Pile(5);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("empiler")){

            sommet.setText(donnee.getText());
            try{
                p.empiler(donnee.getText());
                contenu.setText(p.toString());

            }catch(Exception e){
                this.contenu.setText(p.toString()+" est pleine! ");
            }

        }else{
            try{    
                p.depiler();
                contenu.setText(p.toString());

            }catch(Exception e){
                this.contenu.setText(p.toString()+" est vide! ");
            }

        }
    }

    public void contenuException(String mess){
        this.contenu.setText(p.toString()+" "+mess);

    }

    public static void main(String[] args){
        new IHMPile();
    }
}
