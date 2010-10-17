/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>KingCrab</code> move the actors around it one location further away,other act like a CrabCritter
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
	public KingCrab()
    {
        setColor(Color.YELLOW);
    }
    /**
     * find the locations that the actor in ArrayList<Actor> got by getActors() can move one location far away 
     * @param a Actor a 
     * @return locations a can move one location far away from the KingCrab
     */
   public ArrayList<Location> locCanMoveTo(Actor a)
   {
	   ArrayList<Location> kingAdjLoc = new ArrayList<Location>();
	   kingAdjLoc = getGrid().getValidAdjacentLocations(getLocation());
	   ArrayList<Location> canMoveLoc = new ArrayList<Location>();
	   canMoveLoc = getGrid().getEmptyAdjacentLocations(a.getLocation());
	   // a bug can move to a flower grid
	   ArrayList<Location> occupiedAdjLoc = new ArrayList<Location>();
	   ArrayList<Location> flowerLoc = new ArrayList<Location>();
	   if(a instanceof Bug)
	   {
		   occupiedAdjLoc = getGrid().getOccupiedAdjacentLocations(a.getLocation());
		   Location tempLoc;
		  
		   for (int i = 0; i < occupiedAdjLoc.size(); i++)
		   {
			   tempLoc = occupiedAdjLoc.get(i);
			   Actor tempActor = getGrid().get(tempLoc);
			   if(tempActor instanceof Flower)
			   { 
				   flowerLoc.add(tempLoc);
			   }
		   }
		   canMoveLoc.addAll(flowerLoc);
		   canMoveLoc.removeAll(kingAdjLoc);
	   }
	   
	   canMoveLoc.removeAll(kingAdjLoc);
	   return canMoveLoc; 
	   
   }
    
   /**
    * if the actors got by getActors() can move one location far away,move
    * else remove it from the grid,
    */
   public void processActors(ArrayList<Actor> actors)
   {
	   for (Actor a : actors)
	   {
		  //if ((a instanceof Rock) || (a instanceof Flower)) // rock and folwer can move or not ?
			//  a.removeSelfFromGrid();
		  //else 
		   {
			   ArrayList<Location> loc = locCanMoveTo(a);
			   if (loc.size() != 0)
			   {
				   int n = loc.size();
		         int r = (int) (Math.random() * n);
		         a.moveTo(loc.get(r));
			   }
			   else
				   a.removeSelfFromGrid();
		   }
	   }
   }
   
    
}

