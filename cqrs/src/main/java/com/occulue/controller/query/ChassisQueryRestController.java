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
 * Implements Spring Controller query CQRS processing for entity Chassis.
 *
 * @author your_name_here
 */
@CrossOrigin
@RestController
@RequestMapping("/Chassis")
public class ChassisQueryRestController extends BaseSpringRestController {

	
    /**
     * Handles loading a Chassis using a UUID
     * @param		UUID chassisId
     * @return		Chassis
     */    
    @GetMapping("/load")
    public Chassis load( @RequestParam(required=true) UUID chassisId ) {
    	Chassis entity = null;

    	try {  
    		entity = ChassisBusinessDelegate.getChassisInstance().getChassis( new ChassisFetchOneSummary( chassisId ) );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING, "failed to load Chassis using Id " + chassisId );
            return null;
        }

        return entity;
    }

    /**
     * Handles loading all Chassis business objects
     * @return		Set<Chassis>
     */
    @GetMapping("/")
    public List<Chassis> loadAll() {                
    	List<Chassis> chassisList = null;
        
    	try {
            // load the Chassis
            chassisList = ChassisBusinessDelegate.getChassisInstance().getAllChassis();
            
            if ( chassisList != null )
                LOGGER.log( Level.INFO,  "successfully loaded all Chassiss" );
        }
        catch( Throwable exc ) {
            LOGGER.log( Level.WARNING,  "failed to load all Chassiss ", exc );
        	return null;
        }

        return chassisList;
                            
    }

    /**
     * finder method to findByNameLike
     * @param 		String name
     * @return		Chassis
     */     
	@PostMapping("/findByNameLike")
	public Chassis findByNameLike( @RequestBody(required=true) FindByNameLikeQuery query ) {
		Chassis result = null;
        try {  
            // call the delegate directly
        	result = new ChassisBusinessDelegate().findByNameLike(query);
            
            if ( result != null )
                LOGGER.log( Level.WARNING,  "successfully executed findByNameLike" );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING,  "failed to execute findByNameLike" );
        }
        return result;
	}
    /**
     * finder method to findBySerialNum
     * @param 		String serialNum
     * @return		Chassis
     */     
	@PostMapping("/findBySerialNum")
	public Chassis findBySerialNum( @RequestBody(required=true) FindBySerialNumQuery query ) {
		Chassis result = null;
        try {  
            // call the delegate directly
        	result = new ChassisBusinessDelegate().findBySerialNum(query);
            
            if ( result != null )
                LOGGER.log( Level.WARNING,  "successfully executed findBySerialNum" );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING,  "failed to execute findBySerialNum" );
        }
        return result;
	}
    /**
     * finder method to findByType
     * @param 		ChassisType type
     * @return		List<Chassis>
     */     
	@PostMapping("/findByType")
	public List<Chassis> findByType( @RequestBody(required=true) FindByTypeQuery query ) {
		List<Chassis> result = null;
        try {  
            // call the delegate directly
        	result = new ChassisBusinessDelegate().findByType(query);
            
            if ( result != null )
                LOGGER.log( Level.WARNING,  "successfully executed findByType" );
        }
        catch( Throwable exc ) {
        	LOGGER.log( Level.WARNING,  "failed to execute findByType" );
        }
        return result;
	}


//************************************************************************    
// Attributes
//************************************************************************
    protected Chassis chassis = null;
    private static final Logger LOGGER = Logger.getLogger(ChassisQueryRestController.class.getName());
    
}
