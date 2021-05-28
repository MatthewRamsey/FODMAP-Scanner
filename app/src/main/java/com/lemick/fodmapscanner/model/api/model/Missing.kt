package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Missing (

   @JsonProperty("agb_category") var agbCategory : Int?,
   @JsonProperty("labels") var labels : Int?,
   @JsonProperty("packagings") var packagings : Int?

)