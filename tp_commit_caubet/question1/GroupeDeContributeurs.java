package question1;

import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class GroupeDeContributeurs extends Cotisant implements Iterable<Cotisant>{
  private List<Cotisant> liste;
  
  public GroupeDeContributeurs(String nomDuGroupe){
    super(nomDuGroupe);
    // a completer
    liste = new ArrayList<Cotisant>();
  }
  
  public void ajouter(Cotisant cotisant){
    this.liste.add(cotisant);
    cotisant.setParent(this);
  }
  
  
  public int nombreDeCotisants(){
    int nombre = 0;
    Iterator<Cotisant> iter = liste.iterator();
    while(iter.hasNext()){
        nombre += iter.next().nombreDeCotisants();     
    }    // a completer
    return nombre;
  }
  
  public String toString(){
    String str = new String();
    for(Cotisant cot : liste){
        str += cot.toString()+" ";    
    }
    return str;
  }
  
  public List<Cotisant> getChildren(){
    return this.liste;// a completer
  }
  
  public void debit(int somme) throws SoldeDebiteurException{
    if(somme <0){ throw new RuntimeException("nombre négatif !!!");}
    boolean bon = true;
    
    Iterator<Cotisant> iter = liste.iterator();
    while(iter.hasNext()){
        Cotisant sol = iter.next();
        if((sol.solde()-somme) < 0){ 
            bon = false;
            throw new SoldeDebiteurException("solde négatif !!!");
        }//else{sol.debit(somme);}
    }
    //mod's
    Iterator<Cotisant> iter2 = liste.iterator();
    while(iter2.hasNext() && bon){
        iter2.next().debit(somme);     
    }
}
  
  public void credit(int somme){
    if(somme <0){ throw new RuntimeException("nombre négatif !!!");}
    Iterator<Cotisant> iter = liste.iterator();
    while(iter.hasNext()){
        iter.next().credit(somme);     
    }   
  }
  
  public int solde(){
    int solde = 0;
    //for(Cotisant cot : liste){
    Iterator<Cotisant> iter = liste.iterator();
    while(iter.hasNext()){
        solde += iter.next().solde();     
    }
    return solde;
  }
  
  // méthodes fournies
  
 public Iterator<Cotisant> iterator(){
    return new GroupeIterator(liste.iterator());
  }
  
  private class GroupeIterator implements Iterator<Cotisant>{
    private Stack<Iterator<Cotisant>> stk;
    
    public GroupeIterator(Iterator<Cotisant> iterator){
      this.stk = new Stack<Iterator<Cotisant>>();
      this.stk.push(iterator);
    }
    
    public boolean hasNext(){
      if(stk.empty()){
        return false;
      }else{
         Iterator<Cotisant> iterator = stk.peek();
         if( !iterator.hasNext()){
           stk.pop();
           return hasNext();
         }else{
           return true;
         }
      }
    }
    
    public Cotisant next(){
      if(hasNext()){
        Iterator<Cotisant> iterator = stk.peek();
        Cotisant cpt = iterator.next();
        if(cpt instanceof GroupeDeContributeurs){
          GroupeDeContributeurs gr = (GroupeDeContributeurs)cpt;
          stk.push(gr.liste.iterator());
        }
        return cpt;
      }else{
        throw new NoSuchElementException();
      }
    }
    public void remove(){
      throw new UnsupportedOperationException();
    }
  }
  

  public <T> T accepter(Visiteur<T> visiteur){
    return visiteur.visite(this);
  }
  

}
