package com.example.shippingmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class ShippingApplicationController implements Serializable {
    @FXML
    protected void openPortMenu() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("port-menu.fxml")));
        stage.setScene(scene);
        stage.setTitle("Port Menu");
        stage.show();
    }

    @FXML
    protected void openShipMenu() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ship-menu.fxml")));
        stage.setScene(scene);
        stage.setTitle("Ship Menu");
        stage.show();
    }

    @FXML
    protected void openContainerMenu() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("container-menu.fxml")));
        stage.setScene(scene);
        stage.setTitle("Container Menu");
        stage.show();
    }

    @FXML
    protected void openPalletMenu() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("pallet-menu.fxml")));
        stage.setScene(scene);
        stage.setTitle("Pallet Menu");
        stage.show();
    }

    @FXML
    protected void openSystem() throws IOException {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("system.fxml")));
        stage.setScene(scene);
        stage.setTitle("System");
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
        String originalColor = button.getText().equals("System") ? "#8930cf" : "#d01212";
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
        String originalColor = button.getText().equals("System") ? "#8930cf" : "#d01212";
        button.setStyle(button.getStyle().replace("-fx-background-color: #f67de5;", "-fx-background-color: " + originalColor + ";"));
    }
}
