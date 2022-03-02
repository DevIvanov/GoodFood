package com.juniorteam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientDetails(
    val id : Int,
    val original : String,
    val originalName : String,
    val name : String,
    val nameClean : String,
    val amount : Int,
    val unit : String,
    val unitShort : String,
    val unitLong : String,
    val possibleUnits : List<String>,
    val estimatedCost : EstimatedCost,
    val consistency : String,
    val shoppingListUnits : List<String>,
    val aisle : String,
    val image : String,
    val meta : List<String>,
    val nutrition : Nutrition,
    val categoryPath : List<String>
): Parcelable {

    @Parcelize
    data class EstimatedCost (
        val value : Int,
        val unit : String
    ): Parcelable

    @Parcelize
    data class Nutrition (
        val nutrients : List<Nutrients>,
        val properties : List<Properties>,
        val caloricBreakdown : CaloricBreakdown,
        val weightPerServing : WeightPerServing
    ): Parcelable{

        @Parcelize
        data class Nutrients (
            val name : String,
            val amount : Double,
            val unit : String,
            val percentOfDailyNeeds : Double
        ): Parcelable

        @Parcelize
        data class Properties (
            val name : String,
            val amount : Double,
            val unit : String
        ): Parcelable

        @Parcelize
        data class CaloricBreakdown (
            val percentProtein : Double,
            val percentFat : Double,
            val percentCarbs : Double
        ): Parcelable

        @Parcelize
        data class WeightPerServing (
            val amount : Int,
            val unit : String
        ): Parcelable
    }
}
