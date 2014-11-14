package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the transactiongoods database table.
 * 
 */
@Entity
@Table(name="transactiongoods")
@NamedQuery(name="Transactiongood.findAll", query="SELECT t FROM Transactiongood t")
public class Transactiongood implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int quantity;

	private int total;

	//bi-directional one-to-one association to Product
	@OneToOne
	@JoinColumn(name="p_id")
	private Product product;

	//bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name="t_id")
	private Transaction transaction;

	public Transactiongood() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}