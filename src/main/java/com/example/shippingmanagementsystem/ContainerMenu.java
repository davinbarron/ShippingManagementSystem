package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import Model.Container;
import Model.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ContainerMenu {
    @FXML
    private ListView<Container> containerList;
    @FXML
    private TextField containerIdentifierField;
    @FXML
    private TextField containerSizeField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField loadShipField;
    @FXML
    private TextField unloadShipField;
    @FXML
    private Button loadButton;
    @FXML
    private Button unloadButton;
    @FXML
    private Button addButton;
    private ShippingAPI shippingAPI;

    public ContainerMenu() {
        shippingAPI = ShippingAPIInstance.getInstance();
    }

    public void initialize() {
        updateList();
    }

    @FXML
    protected void addContainer() {
        // Get the entered details
        String containerIdentifier = containerIdentifierField.getText();
        int containerSize = Integer.parseInt(containerSizeField.getText());
        String location = locationField.getText();

        // Add the new container to the system using the ShippingAPI
        boolean isAdded = shippingAPI.addContainer(containerIdentifier, containerSize, location);

        if (isAdded) {
            // Clear the text fields
            containerIdentifierField.clear();
            containerSizeField.clear();
            locationField.clear();

            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The container could not be added. Make sure the location exists.");
        }
    }

    @FXML
    protected void loadContainerToShip() {
        // Get the selected container from the list view
        Container selectedContainer = containerList.getSelectionModel().getSelectedItem();
        System.out.println("Selected container: " + selectedContainer);

        if (selectedContainer != null) {
            // Get the ship identifier from the text field
            String shipIdentifier = loadShipField.getText();

            // Load the container onto the ship using the ShippingAPI
            boolean isLoaded = shippingAPI.loadContainerToShip(selectedContainer.getContainerIdentifier(), shipIdentifier);
            System.out.println("Is container loaded: " + isLoaded);

            if (isLoaded) {
                // Clear the text field
                loadShipField.clear();

                // Update the list
                updateList();
            } else {
                // Show an error message
                System.out.println("Error: The container could not be loaded. Make sure the container and ship exist.");
            }
        }
    }

    @FXML
    protected void unloadContainerFromShip() {
        // Get the entered details
        String containerIdentifier = containerList.getSelectionModel().getSelectedItem().getContainerIdentifier();
        String shipIdentifier = unloadShipField.getText();

        // Unload the container from the ship using the ShippingAPI
        boolean isUnloaded = shippingAPI.unloadContainerFromShip(containerIdentifier, shipIdentifier);

        if (isUnloaded) {
            // Clear the text field
            unloadShipField.clear();

            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The container could not be unloaded. Make sure the container is on the selected ship.");
        }
    }

    private void updateList() {
        // Get the list of containers from the ShippingAPI
        LinkedList<Container> containerLinkedList = shippingAPI.getContainers();

        // Create an empty ObservableList
        ObservableList<Container> observableList = FXCollections.observableArrayList();

        // Iterate over the LinkedList and add each Container to the ObservableList
        for (int i = 0; i < containerLinkedList.size(); i++) {
            observableList.add(containerLinkedList.get(i));
        }

        // Set the ObservableList as the items of the ListView
        containerList.setItems(observableList);
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
