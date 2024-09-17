package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalletTest {
    Pallet pallet = new Pallet("Apples", 100, 1.0, 200.0, 10);

    @Test
    void testGetDescription() {
        assertEquals("Apples", pallet.getDescription());
    }

    @Test
    void testGetQuantity() {
        assertEquals(100, pallet.getQuantity());
    }

    @Test
    void testGetUnitValue() {
        assertEquals(1.0, pallet.getUnitValue());
    }

    @Test
    void testGetTotalWeight() {
        assertEquals(200.0, pallet.getTotalWeight());
    }

    @Test
    void testGetTotalSize() {
        assertEquals(10, pallet.getTotalSize());
    }

    @Test
    void testSetDescription() {
        pallet.setDescription("Oranges");
        assertEquals("Oranges", pallet.getDescription());
    }

    @Test
    void testSetQuantity() {
        pallet.setQuantity(200);
        assertEquals(200, pallet.getQuantity());
    }

    @Test
    void testSetUnitValue() {
        pallet.setUnitValue(2.0);
        assertEquals(2.0, pallet.getUnitValue());
    }

    @Test
    void testSetTotalWeight() {
        pallet.setTotalWeight(400.0);
        assertEquals(400.0, pallet.getTotalWeight());
    }

    @Test
    void testSetTotalSize() {
        pallet.setTotalSize(20);
        assertEquals(20, pallet.getTotalSize());
    }
}

