package main.java.com.storehive.application.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the storeowner database table.
 * 
 */
@Entity
@Table(name="storeowner", uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})
@NamedQuery(name="Storeowner.findAll", query="SELECT s FROM Storeowner s")
public class Storeowner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="device_id")
	private String deviceId;

	private String email;

	private String fullname;

	private String password;

	@Temporal(TemporalType.DATE)
	@Column(name="registered_date")
	private Date registeredDate;

	//bi-directional one-to-one association to Store
	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id", referencedColumnName="so_id")
	private Store store;

	public Storeowner() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisteredDate() {
		return this.registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}