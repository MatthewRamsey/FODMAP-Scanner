package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class NutrientLevels (

   @JsonProperty("fat") var fat : String?,
   @JsonProperty("salt") var salt : String?,
   @JsonProperty("saturated-fat") var saturatedFat : String?,
   @JsonProperty("sugars") var sugars : String?

)