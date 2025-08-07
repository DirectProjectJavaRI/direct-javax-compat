package org.nhindirect.gateway.javaxcompat.smtp.dsn.impl;

import java.util.HashMap;
import java.util.Map;

import org.nhindirect.common.javaxcompat.mail.dsn.DSNFailureTextBodyPartGenerator;
import org.nhindirect.common.javaxcompat.mail.dsn.DSNGenerator;
import org.nhindirect.common.javaxcompat.mail.dsn.DSNStandard.DSNStatus;
import org.nhindirect.common.javaxcompat.mail.dsn.impl.DefaultDSNFailureTextBodyPartGenerator;
import org.nhindirect.common.javaxcompat.mail.dsn.impl.HumanReadableTextAssemblerFactory;
import org.nhindirect.common.options.ConfigurationSource;
import org.nhindirect.common.options.OptionsManager;

public class FailedDeliveryDSNCreator extends AbstractDSNCreator 
{
	static
	{		
		initJVMParams();
	}
	
	private synchronized static void initJVMParams()
	{
		/*
		 * Configuration parameters
		 */
		final Map<String, String> JVM_PARAMS = new HashMap<String, String>();
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_FAILED_PREFIX, "org.nhindirect.gateway.smtp.dsn.impl.DeliveryFailureDSNFailedPrefix");
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_MTA_NAME, "org.nhindirect.gateway.smtp.dsn.imp.DeliveryFailureDSNMTAName");
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_POSTMASTER, "org.nhindirect.gateway.smtp.dsn.impl.DeliveryFailureDNSPostmaster");
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_FAILED_RECIP_TITLE, "org.nhindirect.gateway.smtp.dsn.impl.DeliveryFailureDSNFaileRecipTitle");
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_FAILED_ERROR_MESSAGE, "org.nhindirect.gateway.smtp.dsn.impl.DeliveryFailureDSNFailedErrorMessage");
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_FAILED_HEADER, "org.nhindirect.gateway.smtp.dsn.impl.DeliveryFailureDSNFailedHeader");
		JVM_PARAMS.put(FailedDeliveryDSNCreatorOptions.DSN_FAILED_FOOTER, "org.nhindirect.gateway.smtp.dsn.impl.DeliveryFailureDSNFailedFooter");
		
		OptionsManager.addInitParameters(JVM_PARAMS);
	}
	
	///CLOVER:OFF
	public FailedDeliveryDSNCreator(DSNGenerator generator, String postmasterMailbox, String reportingMta, 
			DSNFailureTextBodyPartGenerator textGenerator)
	{
		this.configSource = null;
		this.generator = generator;
		this.postmasterMailbox = postmasterMailbox;
		this.reportingMta = reportingMta;
		this.textGenerator = textGenerator;
		this.dsnStatus = DSNStatus.DELIVERY_OTHER;
	}
	///CLOVER:ON
	
	public FailedDeliveryDSNCreator(ConfigurationSource configSource)
	{
		this.configSource = configSource;
		
		this.dsnStatus = DSNStatus.DELIVERY_OTHER;
		
		generator = new DSNGenerator(GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_FAILED_PREFIX, 
				configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_PREFIX));
		
		postmasterMailbox = GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_POSTMASTER, 
				configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_POSTMASTER);
		
		reportingMta = GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_MTA_NAME, 
				configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_MTA_NAME);
		
		
		textGenerator = new DefaultDSNFailureTextBodyPartGenerator(
				GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_FAILED_HEADER, 
						configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_HEADER), 
						GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_FAILED_FOOTER, 
								configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_FOOTER), 
						GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_FAILED_RECIP_TITLE, 
								configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_FAILED_RECIP_TITLE), 
						FailedDeliveryDSNCreatorOptions.DEFAULT_ERROR_MESSAGE_TITLE,
					GatewayConfiguration.getConfigurationParam(FailedDeliveryDSNCreatorOptions.DSN_FAILED_ERROR_MESSAGE, 
							configSource, FailedDeliveryDSNCreatorOptions.DEFAULT_ERROR_MESSAGE),
			    HumanReadableTextAssemblerFactory.getInstance());
	}
}

