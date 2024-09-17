package Model;

import java.io.Serializable;

/**
 * The completed version of a models. Container class.
 * The Container class represents a container.
 * It implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class Container implements Serializable {
    private String containerIdentifier;
    private int containerSize;
    private final PalletList pallets;

    private Port port;
    private ContainerShip ship;

    /**
     * Constructs a new Container with the specified identifier and size.

     * @param containerIdentifier The unique identifier for this container.
     * @param containerSize The size of this container.
     */
    public Container(String containerIdentifier, int containerSize) {
        setContainerIdentifier(containerIdentifier);
        setContainerSize(containerSize);
        this.pallets = new PalletList();
    }

    //---------
    // Getters
    //---------

    /**
     * Returns the unique identifier of this container.
     * @return The container's identifier.
     */
    public String getContainerIdentifier() {
        return containerIdentifier;
    }

    /**
     * Returns the size of this container.
     * @return The container's size.
     */
    public int getContainerSize() {
        return containerSize;
    }

    /**
     * Returns the list of pallets in this container.
     * @return The container's pallet list.
     */
    public PalletList getPallets() {
        return pallets;
    }

    /**
     * Returns the total size of all pallets in this container.
     * @return The total size of all pallets.
     */
    public int getTotalPalletSize() {
        int totalSize = 0;
        LinkedList<Pallet>.MyIterator iterator = pallets.iterator();
        while (iterator.hasNext()) {
            totalSize += iterator.next().getTotalSize();
        }
        return totalSize;
    }

    /**
     * Calculates the total value of all pallets in the list.
     * @return The total value of all pallets.
     */
    public double getTotalValue() {
        double totalValue = 0;
        LinkedList<Pallet>.MyIterator iterator = pallets.iterator();
        while (iterator.hasNext()) {
            totalValue += iterator.next().getValue();
        }
        return totalValue;
    }


    //---------
    // Setters
    //---------

    /**
     * Sets the identifier of this container.
     * @param containerIdentifier The new identifier for this container.
     */
    public void setContainerIdentifier(String containerIdentifier) {
        if (containerIdentifier != null && !containerIdentifier.isEmpty()) {
            this.containerIdentifier = containerIdentifier;
        }
    }

    /**
     * Sets the size of this container.
     * @param containerSize The new size for this container.
     */
    public void setContainerSize(int containerSize) {
        if (containerSize == 10 || containerSize == 20 || containerSize == 40) {
            this.containerSize = containerSize;
        }
    }

    /**
     * Sets the location of this container.
     * @param location The new location for this container.
     */
    public void setLocation(Object location) {
        if (location instanceof Port) {
            this.port = (Port) location;
            this.ship = null; // Ensure that the ship attribute is null when the container is at a port
        } else if (location instanceof ContainerShip) {
            this.ship = (ContainerShip) location;
            this.port = null; // Ensure that the port attribute is null when the container is on a ship
        }
    }


    /**
     * Adds a pallet to this container.
     * @param pallet The pallet to be added.
     */
    public void addPallet(Pallet pallet) {
        pallets.add(pallet);
    }


    /**
     * Removes a pallet from this container.
     * @param pallet The pallet to be removed.
     */
    public void removePallet(Pallet pallet) {
        if (pallets.contains(pallet)) {
            pallets.remove(pallet);
        }
    }

    /**
     * Checks if a pallet can be added to this container.
     * @param pallet The pallet to be checked.
     * @return true if the pallet can be added, false otherwise.
     */
    public boolean canAddPallet(Pallet pallet) {
        int containerSizeInCubicFeet = this.getContainerSize() * 8 * 8; // Assuming 8 feet width and height
        int usedSpace = this.getTotalPalletSize();
        return usedSpace + pallet.getTotalSize() <= containerSizeInCubicFeet;
    }

    /**
     * Returns a string representation of this container.
     * This is used for the Container Menu List View.
     * @return A string representation of this container.
     */
    public String toString() {
        String location;
        if (ship != null) {
            location = "On board " + ship.getShipName();
        } else {
            location = "On shore at " + port.getPortName();
        }
        return "Container Identifier: " + getContainerIdentifier() + ", Container Size: " + getContainerSize() + ", Location: " + location;
    }
}
