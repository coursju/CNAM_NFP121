package question3.fifo;





import java.util.*;
import java.lang.reflect.*;

public class TestFabriqueFile extends junit.framework.TestCase{
  protected FileI q,q1;
  
  @Override
  protected void setUp(){
    this.q = Fabrique.creerFile(4);
    this.q1 = Fabrique.creerFile(10);
  }

  public void testEnfiler(){
    q.enfiler(3);
    assertTrue(q.contient(3));
    assertEquals(1, q.taille());
    q.enfiler(5);
    assertEquals(2, q.taille());
    assertTrue(q.contient(5));  
    q.enfiler(2);
    assertEquals(3, q.taille());
    assertTrue(q.contient(2));
    q.enfiler(1);
    assertEquals(4, q.taille());
    assertTrue(q.contient(1));
    assertEquals(q.taille(),q.capacite());
    try{
      q.enfiler(6);
      fail(" Exception attendue !");
    }catch(Exception e){
      assertTrue(e instanceof FilePleineException);
    }
  }
  
	public void testDefiler(){
		q.enfiler(3);
		q.enfiler(5);
	  q.enfiler(2);
	  q.enfiler(1);
	  assertEquals(3, q.defiler());
	  assertEquals(5, q.defiler());
	  q.enfiler(7);
	  assertEquals(2, q.defiler());
	  assertEquals(1, q.defiler());
	  assertEquals(7, q.defiler());
	  try{
	    q.defiler();
	    fail();
		}catch(RuntimeException e){
	  }    
	}
	
	public void testpremier(){
		q.enfiler(3); assertEquals(3, q.premier());
		q.enfiler(5); assertEquals(3, q.premier());
	  q.enfiler(2); assertEquals(3, q.premier());
	  q.enfiler(1); assertEquals(3, q.premier());
	  assertEquals(3, q.defiler());
	  assertEquals(5, q.defiler());
	  assertEquals(2, q.premier());
 	  assertEquals(2, q.defiler());
	  assertEquals(1, q.defiler());
	  try{
	    q.premier();
	    fail();
		}catch(RuntimeException e){
	  }    
	}
	
	public void testCapacite(){
	  FileI<Integer>  q = Fabrique.creerFile(5);
	  assertEquals(5,q.capacite());
	  try{
	    q = Fabrique.creerFile(-5);
	    fail();
		}catch(Exception e){
		  assertTrue( e instanceof FileCapaciteException);
	  }
	  try{
	    q = Fabrique.creerFile(0);
	    fail();
	  }catch(Exception e){
		  assertTrue( e instanceof FileCapaciteException);
	  }   
	  
	 }
	 
	public void testVider(){
	  FileI<Integer>  q = Fabrique.creerFile(5);
	  assertEquals(5,q.capacite());
	  q.enfiler(5);
	  q.enfiler(3);
	  assertEquals(5,q.capacite());
	  assertEquals(2,q.taille());
	  q.vider();
	  assertEquals(5,q.capacite());
	  assertEquals(0,q.taille());
	  try{
	    q.premier();
	    fail("une exception est attendue !!!");
	  }catch(Exception e){
	     assertTrue(e instanceof FileVideException);
	  }
	  try{
	    q.premier();
	    fail("une exception est attendue !!!");
	  }catch(Exception e){
	     assertTrue(e instanceof FileVideException);
	  }
	 }
	 
	 
	public void testEquals(){
	  FileI<Integer> q1 = Fabrique.creerFile(5);
	  FileI<Integer> q2 = Fabrique.creerFile(15);
// 	  FileI<Integer> q1 = new FileImpl(5);
// 	  FileI<Integer> q2 = new FileImpl(15);
	  assertFalse(" q1 == q2 ? ", q1.equals(q2));	  assertFalse(q2.equals(q1));
	  q2 = Fabrique.creerFile(5);
	  assertTrue(" q1 == q2 ? ", q1.equals(q2));
	  assertEquals(5,q1.capacite());
	  assertTrue(q1.equals(q2));	  assertTrue(q2.equals(q1));
	  q1.enfiler(5); q2.enfiler(5);
	  assertTrue(q1.equals(q2));
	  q1.enfiler(3);
	  assertFalse(q1.equals(q2));
	  q2.enfiler(3);
	  assertTrue(q1.equals(q2));
	  q1.enfiler(2); q2.enfiler(2);
	  assertTrue(q1.equals(q2));
	  q1.premier();q2.premier();
	  assertTrue(q1.equals(q2));
	  q1.defiler();
	  assertFalse(q1.equals(q2));
	  q2.defiler();
	  assertTrue(q1.equals(q2));
    q1.enfiler(1); q2.enfiler(1);
	  assertTrue(q1.equals(q2));
	  q1.defiler();
	  assertFalse(q1.equals(q2));
	  q2.defiler();
	  assertTrue(q1.equals(q2));
	 }
	 
	 public void testEqualsBis(){
	  FileI<Integer> q1 = Fabrique.creerFile(5);
	  FileI<Integer> q2 = Fabrique.creerFile(15);
// 	  FileI<Integer> q1 = new FileImpl(5);
// 	  FileI<Integer> q2 = new FileImpl(15);
	  assertFalse(" capacité différente ???", q1.equals(q2));	
// 	  assertFalse(q2.equals(q1));
 	  q2 = Fabrique.creerFile(5);
// 	  assertTrue(" q1 == q2 ? ", q1.equals(q2));
// 	  assertTrue(q1.equals(q2));	  assertTrue(q2.equals(q1));
	  q1.enfiler(5); 
	  assertFalse(" taille différente ???", q1.equals(q2));

	  q2.enfiler(66);
	  assertFalse(" element différent ???", q1.equals(q2));
	  q2.defiler();
	  
    q2.enfiler(5);
 	  assertTrue(" files aux mêmes contenus ???", q1.equals(q2));
 	  q1.enfiler(3); 	  q2.enfiler(32);
 	  q1.enfiler(3); 	  q2.enfiler(33);
 	  assertFalse(" files aux contenus différents ???", q1.equals(q2));
 	  q1.defiler();
 	  q2.defiler();
 	  assertFalse(" files aux contenus différents ???", q1.equals(q2));
 	  q1.vider();
 	  q2.vider();
 	  assertTrue(" deux files vides se sont pas égale ?", q1.equals(q2));
 	  
 	  q1.enfiler(2); q2.enfiler(2);
 	  assertTrue(" files aux mêmes contenus ???", q1.equals(q2));

 	  q1.premier();q2.premier();
 	  assertTrue(" files aux mêmes contenus ???", q1.equals(q2));

 	 	q1.enfiler(3); q2.enfiler(3);
 	 	q1.enfiler(4); q2.enfiler(4);
 	  assertTrue(" files aux mêmes contenus ???", q1.equals(q2));

	 }
	 
	 
	
}

