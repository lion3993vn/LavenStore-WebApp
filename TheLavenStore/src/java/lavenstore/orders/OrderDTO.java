/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.orders;

import java.util.Date;

/**
 *
 * @author Pham Hieu
 */
public class OrderDTO {
    private int ID;
    private int userID;
    private Date date;
    private Date paymentDate;
    private String paymentMethod;
    private String location;
    private String phoneNumber;
    private int price;
    private String status;
    private String orderCode;
    private String note;

    public OrderDTO(int ID, int userID, Date date, Date paymentDate, String paymentMethod, String location, String phoneNumber, int price, String status, String orderCode, String note) {
        this.ID = ID;
        this.userID = userID;
        this.date = date;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.status = status;
        this.orderCode = orderCode;
        this.note = note;
    }

    public OrderDTO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
