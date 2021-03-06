/* Toy program that simulates darts thrown at a target 1KK times. 
 * The target is a circle on a square background. 
 * The limit of the ratio between darts hitting the target
 * is pi. The program empirically estimates pi based on the 
 * MC simulation results. 
 * 
 * The Art and Science of Java - Chapter 6*/

import acm.program.*;
import acm.util.*;

public class Darts extends ConsoleProgram{
	
	public void run() {
	
		int count = 0;
		for (int i=1; i<=LIMIT; i++) {
			int result=throwOutcome();
			count+=result;
		
		}
		
		Double pi = (double) 4 * count / LIMIT;
		println("The estimate of pi from "+LIMIT+" tries is "+pi+" .");
	
	}
	
	/* Method returning the outcome of a dart throw */
	
	private int throwOutcome() {
	
		Double x=rgen.nextDouble(0,1);
		Double y=rgen.nextDouble(0,1);
		int result = ((x*x+y*y<1) ? 1 : 0) ;
		return result;
	
	}
	
	/* Initializing the rgen class*/ 
	RandomGenerator rgen = new RandomGenerator();
	
	/* Instance variables */
	int LIMIT = 1000000;
}
