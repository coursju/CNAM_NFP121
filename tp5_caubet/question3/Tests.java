package question3;

import java.util.Set;
/**
 * classe de test des classes treesetfactory et hashsetfactory
 */
public class Tests extends junit.framework.TestCase {

    /**
     * test1
     * @param factory<set<Integer>>
     */
    public void test1(question3.Factory<Set<Integer>> f) throws Exception {
        Set<Integer> set = f.create();
        for (int i = 20; i > 0; i--)
            set.add(i);
    }

    /**
     * test creation
     */
    public void testCreation() {
        try {
            test1(new TreeSetFactory<Integer>());
            test1(new HashSetFactory<Integer>());
        } catch (NoSuchMethodError e) {
            fail("NoSuchMethodError : " + e.getMessage());
        } catch (Exception e) {
            fail(" exception inattendue : " + e.getMessage());
        }
    }
    
    /**
     * test treesetfactory
     */
       public void test_TreeSetFactory(){
        try{
            question3.TreeSetFactory<Integer> treeSetF1 = new question3.TreeSetFactory<Integer>();
            java.util.Set<Integer> ts = treeSetF1.create();
            assertNotNull("appel de create retourne null ???", ts);
            assertEquals(" un TreeSet est attendu ???", ts.getClass().getName(), "java.util.TreeSet");
            assertEquals(true, ts.add(5));
            assertEquals(true, ts.add(3));
            assertEquals(false, ts.add(3));
            assertEquals("[3, 5]", ts.toString());
        }catch(NoSuchMethodError e){
            fail(" exception : " + e.getMessage());
        }catch(NoClassDefFoundError e){
            fail(" exception : " + e.getMessage());
        }catch(Exception e){
            fail(" exception inattendue : " + e.getMessage());
        }
    }

    /**
     * test hashsetfactory
     */
    public void test_HashSetFactory(){
        try{
            question3.HashSetFactory<Integer> HashSetF1 = new question3.HashSetFactory<Integer>();
            java.util.Set<Integer> ts = HashSetF1.create();
            assertNotNull("appel de create retourne null ???", ts);
            assertEquals(" un HashSet est attendu ???", ts.getClass().getName(), "java.util.HashSet");
            assertEquals(true, ts.add(5));
            assertEquals(true, ts.add(3));
            assertEquals(false, ts.add(3));
            assertEquals("[3, 5]", ts.toString());
        }catch(NoSuchMethodError e){
            fail(" exception : " + e.getMessage());
        }catch(NoClassDefFoundError e){
            fail(" exception : " + e.getMessage());
        }catch(Exception e){
            fail(" exception inattendue : " + e.getMessage());
        }
    }


}
