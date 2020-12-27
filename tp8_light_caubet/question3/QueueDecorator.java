package question3;



import java.util.Iterator;

public abstract class QueueDecorator implements QueueI{
  private QueueI q; // décoré
  
  public QueueDecorator( QueueI q){
    this.q = q;
  }
  public void add(int i){
    q.add(i);
  }
  public int remove(){
    return q.remove();
  }
  public int element(){
    return q.element();
  }  
  public void clear(){
    q.clear();
  }
  public boolean contains(int i){
    return q.contains(i);
  }
  public int size(){
    return q.size();
  }
  public int capacity(){
    return q.capacity();
  } 
  /** Invariant de classe. cf. B. Liskov.*/
  public boolean repOk(){
    return q.repOk();
  }
  /** Fonction d'abstraction. */
  public Object af(){
    return q.af();
  }

  public Iterator<Integer> iterator(){
    return q.iterator();
  }
  
  public boolean equals(Object o){
    return q.equals(o);
  }
  
  public int hashCode(){
    return q.hashCode();
  }
  
  public String toString(){
    return q.toString();
  }
  
}
