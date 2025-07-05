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
 * Event handler for Engine as outlined for the CQRS pattern.  All reads related to Engine are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * @author your_name_here
 *
 */
@Component("engine-handler")
@ProcessingGroup("engine-processing-group")
public class EngineEventHandler {
	
	// core constructor
	EngineEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
	
    /*
     * @param	event CreateEngineEvent
     */
    @EventHandler
    public void handle( CreatedEngineEvent event) {
        entityManager.persist(new Engine(event.getEngineId(), event.getName(), event.getSerialNum(), event.getPlant(), event.getType()));
    }

    /*
     * @param	event UpdateEngineEvent
     */
    @EventHandler
    public Engine handle( UpdatedEngineEvent event) {
    	entityManager.merge(new Engine(event.getEngineId(), event.getName(), event.getSerialNum(), event.getPlant(), event.getType()));
    }
    
    /*
     * @param	event DeleteEngineEvent
     */
    @EventHandler
    public void on( DeletedEngineEvent event) {
    	Engine entity = new Engine();
   
    	// ------------------------------------------
    	// apply the EngineId
    	// ------------------------------------------
    	entity.setEngineId( event.getEngineId() );
    	
    	// ------------------------------------------
    	// add to an Iterable and delete
    	// ------------------------------------------
    	entityManager.remove( entity );
    }    
    
    // attributes
	private final EngineRepository engineEntityRepository;
    private static final Logger LOGGER 			= Logger.getLogger(EngineEventHandler.class.getName());

}
