/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.event;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.repository.*;

/**
 * Event handler for Brakes as outlined for the CQRS pattern.  All reads related to Brakes are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * @author your_name_here
 *
 */
@Component("brakes-handler")
@ProcessingGroup("brakes-processing-group")
public class BrakesEventHandler {
	
	// core constructor
	BrakesEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
	
    /*
     * @param	event CreateBrakesEvent
     */
    @EventHandler
    public void handle( CreatedBrakesEvent event) {
        entityManager.persist(new Brakes(event.getBrakesId(), event.getSerialNum(), event.getName(), event.getPlant(), event.getType()));
    }

    /*
     * @param	event UpdateBrakesEvent
     */
    @EventHandler
    public Brakes handle( UpdatedBrakesEvent event) {
    	entityManager.merge(new Brakes(event.getBrakesId(), event.getSerialNum(), event.getName(), event.getPlant(), event.getType()));
    }
    
    /*
     * @param	event DeleteBrakesEvent
     */
    @EventHandler
    public void on( DeletedBrakesEvent event) {
    	Brakes entity = new Brakes();
   
    	// ------------------------------------------
    	// apply the BrakesId
    	// ------------------------------------------
    	entity.setBrakesId( event.getBrakesId() );
    	
    	// ------------------------------------------
    	// add to an Iterable and delete
    	// ------------------------------------------
    	entityManager.remove( entity );
    }    
    
    // attributes
	private final BrakesRepository brakesEntityRepository;
    private static final Logger LOGGER 			= Logger.getLogger(BrakesEventHandler.class.getName());

}
