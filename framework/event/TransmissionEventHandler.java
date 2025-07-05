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
 * Event handler for Transmission as outlined for the CQRS pattern.  All reads related to Transmission are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * @author your_name_here
 *
 */
@Component("transmission-handler")
@ProcessingGroup("transmission-processing-group")
public class TransmissionEventHandler {
	
	// core constructor
	TransmissionEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
	
    /*
     * @param	event CreateTransmissionEvent
     */
    @EventHandler
    public void handle( CreatedTransmissionEvent event) {
        entityManager.persist(new Transmission(event.getTransmissionId(), event.getName(), event.getSerialNum(), event.getPlant(), event.getType()));
    }

    /*
     * @param	event UpdateTransmissionEvent
     */
    @EventHandler
    public Transmission handle( UpdatedTransmissionEvent event) {
    	entityManager.merge(new Transmission(event.getTransmissionId(), event.getName(), event.getSerialNum(), event.getPlant(), event.getType()));
    }
    
    /*
     * @param	event DeleteTransmissionEvent
     */
    @EventHandler
    public void on( DeletedTransmissionEvent event) {
    	Transmission entity = new Transmission();
   
    	// ------------------------------------------
    	// apply the TransmissionId
    	// ------------------------------------------
    	entity.setTransmissionId( event.getTransmissionId() );
    	
    	// ------------------------------------------
    	// add to an Iterable and delete
    	// ------------------------------------------
    	entityManager.remove( entity );
    }    
    
    // attributes
	private final TransmissionRepository transmissionEntityRepository;
    private static final Logger LOGGER 			= Logger.getLogger(TransmissionEventHandler.class.getName());

}
