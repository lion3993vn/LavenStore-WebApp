/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lavenstore.orders;

import java.util.ArrayList;
import java.util.List;
import lavenstore.products.ProductDTO;

/**
 *
 * @author Pham Hieu
 */
public class Cart {

    private List<ItemCart> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<ItemCart> getItems() {
        return items;
    }

    public int getQuantityByID(int id) {
        return getItemCartByID(id).getQuantity();
    }

    private ItemCart getItemCartByID(int id) {
        for (ItemCart i : items) {
            if (i.getProduct().getID() == id) {
                return i;
            }
        }
        return null;
    }

    public void addItemCart(ItemCart t) {
        if (getItemCartByID(t.getProduct().getID()) != null) {
            ItemCart m = getItemCartByID(t.getProduct().getID());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItemCart(int id) {
        if (getItemCartByID(id) != null) {
            items.remove(getItemCartByID(id));
        }
    }

    public int getTotalInCart() {
        int t = 0;
        for (ItemCart i : items) {
            t += (i.getQuantity() * i.getPrice());
        }
        return t;
    }

    private ProductDTO getProductByID(int id, List<ProductDTO> list) {
        for (ProductDTO i : list) {
            if (i.getID() == id) {
                return i;
            }
        }
        return null;
    }

    public Cart(String txt, List<ProductDTO> list) {
        items = new ArrayList<>();
        try{
            if (txt != null && txt.length() != 0) {
            String[] item = txt.split(",");
            for (String i : item) {
                String[] infoItem = i.split(":");
                int id = Integer.parseInt(infoItem[0]);
                int quantity = Integer.parseInt(infoItem[1]);
                ProductDTO p = getProductByID(id, list);
                ItemCart t = new ItemCart(p, quantity, p.getPrice());
                addItemCart(t);
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
