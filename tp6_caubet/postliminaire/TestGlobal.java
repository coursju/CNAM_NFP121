package postliminaire;

import question1.*;
import question2.*;
import question3.*;

public class TestGlobal extends junit.framework.TestCase{

    private static final boolean JASMIN = true; // false; // assemblé et exécuté
    private static final boolean DELETE = true && JASMIN; // suppression des fichiers.j et .class

    public void testPour() throws Exception{
        Contexte m  = new Memoire();
        Variable x  = new Variable(m,"x",3);
        Variable y = new Variable(m,"y",5);
        Variable z = new Variable(m,"z",2);

        Code code = new Code("TestsPour", m);
        VisiteurExprJasmin vej = new VisiteurExprJasmin(m,code);
        VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
        VisiteurInstJasmin vij = new VisiteurInstJasmin(vej,vbj);

        Instruction i1 =
            new Sequence(
                new Pour( 
                    new Affectation(x,new Constante(0)), new Inf(x, new Constante(10)), new Affectation(x,new Addition(x,new Constante(1))),
                    new Sequence(new Affectation(y,new Addition(y,new Constante(1))),
                        new Afficher(x))),
                new Sequence(
                    new Assertion(new Egal(x, new Constante(10))),
                    new Assertion(new Egal(y, new Constante(15)))
                )

            );

        code.setLimitStack(i1.accepter(vij));
        System.out.println(code.toString());
        
        if(JASMIN)Jasmin.assemble(code.toString(),code.nomDeClasse());
        if(JASMIN)Jasmin.exec(code.nomDeClasse());
        if(DELETE)new java.io.File(code.nomDeClasse()+".j").delete();
        if(DELETE)new java.io.File(code.nomDeClasse()+".class").delete();
    }

    public void testFactoriel() throws Exception{
        Contexte m  = new Memoire();
        Variable x  = new Variable(m,"x",5);
        Variable fact = new Variable(m,"fact",1);

        Code code = new Code("TestsFactoriel", m);
        VisiteurExprJasmin vej = new VisiteurExprJasmin(m,code);
        VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
        VisiteurInstJasmin vij = new VisiteurInstJasmin(vej,vbj);

        Instruction i = 
            new Sequence(
                new TantQue(
                    new Sup(x,new Constante(1)),
                    new Sequence(
                        new Affectation(fact,new Multiplication(fact,x)),
                        new Affectation(x,new Soustraction(x,new Constante(1))))
                ),
                new Sequence(
                  new Assertion(new Egal(fact,new Constante(fact(5)))," 5! != 120 ??? "),
                  new Afficher(fact))
            );

        code.setLimitStack(i.accepter(vij));
        System.out.println(code.nomDeClasse() + " : \n" + code.toString());

        if(JASMIN)Jasmin.assemble(code.toString(),code.nomDeClasse());
        if(JASMIN)Jasmin.exec(code.nomDeClasse());
        if(DELETE)new java.io.File(code.nomDeClasse()+".j").delete();
        if(DELETE)new java.io.File(code.nomDeClasse()+".class").delete();
    }

    public static int fact(int n){
        if(n==0) return 1;
        else return n*fact(n-1);
    }

    public static void print(int x, int y){
        System.out.println(" x : " + x + " , y : " + y);
    }

    public static int somme(int n){
        int s=0; int i=0;
        while(i<n){
            i=i+1;
            s=s+i;
        }
        return s;
    }

    public void testSomme() throws Exception{
        Contexte m = new Memoire();
        Variable n = new Variable(m,"n",100);
        Variable s = new Variable(m,"s",0);
        Variable i = new Variable(m,"i",0);

        Code code = new Code("TestSomme", m);
        VisiteurExprJasmin vej = new VisiteurExprJasmin(m,code);
        VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
        VisiteurInstJasmin vij = new VisiteurInstJasmin(vej,vbj);

        Instruction inst = 
            new Sequence(
                new TantQue(
                    new Inf(i,n),
                    new Sequence(
                        new Affectation(i,new Addition(i,new Constante(1))),
                        new Affectation(s,new Addition(s,i)))
                ),
                new Sequence(
                    new Assertion(new Egal(s, new Constante(somme(100)))," somme(100) != 5050 ??? "),
                    new Afficher(s)
                )

            );

        code.setLimitStack(inst.accepter(vij));
        System.out.println(code.nomDeClasse() + " : \n" + code.toString());
        try{
            if(JASMIN)Jasmin.assemble(code.toString(),code.nomDeClasse());
            if(JASMIN)Jasmin.exec(code.nomDeClasse());
            if(DELETE)new java.io.File(code.nomDeClasse()+".j").delete();
            if(DELETE)new java.io.File(code.nomDeClasse()+".class").delete();
        }catch(Exception e){
            fail("erreur jasmin :" + e.getMessage());
            throw e;
        }

    }

    public static int mult(int a, int b){
        int res=0;
        while(b>0){
            b=b-1;
            res=res+a;
        }
        return res;
    }

    public void testMult() throws Exception{
        //System.out.println("mult(23,17) == " + mult(23,17));
        Contexte m = new Memoire();
        Variable a = new Variable(m,"a",23);
        Variable b = new Variable(m,"b",17);
        Variable res = new Variable(m,"res",0);

        Code code = new Code("TestsMult", m);
        VisiteurExprJasmin vej = new VisiteurExprJasmin(m,code);
        VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
        VisiteurInstJasmin vij = new VisiteurInstJasmin(vej,vbj);

        Instruction inst = 
            new Sequence(
                new TantQue(
                    new Sup(b,new Constante(0)),
                    new Sequence(
                        new Affectation(b,new Soustraction(b,new Constante(1))),
                        new Affectation(res,new Addition(res,a)))
                ),
                new Sequence(
                    new Afficher(res),
                    new Assertion(new Egal(res,new Constante(mult(23,17)))," mult(23,17) != 391 ??? ")
                )
            );

        code.setLimitStack(inst.accepter(vij));
        System.out.println(code.nomDeClasse() + " : \n" + code.toString());
        try{
            if(JASMIN)Jasmin.assemble(code.toString(),code.nomDeClasse());
            if(JASMIN)Jasmin.exec(code.nomDeClasse());
            if(DELETE)new java.io.File(code.nomDeClasse()+".j").delete();
            if(DELETE)new java.io.File(code.nomDeClasse()+".class").delete();
        }catch(Exception e){
            fail("erreur jasmin :" + e.getMessage());
            throw e;
        }

    }

    public static int pgcd(int a, int b){
        while(a != b){
            if(a<b)
                b = b-a;
            else
                a = a-b;
        }
        return b;
    }

    public void testPGCD() throws Exception{

        Contexte m = new Memoire();
        Variable a = new Variable(m,"a",25);
        Variable b = new Variable(m,"b",15);

        Code code = new Code("TestsPGCD", m);
        VisiteurExprJasmin vej = new VisiteurExprJasmin(m,code);
        VisiteurBoolJasmin vbj = new VisiteurBoolJasmin(vej);
        VisiteurInstJasmin vij = new VisiteurInstJasmin(vej,vbj);

        Instruction inst = 
            new Sequence(
                new TantQue(
                    new Non(new Egal(a,b)),
                    new Selection(
                        new Inf(a,b),
                        new Affectation(b,new Soustraction(b,a)),
                        new Affectation(a,new Soustraction(a,b)))
                ),
                new Sequence(
                    new Afficher(b),
                    new Assertion(new Egal(b,new Constante(pgcd(25,15))), "pgcd(25,15) != 5 ??? ")
                )
            );

        code.setLimitStack(inst.accepter(vij));
        System.out.println(code.nomDeClasse() + " : \n" + code.toString());
        try{
            if(JASMIN)Jasmin.assemble(code.toString(),code.nomDeClasse());
            if(JASMIN)Jasmin.exec(code.nomDeClasse());
            if(DELETE)new java.io.File(code.nomDeClasse()+".j").delete();
            if(DELETE)new java.io.File(code.nomDeClasse()+".class").delete();
        }catch(Exception e){
            fail("erreur jasmin :" + e.getMessage());
            throw e;
        }

    }

}

