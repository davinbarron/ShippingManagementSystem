package Model;

import java.io.Serializable;

/**
 * The completed version of a models. PortList class.
 * The PortList class represents a list of ports.
 * It extends the LinkedList class and implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class PortList extends LinkedList<Port> implements Serializable {

    /**
     * Checks if a port with the specified code is unique in the list.
     *
     * @param portCode The code of the port to check.
     * @return true if the port is unique, false otherwise.
     */
    public boolean isUnique(String portCode) {
        Node<Port> current = getHead();
        while (current != null) {
            if (current.getData().getPortCode().equals(portCode)) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Finds a port in the list with the specified code.
     *
     * @param portCode The code of the port to find.
     * @return The node containing the port if found, null otherwise.
     */
    public Node<Port> find(String portCode) {
        Node<Port> current = getHead();
        while (current != null) {
            if (current.getData().getPortCode().equals(portCode)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
