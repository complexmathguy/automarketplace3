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
     * @return		Engine 
     */
	@PostMapping("/create")
    public Engine create( @RequestBody(required=true) CreateEngineCommand command ) {
    	Engine entity = null;
		try {       
        	
			entity = EngineBusinessDelegate.getEngineInstance().createEngine( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return entity;
    }

    /**
     * Handles updating a Engine.  if no key provided, calls create, otherwise calls save
     * @param		Engine engine
     * @return		Engine
     */
	@PutMapping("/update")
    public Engine update( @RequestBody(required=true) RefreshEngineCommand command ) {
		Engine entity = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshEngineCommand
			// -----------------------------------------------
			entity = EngineBusinessDelegate.getEngineInstance().updateEngine(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "EngineController:update() - successfully update Engine - " + exc.getMessage());        	
	    }		
		
		return entity;
	}
 
    /**
     * Handles deleting a Engine entity
     * @param		command ${class.getDeleteCommandAlias()}
     */
    @DeleteMapping("/delete")    
    public void delete( @RequestBody(required=true) CloseEngineCommand command ) {                
    	try {
        	EngineBusinessDelegate delegate = EngineBusinessDelegate.getEngineInstance();

        	delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Engine with key " + command.getEngineId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }

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
