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
 * Implements Struts action processing for business entity Body.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Body")
public class BodyRestController extends BaseSpringRestController {

    /**
     * Handles create a Body.  if not key provided, calls create, otherwise calls save
     * @param		Body	body
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateBodyCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = BodyBusinessDelegate.getBodyInstance().createBody( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Body.  if no key provided, calls create, otherwise calls save
     * @param		Body body
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) RefreshBodyCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshBodyCommand
			// -----------------------------------------------
			completableFuture = BodyBusinessDelegate.getBodyInstance().updateBody(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "BodyController:update() - successfully update Body - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Body entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestBody(required=true) CloseBodyCommand command ) {                
    	CompletableFuture<Void> completableFuture = null;
    	try {
        	BodyBusinessDelegate delegate = BodyBusinessDelegate.getBodyInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Body with key " + command.getBodyId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	
    /**
     * Handles loading a Body using a UUID
     * @param		UUID uuid 
     * @return		Body
     */    
    @GetMapping("/load")
    public Body load( @RequestParam(required=true) UUID uuid ) {    	
    	Body entity = null;

    	try {  
    		entity = BodyBusinessDelegate.getBodyInstance().getBody( new BodyFetchOneSummary( uuid ) );   
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Body using Id " + uuid );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Body business objects
     * @return		Set<Body>
     */
    @GetMapping("/")
    public List<Body> loadAll() {                
    	List<Body> bodyList = null;
        
    	try {
            // load the Body
            bodyList = BodyBusinessDelegate.getBodyInstance().getAllBody();
            
            if ( bodyList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Bodys" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Bodys ", exc );
        	return null;
        }

        return bodyList;
                            
    }





//************************************************************************    
// Attributes
//************************************************************************
    protected Body body = null;
    private static final Logger LOGGER = Logger.getLogger(BodyRestController.class.getName());
    
}
