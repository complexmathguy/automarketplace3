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

public class BrakesValidator {
		
	/**
	 * default constructor
	 */
	protected BrakesValidator() {	
	}
	
	/**
	 * factory method
	 */
	static public BrakesValidator getInstance() {
		return new BrakesValidator();
	}
		
	/**
	 * handles creation validation for a Brakes
	 */
	public void validate( CreateBrakeCommand brakes )throws Exception {
		Assert.notNull( brakes, "CreateBrakeCommand should not be null" );
//		Assert.isNull( brakes.getBrakesId(), "CreateBrakeCommand identifier should be null" );
		Assert.notNull( brakes.getSerialNum(), "Field CreateBrakesCommand.serialNum should not be null" );
		Assert.notNull( brakes.getName(), "Field CreateBrakesCommand.name should not be null" );
	}

	/**
	 * handles update validation for a Brakes
	 */
	public void validate( RefreshBrakeCommand brakes ) throws Exception {
		Assert.notNull( brakes, "RefreshBrakeCommand should not be null" );
		Assert.notNull( brakes.getBrakesId(), "RefreshBrakeCommand identifier should not be null" );
		Assert.notNull( brakes.getSerialNum(), "Field RefreshBrakeCommand.serialNum should not be null" );
		Assert.notNull( brakes.getName(), "Field RefreshBrakeCommand.name should not be null" );
    }

	/**
	 * handles delete validation for a Brakes
	 */
    public void validate( CloseBrakeCommand brakes ) throws Exception {
		Assert.notNull( brakes, "{commandAlias} should not be null" );
		Assert.notNull( brakes.getBrakesId(), "CloseBrakeCommand identifier should not be null" );
	}
	
	/**
	 * handles fetchOne validation for a Brakes
	 */
	public void validate( BrakesFetchOneSummary summary ) throws Exception {
		Assert.notNull( summary, "BrakesFetchOneSummary should not be null" );
		Assert.notNull( summary.getBrakesId(), "BrakesFetchOneSummary identifier should not be null" );
	}



}
