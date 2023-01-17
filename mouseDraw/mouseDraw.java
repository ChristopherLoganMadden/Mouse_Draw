package mouseDraw;

import java.awt.*;
import javax.swing.*;

/**
 * {@code mouseDraw} is a class that allows a user
 * to click on the screen and draw either a rectangle,
 * an ellipse, or a free form shape.
 * 
 * @author Logan Madden
 * @version 202203220
 *
 */

public class mouseDraw {
	/**
	 * The entry point to the program.
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater( () ->{
			CanvasFrame			frame;
			
			frame = new CanvasFrame();
			frame.setTitle("Mouse Draw");
			frame.setVisible(true);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		});
	}
	
}
