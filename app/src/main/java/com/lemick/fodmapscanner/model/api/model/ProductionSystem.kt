package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class ProductionSystem (

   @JsonProperty("warning") var warning : String?

)