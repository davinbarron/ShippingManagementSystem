package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortListTest {
    PortList portList = new PortList();

    @Test
    void testIsUnique() {
        Port port1 = new Port("Port of Los Angeles", "USLAX", "United States");
        Port port2 = new Port("Port of Long Beach", "USLGB", "United States");
        portList.add(port1);
        portList.add(port2);

        assertTrue(portList.isUnique("USNYC"));
        assertFalse(portList.isUnique("USLAX"));
    }

    @Test
    void testFind() {
        Port port1 = new Port("Port of Los Angeles", "USLAX", "United States");
        Port port2 = new Port("Port of Long Beach", "USLGB", "United States");
        portList.add(port1);
        portList.add(port2);

        assertEquals(port1, portList.find("USLAX").getData());
        assertEquals(port2, portList.find("USLGB").getData());
        assertNull(portList.find("USNYC"));
    }
}


