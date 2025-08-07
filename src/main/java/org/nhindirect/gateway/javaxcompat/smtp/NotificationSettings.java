package org.nhindirect.gateway.javaxcompat.smtp;

/**
 * Notification settings for SmtpAgent.
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public class NotificationSettings 
{
	private static final String DEFAULT_TEXT = "Security Agent";
	
	private final boolean autoResponse;
	private final String productName; 
	private final String text;
	
	
	public NotificationSettings()
	{
		this(true, DEFAULT_TEXT, "");
	}
	
	public NotificationSettings(boolean autoResponse)
	{
		this(autoResponse, DEFAULT_TEXT, "");
	}	
	
	public NotificationSettings(boolean autoResponse, String productName, String text)
	{
		this.autoResponse = autoResponse;
		
		if (productName == null || productName.isEmpty())
			this.productName = DEFAULT_TEXT;
		else
			this.productName = productName;
		
		if (text == null)
			this.text = "";
		else
			this.text = text;
	}

	public boolean isAutoResponse() 
	{
		return autoResponse;
	}

	public String getProductName() 
	{
		return productName;
	}

	public boolean hasText()
	{
		return !text.isEmpty();
	}
	
	public String getText() 
	{
		return text;
	}	
}

