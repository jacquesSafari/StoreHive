package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="product_description")
	private String productDescription;

	@Column(name="product_name")
	private String productName;

	@Column(name="product_price")
	private int productPrice;

	@Column(name="product_quantity")
	private int productQuantity;
	
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="c_id")
	private Category category;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="s_id")
	private Store store;

	//bi-directional one-to-one association to Transactiongood
	@OneToOne(mappedBy="product")
	private Transactiongood transactiongood;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Transactiongood getTransactiongood() {
		return this.transactiongood;
	}

	public void setTransactiongood(Transactiongood transactiongood) {
		this.transactiongood = transactiongood;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	
}