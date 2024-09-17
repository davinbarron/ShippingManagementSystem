package Model;

import java.io.Serializable;

/**
 * The completed version of a models. LinkedList generic class.
 * The LinkedList class represents a generic linked list.
 * It implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements Serializable {
    private Node<T> head;
    private int size;

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Returns the head node of this list.
     * @return the head node of this list
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Adds the specified element to the end of this list.
     * @param data the element to add
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newNode);
        }
        size++;
    }

    /**
     * Returns true if this list contains the specified element.
     *
     * @param data the element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param data the element to be removed from this list, if present
     */
    public void remove(T data) {
        if (head == null) return;
        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;  // Decrement size
            return;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                size--;  // Decrement size
                return;
            }
            current = current.getNext();
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null; // Return null if index is out of bounds
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getData();
    }

    /**
     * This method returns a new instance of MyIterator.
     *
     * @return A new MyIterator instance.
     */
    public MyIterator iterator() {
        return new MyIterator();
    }

    /**
     * This class represents an iterator for the LinkedList.
     */
    public class MyIterator {
        private Node<T> current;

        /**
         * Constructor for MyIterator. Initializes the current node to the head of the LinkedList.
         */
        public MyIterator() {
            current = head;
        }

        /**
         * Checks if there is a next node in the LinkedList.
         *
         * @return true if there is a next node, false otherwise.
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the data of the current node and moves to the next node.
         *
         * @return The data of the current node.
         * @throws RuntimeException if there are no more elements.
         */
        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }

}
