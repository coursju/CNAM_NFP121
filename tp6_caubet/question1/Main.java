package question1;

public class Main{

    
    public static void main (String[] args){
        Memoire m;
        Variable i,j;
        VisiteurExpression<Integer> ve;
        VisiteurExpression<String>  vp, vi;

        m  = new Memoire();
        i  = new Variable(m,"i",3);
        j = new Variable(m,"j",5);
        ve = new VisiteurEvaluation( m);
        vi = new VisiteurInfixe( m);
        vp = new VisiteurPostfixe( m);
        
            Expression expr = new Addition(new Constante(3), new Constante(2));
            System.out.println(expr.accepter(vi));
            System.out.println(expr.accepter(vp));
            expr = new Addition(expr, new Constante(2));
            System.out.println(expr.accepter(vi));
            System.out.println(expr.accepter(vp));
            expr = new Soustraction(expr, expr);
            System.out.println(m.lire("i").intValue());
            System.out.println(expr.accepter(vi));
            System.out.println(expr.accepter(vp));
        
    }
}
