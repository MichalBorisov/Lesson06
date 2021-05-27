package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    private static final String INPUT_FILE = "RestaurantInput.txt";
    private static final String OUTPUT_FILE = "RestaurantOutPut.txt";

    public static void main(String[] args) {

        System.out.println("Extension for Home work is below:");
        OrderList orderList2 = new OrderList();
        orderList2 = CustomFileReader.LoadOrderListFromFile(INPUT_FILE);

        orderList2.PrintTotalMenuItemSold("Pizza");
        orderList2.PrintTotalOrderPriceGroupByDate();
        orderList2.PrintListOfOrderedMenuItems();

        for (Order ordertmp:
                orderList2.GetOrderList()) {
            orderList2.PrintSpecificOrderTotalPrice(ordertmp);
        }

        CustomFileWriter.SaveOrderListToFile(OUTPUT_FILE,orderList2);

        System.out.println("Original Home work is below:");
        OrderList orderList = new OrderList();
        ArrayList<MenuItem> tmpList = new ArrayList<MenuItem>();
        Order order = null;
        try {
            tmpList.add(new MenuItem(2, 33.5, "Cola"));
            tmpList.add(new MenuItem(1, 353, "Burger"));
            tmpList.add(new MenuItem(2, 243.77, "Pizza"));
            tmpList.add(new MenuItem(4, 93.5, "Soup"));
        }
        catch (OrderedMenuItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            order = new Order(1,2, LocalDate.of(2012,2,2),tmpList, PaymentType.CASH);
            orderList.AddNewOrder(order);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        orderList.PrintSpecificOrderTotalPrice(order);
        tmpList.clear();

        try {
            tmpList.add(new MenuItem(1, 33.5, "Water"));
            tmpList.add(new MenuItem(4, 353, "Salad Cezar"));
            tmpList.add(new MenuItem(3, 243.77, "Pizza"));
            tmpList.add(new MenuItem(4, 93.5, "Pasta"));
        }
        catch (OrderedMenuItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            order = new Order(2,2, LocalDate.of(2012,2,2),tmpList, PaymentType.CREDIT_CARD);
            orderList.AddNewOrder(order);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        orderList.PrintSpecificOrderTotalPrice(order);
        tmpList.clear();


        try {
            tmpList.add(new MenuItem(2, 33.5, "Cola"));
            tmpList.add(new MenuItem(4, 53, "Soda"));
            tmpList.add(new MenuItem(3, 343.77, "Pasta"));
            tmpList.add(new MenuItem(2, 193.5, "Gulas"));
        }
        catch (OrderedMenuItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            order = new Order(3,2, LocalDate.of(2015,4,2),tmpList, PaymentType.VOUCHER);
            orderList.AddNewOrder(order);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        orderList.PrintSpecificOrderTotalPrice(order);
        tmpList.clear();

        try {
            tmpList.add(new MenuItem(4, 33.5, "Fanta"));
            tmpList.add(new MenuItem(2, 353, "Chicken Wings"));
            tmpList.add(new MenuItem(1, 243.77, "Pizza"));
            tmpList.add(new MenuItem(5, 93.5, "Beef"));
        }
        catch (OrderedMenuItemException e) {
            System.out.println(e.getMessage());
        }

        try {
            order = new Order(4,2, LocalDate.of(2015,4,2),tmpList, PaymentType.CASH);
            orderList.AddNewOrder(order);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
        orderList.PrintSpecificOrderTotalPrice(order);

        orderList.PrintTotalMenuItemSold("Pizza");
        orderList.PrintTotalOrderPriceGroupByDate();
        orderList.PrintListOfOrderedMenuItems();
    }
}
