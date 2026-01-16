package org.nhindirect.gateway.javaxcompat.smtp;


import java.util.Collection;

import javax.mail.internet.InternetAddress;

import org.nhindirect.stagent.javaxcompat.InternetAddressUtils;
import org.nhindirect.stagent.javaxcompat.mail.Message;
import org.nhindirect.stagent.javaxcompat.mail.notifications.Notification;
import org.nhindirect.stagent.javaxcompat.mail.notifications.NotificationCreator;
import org.nhindirect.stagent.javaxcompat.mail.notifications.NotificationHelper;
import org.nhindirect.stagent.javaxcompat.mail.notifications.NotificationMessage;
import org.nhindirect.stagent.javaxcompat.mail.notifications.NotificationType;
import org.nhindirect.stagent.javaxcompat.mail.notifications.ReportingUserAgent;

import lombok.extern.slf4j.Slf4j;


/**
 * Produces MND ack messages based on configuration settings.
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
@Slf4j
public class NotificationProducer implements NotificationCreator
{
	protected final NotificationSettings settings;
	
	/**
	 * Constructs a producer with the notification settings. 
	 * @param settings The notification configuration settings.
	 */
	public NotificationProducer(NotificationSettings settings)
	{
		if (settings == null)
			throw new IllegalArgumentException("Settings cannot be null");
		
		this.settings = settings;
		
		StringBuilder builder = new StringBuilder("Notification settings:");
		builder.append("\n\r\tMDN Auto Response: " + settings.isAutoResponse());
		builder.append("\n\r\tMDN Producer Name: " + settings.getProductName());
		builder.append("\n\r\tMDN Response Test: " + settings.getText());
		
		log.debug(builder.toString());
	}
	
	/**
	 * Creates an ack MDN.
	 * {@inheritDoc}
	 */
	public Notification createNotification(InternetAddress address) 
	{
		return createAck(address);
	}
	
    public Collection<NotificationMessage> produce(Message msg, Collection<InternetAddress> recipients)
    {
        if (msg == null || recipients == null || recipients.size() == 0)
        {
            throw new IllegalArgumentException();
        }

        Collection<InternetAddress> senders = recipients;
        Collection<NotificationMessage> notifications = NotificationHelper.createNotificationMessages(msg, senders, this); 
        
        return notifications;
    }
    
    /*
     * Creates an ack message.
     */
    protected Notification createAck(InternetAddress address)
    {
        Notification notification = new Notification(NotificationType.Processed);
        if (settings.hasText())
        {
            notification.setExplanation(settings.getText());
        }
                
        notification.setReportingAgent(new ReportingUserAgent(InternetAddressUtils.getHost(address), settings.getProductName()));            
        return notification;
    }
    
    
    /**
     * Gets the notification settings for the producer.
     * @return The notification settings for the producer.
     */
    public NotificationSettings getNotificationSettings()
    {
    	return settings;
    }
	
}


