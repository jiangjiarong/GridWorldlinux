/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

package info.gridworld.grid;

import java.util.ArrayList;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with an unbounded number of rows and
 * columns ,objects store by a two dimension Arrry that can become larger if require a out 
 * of array location
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E>
{
    private Object[][] occupantArray; // the array storing the grid elements,can change size
    private int sideLength;

    /**
     * Constructs an initial unbounded grid with the set dimensions.
     */
    public UnboundedGrid2()
    {
    	  sideLength = 16;
        occupantArray = new Object[sideLength][sideLength];
        
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }
    /**
     *all valid locations have non-negative row and column values
     */
    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < sideLength; r++)
        {
            for (int c = 0; c < sideLength; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
    	if (loc == null )
            throw new NullPointerException("loc == null");
    	if (!isValid(loc))
    		   throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
    	if (loc.getRow() >= sideLength || loc.getCol() >= sideLength) // location out of current array,no object exist
    		return null;
    	else
    		return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
    	if (loc == null )
            throw new NullPointerException("loc == null");
    	if (!isValid(loc))
    		   throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
      if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
      E oldOccupant = get(loc);
      
        // make a new array that big enough to store the object
      if (loc.getRow() >= sideLength || loc.getCol() >= sideLength)  
        {
    	  	int biggerSize = sideLength;
    	  	while(biggerSize <= loc.getRow() || biggerSize <= loc.getCol())
    	  		biggerSize *= 2;
    	  	
    	  	Object[][] biggerArray = new Object[biggerSize][biggerSize];
    	  	for (int r = 0; r < sideLength; r++)
            {
    	  		for (int c = 0; c < sideLength; c++)
                {
    	  			// put all the objects in the old occupantArray to the new.
    	  			biggerArray[r][c] = occupantArray[r][c];
                }
            }
    	  	sideLength = biggerSize;
    	  	occupantArray = biggerArray;
        }
      occupantArray[loc.getRow()][loc.getCol()] = obj;
      return oldOccupant;
    }

    public E remove(Location loc)
    {
    	if (loc == null )
            throw new NullPointerException("loc == null");
    	if (!isValid(loc))
    		   throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
    	// out of the current array,nothing to be removed
    	if (loc.getRow() >= sideLength || loc.getCol() >= sideLength)
    		return null;
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
}
