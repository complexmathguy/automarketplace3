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
 * Projector for Body as outlined for the CQRS pattern.
 * 
 * Commands are handled by BodyAggregate
 * 
 * @author your_name_here
 *
 */
@Component("body-entity-projector")
public class BodyEntityProjector {
		
	// core constructor
	public BodyEntityProjector(BodyRepository repository ) {
        this.repository = repository;
    }	

	/*
	 * Insert a Body
	 * 
     * @param	entity Body
     */
    public Body create( Body entity) {
	    LOGGER.info("creating " + entity.toString() );
	   
    	// ------------------------------------------
    	// persist a new one
    	// ------------------------------------------ 
	    return repository.save(entity);
        
    }

	/*
	 * Update a Body
	 * 
     * @param	entity Body
     */
    public Body update( Body entity) {
	    LOGGER.info("updating " + entity.toString() );

	    // ------------------------------------------
    	// save with an existing instance
    	// ------------------------------------------ 
		return repository.save(entity);

    }
    
	/*
	 * Delete a Body
	 * 
     * @param	id		UUID
     */
    public Body delete( UUID id ) {
	    LOGGER.info("deleting " + id.toString() );

    	// ------------------------------------------
    	// locate the entity by the provided id
    	// ------------------------------------------
	    Body entity = repository.findById(id).get();
	    
    	// ------------------------------------------
    	// delete what is discovered 
    	// ------------------------------------------
    	repository.delete( entity );
    	
    	return entity;
    }    




    /**
     * Method to retrieve the Body via an FindBodyQuery
     * @return 	query	FindBodyQuery
     */
    @SuppressWarnings("unused")
    public Body find( UUID id ) {
    	LOGGER.info("handling find using " + id.toString() );
    	try {
    		return repository.findById(id).get();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find a Body - {0}", exc.getMessage() );
    	}
    	return null;
    }
    
    /**
     * Method to retrieve a collection of all Bodys
     *
     * @param	query	FindAllBodyQuery 
     * @return 	List<Body> 
     */
    @SuppressWarnings("unused")
    public List<Body> findAll( FindAllBodyQuery query ) {
    	LOGGER.info("handling findAll using " + query.toString() );
    	try {
    		return repository.findAll();
    	}
    	catch( IllegalArgumentException exc ) {
    		LOGGER.log( Level.WARNING, "Failed to find all Body - {0}", exc.getMessage() );
    	}
    	return null;
    }


    //--------------------------------------------------
    // attributes
    // --------------------------------------------------
	@Autowired
    protected final BodyRepository repository;

    private static final Logger LOGGER 	= Logger.getLogger(BodyEntityProjector.class.getName());

}
