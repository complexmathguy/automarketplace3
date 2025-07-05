/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

	
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import com.thoughtworks.xstream.XStream;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {
		 
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
 
    @Override
    @Bean
    public MongoClient mongoClient() {
    	return MongoClients.create(MongoClientSettings.builder()
                .uuidRepresentation(org.bson.UuidRepresentation.JAVA_LEGACY)
                .applyConnectionString(new ConnectionString(connectionUrl))
                .applicationName( appName )
                .build());
    	
    }    
		
	
	// ------------------------------------------
    // attributes
    // ------------------------------------------
    
	@Value("${mongodb.connection.url}")
	public String connectionUrl	= null;
	@Value("${spring.application.name}")
	public String appName			= null;
	@Value("${database.name}")
	public String databaseName		= null;
	@Value("${mongodb.sagas.collection.name}")
	public String sagasCollectionName;
	@Value("${mongodb.snapshot.events.collection.name}")
	public String snapshotEventsCollectionName;
	@Value("${mongodb.domain.events.collection.name}")
	public String domainEventsCollectionName;

}
