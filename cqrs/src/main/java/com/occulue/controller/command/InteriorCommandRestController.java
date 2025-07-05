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
 * Implements Spring Controller command CQRS processing for entity Interior.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Interior")
public class InteriorCommandRestController extends BaseSpringRestController {

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
    public CompletableFuture<Void> delete( @RequestParam(required=true) UUID interiorId  ) {
    	CompletableFuture<Void> completableFuture = null;
		CloseInteriorCommand command = new CloseInteriorCommand( interiorId );

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
	



//************************************************************************    
// Attributes
//************************************************************************
    protected Interior interior = null;
    private static final Logger LOGGER = Logger.getLogger(InteriorCommandRestController.class.getName());
    
}
