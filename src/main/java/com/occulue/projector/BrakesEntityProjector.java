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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;
import com.occulue.repository.*;

/**
 * Projector for Brakes as outlined for the CQRS pattern.
 * 
 * Commands are handled by BrakesAggregate
 * 
 * @author your_name_here
 *
 */
@Component("brakes-entity-projector")
public class BrakesEntityProjector {
		
	// core constructor
	public BrakesEntityProjector(BrakesRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Brakes
	 * 
     * @param	entity Brakes
     */
    public Brakes create( Brakes entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Brakes
	 * 
     * @param	entity Brakes
     */
    public Brakes update( Brakes entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Brakes
	 * 
     * @param	id		UUID
     */
    public Brakes delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Brakes entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the Brakes via an FindBrakesQuery
     * @return 	query	FindBrakesQuery
     */
    @SuppressWarnings("unused")
    public Brakes find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Brakes - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Brakess
     *
     * @param	query	FindAllBrakesQuery 
     * @return 	List<Brakes> 
     */
    @SuppressWarnings("unused")
    public List<Brakes> findAll( FindAllBrakesQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Brakes - {0}", exc.getMessage() );
    	}
    	return null;
    }


    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final BrakesRepository repository;

    private static final Logger LOGGER 	= Logger.getLogger(BrakesEntityProjector.class.getName());

}
