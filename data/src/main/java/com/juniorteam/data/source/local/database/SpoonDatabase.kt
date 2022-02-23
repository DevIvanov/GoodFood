package com.juniorteam.data.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juniorteam.data.constants.DatabaseConstants.DATABASE_NAME
import com.juniorteam.data.model.entity.IngredientEntity
import com.juniorteam.data.model.entity.ProductEntity
import com.juniorteam.data.model.entity.RecipeEntity
import com.juniorteam.data.source.local.dao.IngredientDao
import com.juniorteam.data.source.local.dao.ProductDao
import com.juniorteam.data.source.local.dao.RecipeDao


@Database(
    entities = [
        RecipeEntity::class,
        IngredientEntity::class,
        ProductEntity::class
               ], version = 1, exportSchema = false)
abstract class SpoonDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        var INSTANCE: SpoonDatabase? = null

        fun getDatabase(context: Context): SpoonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    SpoonDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}