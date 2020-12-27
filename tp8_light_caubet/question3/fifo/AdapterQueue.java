package question3.fifo;

import java.util.Queue;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

public class AdapterQueue<E> implements FileI<E>  {
  private Queue<E> queue;
  private int capacite;
  
  public AdapterQueue(int capacite, java.util.Queue<E> queue){
    if(capacite<=0) throw new FileCapaciteException();
    this.capacite = capacite;
    this.queue = queue;
  }
  
  public void enfiler(E e) throws FilePleineException{
    if(taille()==capacite) throw new FilePleineException();
    queue.add(e);
  }
    
  public E defiler() throws FileVideException{
    if(taille()==0) throw new FileVideException();
    return queue.remove();
  }
  public E premier() throws FileVideException{
    if(taille()==0) throw new FileVideException();
    return queue.element();
  }
  
  public void vider(){
    queue.clear();
  }
  
  public boolean contient(E e){
    return queue.contains(e);
  }
  public int taille(){
    return queue.size();
  }
  public int capacite(){
    return this.capacite;
  }
  public Iterator<E> iterator(){
    return queue.iterator();
  }
  public String toString(){
    return queue.toString();
  }
  
  private static <E> List<E> toList(Queue<E> q){
    List<E> list = new LinkedList<E>();
    for(E e : q) list.add(e);
    return list;
  }
    
   @Override
  public boolean equals(Object o){
    if(!(o instanceof FileI)) return false;
    FileI f = (FileI)o;
    if(this.capacite()!=f.capacite())return false;
    if(this.taille()!=f.taille())return false;
    if(!(o instanceof AdapterQueue)) return false;
    Queue<E> q2 = ((AdapterQueue)o).queue;
    return toList(queue).equals(toList(q2)); //queue.equals(q2); //toList(queue).equals(toList(q2)); 
  }
  
  public int hashCode(){
    return queue.hashCode();
  }
}
