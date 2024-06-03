import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

// construct an empty deque
public class Deque<Item> implements Iterable<Item> {
    private int n; //
    private Node first; // First Node
    private Node last; // Last node

    // Creates pointers for node, and an Item
    private class Node {
        private Item item; // item
        private Node next; // Next pointer
        private Node pre; // points to previous node
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Cannot add a null element");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.pre = null; // avoid loitering
        if (isEmpty()) last = first;
        else oldFirst.pre = first;
        first.next = oldFirst;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Error");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null; // avoid loitering
        if (isEmpty()) first = last;
        else oldLast.next = last;
        last.pre = oldLast;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Error");
        Item item = first.item;
        first = first.next;
        if (n == 1) last = first;
        else first.pre = null;
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("Error");
        Item item = last.item;
        last = last.pre;
        if (n == 1) first = last;
        else last.next = null;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<Item> {
        // current node
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        // case where the queue is empty
        // Should be true
        StdOut.println("Is the queue empty? " + deque.isEmpty());

        for (int i = 1; i <= 3; i++) {
            deque.addFirst(i);
            StdOut.println("add " + i + " to front, with size "
                                   + deque.size() + " items.");
        }
        for (int i = 4; i <= 6; i++) {
            deque.addLast(i);
            StdOut.println("add " + i + " to the back, with size "
                                   + deque.size() + " items.");
        }

        StdOut.println(
                "Size of queue after enqueuing 6 items: " + deque.size());

        // is the queue empty? Should be false
        StdOut.println("Is the queue empty? " + deque.isEmpty());

        StdOut.println("\nIterate over the deque (forward):");
        for (Integer item : deque) {
            StdOut.println(item);
        }
        // Dequeue from the front
        StdOut.println("\nremove items from deque from front");
        while (!deque.isEmpty()) {
            StdOut.println("remove " + deque.removeFirst() +
                                   ", there are: " + deque.size() + " items left.");
        }

        // Refill the deque for next operation
        for (int i = 1; i <= 5; i++) {
            deque.addLast(i);
        }
        // Dequeue from the back
        StdOut.println("\nRemove items from deque from back");
        while (!deque.isEmpty()) {
            StdOut.println("remove" + deque.removeLast() +
                                   ", there are: " + deque.size() + " items left.");

        }
        // Test whether the queue is empty after dequeueing
        // Should be true
        StdOut.println("Is the queue empty? " + deque.isEmpty());
    }
}
