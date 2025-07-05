package com.occulue.aggregate;

import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.context.annotation.Profile;

/**
 * Aggregate handler for Body as outlined for the CQRS pattern, all write responsibilities 
 * related to Body are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class BodyAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public BodyAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public BodyAggregate(CreateBodyCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateBodyCommand" );
    	CreateBodyEvent event = new CreateBodyEvent(command.getBodyId(), command.getName(), command.getPlant());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(RefreshBodyCommand command) throws Exception {
    	LOGGER.info( "handling command RefreshBodyCommand" );
    	RefreshedBodyEvent event = new RefreshedBodyEvent(command.getBodyId(), command.getName(), command.getPlant());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(CloseBodyCommand command) throws Exception {
    	LOGGER.info( "Handling command CloseBodyCommand" );
        apply(new ClosedBodyEvent(command.getBodyId()));
    }

	// ----------------------------------------------
	// association command handlers
	// ----------------------------------------------

    // single association commands

    // multiple association commands

	// ----------------------------------------------
	// intrinsic event source handlers
	// ----------------------------------------------
    @EventSourcingHandler
    void on(CreateBodyEvent event) {	
    	LOGGER.info( "Event sourcing CreateBodyEvent" );
    	this.bodyId = event.getBodyId();
        this.name = event.getName();
        this.plant = event.getPlant();
    }
    
    @EventSourcingHandler
    void on(RefreshedBodyEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.name = event.getName();
        this.plant = event.getPlant();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID bodyId;
    
    private String name;
    private Plant plant;

    private static final Logger LOGGER 	= Logger.getLogger(BodyAggregate.class.getName());
}
