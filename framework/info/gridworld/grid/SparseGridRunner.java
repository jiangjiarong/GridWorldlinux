package info.gridworld.grid;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
/**
* This class runs a world with additional grid choices.>
*/
public class SparseGridRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.addGridClass("info.gridworld.grid.SparseBoundedGrid");
		world.addGridClass("info.gridworld.grid.SparseBoundedGrid2");
		//world.addGridClass("SparseBoundedGrid3");
		world.addGridClass("info.gridworld.grid.UnboundedGrid2");
		world.add(new Location(2, 2), new Critter());
		Bug bob = new Bug();
		bob.setDirection(90);
		world.add(new Location(5, 2), bob);
		world.show();
	}
}