package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class Adjustments (

   @JsonProperty("origins_of_ingredients") var originsOfIngredients : OriginsOfIngredients?,
   @JsonProperty("packaging") var packaging : Packaging??,
   @JsonProperty("production_system") var productionSystem : ProductionSystem?,
  // @JsonProperty("threatened_species") var threatenedSpecies : ThreatenedSpecies

)