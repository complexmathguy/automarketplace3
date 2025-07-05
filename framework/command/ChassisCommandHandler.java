package com.occulue.command;

import java.util.*;

import org.axonframework.modelling.command.Repository;

import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import com.occulue.aggregate.*;
import com.occulue.api.*;
import com.occulue.exception.*;
import com.occulue.repository.*;
import com.occulue.validator.*;

/**
 * Command handler for Chassis as outlined for the CQRS pattern.  All creates, updates, deletes and other writes 
 * related to Chassis are handled here as external events, and delegated to an associated Aggregate.
 * 
 * @author your_name_here
 *
 */
@Component
class ChassisCommandHandler {

    public ChassisCommandHandler( Repository<ChassisAggregate> repository ) {
    	super();
    	this.aggregateRepository = repository;	
    }
    
    @CommandHandler
    public void handle(CreateChassisCommand command) throws Exception {
    	
    	// --------------------------------------
    	// validate the create command
    	// --------------------------------------    	
		ChassisValidator.getInstance().validate( command );
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// -------------------------------------- 
    	aggregateRepository.newInstance(() -> new ChassisAggregate(command));
    }


    @CommandHandler
    public void handle(UpdateChassisCommand command) throws Exception {
    	// --------------------------------------
    	// validate the update command
    	// --------------------------------------    	
		ChassisValidator.getInstance().validate( command );

    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new ChassisAggregate(command));
    }

    
    @CommandHandler
    public void handle(DeleteChassisCommand command) throws Exception {
    	// --------------------------------------
    	// validate the delete command
    	// --------------------------------------    	
		ChassisValidator.getInstance().validate( command );    
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new ChassisAggregate(command));
    }
    
    // attributes
    private final Repository<ChassisAggregate> aggregateRepository;
}