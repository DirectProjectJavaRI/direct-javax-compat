package org.nhindirect.common.javaxcompat.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * Common RFC822/5322 headers and common header collections. 
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
@Slf4j
public class MailStandard 
{
	
	public static class Headers
	{
	    public static final String To = "to";
	    public static final String CC = "cc";
	    public static final String BCC = "bcc";
	    public static final String From = "from";
	    public static final String Sender = "sender";
	    public static final String MessageID = "message-id";
	    public static final String Subject = "subject";
	    public static final String Date = "date";
	    public static final String OrigDate = "orig-date";
	    public static final String InReplyTo = "in-reply-to";
	    public static final String References = "references";
	    public static final String ContentType = "content-type";
	}

	
	/**
	 * Gets a specific header from the message
	 * @param msg The message to extract the message from.
	 * @param headerName The header name.
	 * @return The value of the header.  Return an empty string if the header is not found.
	 */
	public static String getHeader(MimeMessage msg, String headerName)
	{
		String retVal = "";
		
		try
		{
			retVal = msg.getHeader(headerName, ",");
			if (retVal == null)
				retVal = "";
		}
		catch (MessagingException e)
		{
			///CLOVER:OFF			
			log.warn("Failed to retrieve header \"{}\" from message.", headerName, e);
			///CLOVER:ON
		}
		
		return retVal;
	}
}
