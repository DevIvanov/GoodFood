package com.juniorteam.data.mapper

import com.juniorteam.data.model.entity.ProductEntity
import com.juniorteam.domain.mapper.ModelMapper
import com.juniorteam.domain.model.Product
import javax.inject.Inject

class ProductModelMapperImpl @Inject constructor() : ModelMapper<ProductEntity, Product> {
    override fun fromEntity(from: ProductEntity): Product =
        Product(from.id, from.title, from.image, from.imageType)

    override fun toEntity(from: Product): ProductEntity =
        ProductEntity(from.id, from.title, from.image, from.imageType)
}