package question3.fifo;

import java.util.*;

public class FileImpl<E> implements FileI<E>{

  protected LinkedList<E> liste;
  protected int capacite;
  
  public FileImpl(int capacite)throws FileCapaciteException{
    if(capacite<=0) throw new FileCapaciteException();
    this.capacite = capacite;
    this.liste = new LinkedList<E>();
  }
  public void enfiler(E e)throws FilePleineException{
    if(taille()==this.capacite) throw new FilePleineException();
    liste.addLast(e);
  }
  public int capacite(){
    return this.capacite;
  }
  public E defiler() throws FileVideException{
    if(liste.isEmpty()) throw new FileVideException();
    return liste.poll();
  }
  public E premier()throws FileVideException{
    if(liste.isEmpty()) throw new FileVideException();
    return liste.getFirst();
  }
  public boolean contient(E e){
    return liste.contains(e);
  }
  public void vider(){
    liste.clear();
  }
  public int taille(){
    return liste.size();
  }
  public Iterator<E> iterator(){
    return liste.iterator();
  }
  public String toString(){
    return liste.toString();
  }
  public boolean equals(Object o){
    if(!(o instanceof FileImpl)) return false;
    FileImpl f = (FileImpl)o;
    if(this.capacite()!=f.capacite())return false;
    if(this.taille()!=f.taille())return false;
    return liste.equals(f.liste);
  }
  public int hashCode(){
    return this.liste.hashCode();
  }
 
  public boolean repOk(){
      return liste != null && capacite>0 && taille()<=capacite;
  }
  

}
