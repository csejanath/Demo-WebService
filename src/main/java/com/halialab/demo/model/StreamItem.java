/**
 * 
 */
package com.halialab.demo.model;

import java.util.List;

/**
 * @author Janath
 *
 */
public class StreamItem {
	  
	  private List<String> publishers;
	  private String key;
	  private String data;
	  private Long confirmations;
	  private String blockhash;
	  private Long blockindex;
	  private Long blocktime;
	  private String txid;
	  private Integer vout;
	  private Boolean valid;
	  private Long time;
	  private Long timereceived;
	  
	  public List<String> getPublishers() {
	    return publishers;
	  }
	  public void setPublishers(List<String> publishers) {
	    this.publishers = publishers;
	  }
	  public String getKey() {
	    return key;
	  }
	  public void setKey(String key) {
	    this.key = key;
	  }
	  public String getData() {
	    return data;
	  }
	  public void setData(String data) {
	    this.data = data;
	  }
	  public Long getConfirmations() {
	    return confirmations;
	  }
	  public void setConfirmations(Long confirmations) {
	    this.confirmations = confirmations;
	  }
	  public String getBlockhash() {
	    return blockhash;
	  }
	  public void setBlockhash(String blockhash) {
	    this.blockhash = blockhash;
	  }
	  public Long getBlockindex() {
	    return blockindex;
	  }
	  public void setBlockindex(Long blockindex) {
	    this.blockindex = blockindex;
	  }
	  public Long getBlocktime() {
	    return blocktime;
	  }
	  public void setBlocktime(Long blocktime) {
	    this.blocktime = blocktime;
	  }
	  public String getTxid() {
	    return txid;
	  }
	  public void setTxid(String txid) {
	    this.txid = txid;
	  }
	  public Integer getVout() {
	    return vout;
	  }
	  public void setVout(Integer vout) {
	    this.vout = vout;
	  }
	  public Boolean getValid() {
	    return valid;
	  }
	  public void setValid(Boolean valid) {
	    this.valid = valid;
	  }
	  public Long getTime() {
	    return time;
	  }
	  public void setTime(Long time) {
	    this.time = time;
	  }
	  public Long getTimereceived() {
	    return timereceived;
	  }
	  public void setTimereceived(Long timereceived) {
	    this.timereceived = timereceived;
	  }
	}

