package question2;

/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
//public abstract class Chocolate { // à rendre concréte
public class Chocolate extends Beverage {
  
	public Chocolate() {
		description = "Chocolate";
	}
  
	public double cost() {
		return 2.10;
	}
	

	
}
