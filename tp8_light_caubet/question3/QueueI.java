package question3;


/** Interface de file(FIFO), bornée.
 * @see java.util.Queue
 */
public interface QueueI extends Iterable<Integer>{
 
  /** Ajout d'un entier à la file.
   * @param i l'élément à enfiler
   * @throws RuntimeException si la file est pleine
   */
  public void add(int i);
  /** retrait d'un entier à la file.
   * @return l'élément à défiler
   * @throws RuntimeException si la file est vide
   */  
  public int remove();
  /** Lecture du prochain élément à défiler.
   * @return l'élément à défiler
   * @throws RuntimeException si la file est vide
   */ 
  public int element();

  /** Retrait de tous les éléments de la file.
   */
  public void clear();
  /** Test de la présence d'un élément.
   * @param i l'élément à tester
   * @retrun true i est présent, false autrement
   */
  public boolean contains(int i);
  /** Obtention du nombre d'élément de la file.
   * @return le nombre d'élément présent
   */
  public int size();
  /** Obtention du nombre maximal d'élément de la file.
   * @return la capacité de cette file
   */
  public int capacity();
  
  /** Invariant de classe. cf. B. Liskov.
   * @return true si l'invariant est vrai, faux autrement.
   */
  public boolean repOk();
  /** Fonction d'abstraction. cf. B. Liskov.
   * @return la représentation dite abstraite de cette file
   */
  public Object af();
  

  
}
