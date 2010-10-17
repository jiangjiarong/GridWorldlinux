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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it eats like the CrabCritter
 * and moves in the random two spaces on the right or left.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
	public QuickCrab()
    {
        setColor(Color.BLUE);
    }
    /**
     * if the two spaces to its right or left are both empty,return this two steps away right or left
     * location_list,or return the locations to move like the Crab. 
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> twoStepLoc = new ArrayList<Location>();
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        //find the two spaces away right location if valid and empty
        Location neighborRightLoc = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
        if(gr.isValid(neighborRightLoc) && gr.get(neighborRightLoc) == null)
        {
        	Location twoStepRightLoc = neighborRightLoc.getAdjacentLocation(getDirection() + Location.RIGHT);
            if(gr.isValid(twoStepRightLoc) && gr.get(twoStepRightLoc) == null)
            	twoStepLoc.add(twoStepRightLoc);
        }
      //find the two spaces away left location if valid and empty,can use the for() to combine this and above
        Location neighborLeftLoc = loc.getAdjacentLocation(getDirection() + Location.LEFT);
        {
        	Location twoStepLeftLoc = neighborLeftLoc.getAdjacentLocation(getDirection() + Location.LEFT);
            if(gr.isValid(twoStepLeftLoc) && gr.get(twoStepLeftLoc) == null)
            	twoStepLoc.add(twoStepLeftLoc);
        }
        if(twoStepLoc.size() != 0)// two steps away right or left exist
        	return twoStepLoc;
        else
        	return super.getMoveLocations();
    }
    
   
    
}

