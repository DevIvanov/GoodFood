package com.juniorteam.data.model.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.juniorteam.data.constants.DatabaseConstants.RECIPES_TABLE
import kotlinx.parcelize.Parcelize

@Entity(tableName = RECIPES_TABLE, indices = [Index(value = ["id"], unique = true)])
@Parcelize
data class RecipeEntity (
    @PrimaryKey
    val id : Int,
    val title : String,
    val image : String,
    val imageType : String
): Parcelable