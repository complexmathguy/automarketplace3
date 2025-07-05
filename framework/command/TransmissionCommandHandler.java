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
 * Command handler for Transmission as outlined for the CQRS pattern.  All creates, updates, deletes and other writes 
 * related to Transmission are handled here as external events, and delegated to an associated Aggregate.
 * 
 * @author your_name_here
 *
 */
@Component
class TransmissionCommandHandler {

    public TransmissionCommandHandler( Repository<TransmissionAggregate> repository ) {
    	super();
    	this.aggregateRepository = repository;	
    }
    
    @CommandHandler
    public void handle(CreateTransmissionCommand command) throws Exception {
    	
    	// --------------------------------------
    	// validate the create command
    	// --------------------------------------    	
		TransmissionValidator.getInstance().validate( command );
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// -------------------------------------- 
    	aggregateRepository.newInstance(() -> new TransmissionAggregate(command));
    }


    @CommandHandler
    public void handle(UpdateTransmissionCommand command) throws Exception {
    	// --------------------------------------
    	// validate the update command
    	// --------------------------------------    	
		TransmissionValidator.getInstance().validate( command );

    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new TransmissionAggregate(command));
    }

    
    @CommandHandler
    public void handle(DeleteTransmissionCommand command) throws Exception {
    	// --------------------------------------
    	// validate the delete command
    	// --------------------------------------    	
		TransmissionValidator.getInstance().validate( command );    
		
    	// --------------------------------------
    	// delegate to the aggregate
    	// --------------------------------------
    	aggregateRepository.newInstance(() -> new TransmissionAggregate(command));
    }
    
    // attributes
    private final Repository<TransmissionAggregate> aggregateRepository;
}