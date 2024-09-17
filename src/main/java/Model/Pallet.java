package Model;

import java.io.Serializable;

/**
 * The completed version of a models. Pallet class.
 * The Pallet class represents a pallet.
 * It implements the Serializable interface, allowing it to be written to and read from a stream.
 *
 * @author Davin Barron
 * @version 1.0
 */
public class Pallet implements Serializable {
    private String description;
    private int quantity;
    private double unitValue;
    private double totalWeight;
    private int totalSize;

    private Container container;

    /**
     * Constructs a new Pallet with the specified description, quantity, unit value, total weight, and total size.
     *
     * @param description The description of the pallet.
     * @param quantity The quantity of the pallet.
     * @param unitValue The unit value of the pallet.
     * @param totalWeight The total weight of the pallet.
     * @param totalSize The total size of the pallet.
     */
    public Pallet(String description, int quantity, double unitValue, double totalWeight, int totalSize) {
        setDescription(description);
        setQuantity(quantity);
        setUnitValue(unitValue);
        setTotalWeight(totalWeight);
        setTotalSize(totalSize);
    }

    //---------
    // Getters
    //---------

    /**
     * Returns the description of the pallet.
     * @return The pallet's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the quantity of the pallet.
     * @return The pallet's quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the unit value of the pallet.
     * @return The pallet's unit value.
     */
    public double getUnitValue() {
        return unitValue;
    }

    /**
     * Returns the total weight of the pallet.
     * @return The pallet's total weight.
     */
    public double getTotalWeight() {
        return totalWeight;
    }

    /**
     * Returns the total size of the pallet.
     * @return The pallet's total size.
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * Returns the container of the pallet.
     * @return The pallet's container.
     */
    public Container getContainer() {
        return container;
    }

    /**
     * Returns the value of the pallet.
     * @return The pallet's value.
     */
    public double getValue() {
        return getUnitValue() * getQuantity();
    }

    //---------
    // Setters
    //---------

    /**
     * Sets the description of the pallet.
     * @param description The new description for the pallet.
     */
    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    /**
     * Sets the quantity of the pallet.
     * @param quantity The new quantity for the pallet.
     */
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        }
    }

    /**
     * Sets the unit value of the pallet.
     * @param unitValue The new unit value for the pallet.
     */
    public void setUnitValue(double unitValue) {
        if (unitValue > 0) {
            this.unitValue = unitValue;
        }
    }

    /**
     * Sets the total weight of the pallet.
     * @param totalWeight The new total weight for the pallet.
     */
    public void setTotalWeight(double totalWeight) {
        if (totalWeight > 0) {
            this.totalWeight = totalWeight;
        }
    }

    /**
     * Sets the total size of the pallet.
     * @param totalSize The new total size for the pallet.
     */
    public void setTotalSize(int totalSize) {
        if (totalSize > 0) {
            this.totalSize = totalSize;
        }
    }

    /**
     * Sets the container of the pallet.
     * @param container The new container for the pallet.
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * Returns a string representation of the pallet.
     * Used for the Pallet Menu List View.
     * @return A string representation of the pallet.
     */
    public String toString() {
        String containerIdentifier = container.getContainerIdentifier();
        return "Description: " + getDescription() +
                ", Quantity: " + getQuantity() +
                ", Unit Value: " + getUnitValue() +
                ", Total Weight: " + getTotalWeight() +
                ", Total Size: " + getTotalSize() +
                ", Container: " + containerIdentifier;
    }
}
