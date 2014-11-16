package main.java.com.storehive.application.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name="transaction")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="amount_due")
	private int amountDue;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="time_stamp")
	private Date timeStamp;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="s_id")
	private Store store;

	//bi-directional many-to-one association to Transactiongood,fetch=FetchType.LAZY
	@OneToMany(cascade=CascadeType.ALL,mappedBy="transaction")
	private List<Transactiongood> transactiongoods;

	public Transaction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmountDue() {
		return this.amountDue;
	}

	public void setAmountDue(int amountDue) {
		this.amountDue = amountDue;
	}

	public Date getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<Transactiongood> getTransactiongoods() {
		return this.transactiongoods;
	}

	public void setTransactiongoods(List<Transactiongood> transactiongoods) {
		this.transactiongoods = transactiongoods;
	}

	public Transactiongood addTransactiongood(Transactiongood transactiongood) {
		getTransactiongoods().add(transactiongood);
		transactiongood.setTransaction(this);

		return transactiongood;
	}

	public Transactiongood removeTransactiongood(Transactiongood transactiongood) {
		getTransactiongoods().remove(transactiongood);
		transactiongood.setTransaction(null);

		return transactiongood;
	}

}