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

	}
