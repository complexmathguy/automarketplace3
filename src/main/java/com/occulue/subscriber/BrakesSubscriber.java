/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.subscriber;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.axonframework.messaging.responsetypes.ResponseTypes;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.stereotype.Component;


import com.occulue.api.*;
import com.occulue.entity.*;
import com.occulue.exception.*;

/**
 * Subscriber for Brakes related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("brakes-subscriber")
public class BrakesSubscriber extends BaseSubscriber {

	public BrakesSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Brakes>, Brakes> brakesSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllBrakesQuery(), 
                		ResponseTypes.multipleInstancesOf(Brakes.class),
                		ResponseTypes.instanceOf(Brakes.class));
    }

    public SubscriptionQueryResult<Brakes, Brakes> brakesSubscribe(@DestinationVariable UUID brakesId) {
        return queryGateway
                .subscriptionQuery(new FindBrakesQuery(new LoadBrakesFilter(brakesId)), 
                		ResponseTypes.instanceOf(Brakes.class),
                		ResponseTypes.instanceOf(Brakes.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

