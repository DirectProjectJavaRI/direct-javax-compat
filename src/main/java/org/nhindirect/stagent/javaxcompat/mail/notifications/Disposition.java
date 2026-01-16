package org.nhindirect.stagent.javaxcompat.mail.notifications;


/**
 * Encapsulates message disposition status.
 * @author gm2552
 * @author Umesh Madan
 */
public class Disposition
{

	private final TriggerType triggerType;
	private final SendType sendType;
	private NotificationType notification;
	private boolean error;
	
	/**
	 * Initializes an instance with the specified disposition notification type and automatic modes.
	 * @param notification The disposition notification type.
	 */
	public Disposition(NotificationType notification)
	{
		this(TriggerType.Automatic, SendType.Automatic, notification);
	}

	/**
	 * Initializes an instance with the specified disposition notification type and action and sending modes
	 * @param triggerType The action (trigger) mode type
	 * @param sendType The sending mode type
	 * @param notification The disposition notification type
	 */
	public Disposition(TriggerType triggerType, SendType sendType, NotificationType notification)
    {
		
		this.triggerType = triggerType;
        this.sendType = sendType;
        this.notification = notification;
        this.error = false;
    }

	
	/**
	 * Indicates if this disposition is an error report.
	 * @return true if this disposition is an error report.  false otherwise. 
	 */
    public boolean isError() 
    {
		return error;
	}

	/**
	 * Sets the error status indicating if this disposition is an error report.
	 * @param error true if this disposition is an error report.  false otherwise. 
	 */    
	public void setError(boolean error) 
	{
		this.error = error;
	}

	/**
	 * Gets the type of disposition indicated
	 * @return The type of disposition indicated
	 */
	public NotificationType getNotification() 
    {
		return notification;
	}

	/**
	 * Sets the type of disposition indicated.
	 * @param notification The type of disposition indicated.
	 */
	public void setNotification(NotificationType notification) 
	{
		this.notification = notification;
	}

	/**
	 * Gets the trigger action that generated this disposition (action-mode).
	 * @return The trigger action that generated this disposition
	 */
	public TriggerType getTriggerType() 
	{
		return triggerType;
	}

	/**
	 * Gets the sending type (system or user) that sent this disposition (sending-mode). 
	 * @return The sending type that sent this disposition. 
	 */
	public SendType getSendType() 
	{
		return sendType;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
    public String toString()
    {
        StringBuilder notification = new StringBuilder("Disposition: ");
        //
        // Disposition Mode
        //
        notification.append(NotificationHelper.asString(this.triggerType));
        notification.append('/');
        notification.append(NotificationHelper.asString(this.sendType));
        notification.append(';');
        //
        // Disposition Type & Modifier
        //
        notification.append(NotificationHelper.asString(this.notification));
        if (isError())
        {
            notification.append('/');
            notification.append(MDNStandard.Modifier_Error);
        }

        return notification.toString();
    }
}
