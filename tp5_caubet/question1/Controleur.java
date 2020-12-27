package question1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * D�crivez votre classe Controleur ici.
 *
 * @author (votre nom)
 * @version (un num�ro de version ou une date)
 */
public class Controleur implements ActionListener{
    //proprietes
    IHMTestEnsemble ihm;
    
    //constructeur
    public Controleur(IHMTestEnsemble ihm){
        super();
        this.ihm = ihm;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
    if (ae.getActionCommand().equals("union")) {
        ihm.setUnion(ae);
        }
        
    if (ae.getActionCommand().equals("intersection")) {
        ihm.setInter(ae);
        }
        
    if (ae.getActionCommand().equals("difference")) {
        ihm.setDiff(ae);
        }
        
    if (ae.getActionCommand().equals("diffSymetrique")) {
        ihm.setDiffSym(ae);
        }
    }
   
}
