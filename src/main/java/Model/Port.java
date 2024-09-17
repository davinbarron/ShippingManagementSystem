package Model;

import java.io.Serializable;

/**
 * The completed version of a models. Port class.
 * The Port class represents a port.
 * It implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class Port implements Serializable {
    private String portName;
    private String portCode;
    private String country;
    private final LinkedList<Container> containers;
    private final LinkedList<ContainerShip> dockedShips;

    /**
     * Constructs a new Port with the specified name, code, and country.
     *
     * @param portName The name of the port.
     * @param portCode The code of the port.
     * @param country The country where the port is located.
     */
    public Port(String portName, String portCode, String country) {
        setPortName(portName);
        setPortCode(portCode);
        setCountry(country);
        this.containers = new LinkedList<>();
        this.dockedShips = new LinkedList<>();
    }

    //---------
    // Getters
    //---------

    /**
     * Returns the name of the port.
     * @return The port's name.
     */
    public String getPortName() {
        return portName;
    }

    /**
     * Returns the code of the port.
     * @return The port's code.
     */
    public String getPortCode() {
        return portCode;
    }

    /**
     * Returns the country where the port is located.
     * @return The port's country.
     */
    public String getCountry() {
        return country;
    }

    public LinkedList<Container> getContainers() {
        return containers;
    }

    public LinkedList<ContainerShip> getDockedShips() {
        return dockedShips;
    }

    /**
     * Calculates the total value of all containers and docked ships in the port.
     * The total value is calculated by summing up the total value of each container and each docked ship.
     * @return The total value of all containers and docked ships in the port.
     */
    public double getTotalValue() {
        double totalValue = 0;

        // Iterate over the containers in the port
        LinkedList<Container>.MyIterator containerIterator = containers.iterator();
        while (containerIterator.hasNext()) {
            totalValue += containerIterator.next().getTotalValue();
        }

        // Iterate over the docked ships in the port
        LinkedList<ContainerShip>.MyIterator shipIterator = dockedShips.iterator();
        while (shipIterator.hasNext()) {
            totalValue += shipIterator.next().getTotalValue();
        }

        return totalValue;
    }

    //---------
    // Setters
    //---------

    /**
     * Sets the name of the port.
     * @param portName The new name for the port.
     */
    public void setPortName(String portName) {
        if (portName != null && !portName.isEmpty()) {
            this.portName = portName;
        }
    }

    /**
     * Sets the code of the port.
     * @param portCode The new code for the port.
     */
    public void setPortCode(String portCode) {
        if (portCode != null && !portCode.isEmpty()) {
            this.portCode = portCode;
        }
    }

    /**
     * Sets the country where the port is located.
     * @param country The new country for the port.
     */
    public void setCountry(String country) {
        if (country != null && !country.isEmpty()) {
            this.country = country;
        }
    }

    /**
     * Loads a container onto the port.
     *
     * @param container The container to be loaded.
     * @return true if the container was successfully loaded, false otherwise.
     */
    public boolean loadContainer(Container container) {
        if (containers.contains(container)) {
            return false;
        }

        containers.add(container);
        return true;
    }

    /**
     * Unloads a container from the port.
     *
     * @param container The container to be unloaded.
     * @return true if the container was successfully unloaded, false otherwise.
     */
    public boolean unloadContainer(Container container) {
        if (!containers.contains(container)) {
            return false;
        }

        containers.remove(container);
        return true;
    }

    /**
     * Docks a ship at the port.
     *
     * @param ship The ship to be docked.
     * @return true if the ship was successfully docked, false otherwise.
     */
    public boolean dockShip(ContainerShip ship) {
        // Check if the ship is already docked
        if (dockedShips.contains(ship)) {
            return false; // Ship is already docked
        }

        // Dock the ship
        dockedShips.add(ship);
        return true;
    }

    /**
     * Launches a ship from the port.
     *
     * @param ship The ship to be launched.
     * @return true if the ship was successfully launched, false otherwise.
     */
    public boolean launchShip(ContainerShip ship) {
        // Check if the ship is docked
        if (!dockedShips.contains(ship)) {
            return false; // Ship is not docked
        }

        // Launch the ship
        dockedShips.remove(ship);
        return true;
    }

    /**
     * Returns a string representation of the port.
     * Used for the Port Menu List View.
     * @return A string representation of the port.
     */
    public String toString() {
        return "Port Name: " + getPortName() + ", Port Code: " + getPortCode() + ", Country: " + getCountry();
    }

}
