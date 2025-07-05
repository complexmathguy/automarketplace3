/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.api

import org.axonframework.modelling.command.TargetAggregateIdentifier


import java.util.*
import javax.persistence.*

import com.occulue.entity.*;

//-----------------------------------------------------------
// Command definitions
//-----------------------------------------------------------

// Chassis Commands
data class CreateChassisCommand(
    @TargetAggregateIdentifier var chassisId: UUID? = null,
    var name: String? = null,
    var serialNum: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: ChassisType? = null
)

data class RefreshChassisCommand(
    @TargetAggregateIdentifier var chassisId: UUID? = null,
    var name: String? = null,
    var serialNum: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: ChassisType? = null
)

data class CloseChassisCommand(@TargetAggregateIdentifier  var chassisId: UUID? = null)

// single association commands

// multiple association commands


// Body Commands
data class CreateBodyCommand(
    @TargetAggregateIdentifier var bodyId: UUID? = null,
    var name: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null
)

data class RefreshBodyCommand(
    @TargetAggregateIdentifier var bodyId: UUID? = null,
    var name: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null
)

data class CloseBodyCommand(@TargetAggregateIdentifier  var bodyId: UUID? = null)

// single association commands

// multiple association commands


// Engine Commands
data class CreateEngineCommand(
    @TargetAggregateIdentifier var engineId: UUID? = null,
    var name: String? = null,
    var serialNum: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: EngineType? = null
)

data class RefreshEngineCommand(
    @TargetAggregateIdentifier var engineId: UUID? = null,
    var name: String? = null,
    var serialNum: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: EngineType? = null
)

data class CloseEngineCommand(@TargetAggregateIdentifier  var engineId: UUID? = null)

// single association commands

// multiple association commands


// Transmission Commands
data class CreateTransmissionCommand(
    @TargetAggregateIdentifier var transmissionId: UUID? = null,
    var name: String? = null,
    var serialNum: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: TransmissionType? = null
)

data class RefreshTransmissionCommand(
    @TargetAggregateIdentifier var transmissionId: UUID? = null,
    var name: String? = null,
    var serialNum: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: TransmissionType? = null
)

data class CloseTransmissionCommand(@TargetAggregateIdentifier  var transmissionId: UUID? = null)

// single association commands

// multiple association commands


// Brakes Commands
data class CreateBrakeCommand(
    @TargetAggregateIdentifier var brakesId: UUID? = null,
    var serialNum: String? = null,
    var name: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: BrakeType? = null
)

data class RefreshBrakeCommand(
    @TargetAggregateIdentifier var brakesId: UUID? = null,
    var serialNum: String? = null,
    var name: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null,
    @Enumerated(EnumType.STRING) var type: BrakeType? = null
)

data class CloseBrakeCommand(@TargetAggregateIdentifier  var brakesId: UUID? = null)

// single association commands

// multiple association commands


// Interior Commands
data class CreateInteriorCommand(
    @TargetAggregateIdentifier var interiorId: UUID? = null,
    var serialNum: String? = null,
    var name: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null
)

data class RefreshInteriorCommand(
    @TargetAggregateIdentifier var interiorId: UUID? = null,
    var serialNum: String? = null,
    var name: String? = null,
         @AttributeOverrides(
      AttributeOverride( name = "plantNo", column = Column(name = "plant_plantNo")),
      AttributeOverride( name = "street", column = Column(name = "plant_street")),
      AttributeOverride( name = "city", column = Column(name = "plant_city")),
      AttributeOverride( name = "state", column = Column(name = "plant_state")),
      AttributeOverride( name = "zipCode", column = Column(name = "plant_zipCode"))
    )
    var plant: Plant? = null
)

data class CloseInteriorCommand(@TargetAggregateIdentifier  var interiorId: UUID? = null)

// single association commands

// multiple association commands



