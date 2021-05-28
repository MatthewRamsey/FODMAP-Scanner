package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Languages (

   @JsonProperty("en:english") var enEnglish : Int?

)