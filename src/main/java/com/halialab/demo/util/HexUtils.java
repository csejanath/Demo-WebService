/**
 * 
 */
package com.halialab.demo.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Janath
 *
 */
public class HexUtils {
	  
	  private HexUtils() {
	    // do nothing
	  }

	  public static String decode(String hexString) throws DecoderException, UnsupportedEncodingException {
	    return new String(Hex.decodeHex(hexString.toCharArray()), StandardCharsets.UTF_8);
	  }
	  
	  public static String encode(String data) throws UnsupportedEncodingException {
	    return Hex.encodeHexString(data.getBytes(StandardCharsets.UTF_8));
	  }

	}

