package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Languages (

   @JsonProperty("en:english") var enEnglish : Int?

)