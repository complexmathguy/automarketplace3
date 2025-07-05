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
 * Test Transmission class.
 *
 * @author your_name_here
 */
public class TransmissionTest{

        // ------------------------------------
		// default constructor
	    // ------------------------------------
		public TransmissionTest() {
			LOGGER.setUseParentHandlers(false);	// only want to output to the provided LogHandler
		}

		// test methods
		@Test
		/**
		 * Full Create-Read-Update-Delete of a Transmission, through a
		 * TransmissionTest.
		 */
		public void testCRUD() throws Throwable {
			try {
				LOGGER.info("**********************************************************");
				LOGGER.info("Beginning full CRUD test on Transmission...");

				testCreate();
//				testRead();
//				testUpdate();
				testGetAll();
//				testDelete();

				LOGGER.info("Successfully ran a full test on TransmissionTest...");
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
		 * Tests creating a new Transmission.
		 */
		public void testCreate() throws Throwable {
			LOGGER.info( "\n======== CREATE ======== ");
			LOGGER.info( "-- Attempting to create a Transmission");

			StringBuilder msg = new StringBuilder( "-- Failed to create a Transmission" );

			try {            
				Transmission entity = TransmissionBusinessDelegate.getTransmissionInstance().createTransmission( generateNewEntity() );
				thePrimaryKey = entity.getTransmissionId();
				LOGGER.info( "-- Successfully created a Transmission with primary key" + thePrimaryKey );
			}
			catch (Exception e) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests reading a Transmission. 
		 */
		public Transmission testRead() throws Throwable {
			LOGGER.info( "\n======== READ ======== ");
			LOGGER.info( "-- Reading a previously created Transmission" );

			Transmission entity = null;
			StringBuilder msg = new StringBuilder( "-- Failed to read Transmission with primary key" );
			msg.append( thePrimaryKey );
			
			TransmissionFetchOneSummary fetchOneSummary = new TransmissionFetchOneSummary( thePrimaryKey );

			try {
				entity = TransmissionBusinessDelegate.getTransmissionInstance().getTransmission( fetchOneSummary );

				assertNotNull( entity,msg.toString() );

				LOGGER.info( "-- Successfully found Transmission " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
			
			return entity;
		}

		/** 
		 * Tests updating a Transmission.
		 */
		public void testUpdate() throws Throwable {
			LOGGER.info( "\n======== UPDATE ======== ");
			LOGGER.info( "-- Attempting to update a Transmission." );

			StringBuilder msg = new StringBuilder( "Failed to update a Transmission : " );        
			Transmission entity = null;

			try {            
				entity = testRead();

				assertNotNull( entity, msg.toString() );

				LOGGER.info( "-- Now updating the created Transmission." );

				// for use later on...
				thePrimaryKey = entity.getTransmissionId();

				TransmissionBusinessDelegate proxy = TransmissionBusinessDelegate.getTransmissionInstance();            
				proxy.updateTransmission( entity );   

				LOGGER.info( "-- Successfully saved Transmission - " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : primarykey = " + thePrimaryKey + " : entity-" +  entity + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests deleting a Transmission.
		 */
		public void testDelete() throws Throwable {
			LOGGER.info( "\n======== DELETE ======== ");
			LOGGER.info( "-- Deleting a previously created Transmission." );

			try{
			    Transmission entity = testRead(); 
				TransmissionBusinessDelegate.getTransmissionInstance().delete( entity );

				LOGGER.info( "-- Successfully deleted Transmission with primary key " + thePrimaryKey );            
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( "-- Failed to delete Transmission with primary key " + thePrimaryKey );

				throw e;
			}
		}

		/**
		 * Tests getting all Transmissions.
		 */
		public void testGetAll() throws Throwable {    
			LOGGER.info( "======== GETALL ======== ");
			LOGGER.info( "-- Retrieving Collection of Transmissions:" );

			StringBuilder msg = new StringBuilder( "-- Failed to get all Transmission : " );        
			List<Transmission> collection  = null;

			try {
				// call the static get method on the TransmissionBusinessDelegate
				collection = TransmissionBusinessDelegate.getTransmissionInstance().getAllTransmission();

				if ( collection == null || collection.size() == 0 ) {
					LOGGER.warning( unexpectedErrorMsg );
					LOGGER.warning( msg.toString() + " Empty collection returned."  );
				}
				else {
					// Now print out the values
					Transmission entity = null;            
					Iterator<Transmission> iter = collection.iterator();
					int index = 1;

					while( iter.hasNext() ) {
						// Retrieve the entity   
						entity = iter.next();

						assertNotNull( entity,"-- null entity in Collection." );
						assertNotNull( entity.getTransmissionId(), "-- entity in Collection has a null primary key" );        

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
		 * @return		TransmissionTest
		 */
		public TransmissionTest setHandler(Handler handler) {
			this.handler = handler;
			LOGGER.addHandler(handler); // assign so the LOGGER can only output results to the Handler
			return this;
		}

		/**
		 * Returns a new populated Transmission
		 * 
		 * @return Transmission
		 */
		protected Transmission generateNewEntity() {
			Transmission entity = new Transmission( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant(),  TransmissionType.values()[0] );
			
			return( entity );
		}

		//-----------------------------------------------------
		// attributes 
		//-----------------------------------------------------
		protected UUID thePrimaryKey 						= null;
		private final Logger LOGGER 						= Logger.getLogger(TransmissionTest.class.getName());
		private Handler handler 							= null;
		private String unexpectedErrorMsg 					= ":::::::::::::: Unexpected Error :::::::::::::::::";
	}
