package com.lemick.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginsOfIngredients (

    @JsonProperty("aggregated_origins") var aggregatedOrigins : List<AggregatedOrigins>?,
    @JsonProperty("epi_score") var epiScore : Int?,
    @JsonProperty("epi_value") var epiValue : Int?,
    @JsonProperty("origins_from_origins_field") var originsFromOriginsField : List<String>?,
    @JsonProperty("transportation_score_be") var transportationScoreBe : Int?,
    @JsonProperty("transportation_score_ch") var transportationScoreCh : Int?,
    @JsonProperty("transportation_score_de") var transportationScoreDe : Int?,
    @JsonProperty("transportation_score_es") var transportationScoreEs : Int?,
    @JsonProperty("transportation_score_fr") var transportationScoreFr : Int?,
    @JsonProperty("transportation_score_ie") var transportationScoreIe : Int?,
    @JsonProperty("transportation_score_it") var transportationScoreIt : Int?,
    @JsonProperty("transportation_score_lu") var transportationScoreLu : Int?,
    @JsonProperty("transportation_score_nl") var transportationScoreNl : Int?,
    @JsonProperty("transportation_value_be") var transportationValueBe : Int?,
    @JsonProperty("transportation_value_ch") var transportationValueCh : Int?,
    @JsonProperty("transportation_value_de") var transportationValueDe : Int?,
    @JsonProperty("transportation_value_es") var transportationValueEs : Int?,
    @JsonProperty("transportation_value_fr") var transportationValueFr : Int?,
    @JsonProperty("transportation_value_ie") var transportationValueIe : Int?,
    @JsonProperty("transportation_value_it") var transportationValueIt : Int?,
    @JsonProperty("transportation_value_lu") var transportationValueLu : Int?,
    @JsonProperty("transportation_value_nl") var transportationValueNl : Int?,
    @JsonProperty("value_be") var valueBe : Int?,
    @JsonProperty("value_ch") var valueCh : Int?,
    @JsonProperty("value_de") var valueDe : Int?,
    @JsonProperty("value_es") var valueEs : Int?,
    @JsonProperty("value_fr") var valueFr : Int?,
    @JsonProperty("value_ie") var valueIe : Int?,
    @JsonProperty("value_it") var valueIt : Int?,
    @JsonProperty("value_lu") var valueLu : Int?,
    @JsonProperty("value_nl") var valueNl : Int?

) : Parcelable