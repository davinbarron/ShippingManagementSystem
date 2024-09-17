package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContainerShipTest {

    @Test
    void testSettersAndGetters() {
        ContainerShip ship = new ContainerShip("Ship1", "S1", "Flag1", "URL1");

        // Test setters and getters
        ship.setShipName("NewShip1");
        assertEquals("NewShip1", ship.getShipName());

        ship.setShipIdentifier("NewS1");
        assertEquals("NewS1", ship.getShipIdentifier());

        ship.setFlagState("NewFlag1");
        assertEquals("NewFlag1", ship.getFlagState());

        ship.setPhotoURL("NewURL1");
        assertEquals("NewURL1", ship.getPhotoURL());

        Port port = new Port("Port1", "P1", "Country1");
        ship.setPort(port);
        assertEquals(port, ship.getPort());
    }

    @Test
    public void testGetTotalValue() {
        ContainerShip ship = new ContainerShip("Ship1", "Identifier1", "FlagState1", "PhotoURL1");

        Container container1 = new Container("Container1", 10);
        container1.addPallet(new Pallet("Pallet1", 10, 100.0, 50.0, 5));
        ship.loadContainer(container1);

        Container container2 = new Container("Container2", 20);
        container2.addPallet(new Pallet("Pallet2", 20, 200.0, 100.0, 10));
        ship.loadContainer(container2);

        double expected = 5000.0;
        double actual = ship.getTotalValue();
        assertEquals(expected, actual, "The total value should be 5000.0");
    }

    @Test
    void testLoadContainer() {
        ContainerShip ship = new ContainerShip("Ship1", "S1", "Flag1", "URL1");
        Container container = new Container("C1", 10);

        // Load the container onto the ship
        assertTrue(ship.loadContainer(container));

        // Check if the container is on the ship
        assertTrue(ship.getContainers().contains(container));

        // Try to load the container again
        assertFalse(ship.loadContainer(container));
    }

    @Test
    void testUnloadContainer() {
        ContainerShip ship = new ContainerShip("Ship1", "S1", "Flag1", "URL1");
        Container container = new Container("C1", 10);

        // Load the container onto the ship
        ship.loadContainer(container);

        // Unload the container from the ship
        assertTrue(ship.unloadContainer(container));

        // Check if the container is not on the ship
        assertFalse(ship.getContainers().contains(container));

        // Try to unload the container again
        assertFalse(ship.unloadContainer(container));
    }
}

