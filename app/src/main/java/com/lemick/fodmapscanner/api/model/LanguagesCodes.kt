package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class LanguagesCodes (

   @JsonProperty("en") var en : Int?

)