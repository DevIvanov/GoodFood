package com.juniorteam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDetails (
    val id : Int,
    val title : String,
    val breadcrumbs : List<String>,
    val imageType : String,
    val badges : List<String>,
    val importantBadges : List<String>,
    val ingredientCount : Int,
    val generatedText : String,
    val ingredientList : String,
    val ingredients : List<Ingredients>,
    val likes : Int,
    val aisle : String,
    val nutrition : Nutrition,
    val price : Int,
    val servings : Servings,
    val spoonacularScore : Int
): Parcelable {

    @Parcelize
    data class Ingredients (
        val description : String,
        val name : String,
        val safety_level : String
    ): Parcelable

    @Parcelize
    data class Nutrition (
        val nutrients : List<Nutrients>,
        val caloricBreakdown : CaloricBreakdown
    ): Parcelable {

        @Parcelize
        data class Nutrients (
            val name : String,
            val amount : Int,
            val unit : String,
            val percentOfDailyNeeds : Double
        ): Parcelable

        @Parcelize
        data class CaloricBreakdown (
            val percentProtein : Double,
            val percentFat : Int,
            val percentCarbs : Double
        ): Parcelable
    }

    @Parcelize
    data class Servings (
        val number : Int,
        val size : Int,
        val unit : String
    ): Parcelable
}