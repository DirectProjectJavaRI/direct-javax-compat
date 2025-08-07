package org.nhindirect.common.javaxcompat.tx;

import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.ParseException;

import org.nhindirect.common.javaxcompat.mail.MDNStandard;
import org.nhindirect.common.javaxcompat.mail.SMIMEStandard;
import org.nhindirect.common.javaxcompat.mail.dsn.DSNStandard;
import org.nhindirect.common.javaxcompat.tx.impl.DefaultTxDetailParser;
import org.nhindirect.common.tx.model.Tx;
import org.nhindirect.common.tx.model.TxDetail;
import org.nhindirect.common.tx.model.TxDetailType;
import org.nhindirect.common.tx.model.TxMessageType;

import lombok.extern.slf4j.Slf4j;


/**
 * Utility class for message monitoring
 * @author Greg Meyer
 * @since 1.1
 */
@Slf4j
public class TxUtil 
{
	/**
	 * Gets the message type based on the content type headers.
	 * @param msg The message to extract the type from.
	 * @return MDN if the message is an MDN message (<a href="http://tools.ietf.org/html/rfc3798">RFC 3798</a>)<br>
	 * DSN if the message is a DNS message (<a href="http://tools.ietf.org/html/rfc3464">RFC 3464</a>)<br>
	 * Normal for all other message type.<br>
	 * Return Unknown if an error occurs.
	 * 
	 */
	public static TxMessageType getMessageType(MimeMessage msg)
	{
		try
		{
	        ContentType contentType = new ContentType(msg.getContentType());
	
	        if (contentType.match(MDNStandard.MediaType.ReportMessage) && 
	        		contentType.getParameter(MDNStandard.MediaType.ReportType) != null)
	        {	
	        	 
	        	if (contentType.getParameter(MDNStandard.MediaType.ReportType).equalsIgnoreCase(MDNStandard.MediaType.ReportTypeValueNotification))	        
	        		return TxMessageType.MDN;
	        	else if (contentType.getParameter(DSNStandard.MediaType.ReportType).equalsIgnoreCase(DSNStandard.MediaType.ReportTypeValueDelivery))	        
	        		return TxMessageType.DSN;	        	
	        }
	        else if (contentType.match(SMIMEStandard.EncryptedContentMediaType) || 
	        		contentType.match(SMIMEStandard.EncryptedContentMediaTypeAlternative))
	        {
	        	return TxMessageType.SMIME;	 
	        }
	        
	        return TxMessageType.IMF;
		}
		///CLOVER:OFF
		catch (ParseException e)
		{
			log.warn("Failed to discern message type.", e);
		}
		catch (MessagingException e)
		{
			log.warn("Failed to discern message type.", e);
		}	
		return TxMessageType.UNKNOWN;
		///CLOVER:ON
	}	
	
	/**
	 * Determines if the pre-parsed message is requesting timely and reliable delivery.  This is determined by 
     * the existence of the X-DIRECT-FINAL-DESTINATION-DELIVERY message disposition option on the original message.
	 * @param imfMessage The message that is being inspected for timely and reliable messaging.
	 * @return true if the original message indicates that it requires timely and reliable delivery; false otherwise
	 */
	public static boolean isReliableAndTimelyRequested(Tx imfMessage)
	{
		boolean relAndTimelyRequired = false;
		
		if (imfMessage != null)
		{
			// check to see if this message requires the timely and reliable messaging 
			// logic as defined by the implementation guide
			Map<String, TxDetail> details = imfMessage.getDetails();
			if (!details.isEmpty())
			{
				// look for the Disposition options detail
				TxDetail dispositionOptionDetail = details.get(TxDetailType.DISPOSITION_OPTIONS.getType());
				if(dispositionOptionDetail != null)
					// check for the X-DIRECT-FINAL-DESTINATION-DELIVERY option
					if (dispositionOptionDetail.getDetailValue().toLowerCase(Locale.getDefault()).
							contains(MDNStandard.DispositionOption_TimelyAndReliable.toLowerCase(Locale.getDefault())))
						relAndTimelyRequired = true;
			}
		}
		
		return relAndTimelyRequired;
	}
	
	/**
	 * Determines if the message is requesting timely and reliable delivery.  This is determined by 
     * the existence of the X-DIRECT-FINAL-DESTINATION-DELIVERY message disposition option on the original message.
	 * @param msg The message that is being inspected for timely and reliable messaging.
	 * @return true if the original message indicates that it requires timely and reliable delivery; false otherwise
	 */
	public static boolean isReliableAndTimelyRequested(MimeMessage msg)
	{	
		if (msg == null)
			return false;
		
		final TxDetailParser parser = new DefaultTxDetailParser();
		final Map<String, TxDetail> details = parser.getMessageDetails(msg);
		final Tx tx = new Tx(getMessageType(msg), details);
		
		return isReliableAndTimelyRequested(tx);
	}
}

