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
 * Implements Spring Controller query CQRS processing for entity Body.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Body")
public class BodyQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Body using a UUID
     * @param		UUID bodyId
     * @return		Body
     */    
    @GetMapping("/load")
    public Body load( @RequestParam(required=true) UUID bodyId ) {
    	Body entity = null;

    	try {  
    		entity = BodyBusinessDelegate.getBodyInstance().getBody( new BodyFetchOneSummary( bodyId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Body using Id " + bodyId );
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
    private static final Logger LOGGER = Logger.getLogger(BodyQueryRestController.class.getName());
    
}
