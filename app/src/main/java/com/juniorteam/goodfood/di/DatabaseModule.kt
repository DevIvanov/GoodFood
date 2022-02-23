package com.juniorteam.goodfood.di

import android.content.Context
import com.juniorteam.data.mapper.IngredientModelMapperImpl
import com.juniorteam.data.mapper.ProductModelMapperImpl
import com.juniorteam.data.mapper.RecipeModelMapperImpl
import com.juniorteam.data.repository.IngredientRepository
import com.juniorteam.data.repository.ProductRepository
import com.juniorteam.data.repository.RecipeRepository
import com.juniorteam.data.source.local.dao.IngredientDao
import com.juniorteam.data.source.local.dao.ProductDao
import com.juniorteam.data.source.local.dao.RecipeDao
import com.juniorteam.data.source.local.database.SpoonDatabase
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.model.Product
import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.repository.DatabaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun getRoomDbInstanceArticle(@ApplicationContext context: Context): SpoonDatabase =
       SpoonDatabase.getDatabase(context)


    // Recipe table
    @Singleton
    @Provides
    fun getRecipeDao(spoonDatabase: SpoonDatabase): RecipeDao =
        spoonDatabase.recipeDao()

    @Singleton
    @Provides
    fun getRecipeRepository(roomDatabase: SpoonDatabase,
                             mapperImpl: RecipeModelMapperImpl
    ): DatabaseRepository<Recipe> {
        return RecipeRepository(roomDatabase.recipeDao(), mapperImpl)
    }


    // Ingredient table
    @Provides
    fun getIngredientDao(roomDatabase: SpoonDatabase): IngredientDao =
        roomDatabase.ingredientDao()

    @Provides
    fun getIngredientRepository(roomDatabase: SpoonDatabase,
                              mapperImpl: IngredientModelMapperImpl
    ): DatabaseRepository<Ingredient> =
        IngredientRepository(roomDatabase.ingredientDao(), mapperImpl)


    // Product table
    @Provides
    fun getProductDao(roomDatabase: SpoonDatabase): ProductDao =
        roomDatabase.productDao()


    @Provides
    fun getProductRepository(roomDatabase: SpoonDatabase,
                             mapperImpl: ProductModelMapperImpl
    ): DatabaseRepository<Product> =
        ProductRepository(roomDatabase.productDao(), mapperImpl)

}