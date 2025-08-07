package org.nhindirect.common.javaxcompat.tx;

import java.io.InputStream;
import java.util.Map;

import org.nhindirect.common.tx.model.TxDetail;

import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

/**
 * Interface for a parser the transforms a message into a set of Tx details
 * @author Greg Meyer
 * @since 9.0.0
 */
public interface TxDetailParser 
{
	/**
	 * Processed a collection InternetHeaders into a set attributes that will be stored as a set of transaction details.  Most details will
	 * take the same name as their corresponding Mime standard header name.
	 * <p>
	 * This method is particularly helpful when dealing with very large messages that are streamed and you do not wish to 
	 * load the entire message into memory.  Instead, only the headers are extracted from the message stream and processed.
	 * <br>  
	 * <b>NOTE:<\b> Using only message headers will not disable the ability to process some attributes such as MDN dispositions and DNS messages because
	 * they need to be parsed from the message body.  
	 * @param headers The Internet headers to be processed.
	 * @return A map of detail names to values.
	 */
	public Map<String, TxDetail> getMessageDetails(InternetHeaders headers);
	
	/**
	 * Processed a MimeMessage represented as an input stream to a set attributes that will be stored as a set of transaction details.  Most details will
	 * take the same name as their corresponding Mime standard header name.
	 * @param stream The message that will be processed.
	 * @return A map of detail names to values.
	 */
	public Map<String, TxDetail> getMessageDetails(InputStream stream);
	
	/**
	 * Processed a MimeMessage into set attributes that will be stored as a set of transaction details.  Most details will
	 * take the same name as their corresponding Mime standard header name.
	 * @param msg The message that will be processed.
	 * @return A map of detail names to values.
	 */
	public Map<String, TxDetail> getMessageDetails(MimeMessage msg);
}
