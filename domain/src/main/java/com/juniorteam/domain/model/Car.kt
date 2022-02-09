package com.juniorteam.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Car(
    val id: Int,
    val year : Int,
    val horsepower: Int,
    val make : String?,
    val model : String?,
    val price: String?,
    val imageUrl: String?
): Parcelable