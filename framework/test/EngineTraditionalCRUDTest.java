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
 * Test Engine class.
 *
 * @author your_name_here
 */
public class EngineTest{

        // ------------------------------------
		// default constructor
	    // ------------------------------------
		public EngineTest() {
			LOGGER.setUseParentHandlers(false);	// only want to output to the provided LogHandler
		}

		// test methods
		@Test
		/**
		 * Full Create-Read-Update-Delete of a Engine, through a
		 * EngineTest.
		 */
		public void testCRUD() throws Throwable {
			try {
				LOGGER.info("**********************************************************");
				LOGGER.info("Beginning full CRUD test on Engine...");

				testCreate();
//				testRead();
//				testUpdate();
				testGetAll();
//				testDelete();

				LOGGER.info("Successfully ran a full test on EngineTest...");
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
		 * Tests creating a new Engine.
		 */
		public void testCreate() throws Throwable {
			LOGGER.info( "\n======== CREATE ======== ");
			LOGGER.info( "-- Attempting to create a Engine");

			StringBuilder msg = new StringBuilder( "-- Failed to create a Engine" );

			try {            
				Engine entity = EngineBusinessDelegate.getEngineInstance().createEngine( generateNewEntity() );
				thePrimaryKey = entity.getEngineId();
				LOGGER.info( "-- Successfully created a Engine with primary key" + thePrimaryKey );
			}
			catch (Exception e) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests reading a Engine. 
		 */
		public Engine testRead() throws Throwable {
			LOGGER.info( "\n======== READ ======== ");
			LOGGER.info( "-- Reading a previously created Engine" );

			Engine entity = null;
			StringBuilder msg = new StringBuilder( "-- Failed to read Engine with primary key" );
			msg.append( thePrimaryKey );
			
			EngineFetchOneSummary fetchOneSummary = new EngineFetchOneSummary( thePrimaryKey );

			try {
				entity = EngineBusinessDelegate.getEngineInstance().getEngine( fetchOneSummary );

				assertNotNull( entity,msg.toString() );

				LOGGER.info( "-- Successfully found Engine " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
			
			return entity;
		}

		/** 
		 * Tests updating a Engine.
		 */
		public void testUpdate() throws Throwable {
			LOGGER.info( "\n======== UPDATE ======== ");
			LOGGER.info( "-- Attempting to update a Engine." );

			StringBuilder msg = new StringBuilder( "Failed to update a Engine : " );        
			Engine entity = null;

			try {            
				entity = testRead();

				assertNotNull( entity, msg.toString() );

				LOGGER.info( "-- Now updating the created Engine." );

				// for use later on...
				thePrimaryKey = entity.getEngineId();

				EngineBusinessDelegate proxy = EngineBusinessDelegate.getEngineInstance();            
				proxy.updateEngine( entity );   

				LOGGER.info( "-- Successfully saved Engine - " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : primarykey = " + thePrimaryKey + " : entity-" +  entity + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests deleting a Engine.
		 */
		public void testDelete() throws Throwable {
			LOGGER.info( "\n======== DELETE ======== ");
			LOGGER.info( "-- Deleting a previously created Engine." );

			try{
			    Engine entity = testRead(); 
				EngineBusinessDelegate.getEngineInstance().delete( entity );

				LOGGER.info( "-- Successfully deleted Engine with primary key " + thePrimaryKey );            
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( "-- Failed to delete Engine with primary key " + thePrimaryKey );

				throw e;
			}
		}

		/**
		 * Tests getting all Engines.
		 */
		public void testGetAll() throws Throwable {    
			LOGGER.info( "======== GETALL ======== ");
			LOGGER.info( "-- Retrieving Collection of Engines:" );

			StringBuilder msg = new StringBuilder( "-- Failed to get all Engine : " );        
			List<Engine> collection  = null;

			try {
				// call the static get method on the EngineBusinessDelegate
				collection = EngineBusinessDelegate.getEngineInstance().getAllEngine();

				if ( collection == null || collection.size() == 0 ) {
					LOGGER.warning( unexpectedErrorMsg );
					LOGGER.warning( msg.toString() + " Empty collection returned."  );
				}
				else {
					// Now print out the values
					Engine entity = null;            
					Iterator<Engine> iter = collection.iterator();
					int index = 1;

					while( iter.hasNext() ) {
						// Retrieve the entity   
						entity = iter.next();

						assertNotNull( entity,"-- null entity in Collection." );
						assertNotNull( entity.getEngineId(), "-- entity in Collection has a null primary key" );        

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
		 * @return		EngineTest
		 */
		public EngineTest setHandler(Handler handler) {
			this.handler = handler;
			LOGGER.addHandler(handler); // assign so the LOGGER can only output results to the Handler
			return this;
		}

		/**
		 * Returns a new populated Engine
		 * 
		 * @return Engine
		 */
		protected Engine generateNewEntity() {
			Engine entity = new Engine( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant(),  EngineType.values()[0] );
			
			return( entity );
		}

		//-----------------------------------------------------
		// attributes 
		//-----------------------------------------------------
		protected UUID thePrimaryKey 						= null;
		private final Logger LOGGER 						= Logger.getLogger(EngineTest.class.getName());
		private Handler handler 							= null;
		private String unexpectedErrorMsg 					= ":::::::::::::: Unexpected Error :::::::::::::::::";
	}
