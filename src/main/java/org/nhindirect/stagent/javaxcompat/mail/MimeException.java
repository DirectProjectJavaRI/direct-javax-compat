package org.nhindirect.stagent.javaxcompat.mail;

import org.nhindirect.stagent.javaxcompat.NHINDException;

/**
 * Exception thrown when an invalid message in encountered.
 * @author Greg Meyer
 * @author Umesh Madan
 *
 */
public class MimeException extends NHINDException
{
	static final long serialVersionUID = 2409954834440350374L;
	
    public MimeException(MimeError error)
    {
    	super(error);
    }
    
	/**
	 * Constructs an exception with a message and the mime error.
	 * @param error The mime error
	 * @param msg The exception message.
	 */    
    public MimeException(MimeError error, String message)
    {
    	super(error, message);
    }
       
	/**
	 * Constructs an exception with the mime error and the exception that caused the error.
	 * @param error The mime error.
	 * @param innerException The exception that caused the error.
	 */     
    public MimeException(MimeError error, Exception innerException)
    {
    	super(error, innerException);
    }
    
	/**
	 * Constructs an exception with the mime error, a message, and the exception that caused the error.
	 * @param error The mime error.
	 * @param msg The exception message.
	 * @param innerException The exception that caused the error.
	 */      
    public MimeException(MimeError error, String message, Exception innerException)
    {
    	super(error, message, innerException);
    }
	
}

