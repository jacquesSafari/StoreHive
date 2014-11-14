package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;


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

	//bi-directional one-to-one association to Product
	@OneToOne
	@JoinColumn(name="id", referencedColumnName="c_id")
	private Product product;

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

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shoppinglistitem getShoppinglistitem() {
		return this.shoppinglistitem;
	}

	public void setShoppinglistitem(Shoppinglistitem shoppinglistitem) {
		this.shoppinglistitem = shoppinglistitem;
	}

}