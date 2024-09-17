package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainerListTest {
    ContainerList containerList = new ContainerList();

    @Test
    void testIsContainerUnique() {
        Container container1 = new Container("C1", 20);
        Container container2 = new Container("C2", 20);
        containerList.add(container1);
        containerList.add(container2);

        assertTrue(containerList.isContainerUnique("C3"));
        assertFalse(containerList.isContainerUnique("C1"));
    }

    @Test
    void testFind() {
        Container container1 = new Container("C1", 20);
        Container container2 = new Container("C2", 20);
        containerList.add(container1);
        containerList.add(container2);

        assertEquals(container1, containerList.find("C1").getData());
        assertEquals(container2, containerList.find("C2").getData());
        assertNull(containerList.find("C3"));
    }
}
