package com.juniorteam.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.juniorteam.data.constants.DatabaseConstants.INGREDIENTS_TABLE
import kotlinx.parcelize.Parcelize

@Entity(tableName = INGREDIENTS_TABLE, indices = [Index(value = ["id"], unique = true)])
@Parcelize
data class IngredientEntity(
    @PrimaryKey
    val id : Int,
    val name : String,
    val image : String,
): Parcelable