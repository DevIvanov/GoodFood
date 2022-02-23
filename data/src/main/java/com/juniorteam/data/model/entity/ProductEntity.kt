package com.juniorteam.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.juniorteam.data.constants.DatabaseConstants.PRODUCTS_TABLE
import kotlinx.parcelize.Parcelize

@Entity(tableName = PRODUCTS_TABLE, indices = [Index(value = ["id"], unique = true)])
@Parcelize
data class ProductEntity(
    @PrimaryKey
    val id : Int,
    val title : String,
    val image : String,
    val imageType : String
): Parcelable