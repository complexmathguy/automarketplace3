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
 * Event handler for Interior as outlined for the CQRS pattern.  All reads related to Interior are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * @author your_name_here
 *
 */
@Component("interior-handler")
@ProcessingGroup("interior-processing-group")
public class InteriorEventHandler {
	
	// core constructor
	InteriorEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
	
    /*
     * @param	event CreateInteriorEvent
     */
    @EventHandler
    public void handle( CreatedInteriorEvent event) {
        entityManager.persist(new Interior(event.getInteriorId(), event.getSerialNum(), event.getName(), event.getPlant()));
    }

    /*
     * @param	event UpdateInteriorEvent
     */
    @EventHandler
    public Interior handle( UpdatedInteriorEvent event) {
    	entityManager.merge(new Interior(event.getInteriorId(), event.getSerialNum(), event.getName(), event.getPlant()));
    }
    
    /*
     * @param	event DeleteInteriorEvent
     */
    @EventHandler
    public void on( DeletedInteriorEvent event) {
    	Interior entity = new Interior();
   
    	// ------------------------------------------
    	// apply the InteriorId
    	// ------------------------------------------
    	entity.setInteriorId( event.getInteriorId() );
    	
    	// ------------------------------------------
    	// add to an Iterable and delete
    	// ------------------------------------------
    	entityManager.remove( entity );
    }    
    
    // attributes
	private final InteriorRepository interiorEntityRepository;
    private static final Logger LOGGER 			= Logger.getLogger(InteriorEventHandler.class.getName());

}
