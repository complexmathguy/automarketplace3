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
 * Subscriber for Interior related events.  .
 * 
 * @author your_name_here
 *
 */
@Component("interior-subscriber")
public class InteriorSubscriber extends BaseSubscriber {

	public InteriorSubscriber() {
		queryGateway = applicationContext.getBean(QueryGateway.class);
	}
	
    public SubscriptionQueryResult<List<Interior>, Interior> interiorSubscribe() {
        return queryGateway
                .subscriptionQuery(new FindAllInteriorQuery(), 
                		ResponseTypes.multipleInstancesOf(Interior.class),
                		ResponseTypes.instanceOf(Interior.class));
    }

    public SubscriptionQueryResult<Interior, Interior> interiorSubscribe(@DestinationVariable UUID interiorId) {
        return queryGateway
                .subscriptionQuery(new FindInteriorQuery(new LoadInteriorFilter(interiorId)), 
                		ResponseTypes.instanceOf(Interior.class),
                		ResponseTypes.instanceOf(Interior.class));
    }




    // -------------------------------------------------
    // attributes
    // -------------------------------------------------
	@Autowired
    private final QueryGateway queryGateway;
}

