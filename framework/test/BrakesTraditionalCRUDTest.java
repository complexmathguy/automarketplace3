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

import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.api.*;

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
			LOGGER.setUseParentHandlers(false);	// only want to output to the provided LogHandler
		}

		// test methods
		@Test
		/**
		 * Full Create-Read-Update-Delete of a Brakes, through a
		 * BrakesTest.
		 */
		public void testCRUD() throws Throwable {
			try {
				LOGGER.info("**********************************************************");
				LOGGER.info("Beginning full CRUD test on Brakes...");

				testCreate();
//				testRead();
//				testUpdate();
				testGetAll();
//				testDelete();

				LOGGER.info("Successfully ran a full test on BrakesTest...");
				LOGGER.info("**********************************************************");
				LOGGER.info("");
			} catch (Throwable e) {
				throw e;
			} finally {
				if (handler != null) {
					handler.flush();
					LOGGER.removeHandler(handler);
				}
			}
		}

		/** 
		 * Tests creating a new Brakes.
		 */
		public void testCreate() throws Throwable {
			LOGGER.info( "\n======== CREATE ======== ");
			LOGGER.info( "-- Attempting to create a Brakes");

			StringBuilder msg = new StringBuilder( "-- Failed to create a Brakes" );

			try {            
				Brakes entity = BrakesBusinessDelegate.getBrakesInstance().createBrakes( generateNewEntity() );
				thePrimaryKey = entity.getBrakesId();
				LOGGER.info( "-- Successfully created a Brakes with primary key" + thePrimaryKey );
			}
			catch (Exception e) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests reading a Brakes. 
		 */
		public Brakes testRead() throws Throwable {
			LOGGER.info( "\n======== READ ======== ");
			LOGGER.info( "-- Reading a previously created Brakes" );

			Brakes entity = null;
			StringBuilder msg = new StringBuilder( "-- Failed to read Brakes with primary key" );
			msg.append( thePrimaryKey );
			
			BrakesFetchOneSummary fetchOneSummary = new BrakesFetchOneSummary( thePrimaryKey );

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
		 * Tests updating a Brakes.
		 */
		public void testUpdate() throws Throwable {
			LOGGER.info( "\n======== UPDATE ======== ");
			LOGGER.info( "-- Attempting to update a Brakes." );

			StringBuilder msg = new StringBuilder( "Failed to update a Brakes : " );        
			Brakes entity = null;

			try {            
				entity = testRead();

				assertNotNull( entity, msg.toString() );

				LOGGER.info( "-- Now updating the created Brakes." );

				// for use later on...
				thePrimaryKey = entity.getBrakesId();

				BrakesBusinessDelegate proxy = BrakesBusinessDelegate.getBrakesInstance();            
				proxy.updateBrakes( entity );   

				LOGGER.info( "-- Successfully saved Brakes - " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : primarykey = " + thePrimaryKey + " : entity-" +  entity + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests deleting a Brakes.
		 */
		public void testDelete() throws Throwable {
			LOGGER.info( "\n======== DELETE ======== ");
			LOGGER.info( "-- Deleting a previously created Brakes." );

			try{
			    Brakes entity = testRead(); 
				BrakesBusinessDelegate.getBrakesInstance().delete( entity );

				LOGGER.info( "-- Successfully deleted Brakes with primary key " + thePrimaryKey );            
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( "-- Failed to delete Brakes with primary key " + thePrimaryKey );

				throw e;
			}
		}

		/**
		 * Tests getting all Brakess.
		 */
		public void testGetAll() throws Throwable {    
			LOGGER.info( "======== GETALL ======== ");
			LOGGER.info( "-- Retrieving Collection of Brakess:" );

			StringBuilder msg = new StringBuilder( "-- Failed to get all Brakes : " );        
			List<Brakes> collection  = null;

			try {
				// call the static get method on the BrakesBusinessDelegate
				collection = BrakesBusinessDelegate.getBrakesInstance().getAllBrakes();

				if ( collection == null || collection.size() == 0 ) {
					LOGGER.warning( unexpectedErrorMsg );
					LOGGER.warning( msg.toString() + " Empty collection returned."  );
				}
				else {
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
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
		}

		/**
		 * Assigns a common log handler for each test class in the suite
		 * @param		handler	Handler
		 * @return		BrakesTest
		 */
		public BrakesTest setHandler(Handler handler) {
			this.handler = handler;
			LOGGER.addHandler(handler); // assign so the LOGGER can only output results to the Handler
			return this;
		}

		/**
		 * Returns a new populated Brakes
		 * 
		 * @return Brakes
		 */
		protected Brakes generateNewEntity() {
			Brakes entity = new Brakes( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant(),  BrakeType.values()[0] );
			
			return( entity );
		}

		//-----------------------------------------------------
		// attributes 
		//-----------------------------------------------------
		protected UUID thePrimaryKey 						= null;
		private final Logger LOGGER 						= Logger.getLogger(BrakesTest.class.getName());
		private Handler handler 							= null;
		private String unexpectedErrorMsg 					= ":::::::::::::: Unexpected Error :::::::::::::::::";
	}
