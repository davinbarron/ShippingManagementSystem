package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {
    @Test
    void testContainer() {
        Container container = new Container("C1", 10);
        assertEquals("C1", container.getContainerIdentifier());
        assertEquals(10, container.getContainerSize());
    }

    @Test
    void testAddPallet() {
        Container container = new Container("C1", 10);
        Pallet pallet = new Pallet("Pallet1", 1, 1.0, 1.0, 5);
        container.addPallet(pallet);
        assertEquals(1, container.getPallets().size());
    }

    @Test
    public void testGetTotalPalletSize() {
        Container container = new Container("C1", 10);
        container.addPallet(new Pallet("Pallet1", 10, 100.0, 50.0, 5));
        container.addPallet(new Pallet("Pallet2", 20, 200.0, 100.0, 10));

        int expectedTotal = 15; // The total size of all pallets
        int actualTotal = container.getTotalPalletSize();

        assertEquals(expectedTotal, actualTotal, "The total size should be 30");
    }

    @Test
    void testGetTotalValue() {
        Container container = new Container("C1", 10);

        container.addPallet(new Pallet("Pallet1", 10, 100.0, 50.0, 5));
        container.addPallet(new Pallet("Pallet2", 20, 200.0, 100.0, 10));

        double expected = 3000.0;
        double actual = container.getTotalValue();
        assertEquals(expected, actual, "The total value should be 3000.0");
    }

    @Test
    void testRemovePallet() {
        Container container = new Container("C1", 10);
        Pallet pallet = new Pallet("Pallet1", 1, 1.0, 1.0, 5);
        container.addPallet(pallet);
        container.removePallet(pallet);
        assertEquals(0, container.getPallets().size());
    }

    @Test
    void testCanAddPallet() {
        Container container = new Container("C1", 10);
        Pallet pallet1 = new Pallet("Pallet1", 1, 1.0, 1.0, 5);
        assertTrue(container.canAddPallet(pallet1));
        Pallet pallet2 = new Pallet("Pallet2", 1, 1.0, 1.0, 1000);
        assertFalse(container.canAddPallet(pallet2));
    }
}


