package com.juniorteam.data.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarEntity(
    val id: Int,
    val year : Int,
    val horsepower: Int,
    val make : String?,
    val model : String?,
    val price: String?,
    @SerializedName ("img_url")
    val imageUrl: String?
): Parcelable
