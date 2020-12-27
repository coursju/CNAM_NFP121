package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.LinkedList;

public class Pile4 implements PileI {

    private LinkedList<Object> link;
    private int size; 

    /**
     * Cr�ation d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit �tre > 0
     */
    public Pile4(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        this.size = taille;
        link = new LinkedList<Object>();
    }

    public Pile4() {
        this(PileI.CAPACITE_PAR_DEFAUT);
    }

    /**
     * empiler � la pile un �l�ment en param�tre.
     * @Param o
     *      objet � empiler dans la pile
     * @throw PilePleineException
     *      -si pile est pleine, ne peut pas empiler
     */
    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();       
        link.add(o);
    }

    /**
     * depiler supprime de la pile le dernier objet entr�, 
     * retourne cet objet.
     * @return Object 
     * @throw PileVideException
     *      -si pile n'a aucun �l�mnet
     */
    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();       
        return link.removeLast();
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        return link.peekLast() ;
    }

    /**
     * Effectue un test de l'�tat de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        return link.size() == 0; 
    }

    /**
     * Effectue un test de l'�tat de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        return link.size() == size; 
    }

    /**
     * Retourne une repr�sentation en String d'une pile, contenant la
     * repr�sentation en String de chaque �l�ment.
     * 
     * @return une repr�sentation en String d'une pile
     * Utilise la m�thode hasNext() de Maillon
     */
    public String toString() {
        String s = "[";
        for (int i = this.link.size() - 1; i >= 0; i--) {
            s+= this.link.get(i).toString();
            if(i >0){
                s+= ", ";
            }       
        }
        return s + "]"; 
    }

   
	public boolean equals(Object o) {
        if( o instanceof Pile4){
            Pile4 p = (Pile4)o;
            if( this.taille() == p.taille() && this.capacite() == p.capacite() ){
                for( int i = 0; i< this.taille(); i++){
                    if( this.link.get(i) != p.link.get(i) ){ return false;}
                }
            } else{return false;}
        }else{return false;}
        return true;
    }


    public int capacite() {
        return this.size;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int taille() {
        return link.size();
    }
}
