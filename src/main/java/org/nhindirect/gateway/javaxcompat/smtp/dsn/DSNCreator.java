package org.nhindirect.gateway.javaxcompat.smtp.dsn;

import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;

import org.nhindirect.common.tx.model.Tx;

/**
 * Interface definition for creating DSN failure messages based on a Tx object a list of failed recipients.
 * @author Greg Meyer
 * @since 9.0
 */
public interface DSNCreator 
{
	/**
	 * Creates a DSN messages using message information from a Tx object a list of failed recipients. 
	 * @param tx Tx object containing information on the message that failed to be send or delivered.
	 * @param failedRecipeints List of intended recipients that did not receive the original message. 
	 * @return A MimeMessages representing the full DSN message.
	 * @throws MessagingException
	 * @deprecated As of 2.0.1.  This method does not correctly determine the domain of the postmaster for incoming messages.
	 * Use {@link #createDSNFailure(Tx, NHINDAddressCollection, boolean)}.  
	 */
	public MimeMessage createDSNFailure(Tx tx, List<InternetAddress> failedRecipeints) throws MessagingException;
	
	/**
	 * Creates a collection of DSN messages using message information from a Tx object a list of failed recipients.  A DSN message is created
	 * for each unique postmaster domain. <b><b>
	 * @param tx Tx object containing information on the message that failed to be send or delivered.
	 * @param failedRecipeints List of intended recipients that did not receive the original message. 
	 * @param useSenderDomainForPostmaster Indicates if the original sender or the recipients should be used to determine the postmaster domain.  
	 * Generally for rejected outgoing messages, the sender's domain is used, and for incoming messages the recipients' domains are used.
	 * @return A collection of MimeMessages representing the full DSN messages.
	 * @throws MessagingException
	 */
	public Collection<MimeMessage> createDSNFailure(Tx tx, List<InternetAddress> failedRecipeints, boolean useSenderDomainForPostmaster) throws MessagingException;
}

