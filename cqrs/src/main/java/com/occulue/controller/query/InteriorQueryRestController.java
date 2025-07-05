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
 * Implements Spring Controller query CQRS processing for entity Interior.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Interior")
public class InteriorQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Interior using a UUID
     * @param		UUID interiorId
     * @return		Interior
     */    
    @GetMapping("/load")
    public Interior load( @RequestParam(required=true) UUID interiorId ) {
    	Interior entity = null;

    	try {  
    		entity = InteriorBusinessDelegate.getInteriorInstance().getInterior( new InteriorFetchOneSummary( interiorId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Interior using Id " + interiorId );
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
    private static final Logger LOGGER = Logger.getLogger(InteriorQueryRestController.class.getName());
    
}
