package org.nhindirect.stagent.javaxcompat.mail;

import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;

/**
 * Common RFC822/5322 headers and common header collections. 
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
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
	
    public static final String[] DestinationHeaders = new String[] 
    {
    	Headers.To, 
    	Headers.From,
    	Headers.CC,
    	Headers.BCC
    };
    
    public static final String[] OriginHeaders = new String[] {
    	Headers.From, 
    	Headers.Sender};    
    
    public static final char MailAddressSeparator = ',';
    
    public static class MediaType
    {
    	public static final String WrappedMessage = "message/rfc822";
    }
    
    /*
     * Gets the content type of the entity
     */
    public static ContentType getContentType(MimeEntity entity)
    {
    	try
    	{
    		return new ContentType(entity.getContentType());
    	}
    	catch (MessagingException e) {/* no-op */}
    	
    	return null;
    }   
    
    /*
     * Gets the content type of the message
     */
    public static ContentType getContentType(MimeMessage msg)
    {
    	try
    	{
    		return new ContentType(msg.getContentType());
    	}
    	catch (MessagingException e) {/* no-op */}
    	
    	return null;
    }   
}

