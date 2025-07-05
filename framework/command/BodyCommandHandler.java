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
 * Command handler for Body as outlined for the CQRS pattern.  All creates, updates, deletes and other writes 
 * related to Body are handled here as external events, and delegated to an associated Aggregate.
 * 
 * @author your_name_here
 *
 */
@Component
class BodyCommandHandler {

    public BodyCommandHandler( Repository<BodyAggregate> repository ) {
    	super();
    	this.aggregateRepository = repository;	
    }
    
    @CommandHandler
    public void handle(CreateBodyCommand command) throws Exception {
    	
    	// --------------------------------------
    	// validate the create command
    	// --------------------------------------    	
		BodyValidator.getInstance().validate( command );
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// -------------------------------------- 
    	aggregateRepository.newInstance(() -> new BodyAggregate(command));
    }


    @CommandHandler
    public void handle(UpdateBodyCommand command) throws Exception {
    	// --------------------------------------
    	// validate the update command
    	// --------------------------------------    	
		BodyValidator.getInstance().validate( command );

    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new BodyAggregate(command));
    }

    
    @CommandHandler
    public void handle(DeleteBodyCommand command) throws Exception {
    	// --------------------------------------
    	// validate the delete command
    	// --------------------------------------    	
		BodyValidator.getInstance().validate( command );    
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new BodyAggregate(command));
    }
    
    // attributes
    private final Repository<BodyAggregate> aggregateRepository;
}