package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty


data class OrgDatabaseUsda(

    @JsonProperty("available_date") var availableDate: String?,
    @JsonProperty("fdc_category") var fdcCategory: String?,
    @JsonProperty("fdc_data_source") var fdcDataSource: String?,
    @JsonProperty("fdc_id") var fdcId: String?,
    @JsonProperty("modified_date") var modifiedDate: String?,
    @JsonProperty("publication_date") var publicationDate: String = ""

)