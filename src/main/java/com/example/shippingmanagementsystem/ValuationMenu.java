package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class ValuationMenu implements Serializable {
    @FXML
    private ListView<String> valuationList;
    @FXML
    private Button systemButton;
    @FXML
    private Button portButton;
    @FXML
    private Button shipButton;
    @FXML
    private Button containerButton;
    @FXML
    private Button palletButton;

    ShippingAPI shippingAPI = ShippingAPIInstance.getInstance();

    public void initialize() {
        // Initialize the list
        updateList("");
    }

    @FXML
    protected void showSystemValuation() {
        // Calculate the total value of goods in the system
        double totalValue = 0;
        LinkedList<Port> ports = shippingAPI.getPorts();
        for (int i = 0; i < ports.size(); i++) {
            totalValue += ports.get(i).getTotalValue();
        }

        // Update the list with the total value
        updateList("Overall Value of Goods: " + totalValue);
    }

    @FXML
    protected void showPortValuation() {
        // Get the list of ports from the ShippingAPI
        LinkedList<Port> ports = shippingAPI.getPorts();

        // Calculate the total value of goods in each port
        for (int i = 0; i < ports.size(); i++) {
            Port port = ports.get(i);
            double totalValue = port.getTotalValue();
            updateList("Total Value of Goods at Ports: " + totalValue);
        }
    }

    @FXML
    protected void showShipValuation() {
        // Get the list of ships from the ShippingAPI
        LinkedList<ContainerShip> ships = shippingAPI.getShips();

        // Calculate the total value of goods in each ship
        for (int i = 0; i < ships.size(); i++) {
            ContainerShip ship = ships.get(i);
            double totalValue = ship.getTotalValue();
            updateList("Total Value of Goods on Ships: " + totalValue);
        }
    }

    @FXML
    protected void showContainerValuation() {
        // Get the list of containers from the ShippingAPI
        LinkedList<Container> containers = shippingAPI.getContainers();

        // Calculate the total value of goods in each container
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            double totalValue = container.getTotalValue();
            updateList("Total Value of Goods in Containers: " + totalValue);
        }
    }

    @FXML
    protected void showPalletValuation() {
        // Get the list of containers from the ShippingAPI
        LinkedList<Container> containers = shippingAPI.getContainers();

        // Calculate the total value of goods in each pallet
        for (int i = 0; i < containers.size(); i++) {
            LinkedList<Pallet> pallets = containers.get(i).getPallets();
            for (int j = 0; j < pallets.size(); j++) {
                Pallet pallet = pallets.get(j);
                double totalValue = pallet.getValue();
                updateList("Total Value of Goods in Pallets: " + totalValue);
            }
        }
    }

    private void updateList(String valuation) {
        // Create an ObservableList with the valuation
        ObservableList<String> observableList = FXCollections.observableArrayList(valuation);

        // Set the ObservableList as the items of the ListView
        valuationList.setItems(observableList);
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
