

package info.gridworld.grid;

/** 
 * A <code>SparseGridNode</code> is a element of the linked list which used in a sparse array.
 * @author Eduardo
  */
public class SparseGridNode
{
   private Object occupant;
   private int col;
   private SparseGridNode next;//a Reference of next node
   
   public SparseGridNode()
   {
	   col = -1;
	   occupant = null;
	   next  = null;
   }
   
   public SparseGridNode(int colNum, Object obj)
   {
	   col = colNum;
	   occupant = obj;
	   next  = null;
   }
   
   public void setNext(SparseGridNode s)
   {
	   next = s;
   }
   

   public SparseGridNode getNext()
   {
	   return next;	  
   }
   public int getColNum()
   {
	   return col;	   
   }
   
   public Object getOccupant()
   {
	   return occupant;	   
   }
   
   public Object setOccupant(Object obj)
   {
	   Object oldOccupant = occupant;
	   occupant = obj;
	   return oldOccupant;
   }
   

}
