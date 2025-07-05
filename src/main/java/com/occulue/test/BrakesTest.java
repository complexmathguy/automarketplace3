/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.test;

import java.io.*;
import java.util.*;
import java.util.logging.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.util.Assert.state;

import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.api.*;
import com.occulue.subscriber.*;

/**
 * Test Brakes class.
 *
 * @author your_name_here
 */
public class BrakesTest{

    // ------------------------------------
	// default constructor
    // ------------------------------------
	public BrakesTest() {
		subscriber = new BrakesSubscriber();
	}

	// test methods
	@Test
	/*
	 * Initiate BrakesTest.
	 */
	public void startTest() throws Throwable {
		try {
			LOGGER.info("**********************************************************");
			LOGGER.info("Beginning test on Brakes...");
			LOGGER.info("**********************************************************\n");
			
			// ---------------------------------------------
			// jumpstart process
			// ---------------------------------------------
			jumpStart();
			
		} catch (Throwable e) {
			throw e;
		} finally {
		}
	}

	/** 
	 * jumpstart the process by instantiating2 Brakes
	 */
	protected void jumpStart() throws Throwable {
		LOGGER.info( "\n======== create instances to get the ball rolling  ======== ");

		brakesId = BrakesBusinessDelegate.getBrakesInstance()
		.createBrakes( generateNewCommand() )
		.get();

		// ---------------------------------------------
		// set up query subscriptions after the 1st create
		// ---------------------------------------------
		testingStep = "create";
		setUpQuerySubscriptions();

		BrakesBusinessDelegate.getBrakesInstance()
				.createBrakes( generateNewCommand() )
				.get();

	}

	/** 
	 * Set up query subscriptions
	 */
	protected void setUpQuerySubscriptions() throws Throwable {
		LOGGER.info( "\n======== Setting Up Query Subscriptions ======== ");
			
		try {            
			subscriber.brakesSubscribe().updates().subscribe(
					  successValue -> {
						  LOGGER.info(successValue.toString());
						  try {
							  LOGGER.info("GetAll update received for Brakes : " + successValue.getBrakesId());
							  if (successValue.getBrakesId().equals(brakesId)) {
								  if (testingStep.equals("create")) {
									  testingStep = "update";
									  update();
								  } else if (testingStep.equals("delete")) {
									  testingStep = "complete";
									  state( getAll().size() == sizeOfBrakesList - 1 , "value not deleted from list");
									  LOGGER.info("**********************************************************");
									  LOGGER.info("Brakes test completed successfully...");
									  LOGGER.info("**********************************************************\n");
								  }
							  }
						  } catch( Throwable exc ) {
							  LOGGER.warning( exc.getMessage() );
						  }
					  },
					  error -> LOGGER.warning(error.getMessage()),
					  () -> LOGGER.info("Subscription on brakes consumed")
					);
			subscriber.brakesSubscribe( brakesId ).updates().subscribe(
					  successValue -> {
						  LOGGER.info(successValue.toString());
						  try {
							  LOGGER.info("GetOne update received for Brakes : " + successValue.getBrakesId() + " in step " + testingStep);
							  testingStep = "delete";
							  sizeOfBrakesList = getAll().size();
							  delete();
						  } catch( Throwable exc ) {
							  LOGGER.warning( exc.getMessage() );
						  }
					  },
					  error -> LOGGER.warning(error.getMessage()),
					  () -> LOGGER.info("Subscription on brakes for brakesId consumed")

					);
			

			}
			catch (Exception e) {
				LOGGER.warning( e.getMessage() );
				throw e;
			}
		}
		
		/** 
	 * read a Brakes. 
	 */
	protected Brakes read() throws Throwable {
		LOGGER.info( "\n======== READ ======== ");
		LOGGER.info( "-- Reading a previously created Brakes" );

		Brakes entity = null;
		StringBuilder msg = new StringBuilder( "-- Failed to read Brakes with primary key" );
		msg.append( brakesId );
		
		BrakesFetchOneSummary fetchOneSummary = new BrakesFetchOneSummary( brakesId );

		try {
			entity = BrakesBusinessDelegate.getBrakesInstance().getBrakes( fetchOneSummary );

			assertNotNull( entity,msg.toString() );

			LOGGER.info( "-- Successfully found Brakes " + entity.toString() );
		}
		catch ( Throwable e ) {
			LOGGER.warning( unexpectedErrorMsg );
			LOGGER.warning( msg.toString() + " : " + e );

			throw e;
		}
		
		return entity;
	}

	/** 
	 * updating a Brakes.
	 */
	protected void update() throws Throwable {
		LOGGER.info( "\n======== UPDATE ======== ");
		LOGGER.info( "-- Attempting to update a Brakes." );

		StringBuilder msg = new StringBuilder( "Failed to update a Brakes : " );        
		Brakes entity = read();
		RefreshBrakeCommand command = generateUpdateCommand();
		command.setBrakesId(entity.getBrakesId());

		try {            
			assertNotNull( entity, msg.toString() );

			LOGGER.info( "-- Now updating the created Brakes." );

			// for use later on...
			brakesId = entity.getBrakesId();

			BrakesBusinessDelegate proxy = BrakesBusinessDelegate.getBrakesInstance();  

			proxy.updateBrakes( command ).get();

			LOGGER.info( "-- Successfully saved Brakes - " + entity.toString() );
		}
		catch ( Throwable e ) {
			LOGGER.warning( unexpectedErrorMsg );
			LOGGER.warning( msg.toString() + " : primarykey = " + brakesId + " : command -" +  command + " : " + e );

			throw e;
		}
	}

	/** 
	 * delete a Brakes.
	 */
	protected void delete() throws Throwable {
		LOGGER.info( "\n======== DELETE ======== ");
		LOGGER.info( "-- Deleting a previously created Brakes." );

		Brakes entity = null;
		
		try{
		    entity = read(); 
			LOGGER.info( "-- Successfully read Brakes with id " + brakesId );            
		}
		catch ( Throwable e ) {
			LOGGER.warning( unexpectedErrorMsg );
			LOGGER.warning( "-- Failed to read Brakes with id " + brakesId );

			throw e;
		}

		try{
			BrakesBusinessDelegate.getBrakesInstance().delete( new CloseBrakeCommand( entity.getBrakesId() ) ).get();
			LOGGER.info( "-- Successfully deleted Brakes with id " + brakesId );            
		}
		catch ( Throwable e ) {
			LOGGER.warning( unexpectedErrorMsg );
			LOGGER.warning( "-- Failed to delete Brakes with id " + brakesId );

			throw e;
		}
	}

	/**
	 * get all Brakess.
	 */
	protected List<Brakes> getAll() throws Throwable {    
		LOGGER.info( "======== GETALL ======== ");
		LOGGER.info( "-- Retrieving Collection of Brakess:" );

		StringBuilder msg = new StringBuilder( "-- Failed to get all Brakes : " );        
		List<Brakes> collection  = new ArrayList<>();

		try {
			// call the static get method on the BrakesBusinessDelegate
			collection = BrakesBusinessDelegate.getBrakesInstance().getAllBrakes();
			assertNotNull( collection, "An Empty collection of Brakes was incorrectly returned.");
			
			// Now print out the values
			Brakes entity = null;            
			Iterator<Brakes> iter = collection.iterator();
			int index = 1;

			while( iter.hasNext() ) {
				// Retrieve the entity   
				entity = iter.next();

				assertNotNull( entity,"-- null entity in Collection." );
				assertNotNull( entity.getBrakesId(), "-- entity in Collection has a null primary key" );        

				LOGGER.info( " - " + String.valueOf(index) + ". " + entity.toString() );
				index++;
			}
		}
		catch ( Throwable e ) {
			LOGGER.warning( unexpectedErrorMsg );
			LOGGER.warning( msg.toString() + " : " + e );

			throw e;
		}
		
		return collection;			
	}

	/**
	 * Assigns a common log handler for each test class in the suite 
	 * in the event log output needs to go elsewhere
	 * 
	 * @param		handler	Handler
	 * @return		BrakesTest
	 */
	protected BrakesTest setHandler(Handler handler) {
		if ( handler != null )
			LOGGER.addHandler(handler); 
		return this;
	}

	/**
	 * Returns a new populated Brakes
	 * 
	 * @return CreateBrakeCommand alias
	 */
	protected CreateBrakeCommand generateNewCommand() {
        CreateBrakeCommand command = new CreateBrakeCommand( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant(),  BrakeType.values()[0] );
		
		return( command );
	}

		/**
		 * Returns a new populated Brakes
		 * 
		 * @return RefreshBrakeCommand alias
		 */
	protected RefreshBrakeCommand generateUpdateCommand() {
	        RefreshBrakeCommand command = new RefreshBrakeCommand( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant(),  BrakeType.values()[0] );
			
			return( command );
		}
	//-----------------------------------------------------
	// attributes 
	//-----------------------------------------------------
	protected UUID brakesId = null;
	protected BrakesSubscriber subscriber = null;
	private final String unexpectedErrorMsg = ":::::::::::::: Unexpected Error :::::::::::::::::";
	private final Logger LOGGER = Logger.getLogger(BrakesTest.class.getName());
	private String testingStep = "";
	private Integer sizeOfBrakesList = 0;
}
