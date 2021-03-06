package menu;

import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class provides all the tools required to create a generic menu 
 */
public class Menu{
	
	private int h;
	private int l;
	private ArrayList<Button> buttons;
	private Shape background;
	private Shape menu;
	private ArrayList<Shape> buttonsShape;
	private int x;
	private int y;
	private Boolean overlay=false;

	/**
	 * Creates a new menu with the buttons taken from an ArrayList
	 * @param buttons ArrayList of buttons
	 */
	public Menu(ArrayList<Button> buttons) {
		this.h=0;
		this.l=0;
		this.buttons = new ArrayList<>();
		this.buttonsShape = new ArrayList<>();
		buttons.forEach((b) -> {
			this.addButton(b);
		});
	}
	
        /**
         * Creates a new menu with the buttons contained in the ArrayList, without the background overlay
         * @param buttons
         * @param overlay 
         */
	public Menu(ArrayList<Button> buttons, Boolean overlay) {
		this.h=0;
		this.l=0;
		this.overlay=overlay;
		this.buttons = new ArrayList<>();
		this.buttonsShape = new ArrayList<>();
		buttons.forEach((b) -> {
			this.addButton(b);
		});
	}

	/**
	 * Creates an empty menu
	 */
	public Menu() {
		this.h=0;
		this.l=0;
		this.buttons = new ArrayList<>();
		this.buttonsShape = new ArrayList<>();
	}
	
	

	
	/*	Getters	*/
	public int getH() {
		return h;
	}

	public int getL() {
		return l;
	}
	
	/**
	 * Add a single button to the menu and resize it
	 * @param button 
	 */
	public void addButton(Button button){
		this.buttons.add(button);			//Adds the button to the list
		this.h+=button.getH()+20;			//The 20px adds the padding under the button
		int max=0;							//The lenght of the largest button
		for (Button b : this.buttons) {			//Search for the largest button
			if(max<b.getL()){
				max=b.getL();
			}
		}
		this.l=max+20;
	}
	
        /**
         * Initiaizes the menu and the buttons in their positions
         * @param gc
         * @throws SlickException 
         */
	public void init(GameContainer gc) throws SlickException {
		background = new Rectangle(0,0,gc.getWidth(),gc.getHeight());
		
		this.x = Math.round((gc.getWidth()-this.l)/2);
		this.y = Math.round((gc.getHeight()-this.h)/2)+10;
		menu = new Rectangle(this.x,this.y,this.l,this.h);
		
		this.x+=10;
		this.y+=10;
		int temp=this.y;
		for (Button b : this.buttons){
			buttonsShape.add(new Rectangle(this.x,temp,b.getL(),b.getH()));
			temp+=70;
		}
		
	}

        /**
         * Renders the  buttons on the screen and the background overlay
         * @param gc
         * @param g
         * @throws SlickException 
         */
	public void render(GameContainer gc, Graphics g) throws SlickException {
            if(!overlay){
		g.setColor(new Color(0,0,0, 0.65f));
		g.fill(background);
            }
		g.setColor(new Color(60, 60, 60));
		g.fill(menu);
		
		int temp=this.y;
		for(int i=0;i<buttons.size();i++){
			buttons.get(i).render(gc, g, x, temp, buttonsShape.get(i));
			temp+=70;
		}
	}
	
        /**
         * Contains the keyboard and the mouse listner for all the buttons
         * @param gc
         * @param delta
         * @param sbg
         * @throws SlickException 
         */
	public void update(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
		int posX = Mouse.getX();
		int posY = Mouse.getY();
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			gc.resume();
			return;
		}
		
		int temp=this.y;
		for (Button b : this.buttons){
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && posX>this.x && posX<(this.x+b.getL()) &&
					gc.getHeight()-posY>temp && gc.getHeight()-posY<(temp+b.getH())){
				b.update(gc, delta, sbg);
				return;
			}
			temp+=70;
		}
	}
}