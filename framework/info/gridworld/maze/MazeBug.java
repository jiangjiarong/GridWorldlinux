package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private int chooseNorth;  // initial to be 1, plus 1 when chosen the north empty location
									   // minus 1 when move back from the Opposite direction
	private int chooseEast;
	private int chooseSouth;
	private int chooseWest;
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown

	/**
	 * Constructs a maze bug that add its current location and last(0,0) to its first way
	 * chooseNorth, chooseEast, chooseSouth and chooseWest help to select ways
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		
		// the the current location and the last(0,0) to its first way
		ArrayList<Location> firstWay = new ArrayList<Location>(); 
    	next = getLocation();
    	firstWay.add(next);
    	firstWay.add(last);
    	crossLocation.push(firstWay);
    	
    	//initial the choose weight to be 1
    	chooseNorth = 1;
    	chooseEast = 1;
    	chooseSouth = 1;
    	chooseWest = 1;

	}

	/**
	 * go to the end with DFS, when get the end, stop and show message
	 */
	public void act() {
	
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps" 
				+ "  choose toNorth:" + chooseNorth + " toEast:" + chooseEast + " toSouth:" + chooseSouth + " toWest:" + chooseWest;
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			move();
			//increase step count when move 
			stepCount++;
		} 
	}

	/**
	 * Find all positions that can be move to, can only move to the north, east, south and west locations
	 * that is null or contains a flower. 
	 * @param loc  the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		if (crossLocation.empty())  
			return null;
		
		//check whether the locations of four direction around can move to
		ArrayList<Location> valid = new ArrayList<Location>();
		int d = Location.NORTH;
		for (int i = 0; i < Location.FULL_CIRCLE / Location.RIGHT; i++)
	    {
			Location neighborLoc = loc.getAdjacentLocation(d);
	   	if (gr.isValid(neighborLoc) 
	   			&& (getGrid().get(neighborLoc) == null || getGrid().get(neighborLoc) instanceof Flower))
	   		{
	   			valid.add(neighborLoc);
	   		}
	      d = d + Location.RIGHT;
	    }	
	
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * select a new way move forward or move back to the crossing, the choose count cut down 1 
	 * according to the direction move back.
	 * @return true if this bug can move.
	 */
	public boolean canMove() 
	{
		ArrayList<Location> curWay = new ArrayList<Location>();
		Location loc = getLocation();
		ArrayList<Location> validLoc = new ArrayList<Location>();
		validLoc = getValid(loc);
		
		if (hasFindEnd(loc))
		{
			isEnd = true;
			return false;
		}
		
		if (validLoc.size() == 0)
			return false;
		
		// can move, first to looking for an unvisited location, if exist, build a new way 
		// if not exist, move back and cut down the choose weight
		else
		{
			// get the current way, if there is a new way, push in again before the new way push, or remove.
			curWay = crossLocation.pop();	
			Grid<Actor> gr = getGrid();
			ArrayList<Location> notVisited = new ArrayList<Location>();
			ArrayList<Location> visited = new ArrayList<Location>();
			for(Location temLoc : validLoc)
			{
				if (!(gr.get(temLoc) instanceof Flower))
		   			notVisited.add(temLoc);              //find the not visited locations
		   		else
		   			visited.add(temLoc);
			}
			
			// choose the new way to move at first, if new way not exist, move back
			if(notVisited.size() != 0)
			{
				last = loc;
				
				next = selectNext(notVisited);
				
				curWay.add(next);
				crossLocation.push(curWay);
				ArrayList<Location> newWay = new ArrayList<Location>();
				newWay.add(next);
				newWay.add(last);
				crossLocation.push(newWay);		// new way push into the stack	
			}
			else
			{
				next = curWay.get(1);
				last = getLocation();
				ArrayList<Location> validLocNext = new ArrayList<Location>();
				validLocNext = getValid(next);
				for(Location temLoc : validLocNext)
				{
					// check whether the bug move to the crossing thought the visited location
					// cut down the corresponding choose weight
					if (gr.get(temLoc) == null || validLocNext.size() > 1 )
					{		 		
						if(next.getRow() - last.getRow() == 1 && next.getCol() - last.getCol() == 0)
							chooseNorth --;
						if(next.getRow() - last.getRow() == 0 && next.getCol() - last.getCol() == -1)
							chooseEast --;
						if(next.getRow() - last.getRow() == -1 && next.getCol() - last.getCol() == 0)
							chooseSouth --;
						if(next.getRow() - last.getRow() == 0 && next.getCol() - last.getCol() == 1)
							chooseWest --;
						break;
					}	
				}
				// there is the correct direction should be choose , add up 1 to the corresponding choose weight
				ArrayList<Location> nextEmpty = new ArrayList<Location>();
				for(Location temLoc : validLocNext)
					if (gr.get(temLoc) == null)
						nextEmpty.add(temLoc);
				if (nextEmpty.size() == 1)
				{
					if(next.getRow() - nextEmpty.get(0).getRow() == 1 && next.getCol() - nextEmpty.get(0).getCol() == 0)
						chooseNorth ++;
					if(next.getRow() - nextEmpty.get(0).getRow() == 0 && next.getCol() - nextEmpty.get(0).getCol() == -1)
						chooseEast ++;
					if(next.getRow() - nextEmpty.get(0).getRow() == -1 && next.getCol() - nextEmpty.get(0).getCol() == 0)
						chooseSouth ++;
					if(next.getRow() - nextEmpty.get(0).getRow() == 0 && next.getCol() - nextEmpty.get(0).getCol() == 1)
						chooseWest ++;
				}
			}
			return true;
		}
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	

	/**
	 * according the choose weight, select the direction to go if more than one location can be choose
	 * @param locs  locations can be choose
	 * @return the location considered to be the best way
	 */
    public Location selectNext(ArrayList<Location> locs)
    {
    	/*int i = locs.size();
    	int s = (int)(Math.random()*i);
    	return locs.get(s);*/
    	
    	// just one location ,return
    	 if(locs.size() == 1)
    	 {
    		 return locs.get(0);
    	 }
    	 // find the directions can move to
    	 int north = 0;
    	 int east = 0;
    	 int south = 0;
    	 int west = 0;
    	 Location curLoc = getLocation();
    	 int row = curLoc.getRow();
    	 int col = curLoc.getCol();
    	 for(Location loc : locs)
    	 {
    		 if(row - loc.getRow() == 1 && col - loc.getCol() == 0)
    			 north = 1;
    		 if(row - loc.getRow() == 0 && col - loc.getCol() == -1)
    			 east = 1;
    		 if(row - loc.getRow() == -1 && col - loc.getCol() == 0)
    			 south = 1;
    		 if(row - loc.getRow() == 0 && col - loc.getCol() == 1)
    			 west = 1;
    	 }
    	 
    	 // set the seed according the number of choices
    	 int seed = chooseNorth*north + chooseEast*east + chooseSouth*south + chooseWest*west;
       int choose = (int) (Math.random() * seed);
       
       // choose the direction to move if the random number in the range of corresponding choose weight
    	 if(choose < chooseNorth*north && north == 1)
    	 {
    		 for(Location loc : locs)
        		 if(row - loc.getRow() == 1 && col - loc.getCol() == 0)
    	   		 {
        			 chooseNorth++;
        			 return loc;
        		 }
    	 }
    	 if(choose >= chooseNorth*north 
    			 && choose < chooseNorth*north + chooseEast*east && east == 1)
    	 {
    		 for(Location loc : locs)
        		 if(row - loc.getRow() == 0 && col - loc.getCol() == -1)
    	   		 {
    	  			 chooseEast++;
    	  			 return loc;
    	   		 }
    	 }
    	 if(choose >= chooseNorth*north + chooseEast*east 
    			 && choose < chooseNorth*north + chooseEast*east +chooseSouth*south && south == 1)
    	 {
    		 for(Location loc : locs)
        		 if(row - loc.getRow() == -1 && col - loc.getCol() == 0)
    	   		 {
    	  			 chooseSouth++;
    	  			 return loc;
    	   		 }
    	 }
    	 if(choose >= chooseNorth*north + chooseEast*east + chooseSouth*south 
    			 && choose < chooseNorth*north + chooseEast*east +chooseSouth*south + chooseWest*west 
    			 && west == 1)
    	 {
    		 for(Location loc : locs)
    	  		 if(row - loc.getRow() == 0 && col - loc.getCol() == 1)
    	   		 {
    	  			 chooseWest++;
    	  			 return loc;
    	   		 }
    	 }
    			 
		return null;
    }
    /**
     * check whether find the end at the adjacent locations
     * @param loc current location
     * @return true if find the end
     */
    public boolean hasFindEnd(Location loc)
    {
    	Grid<Actor> gr = getGrid();		
    	// just four directions can check
		int d = Location.NORTH;
		for (int i = 0; i < Location.FULL_CIRCLE / Location.RIGHT; i++)
	    {
			Location neighborLoc = loc.getAdjacentLocation(d);
	   	if (gr.isValid(neighborLoc) && gr.get(neighborLoc) instanceof Rock)
	   		if(gr.get(neighborLoc).getColor().equals(Color.RED))
	   			return true;
	      d = d + Location.RIGHT;
	    }    
		return false;
    }
}
