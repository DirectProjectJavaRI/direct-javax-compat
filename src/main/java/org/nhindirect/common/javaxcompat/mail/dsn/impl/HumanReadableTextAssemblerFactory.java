package org.nhindirect.common.javaxcompat.mail.dsn.impl;

public class HumanReadableTextAssemblerFactory 
{
    private static HumanReadableTextAssemblerFactory instance = new HumanReadableTextAssemblerFactory();

    /**
     * get instance of the factory
     * 
     * @return HumanReadableTextAssemblerFactory
     */
    public static HumanReadableTextAssemblerFactory getInstance() 
    {
	  return instance;
    }

    /**
     * Create {@link HumanReadableTextAssembler}
     * 
     * @param log
     * @param bounceHeader
     * @param bounceFooter
     * @param rejectedRecipientsTitle
     * @param errorMessageTitle
     * @param errorMessageDefault
     * @return HumanReadableTextAssembler
     */
    public HumanReadableTextAssembler createHumanReadableTextAssembler(String bounceHeader,
	    String bounceFooter, String rejectedRecipientsTitle, String errorMessageTitle, String errorMessageDefault) 
    {
    	return new HumanReadableTextAssembler(bounceHeader, bounceFooter,
    			rejectedRecipientsTitle, errorMessageTitle, errorMessageDefault);
    }
}
