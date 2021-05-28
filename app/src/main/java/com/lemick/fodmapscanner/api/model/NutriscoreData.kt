package com.lemick.example

import com.fasterxml.jackson.annotation.JsonProperty

   
data class NutriscoreData (

   @JsonProperty("energy") var energy : Int?,
   @JsonProperty("energy_points") var energyPoints : Int?,
   @JsonProperty("energy_value") var energyValue : Int?,
   @JsonProperty("fiber") var fiber : Double?,
   @JsonProperty("fiber_points") var fiberPoints : Int?,
   @JsonProperty("fiber_value") var fiberValue : Double?,
   @JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils") var fruitsVegetablesNutsColzaWalnutOliveOils : Int?,
   @JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils_points") var fruitsVegetablesNutsColzaWalnutOliveOilsPoints : Int?,
   @JsonProperty("fruits_vegetables_nuts_colza_walnut_olive_oils_value") var fruitsVegetablesNutsColzaWalnutOliveOilsValue : Int?,
   @JsonProperty("grade") var grade : String?,
   @JsonProperty("is_beverage") var isBeverage : Int?,
   @JsonProperty("is_cheese") var isCheese : Int?,
   @JsonProperty("is_fat") var isFat : Int?,
   @JsonProperty("is_water") var isWater : Int?,
   @JsonProperty("negative_points") var negativePoints : Int?,
   @JsonProperty("positive_points") var positivePoints : Int?,
   @JsonProperty("proteins") var proteins : Double?,
   @JsonProperty("proteins_points") var proteinsPoints : Int?,
   @JsonProperty("proteins_value") var proteinsValue : Double?,
   @JsonProperty("saturated_fat") var saturatedFat : Double?,
   @JsonProperty("saturated_fat_points") var saturatedFatPoints : Int?,
   @JsonProperty("saturated_fat_ratio") var saturatedFatRatio : Double?,
   @JsonProperty("saturated_fat_ratio_points") var saturatedFatRatioPoints : Int?,
   @JsonProperty("saturated_fat_ratio_value") var saturatedFatRatioValue : Int?,
   @JsonProperty("saturated_fat_value") var saturatedFatValue : Double?,
   @JsonProperty("score") var score : Int?,
   @JsonProperty("sodium") var sodium : Int?,
   @JsonProperty("sodium_points") var sodiumPoints : Int?,
   @JsonProperty("sodium_value") var sodiumValue : Int?,
   @JsonProperty("sugars") var sugars : Double?,
   @JsonProperty("sugars_points") var sugarsPoints : Int?,
   @JsonProperty("sugars_value") var sugarsValue : Double?

)