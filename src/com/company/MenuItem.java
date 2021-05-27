package com.company;

public class MenuItem {
    private String name;
    private double price;
    private int quantity;
    private int orderId;

    public MenuItem(int quantity, double price, String name) throws OrderedMenuItemException {
        this.name = name;
        if(this.name == " " || this.name == null || this.name == "") throw new OrderedMenuItemException("Order name cannot be empty");
        this.price = price;
        if(this.price <= 0) throw new OrderedMenuItemException("Menu item price must be greater then 0");
        this.quantity = quantity;
        if(this.quantity <= 0) throw new OrderedMenuItemException("Quantity must be greater then 0");
    }

    public MenuItem(int orderId, int quantity, double price, String name) throws OrderedMenuItemException {
        this.name = name;
        if(this.name == " " || this.name == null || this.name == "") throw new OrderedMenuItemException("Order name cannot be empty");
        this.price = price;
        if(this.price <= 0) throw new OrderedMenuItemException("Menu item price must be greater then 0");
        this.quantity = quantity;
        if(this.quantity <= 0) throw new OrderedMenuItemException("Quantity must be greater then 0");
        this.orderId = orderId;
        if(this.orderId <= 0) throw new OrderedMenuItemException("Order ID must be greater then 0");
    }
    public Integer GetOrderId()
    {
        return Integer.valueOf(this.orderId);
    }
    public Double GetPrice()
    {
        return Double.valueOf(this.price);
    }
    public Double GetTotalPrice()
    {
        return Double.valueOf(this.price * this.quantity);
    }
    public Integer GetQuantity()
    {
        return Integer.valueOf(this.quantity);
    }
    public String GetMenuItemName()
    {
        return this.name;
    }
}
