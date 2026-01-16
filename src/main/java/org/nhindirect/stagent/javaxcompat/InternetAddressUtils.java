package org.nhindirect.stagent.javaxcompat;

import javax.mail.internet.InternetAddress;

/**
 * Utility function for InternetAddress class.
 * @author gm2552
 * @since 9.0.0
 */
public class InternetAddressUtils {

	private InternetAddressUtils() {
		
	}
	
	/**
	 * The host part associated with an InternetAddress.
	 * @param addr The InternetAddress to parse.
	 * @return The host part of the InternetAddress
	 */
    public static String getHost(InternetAddress addr)
    {
    	String retVal = "";
    	
    	// remove any extra information such as < and >
    	String address = addr.getAddress();
    	int index;
    	if ((index = address.indexOf('<')) > -1)
    		address = address.substring(index + 1);
    	
    	if ((index = address.indexOf('>')) > -1)
    		address = address.substring(0, index); 
    	
    	index = address.indexOf("@");
    	if (index >= 0)
    		retVal = address.substring(index + 1);
    	
    	return retVal;
    }
	
}
