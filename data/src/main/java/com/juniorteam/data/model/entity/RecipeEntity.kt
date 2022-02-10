package com.juniorteam.data.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeEntity(
    val id : Int,
    val title : String,
    val image : String,
    val imageType : String
): Parcelable