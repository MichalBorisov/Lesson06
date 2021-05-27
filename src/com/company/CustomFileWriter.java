package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public  class CustomFileWriter {

    public static void SaveOrderListToFile(String filePath, OrderList orderList)
    {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            if(orderList.GetTotalOrders() > 0)
            {
                for (Order order : orderList.GetOrderList()) {
                    writer.write("Order"+ CustomFileIO.FILE_WRITE_ITEM_DELIMITER
                            +  CustomFileIO.ORDER_ID_SPECIFIC_CHAR + order.GetOrderId() + CustomFileIO.INFO_DELIMITER
                            + order.GetTableId() + CustomFileIO.INFO_DELIMITER
                            + order.GetOrderDate() + CustomFileIO.INFO_DELIMITER
                            + order.GetPaymentType().name() + CustomFileIO.NEW_LINE);
                    for (MenuItem menuItem:
                         order.GetOrderedMenuItems()) {
                        writer.write("MenuItem"+ CustomFileIO.FILE_WRITE_ITEM_DELIMITER
                                + menuItem.GetQuantity() + CustomFileIO.INFO_DELIMITER
                                + menuItem.GetPrice() + CustomFileIO.INFO_DELIMITER
                                + menuItem.GetMenuItemName() + CustomFileIO.INFO_DELIMITER
                                +  CustomFileIO.ORDER_ID_SPECIFIC_CHAR +  menuItem.GetOrderId() + CustomFileIO.NEW_LINE);
                    }
                    writer.write(CustomFileIO.ORDER_FINISHER + CustomFileIO.NEW_LINE);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Writing orders to file: "+filePath+" is successful. Total orders written: " + orderList.GetTotalOrders() + CustomFileIO.NEW_LINE);
    }
}
