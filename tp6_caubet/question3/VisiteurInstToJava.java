package question3;

import java.io.*;

import question1.*;
import question2.*;

/**
 * 
 */
public class VisiteurInstToJava extends VisiteurInstruction<String> {

	private final static int TAB = 2;
	private static final String lineSeparator = System.getProperties().getProperty("line.separator");

	private VisiteurExpression<String> vi;
	private VisiteurExpressionBooleenne<String> vb;

	private int tabulations;

	/**
	 * Cr�ation d'un visiteur d'instructions
	 * 
	 * @param vi
	 *            le visiteur d'expressions arithm�tiques
	 * @param vb
	 *            le visiteur d'expression bool�ennes
	 * @param tabulations
	 *            tabulations initiales
	 */
	public VisiteurInstToJava(VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb, int tabulations) {
		this.vi = vi;
		this.vb = vb;
		this.tabulations = tabulations;
	}

	/**
	 * Cr�ation d'un visiteur d'instructions
	 * 
	 * @param vi
	 *            le visiteur d'expressions arithm�tiques
	 * @param vb
	 *            le visiteur d'expression bool�ennes
	 */
	public VisiteurInstToJava(VisiteurExpression<String> vi, VisiteurExpressionBooleenne<String> vb) {
		this(vi, vb, 0);
	}

	/**
	 * obtention du contexte, ici celui du visiteur d'expression arithm�tiques
	 * 
	 * @return le contexte ici de vi(le visiteur d'expression)
	 */
	public Contexte contexte() {
		return this.vi.contexte();
	}

	/**
	 * Visite d'une instance de la classe Affectation.
	 * 
	 * 
	 * @param a
	 *            une affectation
	 * @return a := exp
	 */
	public String visite(Affectation a) {
		return a.v().accepter(this.vi) + "=" + a.exp().accepter(this.vi); //*
	}

	/**
	 * Visiste d'une s�quence seq(I1,I2) <br>
	 * 
	 * @param seq
	 *            une s�quence
	 * @return i1;i2
	 */
	public String visite(Sequence seq) {
		return seq.i1().accepter(this)   + " ;\n " + seq.i2().accepter(this)+" ; ";
	}

	public String visite(Selection sel) {
		String str = "if(" + sel.cond().accepter(this.vb) + " ){\n " + sel.i1().accepter(this)+ " \n}\n " ;
		if (sel.i2() != null) str = str + " else{\n " + sel.i2().accepter(this)+ " \n} " ;

		return str ;
	}

	public String visite(TantQue tq) {
		return "while(" + tq.cond().accepter(this.vb) + " ){\n " + tq.i1().accepter(this) + " ;\n}";
	}

	public String visite(Pour pour) {
                return "for(" + pour.init().accepter(this) +" ; " + pour.cond().accepter(this.vb) 
				+" ; " + pour.inc().accepter(this) + "){\n " + pour.i1().accepter(this)
				+ " ;\n}";
	}

	public String visite(Afficher a) {
		return "System.out.println(" + a.exp().accepter(this.vi) + ");\n";
	}

	public String visite(Assertion a) {
		return "assert" + a.cond().accepter(this.vb) +"\n";
	}

	private String tab(int n) {
		String str = new String();

		str = str + lineSeparator;
		for (int i = 0; i < this.tabulations + n; i++) {
			str = str + " ";
		}
		this.tabulations += n;
		return str;
	}

}
