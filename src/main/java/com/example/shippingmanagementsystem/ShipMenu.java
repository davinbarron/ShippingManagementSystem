package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import Model.ContainerShip;
import Model.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class ShipMenu implements Serializable {
    @FXML
    private ListView<ContainerShip> shipList;
    @FXML
    private TextField shipNameField;
    @FXML
    private TextField shipIdentifierField;
    @FXML
    private TextField flagStateField;
    @FXML
    private TextField photoURLField;
    @FXML
    private TextField portCodeField;
    @FXML
    private Button addButton;
    @FXML
    private Button launchButton;
    @FXML
    private Button dockButton;
    ShippingAPI shippingAPI = ShippingAPIInstance.getInstance();

    public void initialize() {
        // Load the data into the list
        updateList();
    }

    @FXML
    protected void addShip() {
        // Create a new ContainerShip object with the entered details
        ContainerShip newShip = new ContainerShip(shipNameField.getText(), shipIdentifierField.getText(), flagStateField.getText(), photoURLField.getText());

        // Add the new ship to the system using the ShippingAPI
        boolean isAdded = shippingAPI.addContainerShip(newShip.getShipName(), newShip.getShipIdentifier(), newShip.getFlagState(), newShip.getPhotoURL(), portCodeField.getText());

        if (isAdded) {
            // Clear the text fields
            shipNameField.clear();
            shipIdentifierField.clear();
            flagStateField.clear();
            photoURLField.clear();
            portCodeField.clear();

            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The ship could not be added. Make sure the port exists.");
        }
    }

    @FXML
    protected void launchShip() {
        // Get the selected ship
        ContainerShip selectedShip = shipList.getSelectionModel().getSelectedItem();

        // Launch the ship using the ShippingAPI
        boolean isLaunched = shippingAPI.launchShip(selectedShip.getShipIdentifier(), portCodeField.getText());

        if (isLaunched) {
            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The ship could not be launched. Make sure the ship is at the specified port.");
        }
    }

    @FXML
    protected void dockShip() {
        // Get the selected ship
        ContainerShip selectedShip = shipList.getSelectionModel().getSelectedItem();

        // Dock the ship using the ShippingAPI
        boolean isDocked = shippingAPI.dockShip(selectedShip.getShipIdentifier(), portCodeField.getText());

        if (isDocked) {
            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The ship could not be docked. Make sure the specified port exists.");
        }
    }

    private void updateList() {
        // Get the list of ships from the ShippingAPI
        LinkedList<ContainerShip> shipLinkedList = shippingAPI.getShips();

        // Create an empty ObservableList
        ObservableList<ContainerShip> observableList = FXCollections.observableArrayList();

        // Iterate over the LinkedList and add each ContainerShip to the ObservableList
        for (int i = 0; i < shipLinkedList.size(); i++) {
            observableList.add(shipLinkedList.get(i));
        }

        // Set the ObservableList as the items of the ListView
        shipList.setItems(observableList);
    }

    @FXML
    protected void buttonHover(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(button.getStyle() + "-fx-background-color: #3aa3a0;");
    }

    @FXML
    protected void buttonExit(MouseEvent event) {
        Button button = (Button) event.getSource();
        String originalColor = button.getText().equals("Remove Pallet") ? "#8930cf" : "#d01212";
        button.setStyle(button.getStyle().replace("-fx-background-color: #3aa3a0;", "-fx-background-color: " + originalColor + ";"));
    }

    @FXML
    protected void buttonPress(MouseEvent event) {
        Button button = (Button) event.getSource();
        button.setStyle(button.getStyle() + "-fx-background-color: #f67de5;");
    }

    @FXML
    protected void buttonRelease(MouseEvent event) {
        Button button = (Button) event.getSource();
        String originalColor = button.getText().equals("Remove Pallet") ? "#8930cf" : "#d01212";
        button.setStyle(button.getStyle().replace("-fx-background-color: #f67de5;", "-fx-background-color: " + originalColor + ";"));
    }
}
