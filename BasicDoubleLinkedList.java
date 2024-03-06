package pj03;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected int size;
	protected Node firstNode;
	protected Node lastNode;

	public BasicDoubleLinkedList() {
		size = 0;
		firstNode = null;
		lastNode = null;
	}


		 
	 
	 /**
	 * Adds an element to the end of the list.
	 * 
	 * @param data The data to be added to the end of the list.
	 * @return Reference to the current instance of BasicDoubleLinkedList.
	 * @author Ashton Kabou
	 */

	 
	 
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if (lastNode == null) {
			lastNode = new Node(data, null, null);
			firstNode = lastNode;

		} else {

			Node nextLast = new Node(data, lastNode, null);
			lastNode.setNext(nextLast);
			lastNode = nextLast;
		}

		size++;
		return this;
	}
	
	
	
	
	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param data The data to be added to the front of the list.
	 * @return Reference to the current instance of BasicDoubleLinkedList.
	 * @author Ashton Kabou
	 */
	
	public BasicDoubleLinkedList<T> addToFront(T data) {
		if (firstNode == null) {
			firstNode = new Node(data, null, null);
			lastNode = firstNode;
		} else {
			Node nextFirst = new Node(data, null, firstNode);
			firstNode.setPrev(nextFirst);
			firstNode = nextFirst;
		}
		size++;
		return this;
	}

	/**
	 * Retrieves the first element of the list.
	 * 
	 * @return The first element of the list, or null if the list is empty.
	 * @author Ashton Kabou
	 */
	public T getFirst() {
		if (firstNode == null) {
			return null;
		}
		return firstNode.getItem();
	}

	/**
	 * Retrieves the last element of the list
	 * 
	 * @return The last element of the list or null
	 *  @author Ashton Kabou
	 */
	public T getLast() {
		if (lastNode == null) {
			return null;
		}
		return lastNode.getItem();
	}

	/**
	 * the size of the linked list
	 * 
	 * @return size
	 *  @author Ashton Kabou
	 */
	public int getSize() {
		return size;
	}

	/**
	 * iterator
	 * @return modified list iterator object
	 * @throw UnsupportedOperationException
	 * @throw NoSuchElementException
	 *  @author Ashton Kabou
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException{
		return new DoubIterator();
	}

	/**
	 * remove first occurrence of targetdata from linkedlist
	 * @param targetdata
	 * @param comparator
	 * @return current state of linkedlist
	 *  @author Ashton Kabou
	 */
	public BasicDoubleLinkedList<T> remove(T targetdata, Comparator<T> comparator) {
		Node current = firstNode;
		
		if(size == 0) {
			return this;
		}
		if(size ==1) {
			if(comparator.compare(targetdata, firstNode.getItem())==0) {
				firstNode = null;
				lastNode = null;
				size--;
			}
			return this;
		}
		
		while(current != null) {
			if(comparator.compare(targetdata, current.getItem())==0) {
				if(current.equals(firstNode)) {
					firstNode = current.getNext();
					current.getNext().setPrev(null);
				} else if(current.equals(lastNode)) {
					lastNode = current.getPrev();
					current.getPrev().setNext(null); 
				} else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());				
				}
				
				size--;
				break;
			}
			current=current.getNext();
		}
		return this;
	}

	/**
	 * removes and returns first data from linkedlist
	 * 
	 * @return data of the first data if exists, otherwise null
	 *  @author Ashton Kabou
	 */
	public T retrieveFirstElement() {
		if (firstNode == null) {
			return null;
		}
		T ElementData = firstNode.getItem();

		if (size == 1) {
			firstNode = null;
			lastNode = null;
			return ElementData;
		}

		firstNode = firstNode.getNext();
		firstNode.setPrev(null);
		size--;
		return ElementData;
	}

	/**
	 * removes and returns last data from linkedlist
	 * 
	 * @return data of last data if exists, otherwise null
	 *  @author Ashton Kabou
	 */
	public T retrieveLastElement() {
		if (lastNode == null) {
			return null;
		}
		T ElementData = lastNode.getItem();

		if (size == 1) {
			firstNode = null;
			lastNode = null;
			return ElementData;
		}

		lastNode = lastNode.getPrev();
		lastNode.setNext(null);
		size--;
		return ElementData;
	}

	
	public ArrayList<T> toArrayList() {
		ArrayList<T> arr = new ArrayList<T>();
		DoubIterator iter = (BasicDoubleLinkedList<T>.DoubIterator) iterator();
		
		while(iter.hasNext()) {
			arr.add(iter.next());	
		}	
		return arr;
	}

	protected class Node {
		private T item;

		private Node prev, next = null;

		Node(T item, Node prev, Node next) {
			this.item = item;
			this.prev = prev;
			this.next = next;
		}

		public T getItem() {
			return item;
		}

		public Node getPrev() {
			return prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node n) {
			next = n;
		}

		public void setPrev(Node n) {
			prev = n;
		}
		
		public boolean equals(Node n) {
			if(next == n.getNext() && prev == n.getPrev() && item == n.getItem()) {
				return true;
			}
			return false;
		}
	}

	/**
	 * doubly linked iterator
	 *  @author Ashton Kabou
	 */
	protected class DoubIterator implements ListIterator<T> {
		protected Node previousNode;
		protected Node NextNode;

		public DoubIterator() {
			previousNode =null;
			NextNode = firstNode;
		}

		/**
		 * check if there is another data in list
		 * 
		 * @return true if not last data
		 *  @author Ashton Kabou
		 */
		@Override
		public boolean hasNext() {
			return NextNode != null;
		}

		/**
		 * set current pointer to next one if possible
		 * 
		 * @throw NoSuchElementException when not possible to set pointer to next one
		 * @return data of next data
		 *  @author Ashton Kabou
		 */
		@Override
		public T next() throws NoSuchElementException {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			previousNode = NextNode;
			NextNode = NextNode.next;
			return previousNode.getItem();
		}

		/**
		 * check if there is an data before i in list
		 * 
		 * @return true if not first data
		 *  @author Ashton Kabou
		 */
		@Override
		public boolean hasPrevious() {
			return previousNode != null ;
				}

		/**
		 * set current pointer to previous one if possible
		 * 
		 * @throw NoSuchElementException when not possible to set pointer to previous
		 *        one
		 * @return data of previous data
		 *  @author Ashton Kabou
		 */
		@Override
		public T previous() throws NoSuchElementException {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			NextNode=previousNode;
			previousNode = NextNode.prev;
			return NextNode.getItem();
		}

		

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}
}
