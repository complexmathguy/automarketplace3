/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller.command;

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
import com.occulue.projector.*;

import com.occulue.controller.*;

/** 
 * Implements Spring Controller command CQRS processing for entity Brakes.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Brakes")
public class BrakesCommandRestController extends BaseSpringRestController {

    /**
     * Handles create a Brakes.  if not key provided, calls create, otherwise calls save
     * @param		Brakes	brakes
     * @return		CompletableFuture<UUID> 
     */
	@PostMapping("/create")
    public CompletableFuture<UUID> create( @RequestBody(required=true) CreateBrakeCommand command ) {
		CompletableFuture<UUID> completableFuture = null;
		try {       
        	
			completableFuture = BrakesBusinessDelegate.getBrakesInstance().createBrakes( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return completableFuture;
    }

    /**
     * Handles updating a Brakes.  if no key provided, calls create, otherwise calls save
     * @param		Brakes brakes
     * @return		CompletableFuture<Void>
     */
	@PutMapping("/update")
    public CompletableFuture<Void> update( @RequestBody(required=true) RefreshBrakeCommand command ) {
		CompletableFuture<Void> completableFuture = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshBrakeCommand
			// -----------------------------------------------
			completableFuture = BrakesBusinessDelegate.getBrakesInstance().updateBrakes(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "BrakesController:update() - successfully update Brakes - " + exc.getMessage());        	
	    }		
		
		return completableFuture;
	}
 
    /**
     * Handles deleting a Brakes entity
     * @param		command ${class.getDeleteCommandAlias()}
     * @return		CompletableFuture<Void>
     */
    @DeleteMapping("/delete")    
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID brakesId  ) {
    	CompletableFuture<Void> completableFuture = null;
		CloseBrakeCommand command = new CloseBrakeCommand( brakesId );

    	try {
        	BrakesBusinessDelegate delegate = BrakesBusinessDelegate.getBrakesInstance();

        	completableFuture = delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Brakes with key " + command.getBrakesId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }
        
        return completableFuture;
	}        
	



//************************************************************************    
// Attributes
//************************************************************************
    protected Brakes brakes = null;
    private static final Logger LOGGER = Logger.getLogger(BrakesCommandRestController.class.getName());
    
}
