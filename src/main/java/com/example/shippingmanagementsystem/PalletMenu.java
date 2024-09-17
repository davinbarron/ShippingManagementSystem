package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import Model.Container;
import Model.LinkedList;
import Model.Pallet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class PalletMenu implements Serializable {
    @FXML
    private ListView<Pallet> palletList;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField unitValueField;
    @FXML
    private TextField totalWeightField;
    @FXML
    private TextField totalSizeField;
    @FXML
    private TextField containerIdentifierField;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;

    @FXML
    private Button openSearchButton;

    @FXML
    private Button openValuationButton;

    @FXML
    private Button smartAddButton;

    ShippingAPI shippingAPI = ShippingAPIInstance.getInstance();

    public void initialize() {
        // Load the data into the list
        updateList();
    }

    @FXML
    protected void addPallet() {
        // Create a new Pallet object with the entered details
        Pallet newPallet = new Pallet(descriptionField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(unitValueField.getText()), Double.parseDouble(totalWeightField.getText()), Integer.parseInt(totalSizeField.getText()));

        // Add the new pallet to the system using the ShippingAPI
        boolean isAdded = shippingAPI.addPallet(newPallet.getDescription(), newPallet.getQuantity(), newPallet.getUnitValue(), newPallet.getTotalWeight(), newPallet.getTotalSize(), containerIdentifierField.getText());

        if (isAdded) {
            // Clear the text fields
            descriptionField.clear();
            quantityField.clear();
            unitValueField.clear();
            totalWeightField.clear();
            totalSizeField.clear();
            containerIdentifierField.clear();

            // Update the list
            updateList();
        } else {
            // Show an error message
            System.out.println("Error: The pallet could not be added.");
        }
    }

    @FXML
    protected void removePallet() {
        // Get the selected pallet from the list view
        Pallet selectedPallet = palletList.getSelectionModel().getSelectedItem();
        System.out.println("Selected pallet: " + selectedPallet);

        if (selectedPallet != null) {
            // Get the container of the selected pallet
            Container container = selectedPallet.getContainer();
            System.out.println("Container of the selected pallet: " + container);

            // Remove the pallet from the system using the ShippingAPI
            boolean isRemoved = shippingAPI.removePalletFromContainer(selectedPallet.getDescription(), container.getContainerIdentifier());
            System.out.println("Is pallet removed: " + isRemoved);

            if (isRemoved) {
                // Update the list
                updateList();
            } else {
                // Show an error message
                System.out.println("Error: The pallet could not be removed.");
            }
        }
    }

    private void updateList() {
        // Get the list of containers from the ShippingAPI
        LinkedList<Container> containerLinkedList = shippingAPI.getContainers();

        // Create an empty ObservableList
        ObservableList<Pallet> observableList = FXCollections.observableArrayList();

        // Iterate over the LinkedList and add each Pallet to the ObservableList
        for (int i = 0; i < containerLinkedList.size(); i++) {
            LinkedList<Pallet> palletLinkedList = containerLinkedList.get(i).getPallets();
            for (int j = 0; j < palletLinkedList.size(); j++) {
                observableList.add(palletLinkedList.get(j));
            }
        }

        // Set the ObservableList as the items of the ListView
        palletList.setItems(observableList);
    }

    @FXML
    protected void openSearchWindow() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("pallet-search.fxml")));
        stage.setScene(scene);
        stage.setTitle("Port Menu");
        stage.show();
    }

    @FXML
    protected void openValuationWindow() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("valuation-menu.fxml")));
        stage.setScene(scene);
        stage.setTitle("Port Menu");
        stage.show();
    }

    @FXML
    protected void smartAddWindow() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("smart-add.fxml")));
        stage.setScene(scene);
        stage.setTitle("Smart Add");
        stage.show();
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

