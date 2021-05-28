package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class EcoscoreData (

   @JsonProperty("adjustments") var adjustments : Adjustments?,
   @JsonProperty("agribalyse") var agribalyse : Agribalyse?,
   @JsonProperty("missing") var missing : Missing?,
   @JsonProperty("missing_agribalyse_match_warning") var missingAgribalyseMatchWarning : Int?,
   @JsonProperty("status") var status : String?

)