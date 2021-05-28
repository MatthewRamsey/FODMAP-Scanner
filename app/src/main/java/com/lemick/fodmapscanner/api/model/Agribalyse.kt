package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Agribalyse (

   @JsonProperty("warning") var warning : String?

)