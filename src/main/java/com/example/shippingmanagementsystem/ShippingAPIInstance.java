package com.example.shippingmanagementsystem;

import Controller.ShippingAPI;

import java.io.Serializable;

public class ShippingAPIInstance implements Serializable {
    private static ShippingAPI shippingAPI;

    private ShippingAPIInstance() {
        // private constructor to prevent instantiation
    }

    public static ShippingAPI getInstance() {
        if (shippingAPI == null) {
            shippingAPI = new ShippingAPI();
        }
        return shippingAPI;
    }

    public static void setInstance(ShippingAPI api) {
        shippingAPI = api;
    }
}
