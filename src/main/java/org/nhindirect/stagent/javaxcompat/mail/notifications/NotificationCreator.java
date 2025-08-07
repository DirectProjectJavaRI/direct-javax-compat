package org.nhindirect.stagent.javaxcompat.mail.notifications;


import javax.mail.internet.InternetAddress;

/**
 * Interface for creating notification object for a given email address. 
 * @author Greg Meyer
 */
public interface NotificationCreator 
{
	/**
	 * Creates a specific notification object (provided by the implementer) to be sent.
	 * @param address The Internet address of the sender of the notification.
	 * @return A notification object.
	 */
	public Notification createNotification(InternetAddress address);
}

