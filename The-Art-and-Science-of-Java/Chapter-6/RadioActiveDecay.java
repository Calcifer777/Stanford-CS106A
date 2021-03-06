
/* Toy program that simulates the decaying progress of
 * the atoms of a radio active material.
 * 
 * The Art and Science of Java - Chapter 6*/

import acm.program.*;
import acm.util.*;

public class RadioActiveDecay extends ConsoleProgram {
	
	public void run() {
	
		int atomsRemaining=INITIALATOMS;
		
		println("There are "+INITIALATOMS+" initially.");
		
		int year=0;
		
		while (atomsRemaining>0) {
		
			int annualDecay=0;
			
			for (int i=1; i<=atomsRemaining; i++) {
			
				if (rgen.nextBoolean(DECAYRATE)) {
					annualDecay++;
				}
			}
			
			atomsRemaining-=annualDecay;
			year++;
			println("There are "+atomsRemaining+" at "+
			"the end of year "+year+".");
			
		}
			
	}
	
	/* Creating an instance of the RGen class */
	RandomGenerator rgen = new RandomGenerator();
	
	/*Instance variables*/
	private int INITIALATOMS=10000;
	private Double DECAYRATE=0.5;

}
