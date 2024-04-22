package DTO;

public class materialDTO {
    String material ;
    int id ;
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public materialDTO(int id,String material) {
        this.material = material;
        this.id = id;
    }
    public materialDTO() {
        //TODO Auto-generated constructor stub
    }
    
}   
