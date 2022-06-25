package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class EcoscoreData (

    @JsonProperty("adjustments") var adjustments : Adjustments?,
    @JsonProperty("agribalyse") var agribalyse : Agribalyse?,
    @JsonProperty("missing") var missing : Missing?,
    @JsonProperty("missing_agribalyse_match_warning") var missingAgribalyseMatchWarning : Int?,
    @JsonProperty("status") var status : String?

) : Parcelable