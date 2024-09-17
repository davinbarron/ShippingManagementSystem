package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    LinkedList<Integer> list = new LinkedList<>();

    @Test
    void testAdd() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

    @Test
    void testContains() {
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    void testRemove() {
        list.add(1);
        list.remove(1);
        assertEquals(0, list.size());
    }

    @Test
    void testSize() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.size());
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    public void testMyIterator() {

        list.add(1);
        list.add(2);
        list.add(3);

        LinkedList<Integer>.MyIterator iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());

        assertFalse(iterator.hasNext());
    }
}

