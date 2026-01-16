package org.nhindirect.stagent.javaxcompat.mail.notifications;


/**
 * Specifies how notification was triggered.
 * <p>
 * <a href="http://tools.ietf.org/html/rfc3798">RFC 3798</a>, Disposition modes, 3.2.6.1, action-mode
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public enum TriggerType
{
	/**
	 * Notification was triggered automatically.
	 */
	Automatic
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},
	
	/**
	 * Notification was triggered based on user action.
	 */
	UserInitiated
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	}
}
