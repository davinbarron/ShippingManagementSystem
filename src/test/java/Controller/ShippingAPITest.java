package Controller;

import Model.LinkedList;
import Model.Pallet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShippingAPITest {
    ShippingAPI api = new ShippingAPI();

    @Test
    void testAddPort() {
        assertTrue(api.addPort("Port1", "P1", "Country1"));
        assertFalse(api.addPort("Port2", "P1", "Country2")); // Port code must be unique
    }

    @Test
    void testGetPorts() {
        api.addPort("Port1", "P1", "Country1");
        assertNotNull(api.getPorts());
    }

    @Test
    void testAddContainerShip() {
        api.addPort("Port1", "P1", "Country1");
        assertTrue(api.addContainerShip("Ship1", "S1", "Flag1", "URL1", "P1"));
        assertFalse(api.addContainerShip("Ship2", "S1", "Flag2", "URL2", "P1")); // Ship identifier must be unique
    }

    @Test
    void testLaunchShip() {
        api.addPort("Port1", "P1", "Country1");
        api.addContainerShip("Ship1", "S1", "Flag1", "URL1", "P1");
        assertTrue(api.launchShip("S1", "P1"));
        assertFalse(api.launchShip("S2", "P1")); // Ship not found
    }

    @Test
    void testDockShip() {
        api.addPort("Port1", "P1", "Country1");
        api.addContainerShip("Ship1", "S1", "Flag1", "URL1", "P1");
        api.launchShip("S1", "P1");
        assertTrue(api.dockShip("S1", "P1"));
        assertFalse(api.dockShip("S2", "P1")); // Ship not found
    }

    @Test
    void testGetShips() {
        api.addPort("Port1", "P1", "Country1");
        api.addContainerShip("Ship1", "S1", "Flag1", "URL1", "P1");
        assertNotNull(api.getShips());
    }

    @Test
    void testAddContainer() {
        api.addPort("Port1", "P1", "Country1");
        assertTrue(api.addContainer("C1", 10, "P1")); // Container size is valid and identifier is unique
        assertFalse(api.addContainer("C2", 15, "P1")); // Container size is invalid
        assertFalse(api.addContainer("C1", 20, "P1")); // Container identifier is not unique
    }

    @Test
    void testLoadContainerToShip() {
        api.addPort("Port1", "P1", "Country1");
        api.addContainerShip("Ship1", "S1", "Flag1", "URL1", "P1");
        api.addContainer("C1", 10, "P1");
        assertTrue(api.loadContainerToShip("C1", "S1"));
        assertFalse(api.loadContainerToShip("C1", "S2")); // Container not found
    }

    @Test
    void testUnloadContainerFromShip() {
        api.addPort("Port1", "P1", "Country1");
        api.addContainerShip("Ship1", "S1", "Flag1", "URL1", "P1");
        api.addContainer("C1", 10, "S1");
        api.loadContainerToShip("C1", "S1");
        assertTrue(api.unloadContainerFromShip("C1", "S1"));
        assertFalse(api.unloadContainerFromShip("C1", "S2")); // Container not found
    }


    @Test
    void testGetContainers() {
        api.addContainer("Container1", 10, "C1");
        assertNotNull(api.getContainers());
    }

    @Test
    void testAddPallet() {
        api.addPort("Port1", "P1", "Country1");
        assertTrue(api.addContainer("C1", 10, "P1"));
        assertTrue(api.addPallet("Pallet1", 1, 1.0, 1.0, 5, "C1"));
        assertFalse(api.addPallet("Pallet2", 1, 1.0, 1.0, 1500, "C1"));
        assertFalse(api.addPallet("Pallet3", 1, 1.0, 1.0, 5, "C2"));
    }

    @Test
    void testRemovePalletFromContainer() {
        ShippingAPI api = new ShippingAPI();

        // Add a container and a pallet
        api.addPort("Port1", "P1", "Country1");
        api.addContainer("C1", 10, "P1");
        api.addPallet("Pallet1", 1, 1.0, 1.0, 5, "C1");

        // Remove the pallet from the container
        boolean isRemoved = api.removePalletFromContainer("Pallet1", "C1");

        // Check if the pallet was removed
        assertTrue(isRemoved);

        // Try to remove the pallet again (should return false since the pallet has already been removed)
        isRemoved = api.removePalletFromContainer("Pallet1", "C1");
        assertFalse(isRemoved);
    }

    @Test
    void testSearchGoods() {
        // Add a container
        api.addContainer("Container1", 10, "Port1");

        // Add a pallet to the container
        api.addPallet("Goods1", 10, 100.0, 50.0, 5, "Container1");

        // Search for the goods
        LinkedList<Pallet> foundPallets = api.searchGoods("Goods1");

        // Check that the correct pallet was found
        assertEquals(1, foundPallets.size());
        assertEquals("Goods1", foundPallets.get(0).getDescription());
    }

    @Test
    public void testSmartAddPallet() {
        ShippingAPI api = new ShippingAPI();
        api.addPort("Port1", "Code1", "Country1");
        api.addContainer("Container1", 40, "Code1");

        // Test adding a pallet to a non-existent port
        boolean result = api.smartAddPallet("Goods1", 10, 100.0, 50.0, 5, "NonExistentCode");
        assertFalse(result, "Expected false when adding a pallet to a non-existent port");

        // Test adding a pallet to an existing port but no suitable container
        result = api.smartAddPallet("Goods1", 10, 100.0, 50.0, 5000, "Code1");
        assertFalse(result, "Expected false when no suitable container is available");

        // Test adding a pallet to an existing port and suitable container
        result = api.smartAddPallet("Goods1", 10, 100.0, 50.0, 5, "Code1");
        assertTrue(result, "Expected true when adding a pallet to an existing port and suitable container");
    }

    @Test
    void testReset() {
        api.addPort("Port1", "P1", "Country1");
        api.reset();
        assertEquals(0, api.getPorts().size()); // Assuming getPorts() returns a List or similar collection
    }

    @Test
    void testSaveData() {
        api.addPort("Port1", "P1", "Country1");
        api.saveData("test.ser");
    }

    @Test
    void testLoadData() {
        api.addPort("Port1", "P1", "Country1");
        api.saveData("test.ser");

        ShippingAPI loadedApi = ShippingAPI.loadData("test.ser");
        assertNotNull(loadedApi);
        assertEquals(1, loadedApi.getPorts().size()); // Assuming getPorts() returns a List or similar collection
    }
}
