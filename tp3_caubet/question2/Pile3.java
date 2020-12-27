package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Vector;

/**
 * Décrivez votre classe PileVector ici.
 * 
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Pile3 implements PileI {

    private Vector<Object> v;
    private int size; 

    public Pile3() {
        this(0);
    }

    public Pile3(int taille) {
        size = taille;       
        if(taille <= 0){ 
            size = CAPACITE_PAR_DEFAUT;
        }        
    v = new Vector<Object>(size);
    }

    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();           
        v.add(o);    
    }

    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();            
        Object t = v.elementAt(v.size()-1);
        v.remove(v.elementAt(v.size()-1));
       return t;
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();           
        return v.elementAt(v.size()-1);
    }

    public int taille() {
        return v.size();
    }

    public int capacite() {
        return size;
    }

    public boolean estVide() {
        return v.size()==0;
    }

    public boolean estPleine() {
        return v.size()==size;
    }

    public String toString() {
        String s = "[";
        for (int i = this.v.size() - 1; i >= 0; i--) {
            s+= this.v.get(i).toString();
            if(i >0){
                s+= ", ";
            }
        }
        return s + "]"; 
    }

    public boolean equals(Object o) {
        if( o instanceof Pile3){
            Pile3 p = (Pile3)o;
            if( this.taille() == p.taille() && this.capacite() == p.capacite() ){
                for( int i = 0; i< this.taille(); i++){
                    if( this.v.get(i) != p.v.get(i) ){ return false;}
                }
            } else{return false;}
        }else{return false;}
        return true;   
    }

    public int hashCode() {
        return toString().hashCode();
    }

}
