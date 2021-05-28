package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class SourcesFields (

   @JsonProperty("org-database-usda") var orgDatabaseUsda : OrgDatabaseUsda?

)