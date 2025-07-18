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
 * Subscriber for Engine related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("engine-subscriber")
public class EngineSubscriber extends BaseSubscriber {

	public EngineSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Engine>, Engine> engineSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllEngineQuery(), 
                		ResponseTypes.multipleInstancesOf(Engine.class),
                		ResponseTypes.instanceOf(Engine.class));
    }

    public SubscriptionQueryResult<Engine, Engine> engineSubscribe(@DestinationVariable UUID engineId) {
        return queryGateway
                .subscriptionQuery(new FindEngineQuery(new LoadEngineFilter(engineId)), 
                		ResponseTypes.instanceOf(Engine.class),
                		ResponseTypes.instanceOf(Engine.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

