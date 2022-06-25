package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesFields (

   @JsonProperty("org-database-usda") var orgDatabaseUsda : OrgDatabaseUsda?

) : Parcelable