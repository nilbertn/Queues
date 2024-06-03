import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // initial capacity of underlying resizing array
    private static final int INIT_CAPACITY = 8;
    private Item[] q; // array of items
    private int n; // number of elements in array


    // construct an empty randomized queue
    public RandomizedQueue() {
        this.n = 0;
        q = (Item[]) new Object[INIT_CAPACITY];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Error");
        // double the array size if it is full
        if (n == q.length)
            resize(2 * q.length);
        // [n++] = item;
        q[n] = item;
        n++;

    }

    // helper method that does the resizing
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = q[i];
        q = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Error");
        int r = StdRandom.uniformInt(n);
        Item item = q[r];
        q[r] = q[n - 1]; // swaps random with last
        q[n - 1] = null;
        // avoid loitering
        n--;
        // check array size and shrink
        if (n > 0 && n == q.length / 4)
            resize(q.length / 2); // halves when array is quarter full
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Error");
        return q[StdRandom.uniformInt(n)]; // avoid deprecation
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = n - 1; // index of next item to return
        private Item[] copy; // copy of queue

        // System.arraycopy(q, 0, copy, 0, n);

        // copies array and then randomises it.
        public ReverseArrayIterator() {
            copy = (Item[]) new Object[n];
            for (int j = 0; j < n; j++)
                copy[j] = q[j];
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[i--];
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        // case where the queue is empty
        // Should be true
        StdOut.println("Is the queue empty? " + randomizedQueue.isEmpty());

        // Enqueue
        for (int i = 1; i <= 5; i++)
            randomizedQueue.enqueue("Item " + i);


        // size? should be 4
        StdOut.println(
                "Size of queue after enqueuing 5 items: " + randomizedQueue.size());

        // is the queue empty? Should be false
        StdOut.println("Is the queue empty? " + randomizedQueue.isEmpty());

        // Print random item
        StdOut.println("Random item: " + randomizedQueue.sample());

        // Check to make sure the size of the queue has not changed after print
        // should be 4
        StdOut.println(
                "Size of queue random selection: " + randomizedQueue.size());

        // Dequeue two items randomly ad should not be in order ideally
        StdOut.println("Dequeue item: " + randomizedQueue.dequeue());
        StdOut.println("Dequeue item: " + randomizedQueue.dequeue());

        // size? should be 3
        StdOut.println(
                "Size after dequeuing 2 random items: " + randomizedQueue.size());

        // Iterate over the queue
        StdOut.println("Iteratig over the queue : ");
        for (String item : randomizedQueue) {
            StdOut.println(item);
        }

        // Dequeue the remaining items
        while (!randomizedQueue.isEmpty()) {
            StdOut.println(
                    "Dequeue item: " + randomizedQueue.dequeue());
        }

        // Test whether the queue is empty after dequeueing
        // Should be true
        StdOut.println("Is the queue empty? " + randomizedQueue.isEmpty());
    }
}

