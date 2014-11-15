package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@Table(name="store")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String description;

	@Column(name="is_open")
	private String isOpen;

	@Temporal(TemporalType.DATE)
	@Column(name="last_opened_date")
	private Date lastOpenedDate;

	@Column(name="owner_email")
	private String ownerEmail;

	@Column(name="shop_name")
	private String shopName;

	//bi-directional many-to-one association to Product
	@OneToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="s_id")
	private List<Product> products;

	//bi-directional many-to-one association to Storelocation
	@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name="s_id")
	private List<Storelocation> storelocations;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="store")
	private List<Transaction> transactions;

	//bi-directional one-to-one association to Storeowner
	@OneToOne
	@JoinColumn(name="s_id")
	private Storeowner storeowner;

	public Store() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsOpen() {
		return this.isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public Date getLastOpenedDate() {
		return this.lastOpenedDate;
	}

	public void setLastOpenedDate(Date lastOpenedDate) {
		this.lastOpenedDate = lastOpenedDate;
	}

	public String getOwnerEmail() {
		return this.ownerEmail;
	}

	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setStore(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setStore(null);

		return product;
	}

	public List<Storelocation> getStorelocations() {
		return this.storelocations;
	}

	public void setStorelocations(List<Storelocation> storelocations) {
		this.storelocations = storelocations;
	}

	public Storelocation addStorelocation(Storelocation storelocation) {
		getStorelocations().add(storelocation);
		storelocation.setStore(this);

		return storelocation;
	}

	public Storelocation removeStorelocation(Storelocation storelocation) {
		getStorelocations().remove(storelocation);
		storelocation.setStore(null);

		return storelocation;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setStore(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setStore(null);

		return transaction;
	}

	public Storeowner getStoreowner() {
		return this.storeowner;
	}

	public void setStoreowner(Storeowner storeowner) {
		this.storeowner = storeowner;
	}

}