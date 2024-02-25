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
public class CategoryDTO {
    private int cateID;
    private String cateName;

    public CategoryDTO(int cateID, String cateName) {
        this.cateID = cateID;
        this.cateName = cateName;
    }

    public CategoryDTO() {
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
}
