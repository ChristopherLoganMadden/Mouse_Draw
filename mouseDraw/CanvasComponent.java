package mouseDraw;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

/**
 * {@code CanvasComponent} is the component that 
 * is used to draw the shapes.
 * @author Logan Madden
 * @version 20220324
 *
 */
public class CanvasComponent extends JComponent {
	
	private int 			    defaultWidth = 1024;
	private int				    defaultHeight= 768;
	private Shape			    drawing;
	private ArrayList<Shape>    drawn;
	private JPopupMenu			popupMenu;
	private boolean				isRectangle;		
	private boolean				isEllipse;
	private boolean				isFree;
	Path2D path	= new Path2D.Double();;
	//private 
	
	/*
	 * a class that extends mouse adapter to capture
	 * mouse events in our program
	 */
	
	private class MouseHandeler extends MouseAdapter
	{
		/*
		 * variables for the point where the 
		 * mouse drag is started and a variable 
		 * for where the pointer is being dragged 
		 * at any point on the screen
		 */
		Point 		startingPoint;
		Point		currentPoint;
		
		/**
		 * The method that overrides the {@code MouseDraged}
		 * method in the {@code MouseAdapter} class
		 * 
		 * it allows the user to drag the mouse and 
		 * to draw the shapes by dragging the mouse 
		 * 
		 * @param event a {@code MouseEvent} to be processed
		 */
		public void mouseDragged(MouseEvent event) {
			
			/*
			 * saves the current point.
			 * 
			 * instantiates the shape depending on 
			 * what is selected.
			 */
			currentPoint = event.getPoint();
			
			if(startingPoint == null) {
				startingPoint = currentPoint;				
			}
			
			if(drawing == null && isRectangle) {
				drawing = new Rectangle.Double();
			}
			
			if(drawing == null && isEllipse) {
				drawing = new Ellipse2D.Double();
			}
			
			
			if(drawing == null && isFree) {
				drawing = new Path2D.Double();
				
				/*
				 * sets the beginning point 
				 * of the drawing
				 */
				((Path2D.Double)drawing)
				.moveTo(currentPoint.getX(), currentPoint.getY());
				
			}
			
			/*
			 * if free form is not selected we
			 * set the shape from the center, so
			 * wherever the user clicks and drags from
			 * that will be the center of the shape
			 */
			
			if(isFree == false) {
			
				((RectangularShape)drawing).
				setFrameFromCenter(startingPoint, 
							   	currentPoint);
			
			} else {
				/*
				 * if free form is selected, we draw a line 
				 * between the selected points
				 */
				((Path2D.Double)drawing).lineTo
				(currentPoint.getX(), currentPoint.getY());
				
				}
			
			/*
			 * tell swing to redraw the screen
			 */
			repaint();
			
		}
		/**
		 *  an overridden method from the {@code MouseAdapter}
		 *  class that captures when the mouse is pressed.
		 *  
		 *  @param event a mouse event to be processed
		 *  
		 */
		public void mousePressed(MouseEvent event) {
			if(event.isPopupTrigger() == true) {
				popupMenu.show(event.getComponent(), 
						event.getX(), event.getY());
			}
		}
		
		/**
		 *  an overridden method from the {@code MouseAdapter}
		 *  class that captures when the mouse is released.
		 *  
		 *  @param event a mouse event to be processed
		 *  
		 */
		public void mouseReleased(MouseEvent Event) {
			if(drawing == null) {
				return;
			} 
			
			drawn.add(drawing);
			
			drawing = null;
			startingPoint =null;
		}
		
	}
	/**
	 * construct the {@code CanvasComponent} class
	 */

	public CanvasComponent() {
		/*
		 * the variables to be used 
		 */
		MouseHandeler		 	mouseHandeler;		
		ButtonGroup		     	buttonGroup;
		JRadioButtonMenuItem 	rectButton;
		JRadioButtonMenuItem	ellipseButton;
		JRadioButtonMenuItem	pathBtn;
		
		
		mouseHandeler     	 = new MouseHandeler();		
		drawn              	 = new ArrayList<Shape>();
		popupMenu          	 = new JPopupMenu();
		buttonGroup		     = new ButtonGroup();
		rectButton		     = new JRadioButtonMenuItem("Rectangle",
														true);
		ellipseButton		 = new JRadioButtonMenuItem("Ellipse",
														false);
		pathBtn	     		 = new JRadioButtonMenuItem("Free Form",
														false);
		/*
		 * sets the Rectangle to be the default shape
		 */
		this.isRectangle = rectButton.isSelected();
		
		/*
		 * adding the buttons to a button group 
		 * so the cannot all be selected at once
		 */
		
		buttonGroup.add(rectButton);
		buttonGroup.add(ellipseButton);
		buttonGroup.add(pathBtn);
		
		/*
		 * adding the radio buttons to the
		 * menu 
		 */
		popupMenu.add(rectButton);
		popupMenu.add(ellipseButton);
		popupMenu.add(pathBtn);
		
		/*
		 * setting the pop up menu to the
		 * one that we created
		 */
		setComponentPopupMenu(popupMenu);
		
		/*
		 * adding the mouseHandeler that we 
		 * made to capture mouse events
		 */
		
		addMouseListener(mouseHandeler);
		addMouseMotionListener(mouseHandeler);
		
		/*
		 * adding the actionListener to each of the 
		 * radio buttons. When each radio button is 
		 * selected, it sets the boolean variable that
		 * determines which shape is being drawn to true
		 * and sets the others to false;
		 */
		rectButton.addActionListener(event ->{
			this.isRectangle = rectButton.isSelected();
			this.isEllipse   = ellipseButton.isSelected();
			this.isFree      = pathBtn.isSelected();
		});
		
		ellipseButton.addActionListener(event ->{
			this.isRectangle = rectButton.isSelected();
			this.isEllipse   = ellipseButton.isSelected();
			this.isFree      = pathBtn.isSelected();
		});
		
		pathBtn.addActionListener(event ->{
			this.isRectangle = rectButton.isSelected();
			this.isEllipse   = ellipseButton.isSelected();
			this.isFree      = pathBtn.isSelected();
		});
		
		
	}
	/**
	 * a method to return the preferred Size as a 
	 * {@code Dimension} object. This mehod overrides the
	 * {@code getPreferredSize()} method in the {@code JComponent}
	 * class.
	 * 
	 * @return defaultWith and defaultHeight.
	 * 
	 */
	public Dimension getPreferredSize() {
		return(new Dimension(defaultWidth, defaultHeight));
	}
	
	/**
	 * called whenever swing needs to draw something
	 * to the  screen, or when {@code reapint()} is
	 * called.
	 * 
	 * @param graphics A Graphics object to be drawn 
	 * onto the screen.
	 */
	public void paintComponent(Graphics graphics) {

		Graphics2D			graphics2D;		
		graphics2D =		(Graphics2D)graphics;
		
		/*
		 * used to remember the shapes 
		 * that have been drawn and keeps
		 * them on the screen.
		 */
		for(Shape which : drawn) {
			graphics2D.draw(which);
		}
		
		/*
		 * draws the shape to the screen if we 
		 * are currently drawing.
		 */
		if(drawing != null) {
			graphics2D.draw(drawing);
		}
	}

}
