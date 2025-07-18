/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.api;

import java.util.*;

import javax.persistence.Entity
import javax.persistence.Id

//-----------------------------------------------------------
// Query definitions
//-----------------------------------------------------------

// -----------------------------------------
// Chassis Queries 
// -----------------------------------------

data class LoadChassisFilter(val chassisId :  UUID? = null )

class FindChassisQuery(val filter: LoadChassisFilter = LoadChassisFilter()) {
    override fun toString(): String = "LoadChassisQuery"
}

class FindAllChassisQuery() {
    override fun toString(): String = "LoadAllChassisQuery"
}

data class ChassisFetchOneSummary(@Id var chassisId : UUID? = null) {
}



data class FindByNameLikeFilter(val name: String? = null)
class FindByNameLikeQuery(val filter: FindByNameLikeFilter = FindByNameLikeFilter())

data class FindBySerialNumFilter(val serialNum: String? = null)
class FindBySerialNumQuery(val filter: FindBySerialNumFilter = FindBySerialNumFilter())

data class FindByTypeFilter(val type: ChassisType? = null)
class FindByTypeQuery(val offset: Int? = 1, val limit: Int? = 100, val filter: FindByTypeFilter = FindByTypeFilter() )



// -----------------------------------------
// Body Queries 
// -----------------------------------------

data class LoadBodyFilter(val bodyId :  UUID? = null )

class FindBodyQuery(val filter: LoadBodyFilter = LoadBodyFilter()) {
    override fun toString(): String = "LoadBodyQuery"
}

class FindAllBodyQuery() {
    override fun toString(): String = "LoadAllBodyQuery"
}

data class BodyFetchOneSummary(@Id var bodyId : UUID? = null) {
}





// -----------------------------------------
// Engine Queries 
// -----------------------------------------

data class LoadEngineFilter(val engineId :  UUID? = null )

class FindEngineQuery(val filter: LoadEngineFilter = LoadEngineFilter()) {
    override fun toString(): String = "LoadEngineQuery"
}

class FindAllEngineQuery() {
    override fun toString(): String = "LoadAllEngineQuery"
}

data class EngineFetchOneSummary(@Id var engineId : UUID? = null) {
}





// -----------------------------------------
// Transmission Queries 
// -----------------------------------------

data class LoadTransmissionFilter(val transmissionId :  UUID? = null )

class FindTransmissionQuery(val filter: LoadTransmissionFilter = LoadTransmissionFilter()) {
    override fun toString(): String = "LoadTransmissionQuery"
}

class FindAllTransmissionQuery() {
    override fun toString(): String = "LoadAllTransmissionQuery"
}

data class TransmissionFetchOneSummary(@Id var transmissionId : UUID? = null) {
}





// -----------------------------------------
// Brakes Queries 
// -----------------------------------------

data class LoadBrakesFilter(val brakesId :  UUID? = null )

class FindBrakesQuery(val filter: LoadBrakesFilter = LoadBrakesFilter()) {
    override fun toString(): String = "LoadBrakesQuery"
}

class FindAllBrakesQuery() {
    override fun toString(): String = "LoadAllBrakesQuery"
}

data class BrakesFetchOneSummary(@Id var brakesId : UUID? = null) {
}





// -----------------------------------------
// Interior Queries 
// -----------------------------------------

data class LoadInteriorFilter(val interiorId :  UUID? = null )

class FindInteriorQuery(val filter: LoadInteriorFilter = LoadInteriorFilter()) {
    override fun toString(): String = "LoadInteriorQuery"
}

class FindAllInteriorQuery() {
    override fun toString(): String = "LoadAllInteriorQuery"
}

data class InteriorFetchOneSummary(@Id var interiorId : UUID? = null) {
}






