package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>{
    /** par d�l�gation : utilisation de la class Stack */
    private Stack<T> stk;
    /** la capacit� de la pile */
    private int capacit�;

    /** Cr�ation d'une pile.
     * @param taille la "taille maximale" de la pile, doit �tre > 0
     */
    public Pile2(int taille){
                this.capacit� = taille;

        if (taille <= 0){
            capacit� = CAPACITE_PAR_DEFAUT;
        }
        this.stk = new Stack<T>();
    }

    public Pile2(){
                this(0);

    }

    public void empiler(T o) throws PilePleineException{
        if (estPleine())
            throw new PilePleineException();   

        stk.push(o);
    }

    public T depiler() throws PileVideException{
        if (estVide())
            throw new PileVideException();
        return  stk.pop();
    }

    public T sommet() throws PileVideException{
        return stk.peek();
    }

    /**
     * Effectue un test de l'�tat de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        // � compl�ter
        return stk.empty();
    }

    /**
     * Effectue un test de l'�tat de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        // � compl�ter
        return stk.size()==capacit�;
    }

    /**
     * Retourne une repr�sentation en String d'une pile, contenant la
     * repr�sentation en String de chaque �l�ment.
     * 
     * @return une repr�sentation en String d'une pile
     */
    public String toString() {
        String s = "[";
        for (int i = this.stk.size() - 1; i >= 0; i--) {
            s+= this.stk.get(i).toString();
            if(i >0){
                s+= ", ";
            }       
        }
        return s + "]"; 
    }

    public boolean equals(Object o) {
        if( o instanceof Pile2){
            Pile2 p = (Pile2)o;
            if( this.taille() == p.taille() && this.capacite() == p.capacite() ){
                for( int i = 0; i< this.taille(); i++){
                    if( this.stk.get(i) != p.stk.get(i) ){ return false;}
                }
            } else{return false;}
        }else{return false;}
        return true;
    }

    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * Retourne le nombre d'�l�ment d'une pile.
     * 
     * @return le nombre d'�l�ment
     */
    public int taille() {
        // � compl�ter
        return stk.size();
    }

    /**
     * Retourne la capacit� de cette pile.
     * 
     * @return le nombre d'�l�ment
     */
    public int capacite() {
        // � compl�ter
        return capacit�;
    }


} // Pile2