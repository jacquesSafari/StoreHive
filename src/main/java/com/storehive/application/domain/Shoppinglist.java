package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shoppinglist database table.
 * 
 */
@Entity
@Table(name="shoppinglist")
@NamedQuery(name="Shoppinglist.findAll", query="SELECT s FROM Shoppinglist s")
public class Shoppinglist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="list_name")
	private String listName;

	//bi-directional many-to-one association to Shoppinglistitem
	@OneToMany(mappedBy="shoppinglist")
	private List<Shoppinglistitem> shoppinglistitems;

	public Shoppinglist() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getListName() {
		return this.listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public List<Shoppinglistitem> getShoppinglistitems() {
		return this.shoppinglistitems;
	}

	public void setShoppinglistitems(List<Shoppinglistitem> shoppinglistitems) {
		this.shoppinglistitems = shoppinglistitems;
	}

	public Shoppinglistitem addShoppinglistitem(Shoppinglistitem shoppinglistitem) {
		getShoppinglistitems().add(shoppinglistitem);
		shoppinglistitem.setShoppinglist(this);

		return shoppinglistitem;
	}

	public Shoppinglistitem removeShoppinglistitem(Shoppinglistitem shoppinglistitem) {
		getShoppinglistitems().remove(shoppinglistitem);
		shoppinglistitem.setShoppinglist(null);

		return shoppinglistitem;
	}

}