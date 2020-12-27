package question3;

 

public class PriorityQueueRepOk extends QueueRepOk {
  
  public PriorityQueueRepOk( QueueI q){
    super(q);
    assert q.repOk() : " repOk invalide !";
  }
 
 
  public void add(int i){
    assert super.repOk() : " repOk invalide !";
    super.add(i);
    assert super.repOk() : " repOk invalide !";   
  }
  public int remove(){
    assert super.repOk() : " repOk invalide !";
    int res = super.remove();
    assert super.repOk() : " repOk invalide !";
    return res;
  }
  public void clear(){
    assert super.repOk() : " repOk invalide !";
    super.clear();
    assert super.repOk() : " repOk invalide !";
  }    
  public boolean contains(int i){
    assert super.repOk() : " repOk invalide !";
    boolean res = super.contains(i);
    assert super.repOk() : " repOk invalide !";   
    return res;
  }
  public int size(){
    assert super.repOk() : " repOk invalide !";   
    int nombre = super.size();
    assert super.repOk() : " repOk invalide !";
    return nombre;
  }
  public int capacity(){
    assert super.repOk() : " repOk invalide !";   
    int nombre = super.capacity();
    assert super.repOk() : " repOk invalide !";
    return nombre;
  }
  public boolean equals(Object o){
    assert super.repOk() : " repOk invalide !";  
    boolean res = super.equals(o);
    assert super.repOk() : " repOk invalide !";
    return res;
  }
  public int hashCode(){
    assert super.repOk() : " repOk invalide !";      
    int res = super.hashCode();
    assert super.repOk() : " repOk invalide !";
    return res;
  }
  public Object af(){ // une fonction d'abstraction avec effet de bord
    assert super.repOk() : " repOk invalide !";    
    Object res = super.af();
    assert super.repOk() : " repOk invalide !"; 
    return res;
  }
}

