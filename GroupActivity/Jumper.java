/**
 * This is the GridWorld Project GroupActivity NO.1 for implementing Jumper Exercise.<br/>
 * It is written during the Real Training for 08 Software Engineering of Software School
 * in Sun Yat-Sen University. 
 * Written Time: 2010-09-01
 * @author Kong Xiangpeng (08 Digital Media)
 *
 */

import info.gridworld.actor.*;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
//import info.gridworld.world.*;

import java.awt.Color;

/**
 * A <code>Jumper</code> move two cells forward in each move.
 * It jumps flowers and rocks.It doesn't leave anything behind it when it jumps.<br/>
 * It extends from Actor Class.<br/>
 */
public class Jumper extends Actor {

	/**
	 * Constructor a red Jumper.
	 */
	public Jumper() {
		setColor(Color.RED);
	}
	
	/**
	 * Constructor a Jumper with the given color.
	 * @param color :the color of this jumper.
	 */
	public Jumper(Color color) {
		setColor(color);
	}
	
	/**
	 * Constructor a Jumper with the given direction.
	 * @param direction :the direction of the jumper.
	 */
	public Jumper(int direction) {
		setDirection(direction);
	}
	
	/**
	 * Moves if this jumper can move, turns otherwise.
	 */
	public void act() {
		if (canMove()) {
			move();
		}
		else {
			turn();
		}
	}
	
	/**
	 * Moves the Jumper forward two cells,putting nothing it previously occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location next = getLocation().getAdjacentLocation(getDirection());
		Location nextTwo = next.getAdjacentLocation(getDirection());
		if (gr.isValid(nextTwo)) {
			moveTo(nextTwo);
		}
		else {
			removeSelfFromGrid();
		}
	}
	
	/**
	 * Turns the Jumper 45 degrees to the right without changing its location.
	 */
	public void turn() {
		setDirection(getDirection() + Location.HALF_RIGHT);
	}
	
	/**
	 * Tests whether the Jumper can move forward two cells into a location that is empty and
	 * the location in front of the Jumper contains a flower or rock or just empty.
	 * @return true if it can move
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return false;
		}
		Location next = getLocation().getAdjacentLocation(getDirection());
		Location nextTwo = next.getAdjacentLocation(getDirection());
		
		/*Tests whether the next and nextTwo location in front of the jumper is valid.*/
		if (!gr.isValid(next)) {
			return false;
		}
		if (!gr.isValid(nextTwo)) {
			return false;
		}
		Actor neighbor = gr.get(next);
		Actor neighborTwo = gr.get(nextTwo);
		
		/*Tests whether the neighbor is contains a actor that is not flower or rock.*/
		if (neighbor!=null && !(neighbor instanceof Flower || neighbor instanceof Rock)) {
			return false;
		}
		
		/*Tests whether the second cell in front of it contains a actor.*/
		return neighborTwo == null;
	}
}
