package question2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.*;

public class JPanelListe2 extends JPanel implements ActionListener, ItemListener , KeyListener{

    private JPanel cmd = new JPanel();
    private JLabel afficheur = new JLabel();
    private JTextField saisie = new JTextField();

    private JPanel panelBoutons = new JPanel();
    private JButton boutonRechercher = new JButton("rechercher");
    private JButton boutonRetirer = new JButton("retirer");

    private CheckboxGroup mode = new CheckboxGroup();
    private Checkbox ordreCroissant = new Checkbox("croissant", mode, false);
    private Checkbox ordreDecroissant = new Checkbox("décroissant", mode, false);

    private JButton boutonOccurrences = new JButton("occurrence");

    private JButton boutonAnnuler = new JButton("annuler");

    private TextArea texte = new TextArea();

    private List<String> liste;
    private Map<String, Integer> occurrences;
    
    private Stack<Memento> mementos = new Stack<>();
    
    private Originator originator = new Originator();

    public JPanelListe2(List<String> liste, Map<String, Integer> occurrences) {
        this.liste = liste;
        this.occurrences = occurrences;

        cmd.setLayout(new GridLayout(3, 1));

        cmd.add(afficheur);
        cmd.add(saisie);

        panelBoutons.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelBoutons.add(boutonRechercher);
        panelBoutons.add(boutonRetirer);
        panelBoutons.add(new JLabel("tri du texte :"));
        panelBoutons.add(ordreCroissant);
        panelBoutons.add(ordreDecroissant);
        panelBoutons.add(boutonOccurrences);
        panelBoutons.add(boutonAnnuler);
        cmd.add(panelBoutons);


        if(liste!=null && occurrences!=null){
            afficheur.setText(liste.getClass().getName() + " et "+ occurrences.getClass().getName());
            texte.setText(liste.toString());
        }else{
            texte.setText("la classe Chapitre2CoreJava semble incomplète");
        }

        setLayout(new BorderLayout());

        add(cmd, "North");
        add(texte, "Center");

        boutonRechercher.addActionListener(this);
        boutonOccurrences.addActionListener(this);
        boutonRetirer.addActionListener(this);
        ordreCroissant.addItemListener(this);
        ordreDecroissant.addItemListener(this);
        saisie.addKeyListener(this);
        boutonAnnuler.addActionListener(this);
    }

        public void actionPerformed(ActionEvent ae) {
        try {
            boolean res = false;
            if (ae.getSource() == boutonRechercher || ae.getSource() == saisie) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "+ saisie.getText() + " -->  " + res);
            } else if (ae.getSource() == boutonRetirer) {
                List<String> liste2 = new LinkedList<String>(liste);//creation d' une liste intermediaire
                res = retirerDeLaListeTousLesElementsCommencantPar(saisie.getText());
                if(res){this.saveListe(liste2);}
                afficheur.setText("résultat du retrait de tous les éléments commençant par -->  "+ saisie.getText() + " : " + res);
            } else if (ae.getSource() == boutonAnnuler) {
                if(mementos.size()>=1){this.annuler();}
                
            } else if (ae.getSource() == boutonOccurrences) {
                Integer occur = occurrences.get(saisie.getText());
                if (occur != null)
                    afficheur.setText(" -->  " + occur + " occurrence(s)");
                else{
                    if(Chapitre2CoreJava2.CHAPITRE2.contains(saisie.getText())){
                    afficheur.setText(" -->  0 ");
                }else{afficheur.setText(" -->  ??? ");}
                
                }
            }
            texte.setText(liste.toString());

        } catch (Exception e) {
            afficheur.setText(e.toString());
        }
    }

    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == ordreCroissant){
            this.saveListe();
            liste.sort(String.CASE_INSENSITIVE_ORDER);
        }
        else if (ie.getSource() == ordreDecroissant){
            this.saveListe();
            liste.sort(Comparator.reverseOrder());
        }

        texte.setText(liste.toString());
    }

    private boolean retirerDeLaListeTousLesElementsCommencantPar(String prefixe) {
        boolean resultat = false;
            for( int i = 0; i< liste.size(); i++){
                String str = liste.get(i);
                if(str.startsWith(prefixe)){
                liste.remove(str);
                resultat = true;
                occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
                }
            }
        return resultat;
    }
    
    public void keyPressed(KeyEvent e) { 
        boolean res = false;
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                res = liste.contains(saisie.getText());
                Integer occur = occurrences.get(saisie.getText());
                afficheur.setText("résultat de la recherche de : "+ saisie.getText() + " -->  " + res);
            } 
        } 
    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {} 
    
    public void addMemento(Memento m) {
        mementos.push(m);
    }

    public Memento getMemento() {
        return mementos.pop();
    }
    /**
     * methode saveListe
     */
    public void saveListe(){
        originator.setState(this.liste);
        this.addMemento( originator.save() );
    }
    /**
     * methode saveListe
     * @param List<String> liste2
     */
    public void saveListe(List<String> liste2){
        originator.setState(liste2);
        this.addMemento( originator.save() );
    }
    /**
     * methode annuler
     */
    public void annuler(){
        liste.clear();
        liste.addAll(originator.restore( this.getMemento() ));
        occurrences = Chapitre2CoreJava2.occurrencesDesMots(liste);
    }
}
/**
 * Classe Memento
 */
class Memento {
    private List<String> state = new LinkedList<String>();

    public Memento(List<String> state) {
        this.state.addAll(state);
    }

    public List<String> getState() {
        return state;
    }
}    
/**
 * Classe Originator
 */
class Originator {
    private List<String> state = new LinkedList<String>();
    
    public void setState(List<String> state) {
        this.state.clear();
        this.state.addAll(state);
    }

    public Memento save() {
        return new Memento(state);
    }
    public List<String> restore(Memento m) {
        state = m.getState();
        return state;
    }
}
 