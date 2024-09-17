package Model;

import java.io.Serializable;

/**
 * The completed version of a models. ContainerList class.
 * The ContainerList class represents a list of containers.
 * It extends the LinkedList class and implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class ContainerList extends LinkedList<Container> implements Serializable {

    /**
     * Checks if a container with the specified identifier is unique in the list.
     *
     * @param containerIdentifier The identifier of the container to check.
     * @return true if the container is unique, false otherwise.
     */
    public boolean isContainerUnique(String containerIdentifier) {
        Node<Container> current = getHead();
        while (current != null) {
            if (current.getData().getContainerIdentifier().equals(containerIdentifier)) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Finds a container in the list with the specified identifier.
     *
     * @param identifier The identifier of the container to find.
     * @return The node containing the container if found, null otherwise.
     */
    public Node<Container> find(String identifier) {
        Node<Container> current = getHead();
        while (current != null) {
            if (current.getData().getContainerIdentifier().equals(identifier)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
