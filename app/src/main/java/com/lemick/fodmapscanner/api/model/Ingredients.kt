package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Ingredients (

   @JsonProperty("display") var display : Display?,
   @JsonProperty("small") var small : Small?,
   @JsonProperty("thumb") var thumb : Thumb?

)