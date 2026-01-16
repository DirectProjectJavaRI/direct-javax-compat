package org.nhindirect.common.javaxcompat.mail;

import java.io.ByteArrayOutputStream;

import javax.mail.internet.MimePart;

import org.apache.commons.io.IOUtils;

/**
 * Generic mail utility methods
 * @author Greg Meyer
 * @since 1.1
 */
public class MailUtil 
{
	/**
	 * Serializes a MimePart to a byte array
	 * @param message The message to serializes
	 * @return The message as a byte array.
	 */
	public static byte[] serializeToBytes(MimePart message)
    {
    	byte[] retVal;    	
    	try
    	{
    		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
    		message.writeTo(oStream);
    		oStream.flush();
    		retVal = oStream.toByteArray();
    		IOUtils.closeQuietly(oStream);	
    		
    	}
    	catch (Exception e)
    	{
    		throw new IllegalArgumentException("Failed to serialize message to bytes.", e);
    	}
    	
    	return retVal;
    }
}
