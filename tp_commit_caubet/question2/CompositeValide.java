package question2;

import question1.*;
import java.util.Iterator;

public class CompositeValide implements Visiteur<Boolean>{
    
  int valeur = 0; 
  
  public CompositeValide(int valeur){
      this.valeur = valeur;//!!!si valeur negatif
  }
  
  public Boolean visite(Contributeur c){
      boolean bol = true;
      if((c.solde() - this.valeur) < 0){bol = false;}
    return bol; // a compléter;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = false;
    Iterator<Cotisant> iter = g.iterator();
    while(iter.hasNext()){
        Cotisant cot = iter.next();
        if(cot.nombreDeCotisants() >= 1){res = true;}else{ return false;}   
        boolean out = cot.accepter(this);
        if(out != true){ return false;}
        else{res = out;}
    } 
    return res ;
  }
}