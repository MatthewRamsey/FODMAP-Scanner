package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class OrgDatabaseUsda(

    @JsonProperty("available_date") var availableDate: String?,
    @JsonProperty("fdc_category") var fdcCategory: String?,
    @JsonProperty("fdc_data_source") var fdcDataSource: String?,
    @JsonProperty("fdc_id") var fdcId: String?,
    @JsonProperty("modified_date") var modifiedDate: String?,
    @JsonProperty("publication_date") var publicationDate: String = ""

) : Parcelable