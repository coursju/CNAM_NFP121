package question1;

public class TestsACompleter extends junit.framework.TestCase{
    private Memoire m;
    private Variable i,j,k,l;
    private VisiteurExpression<Integer> ve;
    private VisiteurExpression<String>  vp,vi;

    public void setUp(){
        m  = new Memoire();
        i  = new Variable(m,"i",3);
        j = new Variable(m,"j",5);
        k = new Variable(m,"k",7);
        l = new Variable(m,"l",9);
        ve = new VisiteurEvaluation( m);
        vi = new VisiteurInfixe( m);
        vp = new VisiteurPostfixe( m);
        assertNotNull(i);assertNotNull(j);
        assertNotNull(ve);assertNotNull(vp);assertNotNull(vi);
    }

    public void testUneAddition(){
        Expression expr = new Addition(new Constante(3), new Constante(2));
        assertEquals(" 3+2 != 5 ?, curieux ",5,expr.accepter(ve).intValue());
        assertEquals(m.lire("j"),new Integer(5));
        assertEquals(m.lire("i"),new Integer(3));
        expr = new Addition(j,i);
        assertEquals(new Integer(8),expr.accepter(ve));
    }

    public void testMultiplication(){
        Expression expr = new Multiplication(j, i);
        assertEquals(" 5x3 != 15 ?, curieux", 15, expr.accepter(ve).intValue());
        expr = new Multiplication(expr, new Constante(8));
        assertEquals(" 15 * 8 != 120 ?, curieux", 120, expr.accepter(ve).intValue());
        expr = new Multiplication(k, new Constante(8));
        assertEquals(" 7 * 8 != 56 ?, curieux", 56, expr.accepter(ve).intValue());
    }

    public void testSoustraction(){
        Expression expr = new Soustraction(j, i);
        assertEquals(" 5-3 != 2 ?, curieux", 2, expr.accepter(ve).intValue());
        expr = new Soustraction(expr, new Constante(8));
        assertEquals(" 2 - 8 != -6 ?, curieux", -6, expr.accepter(ve).intValue());
        expr = new Soustraction(k, new Constante(8));
        assertEquals(" 7 - 8 != -1 ?, curieux", -1, expr.accepter(ve).intValue());
    }

    public void testDivision(){
        Expression expr = new Division(j, i);
        assertEquals(" 5/3 != 1 ?, curieux", 1, expr.accepter(ve).intValue());
        try{
            new Division(l,new Constante(0)).accepter(ve);
            fail("division par zéro ? possible ");
        }catch(ArithmeticException ae){
        }
    }

    public void testVisiteurInfixe(){

        try{
            Expression expr = new Addition(new Constante(3), new Constante(2));
            assertEquals("(3 + 2)", expr.accepter(vi));
            expr = new Addition(expr, new Constante(2));
            assertEquals("((3 + 2) + 2)", expr.accepter(vi));
            assertEquals("{i=3, j=5, k=7, l=9}", m.toString());
            expr = new Soustraction(expr, expr);
            Expression add = new Addition(new Constante(5), new Constante(3));
            Expression sub = new Soustraction(new Constante(5), new Constante(2));
            Expression mul = new Multiplication(new Constante(5), new Constante(8));
            Expression div = new Division(new Constante(5), new Constante(4));
            assertEquals("(5 + 3)", add.accepter(vi));
            assertEquals("(5 - 2)", sub.accepter(vi));
            assertEquals("(5 * 8)", mul.accepter(vi));
            assertEquals("(5 / 4)", div.accepter(vi));
            //System.out.println(expr.accepter(vi));
            assertEquals("(((3 + 2) + 2) - ((3 + 2) + 2))", expr.accepter(vi));
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }

    public void testVisiteurPostfixe(){

        try{
            Expression expr = new Addition(new Constante(3), new Constante(2));
            assertEquals("(3,2)+", expr.accepter(vp));
            expr = new Addition(expr, new Constante(2));
            assertEquals("((3,2)+,2)+", expr.accepter(vp));
            assertEquals("{i=3, j=5, k=7, l=9}",m.toString());
            expr = new Soustraction(expr, expr);
            assertEquals("(((3,2)+,2)+,((3,2)+,2)+)-", expr.accepter(vp));
            Expression add = new Addition(new Constante(14), new Constante(5));
            Expression sub = new Soustraction(new Constante(8), new Constante(5));
            Expression mul = new Multiplication(new Constante(5), k);
            Expression div = new Division(new Constante(5), l);
            assertEquals("(14,5)+", add.accepter(vp));
            assertEquals("(8,5)-", sub.accepter(vp));
            assertEquals("(5,k)*", mul.accepter(vp));
            assertEquals("(5,l)/", div.accepter(vp));
        }catch(Exception e){
            fail(" exception inattendue ???" + e.getClass().getName() + e.getMessage());
        }
    }

    public void testConstante() {
        Expression expr = new Constante(5);
        assertEquals(m.lire("j"), expr.accepter(ve));//.intValue());
    }

    public void testVariable() {
        assertEquals(9, l.accepter(ve).intValue());
    }
}
