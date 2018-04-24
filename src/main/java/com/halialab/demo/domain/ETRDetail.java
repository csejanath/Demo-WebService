/**
 * 
 */
package com.halialab.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Janath
 *
 */
@Entity
public class ETRDetail {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // Username with unique constraint
    @Column(name = "assetID", nullable = false, unique = true)
    private String assetID;

    @JsonIgnore
    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Column(name = "filesize")
    private String filesize;
    
    @JsonIgnore
    @Column(name = "nickname")
    private String nickname;

    @JsonIgnore
    @Column(name = "remarks")
    private String remarks;
    
    @JsonIgnore
    @Column(name = "documentType")
    private String documentType;
    
    @JsonIgnore
    @Column(name = "quantity", nullable = false)
    private String quantity;

	/**
	 * 
	 */
	public ETRDetail() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the assetID
	 */
	public String getAssetID() {
		return assetID;
	}

	/**
	 * @param assetID the assetID to set
	 */
	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return fileName;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.fileName = filename;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
    
}
