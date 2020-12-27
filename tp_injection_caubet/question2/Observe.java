package question2;

import java.util.*;


public class Observe extends Observable{
    
    private List<String> liste;
   
    public Observe()
    {
      liste = new ArrayList<String>();  
    }

    public void setObserver(Observer obs){
        	addObserver(obs);
        	System.out.println("nombre d observateurs: "+countObservers());
    }
    
    public void setAdd(String str){
       liste.add(str);
       //System.out.println("methode add ");
       setChanged();
       notifyObservers();
       //clearChanged();
    }
    
    public String toString(){
       return liste.toString();
    }
}
