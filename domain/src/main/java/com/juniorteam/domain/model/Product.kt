package com.juniorteam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id : Int,
    val title : String,
    val image : String,
    val imageType : String
): Parcelable