package org.nhindirect.gateway.javaxcompat.smtp.dsn.impl;

import org.nhindirect.common.options.ConfigurationSource;
import org.nhindirect.common.options.OptionsManager;
import org.nhindirect.common.options.OptionsParameter;

/**
 * Utility class for retrieving parameterized configuration options.  Options for the can be configured in one of two ways: 
 * either by setting appropriate option as an XML element in a mailet's XML configuration (optional and typically found in the config.xml file
 * for Apache James deployments), or by setting the option using the {@link org.nhindirect.stagent.options.OptionsManager} pattern.
 * <p>
 * The precedence of the parameters using the following algorithm.
 * <br>
 * 1. Use the mailet XML configuration
 * <br>
 * 2. Use the OptionsManager configuration
 * <br>
 * 3. Use default settings in the mailet.
 * @author Greg Meyer
 * @since 9.0.0
 */
public class GatewayConfiguration 
{
    /**
     * Gets the configuration parameter requested.  The mailet init parameters are checked first, then the OptionsManager.
     * @param param The parameter to get the value of.
     * @param configSource A ConfigurationSource to search.  May be null.
     * @return If found, returns, the value of the configured value.  Otherwise, the default value is returned
     */
    public static final String getConfigurationParam(String param, ConfigurationSource configSource, String defaultValue)
    {
		// get from the mailet init parameter first
		String paramValue = (configSource == null) ? null : configSource.getPropertyAsString(param, "");
		
		
		if (paramValue == null || paramValue.isEmpty())
		{
			// if not in the configuration source, then try the 
			// Options manager
			OptionsParameter optionsParam = OptionsManager.getInstance().getParameter(param);
			if (optionsParam != null)
				paramValue =  optionsParam.getParamValue();
		}
		
		return (paramValue == null) ? defaultValue : paramValue;
    }
    
    /**
     * Gets the configuration parameter requested as a boolean value.  The same search rules are followed as in {@link #getConfigurationParam(String, Mailet, String)}.
     * @param param The parameter to get the value of.
     * @param configSource A ConfigurationSource to search.  May be null.
     * @return If found, returns, the value of the configured value.  Otherwise, the default value is returned.
     */
    public static final boolean getConfigurationParamAsBoolean(String param, ConfigurationSource configSource, boolean defaultValue)
    {
    	final String paramValue = getConfigurationParam(param, configSource, "");
    	
		// get from the mailet init parameter first
		return (paramValue.isEmpty()) ? defaultValue : Boolean.parseBoolean(paramValue);
    }
}
