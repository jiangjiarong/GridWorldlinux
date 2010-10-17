package info.gridworld.grid;

/** 
 * A <code>SparseGridNodeList</code> is a linked list which used in a sparse array.
 * @author Eduardo
  */
public class SparseGridNodeList
{
	private SparseGridNode first;
	//private int size;
	
	public SparseGridNodeList()
	{
		first = null;
		//size = 0;
	}
	
	/**
	 * insert element at the first 
	 */
	public void insertFirst(SparseGridNode inNode)
	{
		SparseGridNode link = new SparseGridNode();
		link = inNode;
		link.setNext(first);
		first = link;
		//size++;
	}
	/**
	 * delete a node in the list according to the given colNum
	 * @param col
	 * @return the removed node
	 */
	public SparseGridNode delNode(int col)
	{
		if(first == null || first.getColNum() == col)
		{
			SparseGridNode oldNode = first;
			first = first.getNext();
			return oldNode;
		}
		else
		{
			SparseGridNode curNode = first.getNext();
			SparseGridNode prevNode = getFirst();
		   	while(curNode != null)
	     	{
	     		if(curNode.getColNum() == col)
	     		{
	     			prevNode.setNext(curNode.getNext());
	     			return curNode;
	     		}
	     		else
	     		{
	     			prevNode = curNode;
	     			curNode = curNode.getNext();
	     		}
	     	}
		   	return null;
		}
	}
	
	/**
	 * get the first node of the list
	 * @return the first node or null if list is empty
	 */
	public SparseGridNode getFirst()
	{
		return first;
	}

	
}  