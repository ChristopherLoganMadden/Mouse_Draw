package mouseDraw;

import javax.swing.*;

/**
 * {@code CanvasFrame} is the class that 
 * extends the JFrame class and is the container 
 * to the canvas that the user will draw on.
 * @author 	Logan Madden
 * @version 202203220
 *
 */
public class CanvasFrame extends JFrame{

	/**
	 * constructs the CanvasFrame class
	 */
	public CanvasFrame() {
		
		/*
		 * adds the canvasComponent 
		 * to the frame and calls the
		 * pack() method.
		 */
		add(new CanvasComponent());
		
		pack();
		
		/*
		 * puts the frame in the middle 
		 * of the screen.
		 */
		setLocationRelativeTo(null);
		
	}

}
