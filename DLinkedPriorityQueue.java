/**
 * 
 * CS1027-Assignment3
 * 
 * @author Fatima Hasan  This class
 *         implements all the methods in the PriorityQueueADT.java interface and
 *         it will store the data items of the priority queue in a doubly linked
 *         list. it will have 3 private instance variables:front, rear, and
 *         count.
 * @param <T>
 * 
 */
public class DLinkedPriorityQueue<T> implements PriorityQueueADT<T> {
	private DPriorityNode<T> front; // this is a reference to the first node of
									// the doubly linked list.
	private DPriorityNode<T> rear; // this is a reference to the last node of
									// the doubly linked list.
	private int count;// this the number of data items in the priority queue.

	/**
	 * This is the constructor for the DLinkedPriorityQueue, it creates an empty
	 * priority queue.
	 */
	public DLinkedPriorityQueue() {
		front = rear = null;
		count = 0;

	}

	/**
	 * The add(T element, double priority) method adds to the priority queue the
	 * given element with its associated priority.
	 * 
	 * @param T
	 *            element
	 * @param double
	 *            priority
	 */
	public void add(T element, double priority) {
		DPriorityNode<T> Dnode = new DPriorityNode<T>(element, priority);// creates
																			// a
																			// node
		// first node in the queue
		if (isEmpty()) {
			front = rear = Dnode;
			count++;
		} else {
			rear.setNext(Dnode);
			(rear.getNext()).setPrev(rear);
			rear = Dnode;
			// rear.setNext(null);
			count++;
		}

	}

	/**
	 * The updatePriority(T element, double newPriority) throws
	 * InvalidElementException method changes the priority of the given element
	 * to the new value, and it throws the InvalidElementException if the given
	 * element is not
	 */
	public void updatePriority(T element, double newPriority) throws InvalidElementException {
		DPriorityNode<T> current = front;
		boolean found = false;
		while (current != null && !found) {
			if ((current.getElement()).equals(element)) {
				current.setPriority(newPriority);
				found = true;

			} else {
				current = current.getNext();
				found = false;
			}
		}
		if (!found)
			throw new InvalidElementException("Node not found");

	}

	/**
	 * The T removeMin() throws EmptyPriorityQueueException method finds the
	 * data item in the priority queue with smallest priority ,removes it and
	 * returns it. The EmptyPriorityQueueException is thrown if the queue is
	 * empty.
	 * 
	 * @return T data item of the type T
	 */
	public T removeMin() throws EmptyPriorityQueueException {
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Priority Queue is empty!");
		}
		// first find the node with minimum priority
		double smallest = 99999999999999.9999;
		DPriorityNode<T> currDnode = front;
		DPriorityNode<T> minDnode = front;
		for (int i = 0; i < count; i++) {
			if (currDnode.getPriority() < smallest) {
				smallest = currDnode.getPriority();
				minDnode = currDnode;
			}
			currDnode = currDnode.getNext();
		}
		T minimumNode = minDnode.getElement();
		// remove based on where it is in the queue
		// count == 1
		if (count == 1) {
			front = null;
			rear = null;
		} else if (minDnode == front) {
			// front
			front = front.getNext();
			front.setPrev(null);

		} else if (minDnode == rear) {
			// rear
			rear = rear.getPrev();
			rear.setNext(null);
		} else {
			// middle
			minDnode.getPrev().setNext(minDnode.getNext());
			minDnode.getNext().setPrev(minDnode.getPrev());

		}
		count--;
		return minimumNode;

	}

	/**
	 * The isEmpty() method checks if the queue is empty of not.
	 * 
	 * @return boolean if empty true, else it will return false.
	 */
	public boolean isEmpty() {
		if (front == null) {
			return true;
		}
		return false;
	}

	/**
	 * The int size() method returns the size of the queue
	 * 
	 * @return the size of the queue as an integer
	 */
	public int size() {
		return count;
	}

	/**
	 * The String toString() method returns a string representation of the
	 * priority queue.
	 * 
	 * @return a string representation of the queue
	 */
	public String toString() {
		String result = " ";
		DPriorityNode<T> curr = front;
		for (int i = 0; i < count; i++) {
			result += curr.getElement().toString();
			curr = curr.getNext();
		}

		return result;
	}

}
