package org.nhindirect.common.javaxcompat.mail.dsn;

import java.util.Enumeration;
import java.util.List;

import javax.mail.Address;
import javax.mail.Header;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

/**
 * Generator interface for creating the human readable part of a DSN message.
 * @author gm2552
 * @since 9.0.0
 */
public interface DSNFailureTextBodyPartGenerator 
{
	/**
	 * Generates the human readable section of a DSN message with pre-populated information.
	 * @param originalSender The original sender of the message.
	 * @param failedRecipients List of recipients that did not receive the original message.
	 * @param originalMessageHeaders Enumeration of the headers of the original message.
	 * @return A mime body part containing the content of the human readable section of the DSN message
	 * @throws MessagingException
	 */
    public MimeBodyPart generate(Address originalSender, List<Address> failedRecipients,
    	    Enumeration<Header> originalMessageHeaders) throws MessagingException;
}