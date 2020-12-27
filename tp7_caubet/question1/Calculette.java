package question1;


import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;

/**
 * Calculette: Evaluation d'opérations mathématiques,
 * en utilisant les méthodes de la classe <code>java.lang.Math</code>.
 * Seules sont prises en compte les méthodes prenant en paramètre
 * UN ou DEUX double, et renvoie un double.
 * Les méthodes sont listées en utilisant l'introspection.
 * @author Yannick Meudal, modifié pour NFP121
 */

public class Calculette extends JFrame implements ActionListener {

  /** Liste des fonctions disponibles */
  private JComboBox saisieFonction;
  /** Saisie du premier paramètre */  
  private JTextField saisieX        = new JTextField("", 7);
  /** Saisie du second paramètre */
  private JTextField saisieY        = new JTextField("", 7);
  /** Texte recevant le résultat de l'opération */
  private JTextField resultat       = new JTextField("", 25);

 
  /**
   * Hashtable contenant la liste des méthodes disponibles.
   * Clé = String: Nom de la fonction + "(x)" ou "(x,y)".
   * Valeur = pointer sur la Method correspondante.
   */
  private TableMethodesJavaLangMath tableMethodes = TableMethodesJavaLangMath.getInstance();
  
  /**
   * Méthode exécutée à chaque fois qu'une invocation doit être effectuée.
   * Appel lors de l'appui bouton Calculer, touche Entrée dans X ou Y.
   */
  public void actionPerformed(ActionEvent ae){
	 String  selection;
	 // Traitement d'une fonction à un paramètre
	 selection=(String)saisieFonction.getSelectedItem();

	 if (!tableMethodes.cetteMethodeEstPresente(selection)){
		 resultat.setText("Erreur: La fonction est introuvable");
		 return;
	 }

	 try
	 {
	   double res = 0.0;
	   if (tableMethodes.cetteMethodeAttendDeuxParametres(selection))
	     res = tableMethodes.invoquer(selection,new Double(saisieX.getText()), new Double(saisieY.getText()));
	   else
	     res = tableMethodes.invoquer(selection,new Double(saisieX.getText()));
	     
    	// place le résultat
	    resultat.setText(new Double(res).toString());
	    
	 } catch (Exception e) {
	    resultat.setText(e.toString());
	 }
	 

	 
  }
  
  
  public Calculette(){
    super("Calculette Scientifique");
  
  	setSize(520, 100);
	  // Initialisation des varables permettant ultérieurement
	  // l'invocation des fonctions.
	  
	  Container panel = getContentPane();
	  panel.setLayout(new BorderLayout());
	  Panel p = new Panel();
	  p.add(new JLabel("Fonction:"));

	  // Création de la liste des méthodes disponibles, et place les noms dans le ComboBox
	  saisieFonction = new JComboBox(tableMethodes.listeDesMethodes());
	  
	  if (tableMethodes.cetteMethodeAttendUnSeulParametre((String)saisieFonction.getSelectedItem()))
	    saisieY.setEnabled(false);
	    
    p.add(saisieFonction);
	  p.add(new JLabel(" x:"));
	  p.add(saisieX);
	  p.add(new JLabel(" y:"));
	  p.add(saisieY);
	  
      panel.add(p, "North");

      p = new Panel();
      JButton b = new JButton("Calculer");
      
      p.add(b);
      p.add(resultat);
      panel.add(p, "Center");

      // Ajout de tous les actions listener, qui provoquerons
      // L'évaluation de l'expression
      resultat.setEditable(false);
      saisieX.addActionListener(this);
      saisieY.addActionListener(this);
      b.addActionListener(this);

      // Ajout de l'actionlistener du ComboBox:
      //   Activation / Désactivation de y.
      saisieFonction.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent ae){
		      String selection = (String)saisieFonction.getSelectedItem();
		      if (tableMethodes.cetteMethodeAttendUnSeulParametre(selection))
		        saisieY.setEnabled(false);
		      else
		        saisieY.setEnabled(true);
	      }
        });
        
      addWindowListener(
        new WindowAdapter(){
          public void windowClosing(WindowEvent we){
            System.exit(0);
          }
          public void windowClosed(WindowEvent we){
            System.exit(0);
          }
        }
      );
      setVisible(true);
  
  }
 
  public static void main(String[] args){
    new Calculette();
  }
    
}
