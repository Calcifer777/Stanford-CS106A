
import acm.graphics.*;
import acm.program.*;

import java.awt.Color;
import java.awt.event.*;
import java.lang.reflect.Constructor;


public class SimpleDraw extends GraphicsProgram {
	
	/* Initializes the program */

	
	public void run() {
		
		
		/* Sets up the window dimensions */
		
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		
		double height = getHeight();
		
		/* Draws the icons */
		
		double iconHeight = height/(SHAPE_TYPES*2);
		double figureHeight = iconHeight*4/5;
		double lineSize = figureHeight*4/5;
		
		
		for (int i = 0; i < SHAPE_TYPES; i++) {
			
			/* Draws the outer icon frames*/
			
			add(new GRect(iconHeight/2, iconHeight/2+2*(iconHeight)*i, iconHeight, iconHeight));
		
			switch (i) {
			
			/* Draws the inner icon shapes */
			
			case 0: 	// Filled rectangle
				
				iconRectFill = new GRect((2*iconHeight-figureHeight)/2, // x_0 of the shape
						iconHeight-figureHeight/2, // y_0 of the shape
						figureHeight, figureHeight);
				iconRectFill.setFilled(true);
				add(iconRectFill);
				break;
				
			case 1: 	// Empty rectangle
			
				iconRect = new GRect((2*iconHeight-figureHeight)/2, // x_0 of the shape
						iconHeight/2+2*(iconHeight)*i + (iconHeight- figureHeight)/2, // y_0 of the shape
						figureHeight, figureHeight);
				add(iconRect);
				break;
				
			case 2:		// Filled Oval
				
				iconOvalFill = new GOval((2*iconHeight-figureHeight)/2, // x_0 of the shape
						iconHeight/2+2*(iconHeight)*i + (iconHeight- figureHeight)/2, // y_0 of the shape
						figureHeight, figureHeight);
				iconOvalFill.setFilled(true);
				add(iconOvalFill);
				break;
				
			case 3: 	// Empty Oval
				
				iconOval = new GOval((2*iconHeight-figureHeight)/2, // x_0 of the shape
						iconHeight/2+2*(iconHeight)*i + (iconHeight- figureHeight)/2, // y_0 of the shape
						figureHeight, figureHeight);
				add(iconOval);
				break;
				
			case 4: 	// Line
				
				
				iconLine = new GLine((2*iconHeight-lineSize)/2, // x_0 of the line
						iconHeight/2+2*(iconHeight)*i + (iconHeight- lineSize)/2, // y_0 of the line
						(2*iconHeight-lineSize)/2+lineSize, // x_1 of the line
						iconHeight/2+2*(iconHeight)*i + (iconHeight- lineSize)/2+lineSize);
				add(iconLine);
				break;
				
				
				
			}
		}

	}	
		
		/* Calling the mouse listener */
	
	public void init() {
		
		addMouseListeners();
		
	}
	
	/* Called on mouse press to record the coordinates of the click */
	
	public void mousePressed(MouseEvent e) {
	
		last = new GPoint(e.getPoint());
		
		gobj = getElementAt(last);
		
		/* Records the type of object last selected */
		
		if (gobj == iconRectFill || gobj == iconOvalFill || 
				gobj == iconRect || gobj == iconOval ) {
				
			selection = getElementAt(last);
			println(selection.toString());
			
		} else if ( iconLine.getBounds().contains(last)) { 
			
			selection = iconLine;
			println(selection.toString());
			
		}
		
		
		
		/* Draws a new shape */
		
		if(gobj==null && selection==iconRectFill) { 		// Start drawing a filled rectangle
			
			newRect = new GRect(e.getX(), e.getY(), 0, 0);
			newRect.setFilled(true);
			newRect.setFillColor(Color.blue);
			add(newRect);
			
		} else if (gobj==null && selection==iconOvalFill) { // Start drawing a filled oval
			
			newOval = new GOval(e.getX(), e.getY(), 0, 0);
			newOval.setFilled(true);
			newOval.setFillColor(Color.red);
			add(newOval);
			
		} else if(gobj==null && selection==iconRect) {	// Start drawing an empty rectangle
			
			newRect = new GRect(e.getX(), e.getY(), 0, 0);
			newRect.setColor(Color.blue);
			newRect.setFilled(false);
			add(newRect);
			
		} else if (gobj==null && selection==iconOval) {	// Start drawing an empty oval
			
			newOval = new GOval(e.getX(), e.getY(), 0, 0);
			newOval.setColor(Color.red);
			add(newOval);
			
		} else if (gobj==null && selection==iconLine) {	// Start drawing a line
			
			newLine = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
			newLine.setColor(Color.green);
			add(newLine);
		}
		
	}
	
	/* Called on mouse drag to reposition the object */
	
	public void mouseDragged(MouseEvent e) {
	
		double X0 =  (last.getX()<e.getX())?	last.getX()	:	e.getX();
		double Y0 =  (last.getY()<e.getY())?	last.getY()	:	e.getY();
			
		double width =  (last.getX()<e.getX())?		e.getX()-last.getX()	: 	last.getX() - e.getX();
		double height =  (last.getY()<e.getY())?	e.getY()-last.getY()	:	last.getY() - e.getY();


		if(gobj==null && (selection==iconRectFill || selection==iconRect ) ) {
			
			newRect.setBounds( X0,  Y0, width, height);
			
		} else if(gobj==null && (selection==iconOvalFill || selection==iconOval )) {
			
			newOval.setBounds( X0,  Y0, width, height);
			
		} else if (gobj==null && selection==iconLine ) {
			
			newLine.setEndPoint(e.getX(), e.getY());
			
		}
		
		
		if(gobj!=null && gobj != iconRectFill && gobj != iconOvalFill 
				&& gobj!=iconRect && gobj!=iconOval && gobj!=iconLine) {
			
			gobj.move(e.getX()-last.getX(), e.getY()-last.getY());
			last = new GPoint(e.getPoint());
			
		}
		
	}
	
	
	/* Called on mouse click to move this object to the front */
	
	public void mouseClicked(MouseEvent e) {
		
		if (gobj!=null) gobj.sendToFront();
		
	}
		
	/* Instance Variables */
	
	GRect iconRectFill;		// Filled rectangle icon
	GRect iconRect;			// Empty rectangle icon
	GOval iconOvalFill;		// Filled oval icon
	GOval iconOval;			// Empty oval icon
	GLine iconLine;			// Line icon
	
	GPoint last;			// Record the cursor location when last pressed
	GObject gobj;			// Last selected object 
	GObject selection;		// Determines what shape to draw
	
	GRect newRect;
	GOval newOval;
	GLine newLine;
	
	/* Constants */
	
	private static final int SHAPE_TYPES = 5;
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 450;

}
