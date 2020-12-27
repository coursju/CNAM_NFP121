package question3;

import question1.*;
import question2.*;

import java.lang.reflect.*;

public class TestsPerso extends junit.framework.TestCase{


	public void test_CompilationDeFactoriel(){
	  Contexte m = new Memoire();
	  Variable x = new Variable(m,"x",5);
	  Variable fact = new Variable(m,"fact",1);
 
  	//Instruction inst = 
  	  // new TantQue(
        // new Sup(x,new Constante(1)),
       Instruction inst =  new Sequence(
          new Affectation(fact,new Multiplication(fact,x)),
          new Affectation(x,new Soustraction(x,new Constante(1)))//)
        );
 	  
    VisiteurExpression<String> ves = new VisiteurInfixe(m);
    VisiteurExpressionBooleenne<String> vbs = new VisiteurBoolToJava(ves);
    VisiteurInstruction<String> vs = new VisiteurInstToJava(ves,vbs,4);
    
    ClasseJava cj = new ClasseJava("Fact2", "question13", inst, vs);
    System.out.println(cj.sourceComplet());
    try{
      cj.enFichier();
      // côté jnews ce source sera compilé avec javac question3/Fact.java
    }catch(Exception e){
      //e.printStackTrace();
      fail("compilation de Sup, Affectation,Sequence,TantQue ??? " + e.getMessage());
    }
}
}