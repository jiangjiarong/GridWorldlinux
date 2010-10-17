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
 * A <code>BlusterCritter</code> color turn bright or dark depends on the number of critters surrounded 
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
	 private static final double DARKENING_FACTOR = 0.05;
	 private int setCritterNum;
	 //private int currentSurroundNum;
    
    
    public BlusterCritter(int c)
    {
    	super();
        setCritterNum = c;
        //currentSurroundNum = 0;
    }
    /**
     * return the number of critters in the surrounded 24 grid within 2 steps
     * @return number of critters surround
     */
    public int getCurrentSurroundNum()
    {
    	ArrayList<Actor> actors = new ArrayList<Actor>();
    	actors = getActors();
    	int count = 0;
    	for(Actor a: actors)
    		if(a instanceof Critter)
    		{
    			count++;
    			}
    	return count;
    	
    }
    
    public int getSetCritterNum()
    {
    	return setCritterNum;
    }
    /**
     * Count the number of critters in the surrounded 24 grid within 2 steps
     * and changes this critter's color to be brighter that there are fewer than c critters.
     * If there are more than c critters, color darken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
    	int count = 0;
    	for(Actor a: actors)
    		if(a instanceof Critter)
    		{
    			count++;
    			}
    	//currentSurroundNum = count;
    	if(count < setCritterNum)
    	{
    		Color c = getColor();
    		
            int red = (int) c.getRed();
            int green = (int) c.getGreen();
            int blue = (int) c.getBlue();
            if(red < 255) red++;
            if(green < 255) green++;
            if(blue < 255) blue++;
            setColor(new Color(red, green, blue));
            }
    	else
    	{
    		Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

            setColor(new Color(red, green, blue));
            }
    	}
    
    /**
     * A bluster gets the actors within two steps of its current location
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> surroundActors = new ArrayList<Actor>();
        Location loc = getLocation();
        int surRowBegin = loc.getRow() - 2;
        int surColBegin = loc.getCol() - 2;
        
        for(int temRow = surRowBegin; temRow <= surRowBegin + 4; temRow++)
        	for(int temCol = surColBegin; temCol <= surColBegin + 4; temCol++)
        	{
        		Location tempLoc = new Location(temRow,temCol);
        		if(getGrid().isValid(tempLoc))
        		{
        			Actor actor = getGrid().get(tempLoc);
        			if(actor != null && actor != this)
        				surroundActors.add(actor);
        			}
        		}
        return surroundActors;
        }
    
 
}