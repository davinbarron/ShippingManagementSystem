package Model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalletListTest {
    PalletList palletList = new PalletList();

    @Test
    void testFind() {
        Pallet pallet1 = new Pallet("Apples", 100, 1.0, 200.0, 10);
        Pallet pallet2 = new Pallet("Oranges", 200, 2.0, 400.0, 20);
        palletList.add(pallet1);
        palletList.add(pallet2);

        assertEquals(pallet1, palletList.find("Apples").getData());
        assertEquals(pallet2, palletList.find("Oranges").getData());
        assertNull(palletList.find("Bananas"));
    }
}
