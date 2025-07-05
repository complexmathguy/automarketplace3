/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.validator;

import org.springframework.util.Assert;

import com.occulue.api.*;

public class TransmissionValidator {
		
	/**
	 * default constructor
	 */
	protected TransmissionValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public TransmissionValidator getInstance() {
		return new TransmissionValidator();
	}
		
	/**
	 * handles creation validation for a Transmission
	 */
	public void validate( CreateTransmissionCommand transmission )throws Exception {
		Assert.notNull( transmission, "CreateTransmissionCommand should not be null" );
//		Assert.isNull( transmission.getTransmissionId(), "CreateTransmissionCommand identifier should be null" );
		Assert.notNull( transmission.getName(), "Field CreateTransmissionCommand.name should not be null" );
		Assert.notNull( transmission.getSerialNum(), "Field CreateTransmissionCommand.serialNum should not be null" );
	}

	/**
	 * handles update validation for a Transmission
	 */
	public void validate( RefreshTransmissionCommand transmission ) throws Exception {
		Assert.notNull( transmission, "RefreshTransmissionCommand should not be null" );
		Assert.notNull( transmission.getTransmissionId(), "RefreshTransmissionCommand identifier should not be null" );
		Assert.notNull( transmission.getName(), "Field RefreshTransmissionCommand.name should not be null" );
		Assert.notNull( transmission.getSerialNum(), "Field RefreshTransmissionCommand.serialNum should not be null" );
    }

	/**
	 * handles delete validation for a Transmission
	 */
    public void validate( CloseTransmissionCommand transmission ) throws Exception {
		Assert.notNull( transmission, "{commandAlias} should not be null" );
		Assert.notNull( transmission.getTransmissionId(), "CloseTransmissionCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Transmission
	 */
	public void validate( TransmissionFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "TransmissionFetchOneSummary should not be null" );
		Assert.notNull( summary.getTransmissionId(), "TransmissionFetchOneSummary identifier should not be null" );
	}



}
