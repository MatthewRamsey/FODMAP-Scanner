package com.lemick.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class Adjustments (

    @JsonProperty("origins_of_ingredients") var originsOfIngredients : OriginsOfIngredients?,
    @JsonProperty("packaging") var packaging : Packaging??,
    @JsonProperty("production_system") var productionSystem : ProductionSystem?,
  // @JsonProperty("threatened_species") var threatenedSpecies : ThreatenedSpecies

) : Parcelable