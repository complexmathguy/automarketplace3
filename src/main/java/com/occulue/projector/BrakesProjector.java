/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.projector;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for Brakes as outlined for the CQRS pattern.  All event handling and query handling related to Brakes are invoked here
 * and dispersed as an event to be handled elsewhere.
 * 
 * Commands are handled by BrakesAggregate
 * 
 * @author your_name_here
 *
 */
//@ProcessingGroup("brakes")
@Component("brakes-projector")
public class BrakesProjector extends BrakesEntityProjector {
		
	// core constructor
	public BrakesProjector(BrakesRepository repository, QueryUpdateEmitter queryUpdateEmitter ) {
        super(repository);
        this.queryUpdateEmitter = queryUpdateEmitter;
    }	

	/*
     * @param	event CreateBrakeEvent
     */
    @EventHandler( payloadType=CreateBrakeEvent.class )
    public void handle( CreateBrakeEvent event) {
	    LOGGER.info("handling CreateBrakeEvent - " + event );
	    Brakes entity = new Brakes();
            entity.setBrakesId( event.getBrakesId() );
            entity.setSerialNum( event.getSerialNum() );
            entity.setName( event.getName() );
            entity.setPlant( event.getPlant() );
            entity.setType( event.getType() );
	    
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    create(entity);
        
        // ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllBrakes( entity );
    }

    /*
     * @param	event RefreshedBrakeEvent
     */
    @EventHandler( payloadType=RefreshedBrakeEvent.class )
    public void handle( RefreshedBrakeEvent event) {
    	LOGGER.info("handling RefreshedBrakeEvent - " + event );
    	
	    Brakes entity = new Brakes();
            entity.setBrakesId( event.getBrakesId() );
            entity.setSerialNum( event.getSerialNum() );
            entity.setName( event.getName() );
            entity.setPlant( event.getPlant() );
            entity.setType( event.getType() );
 
    	// ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		update(entity);

    	// ------------------------------------------
    	// emit to subscribers that find one
    	// ------------------------------------------    	
        emitFindBrakes( entity );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllBrakes( entity );        
    }
    
    /*
     * @param	event ClosedBrakeEvent
     */
    @EventHandler( payloadType=ClosedBrakeEvent.class )
    public void handle( ClosedBrakeEvent event) {
    	LOGGER.info("handling ClosedBrakeEvent - " + event );
    	
    	// ------------------------------------------
    	// delete delegation
    	// ------------------------------------------
    	Brakes entity = delete( event.getBrakesId() );

    	// ------------------------------------------
    	// emit to subscribers that find all
    	// ------------------------------------------    	
        emitFindAllBrakes( entity );

    }    




    /**
     * Method to retrieve the Brakes via an BrakesPrimaryKey.
     * @param 	id Long
     * @return 	Brakes
     * @exception ProcessingException - Thrown if processing any related problems
     * @exception IllegalArgumentException 
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public Brakes handle( FindBrakesQuery query ) 
    throws ProcessingException, IllegalArgumentException {
    	return find( query.getFilter().getBrakesId() );
    }
    
    /**
     * Method to retrieve a collection of all Brakess
     *
     * @param	query	FindAllBrakesQuery 
     * @return 	List<Brakes> 
     * @exception ProcessingException Thrown if any problems
     */
    @SuppressWarnings("unused")
    @QueryHandler
    public List<Brakes> handle( FindAllBrakesQuery query ) throws ProcessingException {
    	return findAll( query );
    }



/**
 * emit to subscription queries of type FindBrakes,
 * but only if the id matches
 *
 * @param		entity	Brakes
 */
	protected void emitFindBrakes( Brakes entity ) {
		LOGGER.info("handling emitFindBrakes" );
		
	    queryUpdateEmitter.emit(FindBrakesQuery.class,
	                            query -> query.getFilter().getBrakesId().equals(entity.getBrakesId()),
	                            entity);
	}
	
	/**
	 * unconditionally emit to subscription queries of type FindAllBrakes
	 * 
	 * @param		entity	Brakes
	 */
	protected void emitFindAllBrakes( Brakes entity ) {
		LOGGER.info("handling emitFindAllBrakes" );
		
	    queryUpdateEmitter.emit(FindAllBrakesQuery.class,
	                            query -> true,
	                            entity);
	}

	//--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
	private final QueryUpdateEmitter queryUpdateEmitter;
    private static final Logger LOGGER 	= Logger.getLogger(BrakesProjector.class.getName());

}
