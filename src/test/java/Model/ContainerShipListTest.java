package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContainerShipListTest {

    @Test
    void testIsUnique() {
        ContainerShipList shipList = new ContainerShipList();
        ContainerShip ship1 = new ContainerShip("Ship1", "S1", "Flag1", "URL1");
        ContainerShip ship2 = new ContainerShip("Ship2", "S1", "Flag2", "URL2");  // Same identifier as ship1

        // Add ship1 to the list
        shipList.add(ship1);

        // Check if ship1's identifier is unique (it should not be, since ship1 is already in the list)
        assertFalse(shipList.isUnique(ship1.getShipIdentifier()));

        // Check if ship2's identifier is unique (it should not be, since it's the same as ship1's identifier)
        assertFalse(shipList.isUnique(ship2.getShipIdentifier()));
    }

    @Test
    void testFind() {
        ContainerShipList shipList = new ContainerShipList();
        ContainerShip ship1 = new ContainerShip("Ship1", "S1", "Flag1", "URL1");

        // Add ship1 to the list
        shipList.add(ship1);

        // Find ship1 in the list by its identifier
        Node<ContainerShip> foundShipNode = shipList.find(ship1.getShipIdentifier());

        // Check if the found ship is ship1
        assertEquals(ship1, foundShipNode.getData());

        // Try to find a ship with an identifier that is not in the list (should return null)
        assertNull(shipList.find("NonexistentIdentifier"));
    }
}
