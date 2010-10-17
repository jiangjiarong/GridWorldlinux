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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonCritter extends Critter
{
	 private static final double DARKENING_FACTOR = 0.05;
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
            {
        	   Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

            setColor(new Color(red, green, blue));
        	
            }
        else
        {
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
        }
    }

    /**
     * Turns towards the new location as it moves.//drop a flower like the bug but can't move to a 
     * grid that contain a flower 
     */
    public void makeMove(Location loc)
    {
    	  setDirection(getLocation().getDirectionToward(loc));
        //Grid<Actor> gr = getGrid();
        //Location pre = getLocation();
        super.makeMove(loc);
        //if(!pre.equals(loc))
        //{
        //Flower flower = new Flower(getColor());            
        //flower.putSelfInGrid(gr, pre);
        //}
    }
    
}
