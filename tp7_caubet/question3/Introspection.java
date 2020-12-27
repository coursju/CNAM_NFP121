
package question3;

import java.lang.reflect.*;
import java.util.*;

public class Introspection{
    //compteur de methodes finales
    public static int nb =0;

    public static Set<Method> getHeritees(String nomDeLaClasse) throws ClassNotFoundException{
        
        Set<Method> set = new HashSet<Method>();
        Class<?> truc = Class.forName(nomDeLaClasse);
        Method[] m, n;
        
        n = Class.forName(nomDeLaClasse).getMethods();
        
        for(int i = 0; i < n.length; i++){
           if(n[i].getDeclaringClass() != Class.forName(nomDeLaClasse)
                && !n[i].toString().contains("default")
                && !Modifier.isAbstract(n[i].getModifiers())
                ){
                    set.add(n[i]); nb++;
                    //System.out.println(n[i].toString());
                }
            }
        
        if( truc != Class.forName("java.lang.Object")){truc= truc.getSuperclass();}
            
        if(Modifier.isAbstract(truc.getModifiers()) ){
            truc= truc.getSuperclass();
            //System.out.println("super"); 
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
                    //System.out.println(m[i].toString());
                //}
                }
            }
            truc= truc.getSuperclass();
        }
        
        //System.out.println(nb);
        return set;
      
}

    public static void main(String[] args) throws ClassNotFoundException{
        for(Method m : Introspection.getHeritees(args[0])){
            System.out.println(m);

        }
        System.out.println("nombre de methodes= "+nb);

    }

    /**
     * Compare le nom de methode
     * 
     */
    public static boolean compar(Method m, Method[] n){
        for(Method o : n){
            if(m.getName() == o.getName()){
                return true; 
            }
        }return false;
    }

}

