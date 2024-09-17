package Model;

import java.io.Serializable;

/**
 * The completed version of a models. ContainerShip class.
 * The ContainerShip class represents a ship.
 * It implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class ContainerShip implements Serializable {
    private String shipName;
    private String shipIdentifier;
    private String flagState;
    private String photoURL;
    private final LinkedList<Container> containers;
    private Port port;

    /**
     * Constructs a new ContainerShip with the specified name, identifier, flag state, and photo URL.
     *
     * @param shipName The name of the ship.
     * @param shipIdentifier The unique identifier for the ship.
     * @param flagState The flag state of the ship.
     * @param photoURL The URL of the ship's photo.
     */
    public ContainerShip(String shipName, String shipIdentifier, String flagState, String photoURL) {
        setShipName(shipName);
        setShipIdentifier(shipIdentifier);
        setFlagState(flagState);
        setPhotoURL(photoURL);
        this.containers = new LinkedList<>();
    }

    //---------
    // Getters
    //---------

    /**
     * Returns the name of the ship.
     * @return The ship's name.
     */
    public String getShipName() {
        return shipName;
    }

    /**
     * Returns the unique identifier of the ship.
     * @return The ship's identifier.
     */
    public String getShipIdentifier() {
        return shipIdentifier;
    }

    /**
     * Returns the flag state of the ship.
     * @return The ship's flag state.
     */
    public String getFlagState() {
        return flagState;
    }

    /**
     * Returns the URL of the ship's photo.
     * @return The ship's photo URL.
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /**
     * Returns the port where the ship is currently located.
     * @return The ship's current port.
     */
    public Port getPort() {
        return port;
    }

    /**
     * Returns the list of containers on the ship.
     * @return The ship's container list.
     */
    public LinkedList<Container> getContainers() {
        return containers;
    }

    /**
     * This method calculates the total value of all containers on the ship.
     *
     * @return The total value of all containers.
     */
    public double getTotalValue() {
        double totalValue = 0;
        LinkedList<Container>.MyIterator iterator = containers.iterator();
        while (iterator.hasNext()) {
            totalValue += iterator.next().getTotalValue();
        }
        return totalValue;
    }


    //---------
    // Setters
    //---------

    /**
     * Sets the name of the ship.
     * @param shipName The new name for the ship.
     */
    public void setShipName(String shipName) {
        if (shipName != null && !shipName.isEmpty()) {
            this.shipName = shipName;
        }
    }

    /**
     * Sets the unique identifier of the ship.
     * @param shipIdentifier The new identifier for the ship.
     */
    public void setShipIdentifier(String shipIdentifier) {
        if (shipIdentifier != null && !shipIdentifier.isEmpty()) {
            this.shipIdentifier = shipIdentifier;
        }
    }

    /**
     * Sets the flag state of the ship.
     * @param flagState The new flag state for the ship.
     */
    public void setFlagState(String flagState) {
        if (flagState != null && !flagState.isEmpty()) {
            this.flagState = flagState;
        }
    }

    /**
     * Sets the URL of the ship's photo.
     * @param photoURL The new photo URL for the ship.
     */
    public void setPhotoURL(String photoURL) {
        if (photoURL != null && !photoURL.isEmpty()) {
            this.photoURL = photoURL;
        }
    }

    /**
     * Sets the port where the ship is currently located.
     * @param port The new port for the ship.
     */
    public void setPort(Port port) {
        this.port = port;
    }

    /**
     * Loads a container onto the ship.
     *
     * @param container The container to be loaded.
     * @return true if the container was successfully loaded, false otherwise.
     */
    public boolean loadContainer(Container container) {
        // Check if the container is already on the ship
        if (containers.contains(container)) {
            return false; // Container is already on the ship
        }

        // Load the container onto the ship
        containers.add(container);
        return true;
    }

    /**
     * Unloads a container from the ship.
     *
     * @param container The container to be unloaded.
     * @return true if the container was successfully unloaded, false otherwise.
     */
    public boolean unloadContainer(Container container) {
        // Check if the container is on the ship
        if (!containers.contains(container)) {
            return false; // Container is not on the ship
        }

        // Unload the container from the ship
        containers.remove(container);
        return true;
    }

    /**
     * Returns a string representation of the ship.
     * Used for the Ship Menu List View.
     * @return A string representation of the ship.
     */
    public String toString() {
        String location = (port != null) ? port.getPortName() : "At sea";
        return "Ship Name: " + getShipName() + ", Ship Identifier: " + getShipIdentifier() + ", Flag State: " + getFlagState() + ", URL: " + getPhotoURL() + ", Location: " + location;
    }
}

