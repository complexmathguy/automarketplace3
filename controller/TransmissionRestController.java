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
 * Implements Struts action processing for business entity Transmission.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Transmission")
public class TransmissionRestController extends BaseSpringRestController {

    /**
     * Handles create a Transmission.  if not key provided, calls create, otherwise calls save
     * @param		Transmission	transmission
     * @return		Transmission 
     */
	@PostMapping("/create")
    public Transmission create( @RequestBody(required=true) CreateTransmissionCommand command ) {
    	Transmission entity = null;
		try {       
        	
			entity = TransmissionBusinessDelegate.getTransmissionInstance().createTransmission( command );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage(), exc );        	
        }
		
		return entity;
    }

    /**
     * Handles updating a Transmission.  if no key provided, calls create, otherwise calls save
     * @param		Transmission transmission
     * @return		Transmission
     */
	@PutMapping("/update")
    public Transmission update( @RequestBody(required=true) RefreshTransmissionCommand command ) {
		Transmission entity = null;
		try {                        	        
			// -----------------------------------------------
			// delegate the RefreshTransmissionCommand
			// -----------------------------------------------
			entity = TransmissionBusinessDelegate.getTransmissionInstance().updateTransmission(command);;
	    }
	    catch( Throwable exc ) {
	    	LOGGER.log( Level.WARNING, "TransmissionController:update() - successfully update Transmission - " + exc.getMessage());        	
	    }		
		
		return entity;
	}
 
    /**
     * Handles deleting a Transmission entity
     * @param		command ${class.getDeleteCommandAlias()}
     */
    @DeleteMapping("/delete")    
    public void delete( @RequestBody(required=true) CloseTransmissionCommand command ) {                
    	try {
        	TransmissionBusinessDelegate delegate = TransmissionBusinessDelegate.getTransmissionInstance();

        	delegate.delete( command );
    		LOGGER.log( Level.WARNING, "Successfully deleted Transmission with key " + command.getTransmissionId() );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING, exc.getMessage() );
        }

	}        
	
    /**
     * Handles loading a Transmission using a UUID
     * @param		UUID uuid 
     * @return		Transmission
     */    
    @GetMapping("/load")
    public Transmission load( @RequestParam(required=true) UUID uuid ) {    	
    	Transmission entity = null;

    	try {  
    		entity = TransmissionBusinessDelegate.getTransmissionInstance().getTransmission( new TransmissionFetchOneSummary( uuid ) );   
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Transmission using Id " + uuid );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Transmission business objects
     * @return		Set<Transmission>
     */
    @GetMapping("/")
    public List<Transmission> loadAll() {                
    	List<Transmission> transmissionList = null;
        
    	try {
            // load the Transmission
            transmissionList = TransmissionBusinessDelegate.getTransmissionInstance().getAllTransmission();
            
            if ( transmissionList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Transmissions" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Transmissions ", exc );
        	return null;
        }

        return transmissionList;
                            
    }





//************************************************************************    
// Attributes
//************************************************************************
    protected Transmission transmission = null;
    private static final Logger LOGGER = Logger.getLogger(TransmissionRestController.class.getName());
    
}
