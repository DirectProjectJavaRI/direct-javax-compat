package org.nhindirect.gateway.javaxcompat.smtp;

import java.util.Arrays;

import javax.mail.internet.InternetAddress;

import org.nhindirect.common.javaxcompat.mail.MDNStandard;
import org.nhindirect.stagent.javaxcompat.InternetAddressUtils;
import org.nhindirect.stagent.javaxcompat.mail.notifications.Notification;
import org.nhindirect.stagent.javaxcompat.mail.notifications.NotificationType;
import org.nhindirect.stagent.javaxcompat.mail.notifications.ReportingUserAgent;

/**
 * Notification producer for creating MDN dispatched messages.
 * @author Greg Meyer
 * @since 2.0
 */
public class ReliableDispatchedNotificationProducer extends NotificationProducer
{
	/**
	 * Constructor
	 * @param settings Notification specific settings
	 */
	public ReliableDispatchedNotificationProducer(NotificationSettings settings)
	{
		super(settings);
	}
	
	/**
	 * {@inheritDoc}
	 */
    protected Notification createAck(InternetAddress address)
    {
        Notification notification = new Notification(NotificationType.Dispatched);
        if (settings.hasText())
        {
            notification.setExplanation(settings.getText());
        }
                
        notification.setReportingAgent(new ReportingUserAgent(InternetAddressUtils.getHost(address), settings.getProductName()));        
        notification.setExtensions(Arrays.asList(MDNStandard.DispositionOption_TimelyAndReliable));
        return notification;
    }
}
