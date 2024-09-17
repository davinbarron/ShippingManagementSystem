package Controller;

import Model.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The ShippingAPI class provides methods for managing ports, ships, containers, and pallets in a shipping system.
 * It allows adding and removing items, loading and unloading containers, docking and launching ships, and more.
 * It also provides methods for saving and loading the state of the system.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class ShippingAPI implements Serializable {
    private PortList ports;
    private ContainerShipList ships;
    private ContainerList containers;
    private PalletList pallets;

    public ShippingAPI() {
        this.ports = new PortList();
        this.ships = new ContainerShipList();
        this.containers = new ContainerList();
        this.pallets = new PalletList();
    }

    //-------------------
    // Port Handler
    //-------------------

    /**
     * Adds a port to the system.
     * @param portName The name of the port.
     * @param portCode The code of the port.
     * @param country The country where the port is located.
     * @return true if the port was added successfully, false otherwise.
     */
    public boolean addPort(String portName, String portCode, String country) {
        // Check if the port code is unique
        if (ports.isUnique(portCode)) {
            // Create a new port and add it to the list
            Port newPort = new Port(portName, portCode, country);
            ports.add(newPort);
            System.out.println("Port added successfully!");
            return true;
        } else {
            // The port code is not unique
            return false;
        }
    }

    /**
     * Returns a list of all ports in the system.
     * @return A LinkedList of all ports in the system.
     */
    public LinkedList<Port> getPorts() {
        return this.ports;
    }

    //-------------------
    // Ship Handler
    //-------------------

    /**
     * Adds a container ship to the system.
     * @param shipName The name of the ship.
     * @param shipIdentifier The unique identifier for the ship.
     * @param flagState The flag state of the ship.
     * @param photoURL The URL of the ship's photo.
     * @param portCode The code of the port where the ship is docked.
     * @return true if the ship was added successfully, false otherwise.
     */
    public boolean addContainerShip(String shipName, String shipIdentifier, String flagState, String photoURL, String portCode) {
        // Check if the ship identifier is unique
        if (ships.isUnique(shipIdentifier)) {
            // Create a new container ship and add it to the list
            ContainerShip newShip = new ContainerShip(shipName, shipIdentifier, flagState, photoURL);
            ships.add(newShip);

            // Find the port and dock the ship
            Node<Port> portNode = ports.find(portCode);
            if (portNode != null) {
                portNode.getData().dockShip(newShip);
                newShip.setPort(portNode.getData());
                return true;
            }
        }
        // The ship identifier is not unique or the port was not found
        return false;
    }

    /**
     * Launches a ship from a port.
     * @param shipIdentifier The identifier of the ship to be launched.
     * @param portCode The code of the port from which the ship will be launched.
     * @return true if the ship was successfully launched, false otherwise.
     */
    public boolean launchShip(String shipIdentifier, String portCode) {
        // Find the ship
        Node<ContainerShip> shipNode = ships.find(shipIdentifier);
        if (shipNode == null) {
            return false; // Ship not found
        }

        // Find the port
        Node<Port> portNode = ports.find(portCode);
        if (portNode == null) {
            return false; // Port not found
        }

        // Launch the ship
        boolean isLaunched = portNode.getData().launchShip(shipNode.getData());

        // If the ship was launched successfully, set its port to null
        if (isLaunched) {
            shipNode.getData().setPort(null);
        }

        return isLaunched;
    }

    /**
     * Docks a ship at a port.
     * @param shipIdentifier The identifier of the ship to be docked.
     * @param portCode The code of the port where the ship will be docked.
     * @return true if the ship was successfully docked, false otherwise.
     */
    public boolean dockShip(String shipIdentifier, String portCode) {
        // Find the ship
        Node<ContainerShip> shipNode = ships.find(shipIdentifier);
        if (shipNode == null) {
            return false; // Ship not found
        }

        // Find the port
        Node<Port> portNode = ports.find(portCode);
        if (portNode == null) {
            return false; // Port not found
        }

        // Dock the ship at the port
        boolean isDocked = portNode.getData().dockShip(shipNode.getData());

        // If the ship was docked successfully, set its port
        if (isDocked) {
            shipNode.getData().setPort(portNode.getData());
        }

        return isDocked;
    }

    /**
     * Returns a list of all ships in the system.
     * @return A LinkedList of all ships in the system.
     */
    public LinkedList<ContainerShip> getShips() {
        return this.ships;
    }

    //-------------------
    // Container Handler
    //-------------------

    /**
     * Adds a container to the system.
     * @param containerIdentifier The unique identifier for the container.
     * @param containerSize The size of the container.
     * @param locationIdentifier The identifier of the location (port or ship) where the container is located.
     * @return true if the container was added successfully, false otherwise.
     */
    public boolean addContainer(String containerIdentifier, int containerSize, String locationIdentifier) {
        // Check if the container size is valid
        if (containerSize != 10 && containerSize != 20 && containerSize != 40) {
            System.out.println("Container size must be either 10, 20 or 40 feet long.");
            return false; // Invalid container size
        }

        // Check if the container identifier is unique
        if (containers.isContainerUnique(containerIdentifier)) {
            // Create a new container and add it to the list
            Container newContainer = new Container(containerIdentifier, containerSize);
            containers.add(newContainer);

            // Try to find a port or ship with the provided identifier and add the container to it
            Node<Port> portNode = ports.find(locationIdentifier);
            if (portNode != null) {
                portNode.getData().loadContainer(newContainer);
                newContainer.setLocation(portNode.getData());
                return true;
            }

            Node<ContainerShip> shipNode = ships.find(locationIdentifier);
            if (shipNode != null && shipNode.getData().getPort() != null) {
                shipNode.getData().loadContainer(newContainer);
                newContainer.setLocation(shipNode.getData());
                return true;
            }
        }
        // The container identifier is not unique or the location was not found
        return false;
    }

    /**
     * Loads a container onto a ship.
     * @param containerIdentifier The identifier of the container to be loaded.
     * @param shipIdentifier The identifier of the ship onto which the container will be loaded.
     * @return true if the container was successfully loaded onto the ship, false otherwise.
     */
    public boolean loadContainerToShip(String containerIdentifier, String shipIdentifier) {
        // Find the container
        Node<Container> containerNode = containers.find(containerIdentifier);
        if (containerNode == null) {
            return false; // Container not found
        }

        // Find the ship
        Node<ContainerShip> shipNode = ships.find(shipIdentifier);
        if (shipNode == null) {
            return false; // Ship not found
        }

        // Check if the ship is docked at a port
        Port currentPort = shipNode.getData().getPort();
        if (currentPort == null) {
            return false; // Ship is not at a port
        }

        // Load the container onto the ship
        boolean isLoaded = shipNode.getData().loadContainer(containerNode.getData());

        // If the container was loaded successfully, update its location to the ship
        if (isLoaded) {
            containerNode.getData().setLocation(shipNode.getData());
        }

        return isLoaded;
    }

    /**
     * Unloads a container from a ship.
     * @param containerIdentifier The identifier of the container to be unloaded.
     * @param shipIdentifier The identifier of the ship from which the container will be unloaded.
     * @return true if the container was successfully unloaded from the ship, false otherwise.
     */
    public boolean unloadContainerFromShip(String containerIdentifier, String shipIdentifier) {
        // Find the container
        Node<Container> containerNode = containers.find(containerIdentifier);
        if (containerNode == null) {
            return false; // Container not found
        }

        // Find the ship
        Node<ContainerShip> shipNode = ships.find(shipIdentifier);
        if (shipNode == null) {
            return false; // Ship not found
        }

        // Check if the ship is docked at a port
        Port currentPort = shipNode.getData().getPort();
        if (currentPort == null) {
            return false; // Ship is not at a port
        }

        // Unload the container from the ship
        boolean isUnloaded = shipNode.getData().unloadContainer(containerNode.getData());

        // If the container was unloaded successfully, update the container's location
        if (isUnloaded) {
            containerNode.getData().setLocation(currentPort);
        }

        return isUnloaded;
    }

    /**
     * Returns a list of all containers in the system.
     * @return A LinkedList of all containers in the system.
     */
    public LinkedList<Container> getContainers() {
        return this.containers;
    }

    //-------------------
    // Pallet Handler
    //-------------------

    /**
     * Adds a pallet to a container.
     * @param description The description of the pallet.
     * @param quantity The quantity of the pallet.
     * @param unitValue The unit value of the pallet.
     * @param totalWeight The total weight of the pallet.
     * @param totalSize The total size of the pallet.
     * @param containerIdentifier The identifier of the container to which the pallet will be added.
     * @return true if the pallet was added successfully, false otherwise.
     */
    public boolean addPallet(String description, int quantity, double unitValue, double totalWeight, int totalSize, String containerIdentifier) {
        // Create a new pallet
        Pallet newPallet = new Pallet(description, quantity, unitValue, totalWeight, totalSize);

        // Find the container with the provided identifier
        Node<Container> containerNode = containers.find(containerIdentifier);
        if (containerNode == null) {
            return false; // Container not found
        }

        // Check if the container has enough space for the pallet and add it
        if (containerNode.getData().canAddPallet(newPallet)) {
            containerNode.getData().addPallet(newPallet);
            newPallet.setContainer(containerNode.getData());
            return true;
        }

        // There is not enough space in the container
        return false;
    }

    /**
     * Removes a pallet from a container.
     * @param palletDescription The description of the pallet to be removed.
     * @param containerIdentifier The identifier of the container from which the pallet will be removed.
     * @return true if the pallet was successfully removed, false otherwise.
     */
    public boolean removePalletFromContainer(String palletDescription, String containerIdentifier) {
        // Find the container
        Node<Container> containerNode = containers.find(containerIdentifier);
        if (containerNode == null) {
            return false; // Container not found
        }

        // Find the pallet
        Node<Pallet> palletNode = containerNode.getData().getPallets().find(palletDescription);
        if (palletNode == null) {
            return false; // Pallet not found
        }

        // Remove the pallet from the container
        containerNode.getData().removePallet(palletNode.getData());

        // Remove the pallet from the pallets list
        pallets.remove(palletNode.getData());

        return true;
    }

    //-------------------
    // Searching
    //-------------------

    /**
     * Searches for goods in the system.
     * @param description The name of the goods to search for.
     * @return A LinkedList of all pallets containing the specified goods.
     */
    public LinkedList<Pallet> searchGoods(String description) {
        // Create an empty LinkedList to store the found pallets
        LinkedList<Pallet> foundPallets = new LinkedList<>();

        // Get the list of containers from the ShippingAPI
        LinkedList<Container> containerLinkedList = getContainers();

        // Iterate over the LinkedList of containers
        for (int i = 0; i < containerLinkedList.size(); i++) {
            // Get the list of pallets in the current container
            LinkedList<Pallet> palletLinkedList = containerLinkedList.get(i).getPallets();

            // Iterate over the LinkedList of pallets
            for (int j = 0; j < palletLinkedList.size(); j++) {
                // If the description of the pallet matches the description passed as a parameter, add it to the found pallets list
                if (palletLinkedList.get(j).getDescription().equalsIgnoreCase(description)) {
                    foundPallets.add(palletLinkedList.get(j));
                }
            }
        }

        // Return the list of found pallets
        return foundPallets;
    }

    /**
     * This method adds a pallet to a suitable container at a specified port or on a docked ship at the port.
     * The suitability of a container can be determined based the remaining space in the container.
     *
     * @param description The description of the goods.
     * @param quantity The quantity of the goods.
     * @param unitValue The unit value of the goods.
     * @param totalWeight The total weight of the goods.
     * @param totalSize The total size of the goods.
     * @param portCode The code of the port where the goods are to be stored.
     * @return true if the goods were successfully added to a container, false otherwise.
     */
    public boolean smartAddPallet(String description, int quantity, double unitValue, double totalWeight, int totalSize, String portCode) {
        // Create a new pallet
        Pallet newPallet = new Pallet(description, quantity, unitValue, totalWeight, totalSize);

        // Find the port with the provided identifier
        Node<Port> portNode = ports.find(portCode);
        if (portNode == null) {
            return false; // Port not found
        }

        // Get the list of containers at the port
        LinkedList<Container>.MyIterator containerIterator = portNode.getData().getContainers().iterator();

        // Iterate over the containers at the port to find a suitable one
        while (containerIterator.hasNext()) {
            Container container = containerIterator.next();
            if (container.canAddPallet(newPallet)) {
                // Add the pallet to the container and update its location
                container.addPallet(newPallet);
                newPallet.setContainer(container);
                return true;
            }
        }

        // Get the list of docked ships at the port
        LinkedList<ContainerShip>.MyIterator shipIterator = portNode.getData().getDockedShips().iterator();

        // Iterate over the docked ships and their containers to find a suitable one
        while (shipIterator.hasNext()) {
            ContainerShip ship = shipIterator.next();
            containerIterator = ship.getContainers().iterator();
            while (containerIterator.hasNext()) {
                Container container = containerIterator.next();
                if (container.canAddPallet(newPallet)) {
                    // Add the pallet to the container and update its location
                    container.addPallet(newPallet);
                    newPallet.setContainer(container);
                    return true;
                }
            }
        }

        // No suitable container found
        return false;
    }

    //-------------------
    // System Data
    //-------------------

    /**
     * Resets all system data.
     */
    public void reset() {
        this.ports = new PortList();
        this.ships = new ContainerShipList();
        this.containers = new ContainerList();
        this.pallets = new PalletList();
    }

    /**
     * Saves the current state of the system to a file.
     * @param filename The name of the file where the data will be saved.
     */
    public void saveData(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.println("Data saved successfully to " + filename);
        } catch (IOException i) {
            System.out.println("Error saving data: " + i.getMessage());
        }
    }

    /**
     * Loads the state of the system from a file.
     * @param filename The name of the file from which the data will be loaded.
     * @return A ShippingAPI object containing the loaded data.
     */
    public static ShippingAPI loadData(String filename) {
        ShippingAPI api = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            api = (ShippingAPI) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Data loaded successfully from " + filename);
        } catch (IOException i) {
            System.out.println("Error loading data: " + i.getMessage());
        } catch (ClassNotFoundException c) {
            System.out.println("ShippingAPI class not found");
        }
        return api;
    }
}
