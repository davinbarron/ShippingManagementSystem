package Model;

import java.io.Serializable;

/**
 * The completed version of a models. Node generic class.
 * The Node class represents a node in a linked list.
 * It implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class Node<T> implements Serializable {
    private final T data;
    private Node<T> next;

    /**
     * Constructs a new Node with the specified data.
     *
     * @param data the data to be stored in this node
     */
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data stored in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next node in the list
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Sets the next node in the list.
     *
     * @param next the node to be set as the next node
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}

