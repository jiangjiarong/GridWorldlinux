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
 * A <code>zBug</code> move in a “Z” pattern. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int remainSides;

    /**
     * Constructs a zBug that move in a “Z” pattern of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        sideLength = length;
        remainSides = 3;
        turn();
        turn();
        
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove() && remainSides > 0)
        {
            move();
            steps++;
        }
        else if(steps == sideLength && canMove() && remainSides == 3)
        {
            turn();
            turn();
            turn();
            steps = 0;
            remainSides--;
        }
        else if(steps == sideLength && canMove() && remainSides == 2)
        {
            turn();
            turn();
            turn();
            turn();
            turn();
            steps = 0;
            remainSides--;
        }
        else
        {}
    }

}
