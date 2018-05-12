/**
 * 
 */
package com.halialab.demo.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Janath
 *
 */
@DynamoDBTable(tableName = "User_TB")
public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false, updatable = false)
//    private Long id;

    // Username with unique constraint
//    @Column(name = "username", nullable = false, unique = true)
    private String username;

//    @JsonIgnore
//    @Column(name = "password", nullable = false)
    private String passwordHash;

//    @Column(name = "role", nullable = false)
    private String role;
    
//    @JsonIgnore
//    @Column(name = "address", nullable = false)
    private String address;
    
    public User() {
    }

	public User(String username, String passwordHash, String role, String address) {
//		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
		this.address = address;
	}

//	@DynamoDBAttribute(attributeName = "Id")
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@DynamoDBHashKey(attributeName = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@DynamoDBAttribute(attributeName = "PasswordHash")
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@DynamoDBAttribute(attributeName = "Role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the address
	 */
	@DynamoDBAttribute(attributeName = "Address")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
//		return String.format("Customer[id=%s, firstName='%s', lastName='%s']", id, firstName, lastName);

		return "User [username=" + username + ", passwordHash=" + passwordHash + ", role=" + role + ", address="
				+ address + "]";
	}
	
}
