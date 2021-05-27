package com.company;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private int idOrder;
    private int idTable;
    private LocalDate orderDate;
    private ArrayList<MenuItem> orderedMenuItem;
    private PaymentType paymentType;

    public Order(int idOrder, int idTable, LocalDate orderDate, ArrayList<MenuItem> orderedMenuItem, PaymentType paymentType) throws CustomException {
        this.idOrder=idOrder;
        if(this.idOrder <= 0) throw new CustomException("Order Id must be greater then 0");

        this.idTable=idTable;
        if(this.idTable <= 0) throw new CustomException("Table Id must be greater then 0");

        this.orderDate=orderDate;
        this.orderedMenuItem = new ArrayList<>();
        if(orderedMenuItem.size() == 0) throw new CustomException("List of ordered items must be greater then 0");

        this.orderedMenuItem.addAll(orderedMenuItem);
        this.paymentType = paymentType;
    }
    public Order(int idOrder, int idTable, LocalDate orderDate, PaymentType paymentType) throws CustomException {
        this.idOrder=idOrder;
        if(this.idOrder <= 0) throw new CustomException("Order Id must be greater then 0");

        this.idTable=idTable;
        if(this.idTable <= 0) throw new CustomException("Table Id must be greater then 0");

        this.orderDate=orderDate;
        this.orderedMenuItem = new ArrayList<>();

        this.paymentType = paymentType;
    }
    public void InsertNewMenuItem(MenuItem menuItem)
    {
        this.orderedMenuItem.add(menuItem);
    }

    public Integer GetOrderId()
    {
        return Integer.valueOf(this.idOrder);
    }
    public Integer GetTableId()
    {
        return Integer.valueOf(this.idTable);
    }
    public LocalDate GetOrderDate()
    {
        return this.orderDate;
    }
    public ArrayList<MenuItem> GetOrderedMenuItems()
    {
        return this.orderedMenuItem;
    }
    public PaymentType GetPaymentType()
    {
        return this.paymentType;
    }

    public Double GetTotalOrderPrice()
    {
        Double tmp = Double.valueOf(0);
        for (MenuItem item:
                orderedMenuItem) {
            tmp += item.GetTotalPrice();
        }
        return tmp;
    }
    public Double GetItemMenuPrice(String menuItemName)
    {
        Double tmp = Double.valueOf(0);
        for (MenuItem item:
                orderedMenuItem) {
            if(item.GetMenuItemName().equals(menuItemName))
            tmp += item.GetTotalPrice();
        }
        return tmp;
    }
    public Integer GetTotalMenuItem(String menuItemName)
    {
        Integer tmp = Integer.valueOf(0);
        for (MenuItem item:
                orderedMenuItem) {
            if(item.GetMenuItemName().equals(menuItemName))
                tmp += item.GetQuantity();
        }
        return tmp;
    }
}
