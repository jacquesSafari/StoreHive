package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shoppinglistitem database table.
 * 
 */
@Entity
@Table(name="shoppinglistitem")
@NamedQuery(name="Shoppinglistitem.findAll", query="SELECT s FROM Shoppinglistitem s")
public class Shoppinglistitem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="item_name")
	private String itemName;

	//bi-directional one-to-one association to Category
	@OneToOne
	@JoinColumn(name="c_id")
	private Category category;

	//bi-directional many-to-one association to Shoppinglist
	@ManyToOne
	@JoinColumn(name="li_id")
	private Shoppinglist shoppinglist;

	public Shoppinglistitem() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Shoppinglist getShoppinglist() {
		return this.shoppinglist;
	}

	public void setShoppinglist(Shoppinglist shoppinglist) {
		this.shoppinglist = shoppinglist;
	}

}