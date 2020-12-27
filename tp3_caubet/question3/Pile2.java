package question3;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2<T> implements PileI<T>{
    /** par délégation : utilisation de la class Stack */
    private Stack<T> stk;
    /** la capacité de la pile */
    private int capacité;

    /** Création d'une pile.
     * @param taille la "taille maximale" de la pile, doit être > 0
     */
    public Pile2(int taille){
                this.capacité = taille;

        if (taille <= 0){
            capacité = CAPACITE_PAR_DEFAUT;
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
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        // à compléter
        return stk.empty();
    }

    /**
     * Effectue un test de l'état de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        // à compléter
        return stk.size()==capacité;
    }

    /**
     * Retourne une représentation en String d'une pile, contenant la
     * représentation en String de chaque élément.
     * 
     * @return une représentation en String d'une pile
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
     * Retourne le nombre d'élément d'une pile.
     * 
     * @return le nombre d'élément
     */
    public int taille() {
        // à compléter
        return stk.size();
    }

    /**
     * Retourne la capacité de cette pile.
     * 
     * @return le nombre d'élément
     */
    public int capacite() {
        // à compléter
        return capacité;
    }


} // Pile2