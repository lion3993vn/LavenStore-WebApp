/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.orders;

/**
 *
 * @author Pham Hieu
 */
public class OrderDetailsDTO {
    private int ID;
    private int orderID;
    private int ProductID;
    private int quantity;
    private int price;

    public OrderDetailsDTO(int ID, int orderID, int ProductID, int quantity, int price) {
        this.ID = ID;
        this.orderID = orderID;
        this.ProductID = ProductID;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetailsDTO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
