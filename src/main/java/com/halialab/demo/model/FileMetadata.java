/**
 * 
 */
package com.halialab.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Janath
 *
 */
public class FileMetadata implements Serializable {
	  
	  private static final long serialVersionUID = -3914247594925250908L;
	  private String method;
	  private String group;
	  private String action;
	  private String username;
	  private Date date;
	  private Long size;
	  private String fileName;
	  private String fileSize;
	  private String nickname;
	  private String remarks;
	  private String doc_type;
	  private String type;
	  private String quantity;
	  
	  private String otherData;
	  
	  public String getMethod() {
	    return method;
	  }
	  public void setMethod(String method) {
	    this.method = method;
	  }
	  public String getGroup() {
	    return group;
	  }
	  public void setGroup(String group) {
	    this.group = group;
	  }
	  public String getAction() {
	    return action;
	  }
	  public void setAction(String action) {
	    this.action = action;
	  }
	  public String getUsername() {
	    return username;
	  }
	  public void setUsername(String username) {
	    this.username = username;
	  }
	  public Date getDate() {
	    return date;
	  }
	  public void setDate(Date date) {
	    this.date = date;
	  }
	  public Long getSize() {
	    return size;
	  }
	  public void setSize(Long size) {
	    this.size = size;
	  }
	  public String getOtherData() {
	    return otherData;
	  }
	  public void setOtherData(String otherData) {
	    this.otherData = otherData;
	  }
	  public String getFileName() {
	    return fileName;
	  }
	  public void setFileName(String fileName) {
	    this.fileName = fileName;
	  }
	/**
	 * @return the fileSize
	 */
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
	 * @return the doc_type
	 */
	public String getDoc_type() {
		return doc_type;
	}
	/**
	 * @param doc_type the doc_type to set
	 */
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	/**
	 * @return the type
	 */
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
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	}
