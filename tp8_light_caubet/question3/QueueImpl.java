package question3;

import java.util.*;
import question3.fifo.*;


public class QueueImpl implements QueueI {
  // à compléter
  private int capacity;
  
  public QueueImpl(int capacity){
    // à compléter
  }
  public void add(int i){
    // à compléter
  }
  public int capacity(){
    return capacity;
  }
  public int remove(){
  // à compléter
    return 0;     // à compléter
  }
  public int element(){
    // à compléter
    return 0;
  }
  public void clear(){
     // à compléter
  }
  public int size(){
    return 0;  // à compléter
  }
  public boolean contains(int i){
    return false;  // à compléter
  }
  public Iterator<Integer> iterator(){
    return null;  // à compléter
  }
  public String toString(){
    return "à compléter";
  }
  public boolean equals(Object o){
    if(!(o instanceof QueueI)) return false;
    QueueImpl q = (QueueImpl)o;
    if(this.capacity()!=q.capacity())return false;
    if(this.size()!=q.size())return false;
    return false;  // à compléter 
  }
  public int hashCode(){
    return 0;  // à compléter
  }

  public FileI<Integer> af(){
    FileI<Integer> fifo = Fabrique.creerFile(capacity);
    //  // à compléter
    return fifo;
  }
 
  public boolean repOk(){
      return  /* à compléter && */ capacity()>0 && size()<=capacity();
  }
  

}
