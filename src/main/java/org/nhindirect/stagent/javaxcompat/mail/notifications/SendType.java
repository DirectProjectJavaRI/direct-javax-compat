package org.nhindirect.stagent.javaxcompat.mail.notifications;


/**
 * Specifies how user was involved in sending notfication.
 * <p>
 * <a href="http://tools.ietf.org/html/rfc3798">RFC 3798</a>, Disposition modes, 3.2.6.1, sending-mode
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public enum SendType
{
	/**
	 * Notification was sent automatically.
	 */
	Automatic
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},
	
	/**
	 * Notification was sent based on user action.
	 */
	UserMediated
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	}	
}