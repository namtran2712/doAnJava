package DTO;
public class categoryDTO {
    int idProduct;
    String categoryName;

    public int getIdProduct() {
        return idProduct;
    }



    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

  
    public categoryDTO(int idProduct, String categoryName) {
        this.idProduct = idProduct;
        this.categoryName = categoryName;
    }

    public categoryDTO() {
    }

}
