package Model;

import java.io.Serializable;

/**
 * The completed version of a models. PalletList class.
 * The PalletList class represents a list of pallets.
 * It extends the LinkedList class and implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class PalletList extends LinkedList<Pallet> implements Serializable {

    /**
     * Finds a pallet in the list with the specified description.
     *
     * @param description The description of the pallet to find.
     * @return The node containing the pallet if found, null otherwise.
     */
    public Node<Pallet> find(String description) {
        Node<Pallet> current = getHead();
        while (current != null) {
            if (current.getData().getDescription().equals(description)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}

