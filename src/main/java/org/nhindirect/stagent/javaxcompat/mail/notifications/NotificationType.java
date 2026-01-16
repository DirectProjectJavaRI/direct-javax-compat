package org.nhindirect.stagent.javaxcompat.mail.notifications;

/**
 * Indicates what this disposition notification means.
 * <p>
 * <a href="http://tools.ietf.org/html/rfc3798">RFC 3798</a>, Disposition types, 3.2.6.2, includes type (processed) mentioned in document but
 * not listed in grammar.
 * @author Greg Meyer
 * @author Umesh Madan
 */
public enum NotificationType
{
	/**
	 * Indicates message has been received but not displayed to user.
	 */
	Processed
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},
	
	/**
	 * Indicates message has been displayed to user (does not imply the message was read, understood, etc.)
	 */
	Displayed
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},
	
	/**
	 * Indicates message was deleted.
	 */
	Deleted
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},	
	
	/**
	 * Indicates message was dispatched.
	 */
	Dispatched
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},	
	
	/**
	 * Indicates message was denied.
	 */
	Denied
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	},	
	
	/**
	 * Indicates message was in error.
	 */
	Error
	{
	    public String toString() 
	    {
	    	return NotificationHelper.asString(this);
	    }
	}, 
    
    /**
     * Negative delivery notification message is issued by an STA when delivery to a destination 
     * has failed or is considered to have failed.
     */
    Failed
    {
        public String toString() 
        {
            return NotificationHelper.asString(this);
        }
    }   	
}

