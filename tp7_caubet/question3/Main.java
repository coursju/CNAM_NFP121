package question3;
import java.lang.reflect.*;
import java.util.*;
/**
 * Décrivez votre classe Main ici.
 *
 * @author (votre nom)
 * @version (un numéro de version ou une date)
 */
public class Main{

    
    public static int nb =0;
    public static void  Main()throws ClassNotFoundException{
 
        String nomDeLaClasse = "javax.swing.JButton";//"java.util.HashSet";//"java.util.Vector";//"javax.swing.JApplet";//"java.util.Stack";//"junit.framework.TestCase";//"java.util.AbstractSet";//"java.util.WeakHashMap";//"java.util.AbstractCollection";//"java.lang.Object";//
        Set<Method> set = new HashSet<Method>();
        Class<?> truc = Class.forName(nomDeLaClasse);//.getSuperclass();
        Method[] m, n;
        
        n = Class.forName(nomDeLaClasse).getMethods();
        
        for(int i = 0; i < n.length; i++){
           if(n[i].getDeclaringClass() != Class.forName(nomDeLaClasse)
                && !n[i].toString().contains("default")
                && !Modifier.isAbstract(n[i].getModifiers())
                ){
                    set.add(n[i]); nb++;
                    System.out.println(n[i].toString());
                }
            }
          
        if( truc != Class.forName("java.lang.Object")){truc= truc.getSuperclass();}
    
            
        if(Modifier.isAbstract(truc.getModifiers())){//|| Modifier.isInterface(truc.getModifiers())){
            truc= truc.getSuperclass();
            System.out.println("super"); 
        }      
        
        while(truc != null){

            m = truc.getDeclaredMethods();//Declared
            
            for(int i = 0; i < m.length; i++){
                
                //System.out.println(m[i].toString());
                if(m[i].getDeclaringClass() != Class.forName(nomDeLaClasse)
                //&& !m[i].toString().contains("default")
                && Modifier.isProtected(m[i].getModifiers())
                //&& !Modifier.isAbstract(m[i].getModifiers())
                //&& (Modifier.isPublic(m[i].getModifiers()) || Modifier.isProtected(m[i].getModifiers())) )
                //{if(!(compar(m[i], n))
                    ){
                    set.add(m[i]); nb++;
                    System.out.println(m[i].toString());
                //}
                }
            }
            truc= truc.getSuperclass();
        }
        
        System.out.println(nb);
        
      
}
        public static void compar(Method[] m, Method[] n){
           for(Method o : n){
               for(Method p : m){
                 if(p.equals(o)){
                   System.out.println(p.getName() +"== "+o.getName());//+" tostring= "+ o.toString());
                   //return true; 
                 }
               }
           }//return false;
        }
}

