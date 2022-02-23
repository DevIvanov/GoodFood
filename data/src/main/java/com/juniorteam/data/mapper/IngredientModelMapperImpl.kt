package com.juniorteam.data.mapper

import com.juniorteam.data.model.entity.IngredientEntity
import com.juniorteam.domain.mapper.ModelMapper
import com.juniorteam.domain.model.Ingredient
import javax.inject.Inject

class IngredientModelMapperImpl @Inject constructor() : ModelMapper<IngredientEntity, Ingredient> {
    override fun fromEntity(from: IngredientEntity): Ingredient =
        Ingredient(from.id, from.name, from.image)

    override fun toEntity(from: Ingredient): IngredientEntity =
        IngredientEntity(from.id, from.name, from.image)
}