package question1;
import java.util.*;


public class EnsembleTest extends junit.framework.TestCase {
    
    public void testSize(){
        question1.Ensemble<Integer> e1;
        e1 = new question1.Ensemble<Integer>();
        assertTrue(e1.size()==0);
        e1.add(2);
        e1.add(3);
        assertTrue(e1.size()==2);
        e1.remove(2);
        assertTrue(e1.size()==1);     
        e1.remove(3);
        assertTrue(e1.size()==0);  
    }

    public void testIterator(){
        question1.Ensemble<Integer> e1;
        e1 = new question1.Ensemble<Integer>();  
        Iterator it = e1.iterator();
        e1.add(2);
        e1.add(3);
        assertTrue(it.hasNext());
    }

    public void testAdd(){
        question1.Ensemble<Integer> e1;//, e2;
        e1 = new question1.Ensemble<Integer>();
        assertEquals(true, e1.add(2));
        assertEquals(true, e1.add(3));
        assertTrue(e1.size()==2);
        assertFalse(e1.add(2));
        assertTrue(e1.size()==2);

        
       }
    
    public void testUnion() {
        question1.Ensemble<Integer> e3, e4;
        e3 = new question1.Ensemble<Integer>();
        assertEquals(true, e3.add(2));
        assertEquals(true, e3.add(3));

        e4 = new question1.Ensemble<Integer>();
        assertEquals(true, e4.add(3));
        assertEquals(true, e4.add(4));

        question1.Ensemble<Integer> union = e3.union(e4);
        assertEquals(3, union.size());
        assertTrue(union.contains(2));
        assertTrue(union.contains(3));
        assertTrue(union.contains(4));
    }
    
    public void testInter() {
        question1.Ensemble<Integer> e3, e4;
        e3 = new question1.Ensemble<Integer>();
        assertEquals(true, e3.add(2));
        assertEquals(true, e3.add(3));

        e4 = new question1.Ensemble<Integer>();
        assertEquals(true, e4.add(3));
        assertEquals(true, e4.add(4));

        question1.Ensemble<Integer> inter = e3.inter(e4);
        assertEquals(1, inter.size());
        assertTrue(inter.contains(3));
    }

    public void testDiff() {
        question1.Ensemble<Integer> e3, e4;
        e3 = new question1.Ensemble<Integer>();
        assertEquals(true, e3.add(2));
        assertEquals(true, e3.add(3));

        e4 = new question1.Ensemble<Integer>();
        assertEquals(true, e4.add(3));
        assertEquals(true, e4.add(4));

        question1.Ensemble<Integer> diff = e3.diff(e4);
        assertEquals(1, diff.size());
        assertTrue(diff.contains(2));
    } 
    
    public void testDiffSym() {
        question1.Ensemble<Integer> e3, e4;
        e3 = new question1.Ensemble<Integer>();
        assertEquals(true, e3.add(2));
        assertEquals(true, e3.add(3));

        e4 = new question1.Ensemble<Integer>();
        assertEquals(true, e4.add(3));
        assertEquals(true, e4.add(4));

        question1.Ensemble<Integer> diffsym = e3.diffSym(e4);
        assertEquals(2, diffsym.size());
        assertTrue(diffsym.contains(2));
        assertTrue(diffsym.contains(4));
    }
    
}
