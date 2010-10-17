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
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. the element in the grid store by a Sparse Array<br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<Object> extends AbstractGrid<Object>
{
    private SparseGridNodeList[] occupantArray; // the sparse array storing the grid elements
    private int rowNum;
    private int colNum;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rowNum = rows;
        colNum = cols;
        occupantArray = new SparseGridNodeList[rows];
        for(int i = 0; i < rows; i++)
        		occupantArray[i] = new SparseGridNodeList();
        
    }
    /*public int getOccupantNum()
    {
    	ArrayList<Location> locs = new ArrayList<Location>();
    	locs = getOccupiedLocations();
    	return locs.size();
    }*/
    public int getNumRows()
    {
        return rowNum;
    }

    public int getNumCols()
    {
        return colNum;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all the row list, in each row list(SparseGridNodeList) find the occupied locations.
        for (int r = 0; r < getNumRows(); r++)
          {
        		SparseGridNode occupant = occupantArray[r].getFirst();
        		while(occupant != null)
        		{
        			Location temp = new Location(r,occupant.getColNum());
        			theLocations.add(temp);
        			occupant = occupant.getNext();
        		}
          }
        
        return theLocations;
    }

    public Object get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        int r = loc.getRow();
        int c = loc.getCol();
        // Look at the require row list to find the object with colNum
        SparseGridNode locNode = occupantArray[r].getFirst();
        while(locNode != null)
     	   {
     			if(locNode.getColNum() == c)
     				return (Object) locNode.getOccupant();
     			else
     				locNode = locNode.getNext();
     	   }
        return null;
    }

    public Object put(Location loc, Object obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        
        int r = loc.getRow();
        int c = loc.getCol();
        Object oldOccupant;
        // find the old occupant according colNum,change to the new object,return the old
        SparseGridNode locNode = occupantArray[r].getFirst();
        while(locNode != null)
     	   {
     			if(locNode.getColNum() == c)
     			{
     				oldOccupant = (Object) locNode.setOccupant(obj);
     				return oldOccupant;
     			}
     			else
     				locNode = locNode.getNext();
     	   }
        // a empty location,insert a new node
        SparseGridNode newNode = new SparseGridNode(c,obj);
        occupantArray[r].insertFirst(newNode);
        return null;
    }

    public Object remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        int r = loc.getRow();
        int c = loc.getCol();
        Object oldOccupant;
        // use the delNode method in SparseGridNodeList to remove and rebuild.
        oldOccupant = (Object) occupantArray[r].delNode(c).getOccupant();
        return oldOccupant;
    }
}
