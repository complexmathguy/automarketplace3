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
 * Test Interior class.
 *
 * @author your_name_here
 */
public class InteriorTest{

        // ------------------------------------
		// default constructor
	    // ------------------------------------
		public InteriorTest() {
			LOGGER.setUseParentHandlers(false);	// only want to output to the provided LogHandler
		}

		// test methods
		@Test
		/**
		 * Full Create-Read-Update-Delete of a Interior, through a
		 * InteriorTest.
		 */
		public void testCRUD() throws Throwable {
			try {
				LOGGER.info("**********************************************************");
				LOGGER.info("Beginning full CRUD test on Interior...");

				testCreate();
//				testRead();
//				testUpdate();
				testGetAll();
//				testDelete();

				LOGGER.info("Successfully ran a full test on InteriorTest...");
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
		 * Tests creating a new Interior.
		 */
		public void testCreate() throws Throwable {
			LOGGER.info( "\n======== CREATE ======== ");
			LOGGER.info( "-- Attempting to create a Interior");

			StringBuilder msg = new StringBuilder( "-- Failed to create a Interior" );

			try {            
				Interior entity = InteriorBusinessDelegate.getInteriorInstance().createInterior( generateNewEntity() );
				thePrimaryKey = entity.getInteriorId();
				LOGGER.info( "-- Successfully created a Interior with primary key" + thePrimaryKey );
			}
			catch (Exception e) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests reading a Interior. 
		 */
		public Interior testRead() throws Throwable {
			LOGGER.info( "\n======== READ ======== ");
			LOGGER.info( "-- Reading a previously created Interior" );

			Interior entity = null;
			StringBuilder msg = new StringBuilder( "-- Failed to read Interior with primary key" );
			msg.append( thePrimaryKey );
			
			InteriorFetchOneSummary fetchOneSummary = new InteriorFetchOneSummary( thePrimaryKey );

			try {
				entity = InteriorBusinessDelegate.getInteriorInstance().getInterior( fetchOneSummary );

				assertNotNull( entity,msg.toString() );

				LOGGER.info( "-- Successfully found Interior " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
			
			return entity;
		}

		/** 
		 * Tests updating a Interior.
		 */
		public void testUpdate() throws Throwable {
			LOGGER.info( "\n======== UPDATE ======== ");
			LOGGER.info( "-- Attempting to update a Interior." );

			StringBuilder msg = new StringBuilder( "Failed to update a Interior : " );        
			Interior entity = null;

			try {            
				entity = testRead();

				assertNotNull( entity, msg.toString() );

				LOGGER.info( "-- Now updating the created Interior." );

				// for use later on...
				thePrimaryKey = entity.getInteriorId();

				InteriorBusinessDelegate proxy = InteriorBusinessDelegate.getInteriorInstance();            
				proxy.updateInterior( entity );   

				LOGGER.info( "-- Successfully saved Interior - " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : primarykey = " + thePrimaryKey + " : entity-" +  entity + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests deleting a Interior.
		 */
		public void testDelete() throws Throwable {
			LOGGER.info( "\n======== DELETE ======== ");
			LOGGER.info( "-- Deleting a previously created Interior." );

			try{
			    Interior entity = testRead(); 
				InteriorBusinessDelegate.getInteriorInstance().delete( entity );

				LOGGER.info( "-- Successfully deleted Interior with primary key " + thePrimaryKey );            
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( "-- Failed to delete Interior with primary key " + thePrimaryKey );

				throw e;
			}
		}

		/**
		 * Tests getting all Interiors.
		 */
		public void testGetAll() throws Throwable {    
			LOGGER.info( "======== GETALL ======== ");
			LOGGER.info( "-- Retrieving Collection of Interiors:" );

			StringBuilder msg = new StringBuilder( "-- Failed to get all Interior : " );        
			List<Interior> collection  = null;

			try {
				// call the static get method on the InteriorBusinessDelegate
				collection = InteriorBusinessDelegate.getInteriorInstance().getAllInterior();

				if ( collection == null || collection.size() == 0 ) {
					LOGGER.warning( unexpectedErrorMsg );
					LOGGER.warning( msg.toString() + " Empty collection returned."  );
				}
				else {
					// Now print out the values
					Interior entity = null;            
					Iterator<Interior> iter = collection.iterator();
					int index = 1;

					while( iter.hasNext() ) {
						// Retrieve the entity   
						entity = iter.next();

						assertNotNull( entity,"-- null entity in Collection." );
						assertNotNull( entity.getInteriorId(), "-- entity in Collection has a null primary key" );        

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
		 * @return		InteriorTest
		 */
		public InteriorTest setHandler(Handler handler) {
			this.handler = handler;
			LOGGER.addHandler(handler); // assign so the LOGGER can only output results to the Handler
			return this;
		}

		/**
		 * Returns a new populated Interior
		 * 
		 * @return Interior
		 */
		protected Interior generateNewEntity() {
			Interior entity = new Interior( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant() );
			
			return( entity );
		}

		//-----------------------------------------------------
		// attributes 
		//-----------------------------------------------------
		protected UUID thePrimaryKey 						= null;
		private final Logger LOGGER 						= Logger.getLogger(InteriorTest.class.getName());
		private Handler handler 							= null;
		private String unexpectedErrorMsg 					= ":::::::::::::: Unexpected Error :::::::::::::::::";
	}
