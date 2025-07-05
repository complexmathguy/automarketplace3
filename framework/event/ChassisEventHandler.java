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
 * Event handler for Chassis as outlined for the CQRS pattern.  All reads related to Chassis are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * @author your_name_here
 *
 */
@Component("chassis-handler")
@ProcessingGroup("chassis-processing-group")
public class ChassisEventHandler {
	
	// core constructor
	ChassisEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
	
    /*
     * @param	event CreateChassisEvent
     */
    @EventHandler
    public void handle( CreatedChassisEvent event) {
        entityManager.persist(new Chassis(event.getChassisId(), event.getName(), event.getSerialNum(), event.getPlant(), event.getType()));
    }

    /*
     * @param	event UpdateChassisEvent
     */
    @EventHandler
    public Chassis handle( UpdatedChassisEvent event) {
    	entityManager.merge(new Chassis(event.getChassisId(), event.getName(), event.getSerialNum(), event.getPlant(), event.getType()));
    }
    
    /*
     * @param	event DeleteChassisEvent
     */
    @EventHandler
    public void on( DeletedChassisEvent event) {
    	Chassis entity = new Chassis();
   
    	// ------------------------------------------
    	// apply the ChassisId
    	// ------------------------------------------
    	entity.setChassisId( event.getChassisId() );
    	
    	// ------------------------------------------
    	// add to an Iterable and delete
    	// ------------------------------------------
    	entityManager.remove( entity );
    }    
    
    // attributes
	private final ChassisRepository chassisEntityRepository;
    private static final Logger LOGGER 			= Logger.getLogger(ChassisEventHandler.class.getName());

}
