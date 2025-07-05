/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.controller.query;

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
 * Implements Spring Controller query CQRS processing for entity Brakes.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Brakes")
public class BrakesQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Brakes using a UUID
     * @param		UUID brakesId
     * @return		Brakes
     */    
    @GetMapping("/load")
    public Brakes load( @RequestParam(required=true) UUID brakesId ) {
    	Brakes entity = null;

    	try {  
    		entity = BrakesBusinessDelegate.getBrakesInstance().getBrakes( new BrakesFetchOneSummary( brakesId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Brakes using Id " + brakesId );
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
    private static final Logger LOGGER = Logger.getLogger(BrakesQueryRestController.class.getName());
    
}
