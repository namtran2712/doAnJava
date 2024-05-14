package DTO;

import java.sql.Date;
import java.util.ArrayList;

@SuppressWarnings("unused")

public class productDTO {
	private int idProduct;
	private int idCategory;
	private int idMaterial;
	private String name;
	private int quantitySold;
	private String linkImg;
	private Date createAt;
	private ArrayList<particularProduct> particularProducts = new ArrayList<>();

	public productDTO() {

	}

	public productDTO(int idProduct, int idCategory, int idMaterial, String name, int quantitySold, String linkImg,
			Date createAt) {
		this.idProduct = idProduct;
		this.idCategory = idCategory;
		this.idMaterial = idMaterial;
		this.name = name;
		this.quantitySold = quantitySold;
		this.linkImg = linkImg;
		this.createAt = createAt;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public int getIdMaterial() {
		return idMaterial;
	}

	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public ArrayList<particularProduct> getParticularProducts() {
		return particularProducts;
	}

	public void setParticularProducts(int size, Float price, int quantityRemain) {
		particularProduct ptcd = new particularProduct(size, price, quantityRemain);
		particularProducts.add(ptcd);
	}
	

	@Override
	public String toString() {
		return "product [idProduct=" + idProduct + ", idCategory=" + idCategory + ", idMaterial=" + idMaterial
				+ ", name=" + name + ", quantitySold=" + quantitySold + ", linkImg=" + linkImg + ", createAt="
				+ createAt + ", particularProducts=" + particularProducts + "]";
	}

}