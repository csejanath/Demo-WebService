/**
 * 
 */
package com.halialab.demo.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Janath
 *
 */
//@Entity
//@Table(name="LINK_DETAIL")
@DynamoDBTable(tableName = "Link_TB")
public class LinkDetail {
	
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "lid", nullable = false, updatable = false)
    private String lid;

    @JsonIgnore
//    @Column(name = "fileName", nullable = false)
    private String fileName;

//    @Column(name = "fileSize")
    private String fileSize;
    
    @JsonIgnore
//    @Column(name = "type")
    private String type;
    
    @JsonIgnore
//    @Column(name = "hash")
    private String hash;

	/**
	 * @return the lid
	 */
	@DynamoDBHashKey(attributeName = "lid")
	@DynamoDBAutoGeneratedKey 
	public String getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(String lid) {
		this.lid = lid;
	}

	/**
	 * @return the fileName
	 */
	@DynamoDBAttribute(attributeName = "FileName")
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileSize
	 */
	@DynamoDBAttribute(attributeName = "FileSize")
	public String getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the type
	 */
	@DynamoDBAttribute(attributeName = "Type")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the hash
	 */
	@DynamoDBAttribute(attributeName = "Hash")
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

}
