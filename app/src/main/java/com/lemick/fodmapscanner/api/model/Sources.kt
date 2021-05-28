package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Sources (

   @JsonProperty("fields") var fields : List<String>?,
   @JsonProperty("id") var id : String?,
   @JsonProperty("images") var images : List<String>?,
   @JsonProperty("import_t") var importT : Int?,
   @JsonProperty("url") var url : String?

)