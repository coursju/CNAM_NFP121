package question3;

 
public class QueuePrePost extends QueueDecorator{
  
 
  
  public QueuePrePost( QueueI q){
      super(q);
//     assert q.repOk();
    // manque l'exception si la capacité est négative
  }
  
  private int       tailleAvant=-1;
  private QueueImpl queueAvant;
  private int       premierElementAvant=-1;
  
  private void initVariables(){
    queueAvant = new QueueImpl(super.capacity());
    for(int i : this)
      queueAvant.add(i);
    tailleAvant = super.size();
    if(tailleAvant>0)premierElementAvant = super.element();
  }
  
  private void initVariables(int i){
    initVariables();
  }
    
  
  protected boolean pre_add(int i){
    return true; //super.size() <= super.capacity(); // relève de l'invariant ?
  }
  protected boolean post_add(int i, Exception e){
    return (e==null && super.size()==1 && super.element()==i) ||
           (e==null && tailleAvant+1 == super.size() && super.contains(i)) ||
           (e!=null && e instanceof RuntimeException && tailleAvant==super.capacity());
  }
  
  public void add(int i){
    initVariables(i);
    Exception cause = null;
    assert pre_add(i) : "pre assertion add invalide !";
    try{
        super.add(i);
    }catch(Exception e){
        cause = e;
        throw e;
    }finally{
      assert post_add(i, cause) : "post assertion add invalide !";
    }
  }
  
 
  protected boolean pre_remove(){
    return true;
  }
  protected boolean post_remove(int res, Exception e){
    return (e==null && tailleAvant-1 == super.size() && res == premierElementAvant) ||
           (e!=null && tailleAvant==0 && super.equals(queueAvant));
  }
  @Override
  public int remove(){
    initVariables();
    Exception cause = null;
    int res = -1;
    assert pre_remove()  : "pre assertion remove invalide !";
    try{
      res = super.remove();
    }catch(Exception e){
        cause = e;
        throw e;
    }finally{
       assert post_remove(res, cause) : "post assertion remove invalide !";
    }
    return res;
  }
  
  protected boolean pre_clear(){
    return true;
  }
  protected boolean post_clear(){
    return (super.size()==0);
  }
  @Override
  public void clear(){
    initVariables();
    assert pre_clear()  : "pre assertion clear invalide !";
    super.clear();
    assert post_clear() : "post assertion clear invalide !";
  }
  
  protected boolean pre_element(){
    return true;
  }
  protected boolean post_element(int res, Exception e){
    return (e == null && super.size()== tailleAvant && premierElementAvant==res && super.equals(queueAvant)) ||
           (e != null && e instanceof RuntimeException && tailleAvant==0);
  }
  @Override
  public int element(){
    initVariables();
    assert pre_element()  : "pre assertion element invalide !";
    Exception cause = null;
    int res = -1;
    try{
        res = super.element();
    }catch(Exception e){
        cause = e;
        throw e;
    }finally{
        assert post_element(res, cause) : "post assertion element invalide !";
    }
    return res;
  }
  
  protected boolean pre_contains(int i){
    return true;
  }
  protected boolean post_contains(boolean res ){
    return (super.equals(queueAvant)); // incomplète ?
  }
  public boolean contains(int i){
    initVariables(i);    
    assert pre_contains(i) : "pre assertion contains invalide !";
    boolean res = super.contains(i);
    assert post_contains(res) : "post assertion contains invalide !";
    return res;
  }
  
  protected boolean pre_size(){
    return true;
  }
  protected boolean post_size(int res){
    return (super.equals(queueAvant) && res >=0 && res <= super.capacity());
  }
  @Override
  public int size(){
    initVariables();
    assert pre_size() : "pre assertion size invalide !";
    int res = super.size();
    assert post_size(res) : "post assertion size invalide !";
    return res;
  }
  
  protected boolean pre_equals(Object o){
    return o != null && o instanceof QueueI;
  }
  protected boolean post_equals(boolean res){
    return super.equals(queueAvant);
  }
  public boolean equals(Object o){
    initVariables();
    assert pre_equals(o)  : "pre assertion equals invalide !";
    boolean res = super.equals(o);
    assert post_equals(res)  : "post assertion equals invalide !";
    return res;
  }
  
  protected boolean pre_hashCode(){
    return true;
  }
  protected boolean post_hashCode(int res){
    return this.equals(queueAvant);
  }
  
  public int hashCode(){
    initVariables();
    assert pre_hashCode()  : "pre assertion hashCode invalide !";
    int res = super.hashCode();
    assert post_hashCode(res)  : "post assertion hashCode invalide !";    
    return res;
  }
}
