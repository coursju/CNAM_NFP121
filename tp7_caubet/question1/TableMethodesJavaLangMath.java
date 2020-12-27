package question1;

import java.lang.reflect.Method;

import java.util.*;

/** Gestion par introspection des m�thodes de la classe java.lang.Math,<br>
 *  Seules sont conserv�es :les m�thodes retournant un double et d'arit� 1 ou 2<p>
 *   Note : Emploi du Pattern Singleton pour cette table, accessible uniquement en lecture par des accesseurs<p>
 * La convention de nommage est la suivante :
 *        le "Nom" de la fonction suivi de "(double)"  exemple : "sqrt(double)"
 *  ou le "Nom" de la fonction suivi de "(double, double)"  exemple : "IEEEremainder(double, double)"
 */

final public class TableMethodesJavaLangMath {
    /** Singleton */
    private static TableMethodesJavaLangMath instanceUnique = null;

    public static TableMethodesJavaLangMath getInstance(){
        synchronized(TableMethodesJavaLangMath.class){
            if (instanceUnique==null){
                instanceUnique = new TableMethodesJavaLangMath();
            }
            return instanceUnique;
        }
    }

    private TableMethodesJavaLangMath(){
    } 
    // fin du Singleton

    /** 
     * @param  nomDeLaM�thode Nom de la fonction + "(double)" ou "(double, double)"
     * @return true si la fonction est pr�sente
     */
    public boolean cetteMethodeEstPresente(String nomDeLaM�thode){
        return listeDesMethodes.contains(nomDeLaM�thode); // � compl�ter
    }

    /** 
     * @param  nomDeLaM�thode Nom de la fonction + "(double)" ou "(double, double)"
     * @return true si la fonction est binaire, d'arit� 2
     * @throws NoSuchElementException si la m�thode demand�e n'existe pas
     */
    public boolean cetteMethodeAttendDeuxParametres(String nomDeLaMethode)throws NoSuchElementException{
        
            if( cetteMethodeEstPresente(nomDeLaMethode)){
           if(tableDesMethodes.get(nomDeLaMethode).getParameterTypes().length == 2){
               //System.out.println("1 bla");
            return true;
            }
        }else{throw new NoSuchElementException();}
    
        return false; // � compl�ter
    }

    /** 
     * @param  nomDeLaM�thode Nom de la fonction + "(double)" ou "(double, double)"
     * @return true si la fonction est unaire, d'arit� 1
     * @throws NoSuchElementException si la m�thode demand�e n'existe pas 
     */
    public boolean cetteMethodeAttendUnSeulParametre(String nomDeLaMethode)throws NoSuchElementException{        
    
            if( cetteMethodeEstPresente(nomDeLaMethode)){
           if(tableDesMethodes.get(nomDeLaMethode).getParameterTypes().length == 1){
               //System.out.println("1 bla");
            return true;
            }
        }else{throw new NoSuchElementException();}
    
       
        return false;
    }

    /** 
     * Obtention de la liste ordonn�e des m�thodes
     * @return la liste tri�e des fonctions issues de java.lang.Math
     */  
    public String[] listeDesMethodes(){
        String[] tab = new String[listeDesMethodes.size()];
        Iterator it = listeDesMethodes.iterator();
        int i =0;
        while(it.hasNext()){
            tab[i] = (String)it.next();
            i++;
        }
        return tab; // � compl�ter
    }

    /** Invocation d'une m�thode de la table
     * @param  nomDeLaM�thode Nom de la fonction + "(double)"
     * @param arg1 l'op�rande
     * @return un r�sultat
     * @throws NoSuchElementException si la m�thode demand�e n'existe pas ou une exception lev�e par la fonction appel�e
     */
    public double invoquer(String nomDeLaMethode,double arg1) throws Exception{
        double ret = 0.0;
        if(cetteMethodeEstPresente(nomDeLaMethode)&&cetteMethodeAttendUnSeulParametre(nomDeLaMethode)){
        try{
        Method m = tableDesMethodes.get(nomDeLaMethode);
        Object obj = m.invoke(null,arg1);
        ret = (double)obj;
        }catch(Exception e){}
        }else{throw new NoSuchElementException(); }
        return ret;
    }

    /** Invocation d'une m�thode de la table
     * @param  nomDeLaM�thode Nom de la fonction + "(double, double)"
     * @param arg1 l'op�rande
     * @return un r�sultat
     * @throws NoSuchElementException si la m�thode demand�e n'existe pas ou une exception lev�e par la fonction appel�e
     */  
    public double invoquer(String nomDeLaMethode, double arg1, double arg2) throws Exception{
        double ret = 0.0;
        if(cetteMethodeEstPresente(nomDeLaMethode)&&cetteMethodeAttendDeuxParametres(nomDeLaMethode)){
        try{
        Method m = tableDesMethodes.get(nomDeLaMethode);
        Object obj = m.invoke(null,arg1,arg2);
        ret = (double)obj;
        }catch(Exception e){}
        }else{throw new NoSuchElementException(); }
        return ret;
    }

    /**
     * Le dictionnaire contient la liste des m�thodes disponibles.
     * un choix de dictionnaire pourrait �tre pour la Cl� une String soit le Nom de la fonction + "(double)" ou "(double, double)".<br>
     *  et en Valeur =  la Method correspondante.
     *  ou tout autre choix
     */
    private static Map<String,Method> tableDesMethodes;// � compl�ter ...
    private static TreeSet<String> listeDesMethodes;


    /** bloc statique d'intialisation de la table des m�thodes */
    static  {
        Class<?> math = Math.class;
        Method[] m = math.getMethods();
        tableDesMethodes = new TreeMap<String,Method>();
        String nomMethod = new String("");
        listeDesMethodes = new TreeSet<String>();
        for(int i = 0; i < m.length; i++)
        {   
            
            if(m[i].getGenericReturnType() == double.class){
                Class[] p = m[i].getParameterTypes();
                if(p.length > 1 
                && p[0].getName().contains("double")
                && p[1].getName().contains("double")
                && m[i].getName() != "fma"){
                nomMethod = m[i].getName()+"("+p[0].getName()+", "+p[1].getName()+")"; 
                tableDesMethodes.put(nomMethod, m[i]);
                listeDesMethodes.add(nomMethod);
                } 
                if(p.length == 1 
                && p[0].getName().contains("double")){
                nomMethod = m[i].getName()+"("+p[0].getName()+")";
                tableDesMethodes.put(nomMethod, m[i]);
                listeDesMethodes.add(nomMethod);
                }

            }

        } //System.out.println(listeDesMethodes.size()+" --"+ tableDesMethodes.size());
        //System.out.println(listeDesMethodes);
        //System.out.println(tableDesMethodes);
    }

}
