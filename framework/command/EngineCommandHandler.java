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
 * Command handler for Engine as outlined for the CQRS pattern.  All creates, updates, deletes and other writes 
 * related to Engine are handled here as external events, and delegated to an associated Aggregate.
 * 
 * @author your_name_here
 *
 */
@Component
class EngineCommandHandler {

    public EngineCommandHandler( Repository<EngineAggregate> repository ) {
    	super();
    	this.aggregateRepository = repository;	
    }
    
    @CommandHandler
    public void handle(CreateEngineCommand command) throws Exception {
    	
    	// --------------------------------------
    	// validate the create command
    	// --------------------------------------    	
		EngineValidator.getInstance().validate( command );
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// -------------------------------------- 
    	aggregateRepository.newInstance(() -> new EngineAggregate(command));
    }


    @CommandHandler
    public void handle(UpdateEngineCommand command) throws Exception {
    	// --------------------------------------
    	// validate the update command
    	// --------------------------------------    	
		EngineValidator.getInstance().validate( command );

    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new EngineAggregate(command));
    }

    
    @CommandHandler
    public void handle(DeleteEngineCommand command) throws Exception {
    	// --------------------------------------
    	// validate the delete command
    	// --------------------------------------    	
		EngineValidator.getInstance().validate( command );    
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new EngineAggregate(command));
    }
    
    // attributes
    private final Repository<EngineAggregate> aggregateRepository;
}