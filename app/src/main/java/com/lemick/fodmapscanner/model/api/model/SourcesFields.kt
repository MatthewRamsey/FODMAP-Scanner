package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty


data class SourcesFields (

   @JsonProperty("org-database-usda") var orgDatabaseUsda : OrgDatabaseUsda?

)