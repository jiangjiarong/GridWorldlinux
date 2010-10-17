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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> “dances” by making different turns before each move.
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{

    private int[] dancingArry;
    private int curDancing;
    private int turnTimes;

    /**
     * Constructs a dancing bug that dances” by making different turns before each move
     * of a given turn times array
     * @param each move turn times array
     */
    public DancingBug(int[] dancing)
    {
       dancingArry = dancing;
       curDancing = 0;
       turnTimes = dancingArry[curDancing];
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	
    	if(turnTimes > 0)
    	{
    		turn();
    		turnTimes--;
    		}
    	else
       	{
    		if (canMove())
    			move();
    		else
    			turn();
    		if(curDancing == dancingArry.length-1)
        		curDancing = 0;
    		else
    			curDancing++;
    		turnTimes = dancingArry[curDancing];
    		
    		
    	}

    }
}
