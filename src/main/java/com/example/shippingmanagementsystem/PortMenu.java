package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import Model.LinkedList;
import Model.Port;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class PortMenu implements Serializable {
    @FXML
    private ListView<Port> portList;
    @FXML
    private TextField portNameField;
    @FXML
    private TextField portCodeField;
    @FXML
    private TextField countryField;
    @FXML
    private Button addButton;

    ShippingAPI shippingAPI = ShippingAPIInstance.getInstance();

    public void initialize() {
        // Load the data into the list
        updateList();
    }

    @FXML
    protected void addPort() {
        // Create a new Port object with the entered details
        Port newPort = new Port(portNameField.getText(), portCodeField.getText(), countryField.getText());

        // Add the new port to the system using the ShippingAPI
        boolean isAdded = shippingAPI.addPort(newPort.getPortName(), newPort.getPortCode(), newPort.getCountry());

        if (isAdded) {
            // Clear the text fields
            portNameField.clear();
            portCodeField.clear();
            countryField.clear();

            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The port could not be added.");
        }
    }

    private void updateList() {
        // Get the list of ports from the ShippingAPI
        LinkedList<Port> portLinkedList = shippingAPI.getPorts();

        // Create an empty ObservableList
        ObservableList<Port> observableList = FXCollections.observableArrayList();

        // Iterate over the LinkedList and add each Port to the ObservableList
        for (int i = 0; i < portLinkedList.size(); i++) {
            observableList.add(portLinkedList.get(i));
        }

        // Set the ObservableList as the items of the ListView
        portList.setItems(observableList);
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
