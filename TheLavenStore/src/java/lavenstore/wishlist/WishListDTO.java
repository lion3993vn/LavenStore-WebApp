/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.wishlist;

/**
 *
 * @author Pham Hieu
 */
public class WishListDTO {
    private int ID;
    private int UserID;
    private int ProductID;

    public WishListDTO() {
    }

    public WishListDTO(int ID, int UserID, int ProductID) {
        this.ID = ID;
        this.UserID = UserID;
        this.ProductID = ProductID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }
    
}
