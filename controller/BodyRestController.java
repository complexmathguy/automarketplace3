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
     * @return		Body 
     */
	@PostMapping("/create")
    public Body create( @RequestBody(required=true) CreateBodyCommand command ) {
    	Body entity = null;
		try {       
        	
			entity = BodyBusinessDelegate.getBodyInstance().createBody( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return entity;
    }

    /**
     * Handles updating a Body.  if no key provided, calls create, otherwise calls save
     * @param		Body body
     * @return		Body
     */
	@PutMapping("/update")
    public Body update( @RequestBody(required=true) RefreshBodyCommand command ) {
		Body entity = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshBodyCommand
			// -----------------------------------------------
			entity = BodyBusinessDelegate.getBodyInstance().updateBody(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "BodyController:update() - successfully update Body - " + exc.getMessage());        	
	    }		
		
		return entity;
	}
 
    /**
     * Handles deleting a Body entity
     * @param		command ${class.getDeleteCommandAlias()}
     */
    @DeleteMapping("/delete")    
    public void delete( @RequestBody(required=true) CloseBodyCommand command ) {                
    	try {
        	BodyBusinessDelegate delegate = BodyBusinessDelegate.getBodyInstance();

        	delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Body with key " + command.getBodyId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }

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
