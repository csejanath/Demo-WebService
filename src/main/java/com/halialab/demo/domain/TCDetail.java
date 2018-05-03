/**
 * 
 */
package com.halialab.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Janath
 *
 */
@Entity
@Table(name="TC_DETAIL")
public class TCDetail {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false, updatable = false)
    private Long cid;

    @JsonIgnore
    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Column(name = "fileSize")
    private String fileSize;
    
    @JsonIgnore
    @Column(name = "nickname")
    private String nickname;

    @JsonIgnore
    @Column(name = "remarks")
    private String remarks;
    
    @JsonIgnore
    @Column(name = "doc_type")
    private String doc_type;

	/**
	 * @return the cid
	 */
	public Long getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}

	/**
	 * @return the fileName
	 */
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

}
