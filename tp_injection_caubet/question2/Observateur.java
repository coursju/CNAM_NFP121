package question2;

import java.util.*;
public class Observateur implements Observer{
    
    public Observateur()
    {
       System.out.println("***1 observateur cree***");
    }

    @Override
    public void update(Observable o, Object arg){
       System.out.println("methode update: "+o.toString());
    }
}
