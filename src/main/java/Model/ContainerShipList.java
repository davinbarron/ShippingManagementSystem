package Model;

import java.io.Serializable;

/**
 * The completed version of a models. ContainerShipList class.
 * The ContainerShipList class represents a list of container ships.
 * It extends the LinkedList class and implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class ContainerShipList extends LinkedList<ContainerShip> implements Serializable {

    /**
     * Checks if a ship with the specified identifier is unique in the list.
     *
     * @param shipIdentifier The identifier of the ship to check.
     * @return true if the ship is unique, false otherwise.
     */
    public boolean isUnique(String shipIdentifier) {
        Node<ContainerShip> current = getHead();
        while (current != null) {
            if (current.getData().getShipIdentifier().equals(shipIdentifier)) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Finds a ship in the list with the specified identifier.
     *
     * @param shipIdentifier The identifier of the ship to find.
     * @return The node containing the ship if found, null otherwise.
     */
    public Node<ContainerShip> find(String shipIdentifier) {
        Node<ContainerShip> current = getHead();
        while (current != null) {
            if (current.getData().getShipIdentifier().equals(shipIdentifier)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
