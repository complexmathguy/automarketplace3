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

import org.axonframework.eventsourcing.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class SnapshotConfig {

	// --------------------------------------------------------
	// define a snapshot trigger for each aggregate,
	// as implicitly defined per class and explicitly defined in the model
	// --------------------------------------------------------
	@Bean
    public SnapshotTriggerDefinition chassisAggregateSnapshotTriggerDefinition(
        Snapshotter snapshotter,
        @Value("${axon.aggregate.chassis.snapshot-threshold:10}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

	@Bean
    public SnapshotTriggerDefinition bodyAggregateSnapshotTriggerDefinition(
        Snapshotter snapshotter,
        @Value("${axon.aggregate.body.snapshot-threshold:10}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

	@Bean
    public SnapshotTriggerDefinition engineAggregateSnapshotTriggerDefinition(
        Snapshotter snapshotter,
        @Value("${axon.aggregate.engine.snapshot-threshold:10}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

	@Bean
    public SnapshotTriggerDefinition transmissionAggregateSnapshotTriggerDefinition(
        Snapshotter snapshotter,
        @Value("${axon.aggregate.transmission.snapshot-threshold:10}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

	@Bean
    public SnapshotTriggerDefinition brakesAggregateSnapshotTriggerDefinition(
        Snapshotter snapshotter,
        @Value("${axon.aggregate.brakes.snapshot-threshold:10}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

	@Bean
    public SnapshotTriggerDefinition interiorAggregateSnapshotTriggerDefinition(
        Snapshotter snapshotter,
        @Value("${axon.aggregate.interior.snapshot-threshold:10}") int threshold) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, threshold);
    }

	
}