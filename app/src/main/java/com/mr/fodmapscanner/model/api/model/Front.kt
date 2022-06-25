package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class Front (

    @JsonProperty("display") var display : Display?,
    @JsonProperty("small") var small : Small?,
    @JsonProperty("thumb") var thumb : Thumb?

) : Parcelable