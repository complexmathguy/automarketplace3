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
 * Implements Spring Controller query CQRS processing for entity Engine.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Engine")
public class EngineQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Engine using a UUID
     * @param		UUID engineId
     * @return		Engine
     */    
    @GetMapping("/load")
    public Engine load( @RequestParam(required=true) UUID engineId ) {
    	Engine entity = null;

    	try {  
    		entity = EngineBusinessDelegate.getEngineInstance().getEngine( new EngineFetchOneSummary( engineId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Engine using Id " + engineId );
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
    private static final Logger LOGGER = Logger.getLogger(EngineQueryRestController.class.getName());
    
}
