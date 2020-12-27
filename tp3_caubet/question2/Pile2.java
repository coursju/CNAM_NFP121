package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile2 implements PileI {
    private Stack<Object> stk;
    private int capacite;

    public Pile2(int taille) {
        this.capacite = taille;
        if (taille <= 0){
            capacite = CAPACITE_PAR_DEFAUT;
        }
        this.stk = new Stack<Object>();
    }

    public Pile2() {
        this(0);
    }

    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();   
        stk.push(o);
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        return  stk.pop();
    }

    public Object sommet() throws PileVideException {
        return stk.peek();
    }

    public boolean estVide() {
        return stk.empty();
    }

    public boolean estPleine() {
        return stk.size()==capacite;
    }

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

    public int hashCode() {
        return toString().hashCode();
    }

    public int taille() {
        return stk.size();
    }

    public int capacite() {
        return capacite;
    }

} 
