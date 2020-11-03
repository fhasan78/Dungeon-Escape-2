/**
 * CS1027-Assignment3
 * 
 * @author Fatima Hasan 
 * @param <T>
 *            This class has four instance variables, element, next, prev, and
 *            priority, and it represents the nodes of the doubly linked list
 *            used to implement the priority queue
 */
public class DPriorityNode<T> {
	private T element; // reference to the data item stored in this node
	private DPriorityNode<T> next; // reference to the next node in the linked
									// list
	private DPriorityNode<T> prev; // reference to the previous node in the
									// linked list
	private double priority; // this is the priority of the data item stored in
								// this node

	/**
	 * The DPriorityNode (T data, double prio) method creates a node storing the
	 * given data item and priority
	 * 
	 * @param data
	 * @param prio
	 */
	public DPriorityNode(T data, double prio) {
		element = data;
		priority = prio;
		next = null;
		prev = null;
	}

	/**
	 * The DPriorityNode () method creates an empty node,with null data and zero
	 * priority
	 */
	public DPriorityNode() {
		element = null;
		priority = 0;
		next = null;
		prev = null;
	}

	/**
	 * The getPriority() method returns the priority of the node
	 * 
	 * @return the priority of the node
	 */
	public double getPriority() {
		return priority;
	}

	/**
	 * The getElement() method returns the element(data item)
	 * 
	 * @return the element(data item)
	 */
	public T getElement() {
		return element;
	}

	/**
	 * The getNext() method gets the next node
	 * 
	 * @return the next node
	 */
	public DPriorityNode<T> getNext() {
		return next;
	}

	/**
	 * The getPrev() method returns the previous node
	 * 
	 * @return the previous node
	 */
	public DPriorityNode<T> getPrev() {
		return prev;
	}

	/**
	 * The setElement(T eleme) method sets the element to the element given as a
	 * parameter
	 * 
	 * @param eleme
	 */
	public void setElement(T eleme) {
		this.element = eleme;

	}

	/**
	 * The setNext(DPriorityNode<T> nex) method sets the node given as the next
	 * 
	 * @param nex
	 */
	public void setNext(DPriorityNode<T> nex) {
		next = nex;
	}

	/**
	 * The setPrev(DPriorityNode<T> previous) method set the node given as the
	 * previous
	 * 
	 * @param previous
	 */
	public void setPrev(DPriorityNode<T> previous) {
		prev = previous;
	}

	/**
	 * The setPriority(double prior) method sets the priority given as the
	 * priority
	 * 
	 * @param prior
	 */
	public void setPriority(double prior) {
		priority = prior;
	}
}
