/**
 * This is the GridWorld Project GroupActivity NO.1 for implementing Jumper Exercise.<br/>
 * It is written during the Real Training for 08 Software Engineering of Software School
 * in Sun Yat-Sen University. 
 * Written Time: 2010-09-01
 * @author Kong Xiangpeng (08 Digital Media)
 *
 */

import info.gridworld.actor.*;
import info.gridworld.grid.*;

/**
* This class runs a world that contains a flower, a bug and a rock, 
* added at random locations and a jumper with the location at (3,1). 
* Click on empty locations to add additional actors. Click on populated 
* locations to invoke methods on their occupants. <br />
* <strong>This class is mainly used as the test and runner class for Jumper class.</strong>
*/
public class JumperRunner {

	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		
		Jumper jumper = new Jumper();
		world.add(new Location(3,1), jumper);
		world.add(new Flower());
		world.add(new Rock());
		world.add(new Bug());
		
		world.show();
	}
}
