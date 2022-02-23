package com.juniorteam.data.mapper

import com.juniorteam.data.model.entity.RecipeEntity
import com.juniorteam.domain.mapper.ModelMapper
import com.juniorteam.domain.model.Recipe
import javax.inject.Inject

class RecipeModelMapperImpl @Inject constructor() : ModelMapper<RecipeEntity, Recipe> {
    override fun fromEntity(from: RecipeEntity): Recipe =
        Recipe(from.id, from.title, from.image, from.imageType)

    override fun toEntity(from: Recipe): RecipeEntity =
        RecipeEntity(from.id, from.title, from.image, from.imageType)
}