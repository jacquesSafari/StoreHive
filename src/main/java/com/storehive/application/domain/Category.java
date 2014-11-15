package main.java.com.storehive.application.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name="category")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="category_description")
	private String categoryDescription;

	@Column(name="category_name")
	private String categoryName;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy = "category")
	private List<Product> products;

	//bi-directional one-to-one association to Shoppinglistitem
	@OneToOne(mappedBy="category")
	private Shoppinglistitem shoppinglistitem;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryDescription() {
		return this.categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCategory(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCategory(null);

		return product;
	}

	public Shoppinglistitem getShoppinglistitem() {
		return this.shoppinglistitem;
	}

	public void setShoppinglistitem(Shoppinglistitem shoppinglistitem) {
		this.shoppinglistitem = shoppinglistitem;
	}

}