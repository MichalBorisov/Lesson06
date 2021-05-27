package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class OrderList {
    private ArrayList<Order> orderList = new ArrayList<Order>();

    public OrderList() {
        orderList = new ArrayList<Order>();
    }

    public OrderList(ArrayList<Order> orderList) throws CustomException {
        this.orderList.addAll(orderList);
        if(orderList.size() == 0) throw new CustomException("List of orders must be greater then 0");
    }

    public void AddNewOrder(Order order) {
        this.orderList.add(order);
    }

    public Integer GetTotalOrders() {
        return Integer.valueOf(this.orderList.size());
    }
    public ArrayList<Order> GetOrderList() {
        return this.orderList;
    }

    private Double getSpecificOrderTotalPrice(Order order) {
        return order.GetTotalOrderPrice();
    }
    public void PrintSpecificOrderTotalPrice(Order order)
    {
        System.out.println("Order #" +order.GetOrderId() +" for table " + order.GetTableId() + " has total price of " + getSpecificOrderTotalPrice(order));
        System.out.println();
    }
    private Integer getTotalMenuItemSold(String menuItemName) {
        Integer tmp = Integer.valueOf(0);
        for (Order order :
                orderList) {
            tmp += order.GetTotalMenuItem(menuItemName);
        }
        return tmp;
    }
    public void PrintTotalMenuItemSold(String menuItemName)
    {
        System.out.println(menuItemName +" was sold "+getTotalMenuItemSold(menuItemName)+" times");
        System.out.println();
    }
    private Map<LocalDate, Double> getTotalOrderPriceGroupByDate() {
        Map<LocalDate, Double> tmpHashMap = new HashMap<LocalDate, Double>();
        for (Order order :
                orderList) {
            if(!tmpHashMap.containsKey(order.GetOrderDate()))
                tmpHashMap.put(order.GetOrderDate(),order.GetTotalOrderPrice());
            else
                tmpHashMap.put(order.GetOrderDate(),tmpHashMap.get(order.GetOrderDate())+order.GetTotalOrderPrice());
        }
        return tmpHashMap;
    }

    public void PrintTotalOrderPriceGroupByDate()
    {
        getTotalOrderPriceGroupByDate().forEach((day,total) -> System.out.println("Date: " +day.toString() + " | Total: " + total.toString()));
        System.out.println();
    }

    private HashSet<String> getListOfOrderedMenuItems() {
        HashSet<String> tmpHashSet = new HashSet<String>();
        for (Order order :
                orderList) {
            ArrayList<MenuItem> tmpMenuItemList= order.GetOrderedMenuItems();
            for (MenuItem menuItem :
                    tmpMenuItemList) {
                tmpHashSet.add(menuItem.GetMenuItemName());
            }
        }
        return tmpHashSet;
    }
    public void PrintListOfOrderedMenuItems()
    {
        getListOfOrderedMenuItems().forEach(menuItem -> System.out.println(menuItem));
        System.out.println();
    }
}
