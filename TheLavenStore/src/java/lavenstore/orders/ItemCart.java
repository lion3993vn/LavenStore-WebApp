/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.orders;

import lavenstore.products.ProductDTO;

/**
 *
 * @author Pham Hieu
 */
public class ItemCart {
    private ProductDTO product;
    private int quantity;
    private int price;

    public ItemCart() {
    }

    public ItemCart(ProductDTO product, int quantity, int price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
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