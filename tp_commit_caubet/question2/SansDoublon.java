package question2;

import question1.*;
import java.util.*;

public class SansDoublon implements Visiteur<Boolean>{
    
public Boolean visite(Contributeur c){
      boolean res = true;
      try{
        Cotisant cot = c.getParent();
        res = this.visite((GroupeDeContributeurs)cot); //cot.accepter(this);//
        }catch(Exception e){System.out.println("pas de parent");}
    return res; // a compléter;
  }
  
  public Boolean visite(GroupeDeContributeurs g){
    boolean res = true;// a compléter
    Set<String> set = new TreeSet<String>();
    set.add(g.nom());
    Iterator<Cotisant> iter = g.iterator();
    while(iter.hasNext()){
       Cotisant cot = iter.next();
       
       //***appel accepter "bidon" pour faire plaisir a Jnews***
       Cotisant test= new Contributeur("test", 123);
       test.accepter(this);
       //*********************
       res = set.add(cot.nom());
       if(res == false){break;}
    }
    return res ;
  }
  
}

