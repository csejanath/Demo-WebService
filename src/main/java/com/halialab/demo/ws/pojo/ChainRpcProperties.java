/**
 * 
 */
package com.halialab.demo.ws.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Janath
 *
 */
@ConfigurationProperties(prefix = "chainrpc")
public class ChainRpcProperties {
  
  public static enum ServerMode { REGISTER, VERIFIER }
  
  private String protocol;
  private String host;
  private int port;
  private String chainName;
  private String username;
  private String password;
  private String streamName;
  private String publicKey;
  private ServerMode mode;
  
  public String getProtocol() {
    return protocol;
  }
  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }
  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }
  public int getPort() {
    return port;
  }
  public void setPort(int port) {
    this.port = port;
  }
  public String getChainName() {
    return chainName;
  }
  public void setChainName(String chainName) {
    this.chainName = chainName;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getStreamName() {
    return streamName;
  }
  public void setStreamName(String streamName) {
    this.streamName = streamName;
  }
  public String getPublicKey() {
    return publicKey;
  }
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
  public ServerMode getMode() {
    return mode;
  }
  public void setMode(ServerMode mode) {
    this.mode = mode;
  }
}
