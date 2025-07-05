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
 * Event handler for Body as outlined for the CQRS pattern.  All reads related to Body are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * @author your_name_here
 *
 */
@Component("body-handler")
@ProcessingGroup("body-processing-group")
public class BodyEventHandler {
	
	// core constructor
	BodyEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
	
    /*
     * @param	event CreateBodyEvent
     */
    @EventHandler
    public void handle( CreatedBodyEvent event) {
        entityManager.persist(new Body(event.getBodyId(), event.getName(), event.getPlant()));
    }

    /*
     * @param	event UpdateBodyEvent
     */
    @EventHandler
    public Body handle( UpdatedBodyEvent event) {
    	entityManager.merge(new Body(event.getBodyId(), event.getName(), event.getPlant()));
    }
    
    /*
     * @param	event DeleteBodyEvent
     */
    @EventHandler
    public void on( DeletedBodyEvent event) {
    	Body entity = new Body();
   
    	// ------------------------------------------
    	// apply the BodyId
    	// ------------------------------------------
    	entity.setBodyId( event.getBodyId() );
    	
    	// ------------------------------------------
    	// add to an Iterable and delete
    	// ------------------------------------------
    	entityManager.remove( entity );
    }    
    
    // attributes
	private final BodyRepository bodyEntityRepository;
    private static final Logger LOGGER 			= Logger.getLogger(BodyEventHandler.class.getName());

}
