package com.mr.fodmapscanner.model.api.model
import android.os.Parcelable

import com.fasterxml.jackson.annotation.JsonProperty


import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (

    @JsonProperty("id") var id : String = "",
    @JsonProperty("product_name") var productName : String = "",
    @JsonProperty("image_front_small_url") var imageFrontSmallUrl : String? = "",
    @JsonProperty("image_front_thumb_url") var imageFrontThumbUrl : String? = "",
    @JsonProperty("image_front_url") var imageFrontUrl : String? = "",
    @JsonProperty("image_ingredients_small_url") var imageIngredientsSmallUrl : String? = "",
    @JsonProperty("image_ingredients_thumb_url") var imageIngredientsThumbUrl : String? = "",
    @JsonProperty("image_ingredients_url") var imageIngredientsUrl : String? = "",
    @JsonProperty("image_nutrition_small_url") var imageNutritionSmallUrl : String? = "",
    @JsonProperty("image_nutrition_thumb_url") var imageNutritionThumbUrl : String? = "",
    @JsonProperty("image_nutrition_url") var imageNutritionUrl : String? = "",
    @JsonProperty("image_small_url") var imageSmallUrl : String = "",
    @JsonProperty("image_thumb_url") var imageThumbUrl : String = "",
    @JsonProperty("image_url") var imageUrl : String? = "",
    @JsonProperty("informers") var informers : List<String>? = arrayListOf(),
    @JsonProperty("informers_tags") var informersTags : List<String>? = arrayListOf(),
    @JsonProperty("ingredients") var ingredients : List<Ingredient> = arrayListOf(),
    @JsonProperty("ingredients_analysis_tags") var ingredientsAnalysisTags : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_debug") var ingredientsDebug : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_from_or_that_may_be_from_palm_oil_n") var ingredientsFromOrThatMayBeFromPalmOilN : Int? = null,
    @JsonProperty("ingredients_from_palm_oil_n") var ingredientsFromPalmOilN : Int? = null,
    @JsonProperty("ingredients_from_palm_oil_tags") var ingredientsFromPalmOilTags : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_hierarchy") var ingredientsHierarchy : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_ids_debug") var ingredientsIdsDebug : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_n") var ingredientsN : Int? = null,
    @JsonProperty("ingredients_n_tags") var ingredientsNTags : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_original_tags") var ingredientsOriginalTags : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_percent_analysis") var ingredientsPercentAnalysis : Int? = null,
    @JsonProperty("ingredients_tags") var ingredientsTags : List<String>? = arrayListOf(),
    @JsonProperty("ingredients_text") var ingredientsText : String? = "",
    @JsonProperty("ingredients_text_debug") var ingredientsTextDebug : String? = "",
    @JsonProperty("ingredients_text_en") var ingredientsTextEn : String? = "",

    ) : Parcelable