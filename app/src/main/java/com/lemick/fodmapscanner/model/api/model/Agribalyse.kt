package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Agribalyse (

   @JsonProperty("warning") var warning : String?

)