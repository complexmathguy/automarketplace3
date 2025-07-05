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
 * Test Body class.
 *
 * @author your_name_here
 */
public class BodyTest{

        // ------------------------------------
		// default constructor
	    // ------------------------------------
		public BodyTest() {
			LOGGER.setUseParentHandlers(false);	// only want to output to the provided LogHandler
		}

		// test methods
		@Test
		/**
		 * Full Create-Read-Update-Delete of a Body, through a
		 * BodyTest.
		 */
		public void testCRUD() throws Throwable {
			try {
				LOGGER.info("**********************************************************");
				LOGGER.info("Beginning full CRUD test on Body...");

				testCreate();
//				testRead();
//				testUpdate();
				testGetAll();
//				testDelete();

				LOGGER.info("Successfully ran a full test on BodyTest...");
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
		 * Tests creating a new Body.
		 */
		public void testCreate() throws Throwable {
			LOGGER.info( "\n======== CREATE ======== ");
			LOGGER.info( "-- Attempting to create a Body");

			StringBuilder msg = new StringBuilder( "-- Failed to create a Body" );

			try {            
				Body entity = BodyBusinessDelegate.getBodyInstance().createBody( generateNewEntity() );
				thePrimaryKey = entity.getBodyId();
				LOGGER.info( "-- Successfully created a Body with primary key" + thePrimaryKey );
			}
			catch (Exception e) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests reading a Body. 
		 */
		public Body testRead() throws Throwable {
			LOGGER.info( "\n======== READ ======== ");
			LOGGER.info( "-- Reading a previously created Body" );

			Body entity = null;
			StringBuilder msg = new StringBuilder( "-- Failed to read Body with primary key" );
			msg.append( thePrimaryKey );
			
			BodyFetchOneSummary fetchOneSummary = new BodyFetchOneSummary( thePrimaryKey );

			try {
				entity = BodyBusinessDelegate.getBodyInstance().getBody( fetchOneSummary );

				assertNotNull( entity,msg.toString() );

				LOGGER.info( "-- Successfully found Body " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : " + e );

				throw e;
			}
			
			return entity;
		}

		/** 
		 * Tests updating a Body.
		 */
		public void testUpdate() throws Throwable {
			LOGGER.info( "\n======== UPDATE ======== ");
			LOGGER.info( "-- Attempting to update a Body." );

			StringBuilder msg = new StringBuilder( "Failed to update a Body : " );        
			Body entity = null;

			try {            
				entity = testRead();

				assertNotNull( entity, msg.toString() );

				LOGGER.info( "-- Now updating the created Body." );

				// for use later on...
				thePrimaryKey = entity.getBodyId();

				BodyBusinessDelegate proxy = BodyBusinessDelegate.getBodyInstance();            
				proxy.updateBody( entity );   

				LOGGER.info( "-- Successfully saved Body - " + entity.toString() );
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( msg.toString() + " : primarykey = " + thePrimaryKey + " : entity-" +  entity + " : " + e );

				throw e;
			}
		}

		/** 
		 * Tests deleting a Body.
		 */
		public void testDelete() throws Throwable {
			LOGGER.info( "\n======== DELETE ======== ");
			LOGGER.info( "-- Deleting a previously created Body." );

			try{
			    Body entity = testRead(); 
				BodyBusinessDelegate.getBodyInstance().delete( entity );

				LOGGER.info( "-- Successfully deleted Body with primary key " + thePrimaryKey );            
			}
			catch ( Throwable e ) {
				LOGGER.warning( unexpectedErrorMsg );
				LOGGER.warning( "-- Failed to delete Body with primary key " + thePrimaryKey );

				throw e;
			}
		}

		/**
		 * Tests getting all Bodys.
		 */
		public void testGetAll() throws Throwable {    
			LOGGER.info( "======== GETALL ======== ");
			LOGGER.info( "-- Retrieving Collection of Bodys:" );

			StringBuilder msg = new StringBuilder( "-- Failed to get all Body : " );        
			List<Body> collection  = null;

			try {
				// call the static get method on the BodyBusinessDelegate
				collection = BodyBusinessDelegate.getBodyInstance().getAllBody();

				if ( collection == null || collection.size() == 0 ) {
					LOGGER.warning( unexpectedErrorMsg );
					LOGGER.warning( msg.toString() + " Empty collection returned."  );
				}
				else {
					// Now print out the values
					Body entity = null;            
					Iterator<Body> iter = collection.iterator();
					int index = 1;

					while( iter.hasNext() ) {
						// Retrieve the entity   
						entity = iter.next();

						assertNotNull( entity,"-- null entity in Collection." );
						assertNotNull( entity.getBodyId(), "-- entity in Collection has a null primary key" );        

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
		 * @return		BodyTest
		 */
		public BodyTest setHandler(Handler handler) {
			this.handler = handler;
			LOGGER.addHandler(handler); // assign so the LOGGER can only output results to the Handler
			return this;
		}

		/**
		 * Returns a new populated Body
		 * 
		 * @return Body
		 */
		protected Body generateNewEntity() {
			Body entity = new Body( null,  org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(16),  new Plant() );
			
			return( entity );
		}

		//-----------------------------------------------------
		// attributes 
		//-----------------------------------------------------
		protected UUID thePrimaryKey 						= null;
		private final Logger LOGGER 						= Logger.getLogger(BodyTest.class.getName());
		private Handler handler 							= null;
		private String unexpectedErrorMsg 					= ":::::::::::::: Unexpected Error :::::::::::::::::";
	}
