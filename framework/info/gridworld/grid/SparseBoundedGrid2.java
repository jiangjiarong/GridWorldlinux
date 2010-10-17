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

import java.util.*;

/**
 * An <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. the element in the grid store by a HashMap.
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    private HashMap<Location, E> occupantMap;
    private int rowNum;
    private int colNum;
    
    /**
     * Constructs an SparseBounded grid with given cols and rows.
     */
    public SparseBoundedGrid2(int rows, int cols)
    {
        occupantMap = new HashMap<Location, E>();
        rowNum = rows;
        colNum = cols;
    }

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
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    public E get(Location loc)
    {
    	  if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
    	  if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
    	  if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return occupantMap.remove(loc);
    }
}
