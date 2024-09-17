package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    Node<Integer> node1 = new Node<>(1);
    Node<Integer> node2 = new Node<>(2);

    @Test
    void testGetData() {
        assertEquals(1, node1.getData());
        assertEquals(2, node2.getData());
    }

    @Test
    void testGetNext() {
        node1.setNext(node2);
        assertEquals(node2, node1.getNext());
    }

    @Test
    void testSetNext() {
        node1.setNext(node2);
        assertEquals(node2, node1.getNext());
        node1.setNext(null);
        assertNull(node1.getNext());
    }
}

