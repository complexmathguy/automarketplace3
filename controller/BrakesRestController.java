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
 * Implements Struts action processing for business entity Brakes.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Brakes")
public class BrakesRestController extends BaseSpringRestController {

    /**
     * Handles create a Brakes.  if not key provided, calls create, otherwise calls save
     * @param		Brakes	brakes
     * @return		Brakes 
     */
	@PostMapping("/create")
    public Brakes create( @RequestBody(required=true) CreateBrakeCommand command ) {
    	Brakes entity = null;
		try {       
        	
			entity = BrakesBusinessDelegate.getBrakesInstance().createBrakes( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return entity;
    }

    /**
     * Handles updating a Brakes.  if no key provided, calls create, otherwise calls save
     * @param		Brakes brakes
     * @return		Brakes
     */
	@PutMapping("/update")
    public Brakes update( @RequestBody(required=true) RefreshBrakeCommand command ) {
		Brakes entity = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshBrakeCommand
			// -----------------------------------------------
			entity = BrakesBusinessDelegate.getBrakesInstance().updateBrakes(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "BrakesController:update() - successfully update Brakes - " + exc.getMessage());        	
	    }		
		
		return entity;
	}
 
    /**
     * Handles deleting a Brakes entity
     * @param		command ${class.getDeleteCommandAlias()}
     */
    @DeleteMapping("/delete")    
    public void delete( @RequestBody(required=true) CloseBrakeCommand command ) {                
    	try {
        	BrakesBusinessDelegate delegate = BrakesBusinessDelegate.getBrakesInstance();

        	delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Brakes with key " + command.getBrakesId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }

	}        
	
    /**
     * Handles loading a Brakes using a UUID
     * @param		UUID uuid 
     * @return		Brakes
     */    
    @GetMapping("/load")
    public Brakes load( @RequestParam(required=true) UUID uuid ) {    	
    	Brakes entity = null;

    	try {  
    		entity = BrakesBusinessDelegate.getBrakesInstance().getBrakes( new BrakesFetchOneSummary( uuid ) );   
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Brakes using Id " + uuid );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Brakes business objects
     * @return		Set<Brakes>
     */
    @GetMapping("/")
    public List<Brakes> loadAll() {                
    	List<Brakes> brakesList = null;
        
    	try {
            // load the Brakes
            brakesList = BrakesBusinessDelegate.getBrakesInstance().getAllBrakes();
            
            if ( brakesList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Brakess" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Brakess ", exc );
        	return null;
        }

        return brakesList;
                            
    }





//************************************************************************    
// Attributes
//************************************************************************
    protected Brakes brakes = null;
    private static final Logger LOGGER = Logger.getLogger(BrakesRestController.class.getName());
    
}
