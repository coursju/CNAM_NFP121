package question2;

import question1.*;

public class TestsDuVisiteurBoolEvaluation extends junit.framework.TestCase {

    protected Contexte m;
    protected VisiteurExpressionBooleenne<Boolean> veb;

    public void setUp() {
        m = new Memoire();
        veb = new VisiteurBoolEvaluation(new VisiteurEvaluation(m));
    }

    public void testVisiteurBoolEvaluation() {
        assertTrue(new Vrai().accepter(veb));
        assertFalse(new Faux().accepter(veb));
        assertTrue(new Sup(new Constante(5), new Constante(3)).accepter(veb));

        // etc ...
        assertTrue(new Egal(new Addition(new Constante(5), new Constante(3)), new Constante(8)).accepter(veb));
                assertTrue("(i < j)", new Inf(new Variable(m, "i", 3), new Variable(m, "j", 5)).accepter(veb));
                assertTrue(new Inf(new Variable(m, "i", 1),
                           new Addition(new Variable(m, "j", 2), new Constante(1))).accepter(veb));
                assertTrue(new Ou(new Sup(new Variable(m, "i", 8), new Variable(m, "j", 1)),
                           new Inf(new Variable(m, "i", 1), new Variable(m, "j", 8))).accepter(veb));
                assertTrue(new Et(new Sup(new Constante(5), new Constante(3)),
                           new Inf(new Variable(m, "i",2), new Variable(m, "j",6))).accepter(veb));
    }
    
}
