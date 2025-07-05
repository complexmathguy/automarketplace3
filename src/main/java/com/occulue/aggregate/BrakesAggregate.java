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
 * Aggregate handler for Brakes as outlined for the CQRS pattern, all write responsibilities 
 * related to Brakes are handled here.
 * 
 * @author your_name_here
 * 
 */
@Aggregate
public class BrakesAggregate {  

	// -----------------------------------------
	// Axon requires an empty constructor
    // -----------------------------------------
    public BrakesAggregate() {
    }

	// ----------------------------------------------
	// intrinsic command handlers
	// ----------------------------------------------
    @CommandHandler
    public BrakesAggregate(CreateBrakeCommand command) throws Exception {
    	LOGGER.info( "Handling command CreateBrakeCommand" );
    	CreateBrakeEvent event = new CreateBrakeEvent(command.getBrakesId(), command.getSerialNum(), command.getName(), command.getPlant(), command.getType());
    	
        apply(event);
    }

    @CommandHandler
    public void handle(RefreshBrakeCommand command) throws Exception {
    	LOGGER.info( "handling command RefreshBrakeCommand" );
    	RefreshedBrakeEvent event = new RefreshedBrakeEvent(command.getBrakesId(), command.getSerialNum(), command.getName(), command.getPlant(), command.getType());        
    	
        apply(event);
    }

    @CommandHandler
    public void handle(CloseBrakeCommand command) throws Exception {
    	LOGGER.info( "Handling command CloseBrakeCommand" );
        apply(new ClosedBrakeEvent(command.getBrakesId()));
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
    void on(CreateBrakeEvent event) {	
    	LOGGER.info( "Event sourcing CreateBrakeEvent" );
    	this.brakesId = event.getBrakesId();
        this.serialNum = event.getSerialNum();
        this.name = event.getName();
        this.plant = event.getPlant();
        this.type = event.getType();
    }
    
    @EventSourcingHandler
    void on(RefreshedBrakeEvent event) {
    	LOGGER.info( "Event sourcing classObject.getUpdateEventAlias()}" );
        this.serialNum = event.getSerialNum();
        this.name = event.getName();
        this.plant = event.getPlant();
        this.type = event.getType();
    }   

	// ----------------------------------------------
	// association event source handlers
	// ----------------------------------------------


    // ------------------------------------------
    // attributes
    // ------------------------------------------
	
    @AggregateIdentifier
    private UUID brakesId;
    
    private String serialNum;
    private String name;
    private Plant plant;
    private BrakeType type;

    private static final Logger LOGGER 	= Logger.getLogger(BrakesAggregate.class.getName());
}
