package com.lemick.fodmapscanner.model.api.model

import com.fasterxml.jackson.annotation.JsonProperty


data class Front (

    @JsonProperty("display") var display : Display?,
    @JsonProperty("small") var small : Small?,
    @JsonProperty("thumb") var thumb : Thumb?

)