package question2;

import java.util.*;
import question1.*;

public class DebitMaximal implements Visiteur<Integer>{

  public Integer visite(Contributeur c){      
    return c.solde(); // a compléter
  }
  
  public Integer visite(GroupeDeContributeurs g){
    Integer res = g.solde();
    Iterator<Cotisant> iter = g.iterator();
    while(iter.hasNext()){
    Cotisant cot = iter.next();
     if(cot.nombreDeCotisants() >= 1){
     Integer var = cot.accepter(this);
      if(var < res){res = var;}
      }
     }
    return res ;
  }
}
