/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.occulue.api.*;
import com.occulue.delegate.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

/** 
 * Implements Struts action processing for business entity Interior.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Interior")
public class InteriorRestController extends BaseSpringRestController {

    /**
     * Handles create a Interior.  if not key provided, calls create, otherwise calls save
     * @param		Interior	interior
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateInteriorCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = InteriorBusinessDelegate.getInteriorInstance().createInterior( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Interior.  if no key provided, calls create, otherwise calls save
     * @param		Interior interior
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) RefreshInteriorCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshInteriorCommand
			// -----------------------------------------------
			completableFuture = InteriorBusinessDelegate.getInteriorInstance().updateInterior(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "InteriorController:update() - successfully update Interior - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Interior entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestBody(required=true) CloseInteriorCommand command ) {                
    	CompletableFuture<Void> completableFuture = null;
    	try {
        	InteriorBusinessDelegate delegate = InteriorBusinessDelegate.getInteriorInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Interior with key " + command.getInteriorId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	
    /**
     * Handles loading a Interior using a UUID
     * @param		UUID uuid 
     * @return		Interior
     */    
    @GetMapping("/load")
    public Interior load( @RequestParam(required=true) UUID uuid ) {    	
    	Interior entity = null;

    	try {  
    		entity = InteriorBusinessDelegate.getInteriorInstance().getInterior( new InteriorFetchOneSummary( uuid ) );   
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Interior using Id " + uuid );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Interior business objects
     * @return		Set<Interior>
     */
    @GetMapping("/")
    public List<Interior> loadAll() {                
    	List<Interior> interiorList = null;
        
    	try {
            // load the Interior
            interiorList = InteriorBusinessDelegate.getInteriorInstance().getAllInterior();
            
            if ( interiorList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Interiors" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Interiors ", exc );
        	return null;
        }

        return interiorList;
                            
    }





//************************************************************************    
// Attributes
//************************************************************************
    protected Interior interior = null;
    private static final Logger LOGGER = Logger.getLogger(InteriorRestController.class.getName());
    
}
