/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.products;

/**
 *
 * @author Pham Hieu
 */
public class ProductDTO {
    private int ID;
    private String name;
    private int cateID;
    private int quantity;
    private int price;
    private float rate;
    private String description;
    private String image;

    public ProductDTO(int ID, String name, int cateID, int quantity, int price, float rate, String description, String image) {
        this.ID = ID;
        this.name = name;
        this.cateID = cateID;
        this.quantity = quantity;
        this.price = price;
        this.rate = rate;
        this.description = description;
        this.image = image;
    }

    public ProductDTO() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
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

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
