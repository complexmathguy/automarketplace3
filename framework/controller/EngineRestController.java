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
 * Implements Struts action processing for business entity Engine.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Engine")
public class EngineRestController extends BaseSpringRestController {

    /**
     * Handles create a Engine.  if not key provided, calls create, otherwise calls save
     * @param		Engine	engine
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateEngineCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = EngineBusinessDelegate.getEngineInstance().createEngine( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Engine.  if no key provided, calls create, otherwise calls save
     * @param		Engine engine
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) RefreshEngineCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshEngineCommand
			// -----------------------------------------------
			completableFuture = EngineBusinessDelegate.getEngineInstance().updateEngine(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "EngineController:update() - successfully update Engine - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Engine entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestBody(required=true) CloseEngineCommand command ) {                
    	CompletableFuture<Void> completableFuture = null;
    	try {
        	EngineBusinessDelegate delegate = EngineBusinessDelegate.getEngineInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Engine with key " + command.getEngineId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	
    /**
     * Handles loading a Engine using a UUID
     * @param		UUID uuid 
     * @return		Engine
     */    
    @GetMapping("/load")
    public Engine load( @RequestParam(required=true) UUID uuid ) {    	
    	Engine entity = null;

    	try {  
    		entity = EngineBusinessDelegate.getEngineInstance().getEngine( new EngineFetchOneSummary( uuid ) );   
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Engine using Id " + uuid );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Engine business objects
     * @return		Set<Engine>
     */
    @GetMapping("/")
    public List<Engine> loadAll() {                
    	List<Engine> engineList = null;
        
    	try {
            // load the Engine
            engineList = EngineBusinessDelegate.getEngineInstance().getAllEngine();
            
            if ( engineList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Engines" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Engines ", exc );
        	return null;
        }

        return engineList;
                            
    }





//************************************************************************    
// Attributes
//************************************************************************
    protected Engine engine = null;
    private static final Logger LOGGER = Logger.getLogger(EngineRestController.class.getName());
    
}
