package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CustomFileReader {

    private static Order parseOrder(String orderLine) throws CustomException {
        String[] orderInfo = orderLine.split(CustomFileIO.INFO_DELIMITER);

        Integer orderId = null;
        Integer tableId = null;
        LocalDate orderDate = null;
        PaymentType paymentType = PaymentType.getPaymentType(orderInfo[3]);

        try {
            orderDate = LocalDate.parse(orderInfo[2]);
        }
        catch (DateTimeParseException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        try {
            tableId = Integer.parseInt(orderInfo[1]);
            orderId = Integer.parseInt(orderInfo[0].replace(CustomFileIO.ORDER_ID_SPECIFIC_CHAR,""));
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return new Order(orderId,tableId,orderDate,paymentType );
    }

    private static MenuItem parseMenuItem(String menuItemLine) throws CustomException, OrderedMenuItemException {
        String[] plantInfo = menuItemLine.split(CustomFileIO.INFO_DELIMITER);

        Integer amount = null;
        Double pricePerOne = null;
        String name = plantInfo[2];
        Integer orderId = null;

        try {
            amount = Integer.parseInt(plantInfo[0]);
            pricePerOne = Double.parseDouble(plantInfo[1]);
            orderId = Integer.parseInt(plantInfo[3].replace(CustomFileIO.ORDER_ID_SPECIFIC_CHAR,""));
        }
        catch (NumberFormatException e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        return new MenuItem(orderId,amount,pricePerOne,name );
    }

    public static OrderList LoadOrderListFromFile(String filePath)
    {
        OrderList orderList = new OrderList();
        Order order = null;
        MenuItem menuItem = null;
        try (Scanner scanner = new Scanner(new BufferedReader(new java.io.FileReader(filePath)))) {
            while (scanner.hasNext()) {
                String fileLine = scanner.nextLine();
                if (!fileLine.equals(CustomFileIO.ORDER_FINISHER)) {
                    if (fileLine.split(CustomFileIO.FILE_READ_ITEM_DELIMITER)[0].equals("Order")) {
                        try {
                            order = parseOrder(fileLine.split(CustomFileIO.FILE_READ_ITEM_DELIMITER)[1]);
                        } catch (CustomException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (fileLine.split(CustomFileIO.FILE_READ_ITEM_DELIMITER)[0].equals("MenuItem")) {
                        try {
                            menuItem = parseMenuItem(fileLine.split(CustomFileIO.FILE_READ_ITEM_DELIMITER)[1]);
                            order.InsertNewMenuItem(menuItem);
                        } catch (OrderedMenuItemException | CustomException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                else if(fileLine.equals(CustomFileIO.ORDER_FINISHER))
                {
                    orderList.AddNewOrder(order);
                    order = null;
                    menuItem = null;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Reading orders from file: "+filePath+" is successful. Total orders read: " + orderList.GetTotalOrders() + CustomFileIO.NEW_LINE);
        return orderList;
    }
}
