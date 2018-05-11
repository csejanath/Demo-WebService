///**
// * 
// */
//package com.halialab.demo.domain;
//
//import java.util.UUID;
//
//import org.springframework.data.cassandra.core.mapping.PrimaryKey;
//import org.springframework.data.cassandra.core.mapping.Table;
//
///**
// * @author Janath
// *
// */
//@Table("user")
//public class Usr {
//	
//    @PrimaryKey
//    private UUID id;
//    private String username;
//    private String password;
//    private String role;
//    private String address;
//    
//	/**
//	 * @param id
//	 * @param username
//	 * @param password
//	 * @param role
//	 * @param address
//	 */
//	public Usr(UUID id, String username, String password, String role, String address) {
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.role = role;
//		this.address = address;
//	}
//	/**
//	 * @return the id
//	 */
//	public UUID getId() {
//		return id;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setId(UUID id) {
//		this.id = id;
//	}
//	/**
//	 * @return the username
//	 */
//	public String getUsername() {
//		return username;
//	}
//	/**
//	 * @param username the username to set
//	 */
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	/**
//	 * @return the password
//	 */
//	public String getPassword() {
//		return password;
//	}
//	/**
//	 * @param password the password to set
//	 */
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	/**
//	 * @return the role
//	 */
//	public String getRole() {
//		return role;
//	}
//	/**
//	 * @param role the role to set
//	 */
//	public void setRole(String role) {
//		this.role = role;
//	}
//	/**
//	 * @return the address
//	 */
//	public String getAddress() {
//		return address;
//	}
//	/**
//	 * @param address the address to set
//	 */
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//}
