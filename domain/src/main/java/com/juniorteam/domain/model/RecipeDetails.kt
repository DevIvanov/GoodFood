package com.juniorteam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeDetails(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val sourceUrl: String?,
    val glutenFree: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val product: List<ProductForDetails>?,
    val winePairing: WinePairing?
) : Parcelable {
    @Parcelize
    data class ProductForDetails(
        val id: Int,
        val aisle: String,
        val image: String,
        val name: String,
        val measures: Measures
    ) : Parcelable {
        @Parcelize
        data class Measures(
            val amount: Float,
            val unitShort: String
        ) : Parcelable
    }

    @Parcelize
    data class WinePairing(
        val pairedWines: List<String>,
        val pairingText: String,
        val productMatches: WineMatches
    ) : Parcelable {
        @Parcelize
        data class WineMatches(
            val id: Int,
            val title: String,
            val imageUrl: String
        ) : Parcelable
    }
}