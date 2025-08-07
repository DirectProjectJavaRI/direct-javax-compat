package org.nhindirect.stagent.javaxcompat.mail.notifications;

import java.util.Calendar;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetHeaders;

import org.nhindirect.stagent.javaxcompat.mail.MailStandard;
import org.nhindirect.stagent.javaxcompat.mail.Message;
import org.nhindirect.stagent.javaxcompat.mail.MimeStandard;

/**
 * Represents a message disposition notification (MDN) sent to a message sender, as per <a href="http://tools.ietf.org/html/rfc3798">RFC 3798</a> 
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public class NotificationMessage extends Message 
{
	/**
	 * Initializes an MDN to the specified recipient.
	 * @param to The MDN recipient.
	 * @param notification The notification to send.
	 * @throws MessagingException
	 */
    public NotificationMessage(String to, Notification notification) throws MessagingException 
	{
    	this(to, null, notification);
	}
    
    /**
     * Initializes an MDN to the specified recipient.
     * @param to The MDN recipient.
     * @param from The original message receiver who is sending the MDN
     * @param notification The notification to send.
     * @throws MessagingException
     */
    public NotificationMessage(String to, String from, Notification notification) throws MessagingException 
    {
    	super(createHeaders(to, from, notification), notification.serializeToBytes());
    	setSentDate(Calendar.getInstance().getTime());
    	
    } 
    
    private static InternetHeaders createHeaders(String to, String from, Notification notification) throws MessagingException
    {
    	InternetHeaders headers = new InternetHeaders();
    	
    	if (to != null && !to.isEmpty())
    		headers.addHeader(MailStandard.Headers.To, to);
    	
    	if (from != null && !from.isEmpty())
    		headers.addHeader(MailStandard.Headers.From, from);
    	
    	headers.addHeader(MimeStandard.VersionHeader, "1.0");
    	
    	// get the boundary
    	ContentType type = new ContentType(notification.getAsMultipart().getContentType());
    	String boundary = type.getParameter("boundary");	
    	
    	headers.addHeader(MailStandard.Headers.ContentType, MDNStandard.MediaType.DispositionReport + 
    			"; boundary=\"" + boundary + "\"");    	
    	
    	return headers;

    }
    
    /**
     * Takes a message and constructs an MDN.
     * @param message The message to send notification about.
     * @param notification The notification to create.
     * @return The MDN.
     */
    public static NotificationMessage createNotificationFor(Message message, Notification notification)
    {
        if (message == null)
        {
            throw new IllegalArgumentException();
        }

        if (notification == null)
        {
            throw new IllegalArgumentException();
        }
        //
        // Verify that the message is not itself an MDN!
        //
        if (NotificationHelper.isMDN(message))
        {
            throw new IllegalArgumentException("Message is an MDN");
        }
        
        String notifyTo = NotificationHelper.getNotificationDestination(message);
        if (notifyTo == null || notifyTo.isEmpty())
        {
            throw new IllegalArgumentException("Invalid Disposition-Notification-To Header");
        }
        
        NotificationMessage notificationMessage = null;
        
        try
        {
	        String originalMessageID = message.getMessageID();
	        if (originalMessageID != null && !originalMessageID.isEmpty())
	        {
	            notification.setOriginalMessageId(originalMessageID);
	        }
	        	        
	        notificationMessage = new NotificationMessage(notifyTo, notification);
	        notificationMessage.setHeader(MailStandard.Headers.MessageID, UUID.randomUUID().toString());
	        String subject = message.getHeader(MailStandard.Headers.Subject, ",");
	        if (subject == null)
	        	subject = "";

	        final String subjectPrefix = NotificationHelper.asString(notification.getDisposition().getNotification(), true);
	        notificationMessage.setHeader(MailStandard.Headers.Subject, subjectPrefix + ": " + subject);
	        notificationMessage.saveChanges();
        }
        catch (MessagingException e) {/* no-op */}
        
        return notificationMessage;
    }   
}
