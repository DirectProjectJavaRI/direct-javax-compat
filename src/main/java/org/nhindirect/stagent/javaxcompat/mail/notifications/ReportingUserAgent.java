package org.nhindirect.stagent.javaxcompat.mail.notifications;

/**
 * Represents a Reporting-UA as specified by <a href="http://tools.ietf.org/html/rfc3798">RFC 3798</a>.
 * <p>
 * From RFC 3798, 3.2.2, The Reporting-UA field <br>
 * <i>
 * reporting-ua-field = "Reporting-UA" ":" ua-name ";" ua-product
 * 
 * For Internet Mail user agents, it is recommended that this field contain both: 
 * the DNS name of the particular instance of the MUA that generated the MDN and the
 * name of the product
 * </i>
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public class ReportingUserAgent 
{
	private String name;
    private String product;
	
    /**
     * Initializes an instance with the specified user agent name and product name.
     * @param name The user agent name
     * @param product The user agent product
     */
    public ReportingUserAgent(String name, String product)
    {
        setName(name);
        setProduct(product);
    }
	
    /**
     * Gets the user agent's domain name.
     * @return The user agent's domain name
     */
    public String getName() 
    {
		return name;
	}

    /**
     * Sets the user agent's domain name.
     * @param name The user agent's domain name
     */
	public void setName(String name) 
	{
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException();
		
		this.name = name;
	}

	/**
	 * Gets the user agent's product
	 * @return The user agent's product
	 */
	public String getProduct() 
	{
		return product;
	}

	/**
	 * Sets the user agent's product.
	 * @param product The user agent's product
	 */
	public void setProduct(String product) 
	{
		if (product == null || product.isEmpty())
			throw new IllegalArgumentException();		
		
		this.product = product;
	}    
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		return name + "; " + product;		
	}
}

