package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PortTest {
    Port port = new Port("Port of Los Angeles", "USLAX", "United States");
    Container container = new Container("C1", 20);
    ContainerShip ship = new ContainerShip("Ship1", "S1", "US", "url");

    @Test
    void testGetPortName() {
        assertEquals("Port of Los Angeles", port.getPortName());
    }

    @Test
    void testGetPortCode() {
        assertEquals("USLAX", port.getPortCode());
    }

    @Test
    void testGetCountry() {
        assertEquals("United States", port.getCountry());
    }

    @Test
    void testGetTotalValue() {
        // Create a new Port instance
        Port port = new Port("Port1", "Code1", "Country1");

        // Add some containers and ships to the port
        Container container1 = new Container("Container1", 10);
        container1.addPallet(new Pallet("Pallet1", 10, 100.0, 50.0, 5));
        port.loadContainer(container1);

        ContainerShip ship1 = new ContainerShip("Ship1", "Identifier1", "FlagState1", "PhotoURL1");
        Container container2 = new Container("Container2", 20);
        container2.addPallet(new Pallet("Pallet2", 20, 200.0, 100.0, 10));
        ship1.loadContainer(container2);
        port.dockShip(ship1);

        // Test the getTotalValue method
        double expected = 5000.0; // The total value of all containers and ships
        double actual = port.getTotalValue();
        assertEquals(expected, actual, "The total value should be 5000.0");
    }

    @Test
    void testSetPortName() {
        port.setPortName("Port of Long Beach");
        assertEquals("Port of Long Beach", port.getPortName());
    }

    @Test
    void testSetPortCode() {
        port.setPortCode("USLGB");
        assertEquals("USLGB", port.getPortCode());
    }

    @Test
    void testSetCountry() {
        port.setCountry("United States");
        assertEquals("United States", port.getCountry());
    }

    @Test
    void testLoadContainer() {
        assertTrue(port.loadContainer(container));
        assertFalse(port.loadContainer(container));
    }

    @Test
    void testUnloadContainer() {
        assertFalse(port.unloadContainer(container));
        port.loadContainer(container);
        assertTrue(port.unloadContainer(container));
    }

    @Test
    void testDockShip() {
        assertTrue(port.dockShip(ship));
        assertFalse(port.dockShip(ship));
    }

    @Test
    void testLaunchShip() {
        assertFalse(port.launchShip(ship));
        port.dockShip(ship);
        assertTrue(port.launchShip(ship));
    }
}

