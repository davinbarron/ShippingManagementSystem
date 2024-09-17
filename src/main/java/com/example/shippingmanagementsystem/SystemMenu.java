package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.Serializable;

public class SystemMenu implements Serializable {
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button resetButton;

    ShippingAPI shippingAPI = ShippingAPIInstance.getInstance();

    @FXML
    protected void saveData() {
        String filename = "shippingData.ser";
        shippingAPI.saveData(filename);
        System.out.println("Data saved successfully.");
    }

    @FXML
    protected void loadData() {
        String filename = "shippingData.ser";
        ShippingAPI loadedAPI = ShippingAPI.loadData(filename);
        if (loadedAPI != null) {
            ShippingAPIInstance.setInstance(loadedAPI);
            shippingAPI = ShippingAPIInstance.getInstance();
            System.out.println("Data loaded successfully.");
        } else {
            System.out.println("Failed to load data.");
        }
    }

    @FXML
    protected void resetData() {
        shippingAPI.reset();
        System.out.println("Data reset successfully.");
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
