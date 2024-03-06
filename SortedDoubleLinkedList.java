package pj03;

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
    private Comparator<T> comparator;

    // Constructor
    public SortedDoubleLinkedList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    // Method to add data to the sorted list
    public SortedDoubleLinkedList<T> add(T data) {
        // If the list is empty or data should be added to the front
        if (size == 0 || comparator.compare(firstNode.getItem(), data) > 0 || 
            comparator.compare(lastNode.getItem(), data) == 0) {
            super.addToFront(data);
            return this;
        }

        // If data should be added to the end
        if (comparator.compare(lastNode.getItem(), data) < 0 || 
            comparator.compare(lastNode.getItem(), data) == 0) {
            super.addToEnd(data);
            return this;
        }

        int PreviousIndex = -1;
        int CurrentIndex;

        Node current = firstNode;
        Node newNode;

        // Loop through the list to find the appropriate position for data
        while (true) {
            CurrentIndex = comparator.compare(current.getItem(), data);
            if (PreviousIndex < 0 && CurrentIndex > 0 || CurrentIndex == 0) {
                newNode = new Node(data, current.getPrev(), current);
                current.getPrev().setNext(newNode);
                current.setPrev(newNode);
                size++;
                break;
            }
            PreviousIndex = CurrentIndex;
            current = current.getNext();
        }
        return this;
    }

    // Override addToEnd method to throw UnsupportedOperationException
    @Override
    public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    // Override addToFront method to throw UnsupportedOperationException
    @Override
    public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    // Override iterator method to use the super class's iterator
    @Override
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    // Override remove method to cast the returned type
    @Override
    public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
        return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
    }
}
