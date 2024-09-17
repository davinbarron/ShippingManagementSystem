package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import Model.Pallet;
import Model.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class PalletSearch implements Serializable {
    @FXML
    private ListView<Pallet> palletList;
    @FXML
    private TextField descriptionField;
    @FXML
    private Button searchButton;

    ShippingAPI shippingAPI = ShippingAPIInstance.getInstance();

    public void initialize() {
        // Initialize the list
        updateList("");
    }

    @FXML
    protected void searchGoods() {
        // Get the goods name from the search field
        String goodsName = descriptionField.getText();

        // Update the list with the search results
        updateList(goodsName);
    }

    private void updateList(String goodsName) {
        // Search for goods using the ShippingAPI
        LinkedList<Pallet> foundPallets = shippingAPI.searchGoods(goodsName);

        // Create an empty ObservableList
        ObservableList<Pallet> observableList = FXCollections.observableArrayList();

        // Add the found pallets to the ObservableList
        for (int i = 0; i < foundPallets.size(); i++) {
            observableList.add(foundPallets.get(i));
        }

        // Set the ObservableList as the items of the ListView
        palletList.setItems(observableList);
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
