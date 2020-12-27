package question2;

import java.text.DecimalFormat;

/**
 * extrait de http://www.oreilly.com/catalog/hfdesignpat/
 */
public abstract class Beverage {
	String description = "Unknown Beverage";
        private DecimalFormat  df = new DecimalFormat("##.##"); 
        
	public String getDescription() {
		return description;
	}
 
	public abstract double cost();
	
		public String toString(){
		  return getDescription()+" $"+(double) Math.round(cost() * 100) / 100;//df.format(cost());
		}
	

}
