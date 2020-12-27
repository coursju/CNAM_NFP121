package syntaxe_exemples;

import java.util.*;

public class ExemplesSyntaxeConfiguration{
    private static boolean T = false; // true; // false
    static{
        //T = T || System.getProperties().getProperty("verbose").equals("true");
    }
    // Successivement (cf. config.java)
    //   exemples d'affectation de variables de types primitifs
    //   exemples d'affectation d'instance "wrapper" de ces types
    //   exemples de tableaux de types primitifs
    //   exemples de tableaux de beans
    //   exemples d'appels de méthodes (appels des "mutateurs" sans la variable d'instance)
   

    private byte b;
    private short s;
    private int i;
    private long l;
    private float f;
    private double d;
    private boolean bool;
    private char c;
    private String str;

    private Byte b_;
    private Short s_;
    private Integer i_;
    private Long l_;
    private Float f_;
    private Double d_;
    private Boolean bool_;
    private Character c_;

    private byte[] b_array;
    private short[] s_array;
    private int[] i_array;
    private long[] l_array;
    private float[] f_array;
    private double[] d_array;
    private boolean[] bool_array;
    private char[] c_array;

    private Byte[] byte_array;
    private Integer[] integer_array;
    private Boolean[] boolean_array;
    private String[] string_array;

    private ExemplesSyntaxeConfiguration[] exemples_array;
    private List<ExemplesSyntaxeConfiguration> exemples_list;
    
    private List<Integer> integer_list = new ArrayList<>();
    

    public void setB(byte b){
        if(T)System.out.println("appel du mutateur setB(" + b + ")");
        this.b = b;
    }

    public byte getB(){return this.b;}

    public void setS(short s){
        if(T)System.out.println("appel du mutateur setS(" + s + ")");
        this.s = s;
    }

    public short getS(){return this.s;}

    public void setI(int i){
        if(T)System.out.println("appel du mutateur setI(" + i + ")");
        this.i = i;
    }

    public int getI(){return this.i;}

    public void setL(long l){
        if(T)System.out.println("appel du mutateur setL(" + l + ")");
        this.l = l;
    }

    public long getL(){return this.l;}

    public void setF(float f){
        if(T)System.out.println("appel du mutateur setF(" + f + ")");
        this.f = f;
    }

    public float getF(){return this.f;}

    public void setD(double d){
        if(T)System.out.println("appel du mutateur setD(" + d + ")");
        this.d = d;
    }

    public double getD(){return this.d;}

    public void setBool(boolean b){
        if(T)System.out.println("appel du mutateur setBool(" + b + ")");
        this.bool = b;
    }

    public boolean getBool(){return this.bool;}

    public void setC(char c){
        if(T)System.out.println("appel du mutateur setC(" + c + ")");
        this.c = c;
    }

    public char getC(){return this.c;}

    // wrapper
    public void setB_(Byte b){
        if(T)System.out.println("appel du mutateur setB_(" + b + ")");
        this.b_ = b;
    }

    public Byte getB_(){return this.b_;}

    public void setS_(Short s){
        if(T)System.out.println("appel du mutateur setS_(" + s + ")");
        this.s_ = s;
    }

    public Short getS_(){return this.s_;}

    public void setI_(Integer i){
        if(T)System.out.println("appel du mutateur setI_(" + i + ")");
        this.i_ = i;
    }

    public Integer getI_(){return this.i_;}

    public void setL_(Long l){
        if(T)System.out.println("appel du mutateur setL_(" + l + ")");
        this.l_ = l;
    }

    public Long getL_(){return this.l_;}

    public void setF_(Float f){
        if(T)System.out.println("appel du mutateur setF_(" + f + ")");
        this.f_ = f;
    }

    public Float getF_(){return this.f_;}

    public void setD_(Double d){
        if(T)System.out.println("appel du mutateur setD_(" + d + ")");
        this.d_ = d;
    }

    public Double getD_(){return this.d_;}

    public void setBool_(Boolean b){
        if(T)System.out.println("appel du mutateur setBool_(" + b + ")");
        this.bool_ = b;
    }

    public Boolean getBool_(){return this.bool_;}

    public void setC_(Character c){
        if(T)System.out.println("appel du mutateur setC(" + c + ")");
        this.c_ = c;
    }

    public Character getC_(){return this.c_;}

    public void setStr(String str){
        if(T)System.out.println("appel du mutateur setStr(" + str + ")");
        this.str = str;
    }

    public String getStr(){return this.str;}

    public void setConcat(String s){
        if(T)System.out.println("appel du mutateur setConcat(" + s + ")");
        this.str = this.str + s;
    }

    public void setB_array(byte[] b){
        if(T)System.out.println("appel du mutateur setB_array(" + b + ")");
        this.b_array = b;
    }
    public byte[] getB_array(){ return b_array;}

    public void setByte_array(Byte[] b){
        if(T)System.out.println("appel du mutateur setByte_array(" + b + ")");
        this.byte_array = b;
    }

    public Byte[] getByte_array(){ return byte_array;}

    public void setInteger_array(Integer[] i){
        if(T)System.out.println("appel du mutateur setInteger_array(" + i + ")");
        this.integer_array = i;
    }

    public Integer[] getInteger_array(){ return integer_array;}

    public void setString_array(String[] s){
        if(T)System.out.println("appel du mutateur setString_array(" + s + ")");
        this.string_array = s;
    }

    public String[] getString_array(){ return string_array;}

    public void setExemples_array(ExemplesSyntaxeConfiguration[] exemples){
        if(T)System.out.println("appel du mutateur setExemples_array(" + exemples + ")");
        this.exemples_array = exemples;
    }


    public void setExemples_list(List<ExemplesSyntaxeConfiguration> liste){
        if(T)System.out.println("appel du mutateur setExemples_list(" + liste + ")");
        this.exemples_list = liste;
    }
    
    public void setElement(int elt){
        integer_list.add(elt);   
        if(T)System.out.println("appel du mutateur setElement(" + integer_list + "), ajout à la liste");
    }
    public List<Integer> getInteger_list(){
        return integer_list;
    }
    
    List<Collection<Integer>> liste = new ArrayList<>();
    public void setCollections(Collection<Integer>[] collections){
        for(Collection<Integer> c : collections){
           liste.add(c);
        }
    }
    public List<Collection<Integer>> getCollections(){
        return liste;
    }
    public List<Integer> getAllElementsCollections(){
      List<Integer> l = new ArrayList<>();
      for(Collection<Integer> c : liste){
           l.addAll(c);
      }
        return l;
    }
    
}
