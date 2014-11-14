package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the storelocation database table.
 * 
 */
@Entity
@Table(name="storelocation")
@NamedQuery(name="Storelocation.findAll", query="SELECT s FROM Storelocation s")
public class Storelocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String latitude;

	private String longitude;

	//bi-directional one-to-one association to Store
	@OneToOne(mappedBy="storelocation")
	private Store store;

	public Storelocation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}