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
 * Command handler for Interior as outlined for the CQRS pattern.  All creates, updates, deletes and other writes 
 * related to Interior are handled here as external events, and delegated to an associated Aggregate.
 * 
 * @author your_name_here
 *
 */
@Component
class InteriorCommandHandler {

    public InteriorCommandHandler( Repository<InteriorAggregate> repository ) {
    	super();
    	this.aggregateRepository = repository;	
    }
    
    @CommandHandler
    public void handle(CreateInteriorCommand command) throws Exception {
    	
    	// --------------------------------------
    	// validate the create command
    	// --------------------------------------    	
		InteriorValidator.getInstance().validate( command );
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// -------------------------------------- 
    	aggregateRepository.newInstance(() -> new InteriorAggregate(command));
    }


    @CommandHandler
    public void handle(UpdateInteriorCommand command) throws Exception {
    	// --------------------------------------
    	// validate the update command
    	// --------------------------------------    	
		InteriorValidator.getInstance().validate( command );

    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new InteriorAggregate(command));
    }

    
    @CommandHandler
    public void handle(DeleteInteriorCommand command) throws Exception {
    	// --------------------------------------
    	// validate the delete command
    	// --------------------------------------    	
		InteriorValidator.getInstance().validate( command );    
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new InteriorAggregate(command));
    }
    
    // attributes
    private final Repository<InteriorAggregate> aggregateRepository;
}